package me.shinsungyoung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsungyoung.springbootdeveloper.domain.Article;

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자
@Getter
public class AddArticleRequest {

    private String title;

    private String content;

    public Article toEntity(){ // 생성자를 사용해 객체생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
