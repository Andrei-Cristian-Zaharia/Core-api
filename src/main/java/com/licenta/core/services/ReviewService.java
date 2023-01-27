package com.licenta.core.services;

import com.licenta.core.enums.ReviewCategory;
import com.licenta.core.exceptionHandlers.reviewExceptions.ReviewDeleteForbidden;
import com.licenta.core.models.*;
import com.licenta.core.models.createRequestDTO.CreateReviewDTO;
import com.licenta.core.models.responseDTO.PersonResponseDTO;
import com.licenta.core.models.responseDTO.ReviewResponseDTO;
import com.licenta.core.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService {

    private final RecipeRestTemplateService recipeRestTemplateService;
    private final RestaurantRestTemplateService restaurantRestTemplateService;
    private final ModelMapper modelMapper;
    private final PersonService personService;
    private final ReviewRepository reviewRepository;

    public ReviewService(RecipeRestTemplateService recipeRestTemplateService,
                         RestaurantRestTemplateService restaurantRestTemplateService,
                         ModelMapper modelMapper, PersonService personService, ReviewRepository reviewRepository) {
        this.recipeRestTemplateService = recipeRestTemplateService;
        this.restaurantRestTemplateService = restaurantRestTemplateService;
        this.modelMapper = modelMapper;
        this.personService = personService;
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewResponseDTO> getAllReviewsForEntity(RetrieveReviewDTO retrieveReviewDTO) {

        return reviewRepository.getReviewsByCategory(retrieveReviewDTO.getCategory()).stream()
                .filter((Review r) -> {
                    if (retrieveReviewDTO.getCategory().equals(ReviewCategory.RECIPE.name())) {
                        return Objects.equals(r.getRecipeId().getId(), retrieveReviewDTO.getId());
                    } else {
                        return Objects.equals(r.getRestaurantId().getId(), retrieveReviewDTO.getId());
                    }
                }).map((Review r) -> {
                    ReviewResponseDTO result = modelMapper.map(r, ReviewResponseDTO.class);
                    result.setPerson(modelMapper.map(r.getOwnerId(), PersonResponseDTO.class));

                    return result;
                }).toList();
    }

    public Integer getRatingForEntity(RetrieveReviewDTO retrieveReviewDTO) {
        List<Integer> ratings = reviewRepository.getReviewsByCategory(retrieveReviewDTO.getCategory()).stream()
                .filter((Review r) -> {
                    if (retrieveReviewDTO.getCategory().equals(ReviewCategory.RECIPE.name())) {
                        return Objects.equals(r.getRecipeId().getId(), retrieveReviewDTO.getId());
                    } else {
                        return Objects.equals(r.getRestaurantId().getId(), retrieveReviewDTO.getId());
                    }
                })
                .map(Review::getRating).toList();

        if (ratings.isEmpty()) return 0;

        return ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
    }

    @Transactional
    public Review createReview(CreateReviewDTO createReviewDTO) {

        Review newReview = new Review();

        Person owner = personService.getPersonById(createReviewDTO.getOwnerId());

        if (ReviewCategory.RECIPE.name().equals(createReviewDTO.getCategory())) {
            Recipe recipe = recipeRestTemplateService.getRecipeById(createReviewDTO.getRecipeId());

            newReview.setCategory(ReviewCategory.RECIPE.name());
            newReview.setRecipeId(recipe);
        } else {
            Restaurant restaurant = restaurantRestTemplateService.getRestaurantById(createReviewDTO.getRestaurantId());

            newReview.setCategory(ReviewCategory.RESTAURANT.name());
            newReview.setRestaurantId(restaurant);
        }

        newReview.setRating(createReviewDTO.getRating());
        newReview.setText(createReviewDTO.getText());
        newReview.setTitle(createReviewDTO.getTitle());
        newReview.setCreationDate(LocalDate.now());
        newReview.setOwnerId(owner);

        return reviewRepository.save(newReview);
    }

    @Transactional
    public void deleteReview(String email, Long id) {

        Optional<Review> review = reviewRepository.findById(id);

        review.ifPresent((Review r) -> {

            Person person = personService.getPersonByEmailAddressString(email);

            if (!Objects.equals(person.getId(), review.get().getOwnerId().getId())) {
                throw new ReviewDeleteForbidden();
            }
        });

        reviewRepository.deleteById(id);
    }
}
