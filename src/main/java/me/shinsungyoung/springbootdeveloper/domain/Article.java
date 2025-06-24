package me.shinsungyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // 엔티티로 지정
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id // id 필드를 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // 'title'이라는 notnull 컬럼과 맵핑
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될떄 수정시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder // 빌더패턴으로 객체 생성
    public Article(String title, String content){
        this.title   = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
