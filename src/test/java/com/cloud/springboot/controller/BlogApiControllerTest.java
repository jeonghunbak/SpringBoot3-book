package com.cloud.springboot.controller;

import com.cloud.springboot.domain.Article;
import com.cloud.springboot.dto.AddArticleRequest;
import com.cloud.springboot.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("add Article")
    @Test
    public void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "Spring boot title";
        final String content = "Cloud";
        final AddArticleRequest request = new AddArticleRequest(title, content);

        //Serialize JSON
        final String requestBody = objectMapper.writeValueAsString(request);

        //given
        //요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);// 크기가 1인지
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("find All Articles")
    @Test
    public void findAllArticles() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }


    @DisplayName("view Article!")
    @Test
    public void findArticle () throws Exception {
        final String url = "/api/articles/{id}";
        final String title = "view Title";
        final String content = "view Content";

        Article article = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

       final ResultActions resultActions = mockMvc.perform(get(url, article.getId()));

       resultActions
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value(title))
               .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("DELETE ARTICLE")
    @Test
    public void deleteArticle () throws Exception {
        final String url = "/api/articles/{id}";
        final String title = "delete title";
        final String content = "delete content";

        Article article = blogRepository.save(Article
                .builder()
                .title(title)
                .content(content)
                .build());

        ResultActions resultActions = mockMvc.perform(get(url, article.getId()))
                .andExpect(status().isOk());

        List<Article> list = blogRepository.findAll();

        assertThat(list).isEmpty();
    }

}