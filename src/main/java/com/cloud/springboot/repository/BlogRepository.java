package com.cloud.springboot.repository;

import com.cloud.springboot.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
