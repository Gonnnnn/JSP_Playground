package inflearnSpring.springCore;

import inflearnSpring.springCore.AppConfig;
import inflearnSpring.springCore.member.Grade;
import inflearnSpring.springCore.member.Member;
import inflearnSpring.springCore.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = h.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
