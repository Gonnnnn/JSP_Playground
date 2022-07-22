package inflearnSpring.springCore.componentscan;

import inflearnSpring.springCore.AutoAppConfig;
import inflearnSpring.springCore.member.MemberRepository;
import inflearnSpring.springCore.member.MemberService;
import inflearnSpring.springCore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void BasicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);

//        FixDiscountPolicy에도 @Component를 달아주고
//        OrderServiceImpl에 생성자를 달아주면 오류난다. DiscountPolicy로 bean 2개가 조회되기 때문
//        이 때는 파라미터명을 rate 혹은 fix를 붙여 명시해주면 해결된다.
    }
}
