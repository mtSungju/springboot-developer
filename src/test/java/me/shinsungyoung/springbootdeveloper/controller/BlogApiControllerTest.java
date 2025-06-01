package me.shinsungyoung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.shinsungyoung.springbootdeveloper.domain.Article;
import me.shinsungyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsungyoung.springbootdeveloper.repository.BlogRepository;
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
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화 , 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach // 테스트 실행전 실행하는 메소드
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }


    @Test
    @DisplayName("addArticle : 블로그 글 추가 성공!!")
    public void addArticle() throws Exception{

        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest request = new AddArticleRequest(title, content);

        // 객체 => Json 으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(request);

        //when
        //설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url) // MockMvc를 사용하여 HTTP 요청을 실행합니다.
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());


        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1); // 크기가 1인지 검증
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @DisplayName("findAllArticles : 블로그 글 목록 조회에 성공한다.")
    public void findAllArticles() throws  Exception{

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
                .accept(MediaType.APPLICATION_JSON)); // GET 요청에 대한 Accept 헤더를 설정

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @Test
    @DisplayName("findArticle : 블로그 글 조회에 성공한다")
    public void findArticle() throws Exception{
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @Test
    @DisplayName("delete Article : 블로그 글 삭제에 성공한다")
    public void deleteArticle() throws  Exception{
        //given : 블로그 글을 저장한다.
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article saveArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when : 저장한 블로그 글의 id값으로 삭제 API 호출
        ResultActions resultActions = mockMvc.perform(delete(url, saveArticle.getId()))
                .andExpect(status().isOk());


        //then : 응답코드가 200이고 블로그 글 리스트를 전체 조회해 조회한 배열크기가 0인지 확인한다
        List<Article> articles = blogRepository.findAll();

        assertThat(articles).isEmpty();
    }


}