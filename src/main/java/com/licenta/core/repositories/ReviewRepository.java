package com.licenta.core.repositories;

import com.licenta.core.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getReviewsByCategory(String category);

    List<Review> getReviewsByCategoryOrderByCreationDateDesc(String category);

    List<Review> getReviewsByOwnerId(Long id);

    List<Review> getReviewsByOwnerId_EmailAddress(String email);

    Optional<Review> findByOwnerId_EmailAddressAndRecipeId_Id(String email, Long recipeId);
    Optional<Review> findByOwnerId_IdAndRestaurantId_Id(String email, Long restaurantId);
}
