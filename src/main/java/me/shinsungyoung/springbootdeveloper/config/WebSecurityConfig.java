package me.shinsungyoung.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.shinsungyoung.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));

    }

    // 특정 http 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth // 인증,인가 설정
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/singup"),
                                new AntPathRequestMatcher("/user")
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin // 폼 기반 로그인  설정
                        .loginPage("/login")
                        .defaultSuccessUrl("/articles")
                ).logout(logout->logout // 로그아웃 설정
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                ).csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception{
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userService); // 사용자 정보 서비스 설정
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return new ProviderManager(daoAuthenticationProvider);
//    }

    // 위의 코드를 authenticationConfiguration 사용함으로써 간결하게 바꿈 (UserDetailsService, PasswordEncoder, AuthenticationProvider 등 필요한 구성 요소들을 Spring이 알아서 연결해준다)
    // AuthenticationManager는 AuthenticationConfiguration을 통해 가져오기 (추천)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
