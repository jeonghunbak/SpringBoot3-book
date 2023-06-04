package com.cloud.springboot.service;

import com.cloud.springboot.domain.Article;
import com.cloud.springboot.dto.AddArticleRequest;
import com.cloud.springboot.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save((request).toEntity());
    }
}
