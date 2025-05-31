package me.shinsungyoung.springbootdeveloper.repository;

import me.shinsungyoung.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
