package inflearnSpring.springCore.autowired;

import inflearnSpring.springCore.AutoAppConfig;
import inflearnSpring.springCore.discount.DiscountPolicy;
import inflearnSpring.springCore.member.Grade;
import inflearnSpring.springCore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class AllBeanTest {

    @Test
    void findAllBean() {
        // config class 두개 넣으면 둘 다 훑는다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        // DiscountService의 map, list 필드에 여러개의 빈이 잘 들어간다!

        DiscountService ds = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "gon", Grade.VIP);
        int priceFixDiscount = ds.discount(member, 20000, "fixDiscountPolicy");
        int priceRateDiscount = ds.discount(member, 20000, "rateDiscountPolicy");
        System.out.println("priceFixDiscount = " + priceFixDiscount);
        System.out.println("priceRateDiscount = " + priceRateDiscount);
        assertThat(priceFixDiscount).isEqualTo(1000);
        assertThat(priceRateDiscount).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String Policy) {
            DiscountPolicy discountPolicy = policyMap.get(Policy);
            return discountPolicy.discount(member, price);
        }
    }
}