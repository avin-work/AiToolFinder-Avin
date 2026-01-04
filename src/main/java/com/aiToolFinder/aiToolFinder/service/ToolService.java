package com.aiToolFinder.aiToolFinder.service;

import com.aiToolFinder.aiToolFinder.entity.Review;
import com.aiToolFinder.aiToolFinder.entity.Tool;
import com.aiToolFinder.aiToolFinder.repository.ReviewRepository;
import com.aiToolFinder.aiToolFinder.repository.ToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolService {

    private final ToolRepository toolRepository;
    private final ReviewRepository reviewRepository;

    public ToolService(ToolRepository toolRepository, ReviewRepository reviewRepository) {
        this.toolRepository = toolRepository;
        this.reviewRepository = reviewRepository;
    }

    // USER: Fetch & filter tools
    public List<Tool> getFilteredTools(String category, String pricingType, Double minRating) {

        return toolRepository.findAll().stream()
                .filter(t -> category == null || t.getCategory().equalsIgnoreCase(category))
                .filter(t -> pricingType == null || t.getPricingType().equalsIgnoreCase(pricingType))
                .filter(t -> minRating == null || t.getRating() >= minRating)
                .collect(Collectors.toList());
    }

    // ADMIN: Add new tool
    public Tool addTool(Tool tool) {
        return toolRepository.save(tool);
    }

    // ADMIN: Update existing tool
    public Tool updateTool(Long id, Tool updatedTool) {

        Tool existingTool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        if (updatedTool.getName() != null)
            existingTool.setName(updatedTool.getName());

        if (updatedTool.getUseCase() != null)
            existingTool.setUseCase(updatedTool.getUseCase());

        if (updatedTool.getCategory() != null)
            existingTool.setCategory(updatedTool.getCategory());

        if (updatedTool.getPricingType() != null)
            existingTool.setPricingType(updatedTool.getPricingType());

        return toolRepository.save(existingTool);
    }

    public void updateRating(Long toolId) {

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
}
