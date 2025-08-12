package me.shinsungyoung.springbootdeveloper.bak;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // MockMvc 생성 및 자동구성
class TestControllerTest_2 {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach // 테스트 전에 한번씩 실행
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach // 테스트 종료 될때 한번씩 실행
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("아티클 조회에 성공한다")
    @Test
    public void getAllMembers() throws Exception{
        final String url = "/test";
        Member saveMember = memberRepository.save(new Member(1L, "최성주"));

        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON)); // 클라이언트가 서버에게 json형태의 응답을 원한다

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(saveMember.getId()))
                .andExpect(jsonPath("$[0].name").value(saveMember.getName()));

    }


}