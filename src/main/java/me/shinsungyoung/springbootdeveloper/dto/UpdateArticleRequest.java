package me.shinsungyoung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본생성자
@Getter
@AllArgsConstructor
public class UpdateArticleRequest {

    private String title;
    private String content;
}
