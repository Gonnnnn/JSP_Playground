package inflearnSpring.springCore;

import inflearnSpring.springCore.discount.DiscountPolicy;
import inflearnSpring.springCore.discount.RateDiscountPolicy;
import inflearnSpring.springCore.member.MemberRepository;
import inflearnSpring.springCore.member.MemberService;
import inflearnSpring.springCore.member.MemberServiceImpl;
import inflearnSpring.springCore.member.MemoryMemberRepository;
import inflearnSpring.springCore.order.OrderService;
import inflearnSpring.springCore.order.OrderServiceImpl;

public class AppConfig {

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private RateDiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
