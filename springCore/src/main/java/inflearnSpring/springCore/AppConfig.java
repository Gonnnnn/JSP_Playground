package inflearnSpring.springCore;

import inflearnSpring.springCore.discount.DiscountPolicy;
import inflearnSpring.springCore.discount.FixDiscountPolicy;
import inflearnSpring.springCore.discount.RateDiscountPolicy;
import inflearnSpring.springCore.member.MemberRepository;
import inflearnSpring.springCore.member.MemberService;
import inflearnSpring.springCore.member.MemberServiceImpl;
import inflearnSpring.springCore.member.MemoryMemberRepository;
import inflearnSpring.springCore.order.OrderService;
import inflearnSpring.springCore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
//        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
//        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
//        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }
}
