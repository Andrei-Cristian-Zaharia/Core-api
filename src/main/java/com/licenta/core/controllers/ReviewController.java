package com.licenta.core.controllers;

import com.licenta.core.models.RetrieveReviewDTO;
import com.licenta.core.models.Review;
import com.licenta.core.models.createRequestDTO.CreateReviewDTO;
import com.licenta.core.models.editDTO.EditReviewDTO;
import com.licenta.core.models.responseDTO.ReviewResponseDTO;
import com.licenta.core.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<Review> create(@RequestBody CreateReviewDTO createReviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(createReviewDTO));
    }

    @GetMapping("/all/user")
    public @ResponseBody ResponseEntity<List<ReviewResponseDTO>> getAllForUser(@RequestParam String email){
        return ResponseEntity.ok().body(reviewService.getAllForUser(email));
    }

    @GetMapping("/check/entity/existence")
    public @ResponseBody ResponseEntity<Boolean> checkReviewEntityExistence(@RequestParam String email,
                                                                      @RequestParam Long entityId,
                                                                      @RequestParam String category) {
        return ResponseEntity.ok().body(reviewService.checkEntityReviewExistence(email, entityId, category));
    }

    @GetMapping("/countUserReviews")
    public @ResponseBody ResponseEntity<Integer> getReviewsOfUser(@RequestParam String email) {
        return ResponseEntity.ok(reviewService.countUsersReview(email));
    }

    @GetMapping("/averageRecipeRate")
    public @ResponseBody ResponseEntity<Float> averageRecipeRate(@RequestParam Long recipeId) {

        return ResponseEntity.ok(reviewService.averageRecipeRating(recipeId));
    }

    @GetMapping("/averageRateUser")
    public @ResponseBody ResponseEntity<Float> averageRateUser(@RequestParam String email) {

        return ResponseEntity.ok(reviewService.averageRateForUser(email));
    }

    @PostMapping("/all/entity")
    public @ResponseBody ResponseEntity<List<ReviewResponseDTO>> getAllForEntity(@RequestBody RetrieveReviewDTO retrieveReviewDTO){
        return ResponseEntity.ok().body(reviewService.getAllReviewsForEntity(retrieveReviewDTO));
    }

    @PostMapping("/rating")
    public @ResponseBody ResponseEntity<Integer> getRatingForEntity(@RequestBody RetrieveReviewDTO retrieveReviewDTO){
        return ResponseEntity.ok().body(reviewService.getRatingForEntity(retrieveReviewDTO));
    }

    @PostMapping("/edit")
    public @ResponseBody ResponseEntity<ReviewResponseDTO> editReview(@RequestBody EditReviewDTO editReviewDTO) {
        return ResponseEntity.ok().body(reviewService.editReview(editReviewDTO));
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseEntity<String> delete(@RequestHeader String email, @RequestParam Long id) {
        reviewService.deleteReview(email, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }
}
