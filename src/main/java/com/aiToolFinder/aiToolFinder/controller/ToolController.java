package com.aiToolFinder.aiToolFinder.controller;

import com.aiToolFinder.aiToolFinder.entity.Tool;
import com.aiToolFinder.aiToolFinder.service.ToolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    // USER: Get tools with filters
    @GetMapping
    public List<Tool> getTools(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String pricingType,
            @RequestParam(required = false) Double minRating
    ) {
        return toolService.getFilteredTools(category, pricingType, minRating);
    }

    @GetMapping("/filter")
    public List<Tool> getFilteredTools(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String pricingType,
            @RequestParam(required = false) Double minRating
    ) {
        return toolService.getFilteredTools(category, pricingType, minRating);
    }
}
