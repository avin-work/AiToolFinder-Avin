package com.aiToolFinder.aiToolFinder.controller;

import com.aiToolFinder.aiToolFinder.entity.Review;
import com.aiToolFinder.aiToolFinder.service.ReviewService;
import com.aiToolFinder.aiToolFinder.service.ToolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*")
public class ReviewController {

    private final ReviewService reviewService;
    private final ToolService toolService;

    public ReviewController(ReviewService reviewService, ToolService toolService) {
        this.reviewService = reviewService;
        this.toolService = toolService;
    }

    // USER: Submit review
    @PostMapping("user/review")
    public Review postReview(@RequestBody Review review){
        toolService.updateRating(review.getToolId());
        return reviewService.postReview(review);
    }

    @GetMapping("user/allReviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

}
