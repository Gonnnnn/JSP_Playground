package inflearnSpring.springCore.beanfind;

import inflearnSpring.springCore.AppConfig;
import inflearnSpring.springCore.member.MemberService;
import inflearnSpring.springCore.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
//        assertThat은 assertj꺼
        MemberService memberService = h.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void findBeanByType() {
//        type명만으로도 getBean으로 조회 가능
        MemberService memberService = h.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 구체 타입으로 조회")
    void findBeanByType2() {
//        객체 자체의 타입으로도 조회 가능하긴 하다. 좋은건 아니겠지
        MemberService memberService = h.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
//        MemberService xxxx = h.getBean("xxxx", MemberService.class);
//        해당 예외가 터지면 성공!
//        참고로 얘는 Junit꺼
        assertThrows(NoSuchBeanDefinitionException.class, () -> h.getBean("xxxx", MemberService.class));
    }
}