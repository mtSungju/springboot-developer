package me.shinsungyoung.springbootdeveloper;

import me.shinsungyoung.springbootdeveloper.bak.Member;
import me.shinsungyoung.springbootdeveloper.bak.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-member.sql")
    @Test
    @DisplayName("jpa test")
    public void getAllMembers(){
        //when 테스트 실행
        List<Member> members = memberRepository.findAll();
        
        //then 테스트결과 검증
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-member.sql")
    @Test
    public void getMemberById(){

        Member member = memberRepository.findById(2L).get();

        assertThat(member.getName()).isEqualTo("B");

    }

    @Sql("/insert-member.sql")
    @Test
    public void getMemberByName(){
        Member member = memberRepository.findByName("C").get();

        assertThat(member.getName()).isEqualTo("C");
    }

    @Test
    public void saveMember(){
        Member member = new Member(1L, "최성주");

        memberRepository.save(member);

        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("최성주");
    }

    @Test
    public void saveMembers(){
        List<Member>member = List.of(new Member(2L, "B"), new Member(3L, "C"));

        memberRepository.saveAll(member);

        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Sql("/insert-member.sql")
    public void deleteMemberById(){
        //when
        memberRepository.deleteById(2L);
        //then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    @Test
    @Sql("/insert-member.sql")
    public void deleteAll(){
        memberRepository.deleteAll();

        assertThat(memberRepository.findAll().size()).isZero();
    }

    @Test
    @Sql("/insert-member.sql")
    public void update(){
        Member member = memberRepository.findById(2L).get();

        member.changeName("choisSungJu");

        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("choisSungJu");
    }
}