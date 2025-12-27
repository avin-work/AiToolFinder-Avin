package com.aiToolFinder.aiToolFinder.controller;

import com.aiToolFinder.aiToolFinder.entity.Tool;
import com.aiToolFinder.aiToolFinder.service.ReviewService;
import com.aiToolFinder.aiToolFinder.service.ToolService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ToolService toolService;
    private final ReviewService reviewService;

    public AdminController(ToolService toolService, ReviewService reviewService) {
        this.toolService = toolService;
        this.reviewService = reviewService;
    }

    // Add new AI Tool
    @PostMapping("/tools")
    public Tool addTool(@RequestBody Tool tool) {
        return toolService.addTool(tool);
    }

    // Update AI Tool
    @PutMapping("/tools/{id}")
    public Tool updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        return toolService.updateTool(id, tool);
    }

    // Delete AI Tool
    @DeleteMapping("/tools/{id}")
    public String deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return "Tool deleted successfully";
    }

    // Approve Review
    @PutMapping("/reviews/{id}/approve")
    public String approveReview(@PathVariable Long id) {
        reviewService.approveReview(id);
        return "Review approved successfully";
    }

    // Reject Review
    @PutMapping("/reviews/{id}/reject")
    public String rejectReview(@PathVariable Long id) {
        reviewService.rejectReview(id);
        return "Review rejected successfully";
    }
}
