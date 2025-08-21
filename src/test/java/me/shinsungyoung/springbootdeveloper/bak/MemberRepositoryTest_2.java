package me.shinsungyoung.springbootdeveloper.bak;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest_2 {

    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-member.sql")
    @Test
    public void getAllMembers(){
        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-member.sql")
    @Test
    public void getMemberById(){
        Member member = memberRepository.findById(2L).orElseThrow(()-> new EntityNotFoundException("해당 아이디의 회원을 찾을수없습니다."));

        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-member.sql")
    @Test
    public void getMemberByName(){
        Member member =  memberRepository.findByName("C").get();

        assertThat(member.getId()).isEqualTo(3);
    }

}