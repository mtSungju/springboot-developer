package me.shinsungyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.shinsungyoung.springbootdeveloper.domain.Article;
import me.shinsungyoung.springbootdeveloper.dto.ArticleListViewResponse;
import me.shinsungyoung.springbootdeveloper.dto.ArticleResponse;
import me.shinsungyoung.springbootdeveloper.dto.ArticleViewResponse;
import me.shinsungyoung.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장
        
        return "articleList"; // articleList.html 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        System.out.println("id ==>" + id);
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleListViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){ // id 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        }else{ // 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }

}
