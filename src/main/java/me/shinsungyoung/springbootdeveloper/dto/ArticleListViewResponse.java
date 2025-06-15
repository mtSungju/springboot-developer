package me.shinsungyoung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.shinsungyoung.springbootdeveloper.domain.Article;

@Getter
@AllArgsConstructor
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article){
        this.id      = article.getId();
        this.title   = article.getTitle();
        this.content = article.getContent();
    }


}
