package com.cloud.springboot.service;

import com.cloud.springboot.domain.Article;
import com.cloud.springboot.dto.AddArticleRequest;
import com.cloud.springboot.dto.UpdateArticle;
import com.cloud.springboot.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save((request).toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Not Found : " + id));
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticle request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found" + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
