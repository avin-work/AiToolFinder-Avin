package com.aiToolFinder.aiToolFinder.service;

import com.aiToolFinder.aiToolFinder.entity.Review;
import com.aiToolFinder.aiToolFinder.entity.Tool;
import com.aiToolFinder.aiToolFinder.repository.ReviewRepository;
import com.aiToolFinder.aiToolFinder.repository.ToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ToolRepository toolRepository;
    private final ToolService toolService;

    public ReviewService(ReviewRepository reviewRepository,
                         ToolRepository toolRepository,
                         ToolService toolService) {
        this.reviewRepository = reviewRepository;
        this.toolRepository = toolRepository;
        this.toolService = toolService;
    }

    // USER: Submit review
    public Review postReview(Review review){
        Review r = reviewRepository.save(review);
        reviewRepository.flush();
        toolService.updateRating(review.getToolId());
        return r;
    }

    // ADMIN: Approve review
    public void approveReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus("APPROVED");
        reviewRepository.save(review);

        updateToolRating(review.getToolId());
    }

    // Calculate average rating
    private void updateToolRating(Long toolId) {

        List<Review> reviews = reviewRepository.findAll();

        double avgRating = reviews.stream()
                .filter(r -> r.getToolId().equals(toolId))
                .filter(r -> r.getStatus().equals("APPROVED"))
                .mapToInt(Review::getUserRating)
                .average()
                .orElse(0.0);

        Tool tool = toolRepository.findById(toolId)
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        tool.setRating(avgRating);
        toolRepository.save(tool);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll().stream().toList();
    }
}
