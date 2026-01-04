package com.aiToolFinder.aiToolFinder.repository;

import com.aiToolFinder.aiToolFinder.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
