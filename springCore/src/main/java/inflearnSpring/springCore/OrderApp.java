package inflearnSpring.springCore;

import inflearnSpring.springCore.member.Grade;
import inflearnSpring.springCore.member.Member;
import inflearnSpring.springCore.member.MemberService;
import inflearnSpring.springCore.order.Order;
import inflearnSpring.springCore.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = h.getBean("memberService", MemberService.class);
        OrderService orderService = h.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "Apple", 20000);
        System.out.println("order = " + order);
    }
}
