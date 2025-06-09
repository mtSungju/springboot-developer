package me.shinsungyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsungyoung.springbootdeveloper.domain.Article;
import me.shinsungyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsungyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsungyoung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final 이나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;
    
    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){

        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
       return blogRepository.findAll();
    }

    public Article findById(long id){

        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found :" + id));

        //orElseThrow는 Optional의 인자가 null일 경우 예외처리를 시킨다.
        //JPA에서 findById가 Optional을 반환해서 위처럼 사용하기도 한다.
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
