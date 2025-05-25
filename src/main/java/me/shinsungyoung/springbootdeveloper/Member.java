package me.shinsungyoung.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자
@Getter
@Entity // 엔티티로 지정 : Member클래스와 실제 데이터베이스의 테이블을 맵핑
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable= false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public void changeName(String name){
        this.name = name;
    }

}
