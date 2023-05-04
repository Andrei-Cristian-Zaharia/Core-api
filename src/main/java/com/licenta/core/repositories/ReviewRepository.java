package com.licenta.core.repositories;

import com.licenta.core.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getReviewsByCategory(String category);

    List<Review> getReviewsByCategoryOrderByCreationDateDesc(String category);

    List<Review> getReviewsByOwnerId(Long id);

    List<Review> getReviewsByOwnerId_EmailAddress(String email);

    Integer countByOwnerId_EmailAddress(String email);

    @Query(value = "SELECT AVG(rating) as avgRate FROM reviews r WHERE r.id_recipe = ?1", nativeQuery = true)
    Float getAverageRecipeRating(Long id);

    @Query(value = "SELECT AVG(rating) as avgRate " +
            "FROM review r " +
            "INNER JOIN recipe re USING (id_recipe) " +
            "INNER JOIN person p ON (p.id_person = re.id_person) " +
            "WHERE p.email_address = ?1", nativeQuery = true)
    Float getRecipesAverageForUser(String email);

    Optional<Review> findByOwnerId_EmailAddressAndRecipeId_Id(String email, Long recipeId);
    Optional<Review> findByOwnerId_EmailAddressAndRestaurantId_Id(String email, Long restaurantId);
}
