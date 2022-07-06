package inflearnSpring.springCore.beanfind;

import inflearnSpring.springCore.AppConfig;
import inflearnSpring.springCore.discount.DiscountPolicy;
import inflearnSpring.springCore.discount.FixDiscountPolicy;
import inflearnSpring.springCore.discount.RateDiscountPolicy;
import inflearnSpring.springCore.member.MemberRepository;
import inflearnSpring.springCore.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameTypeTest {
    @Configuration
    static class TempAppConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(TempAppConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류")
    void findBeanByTypeDuplicate() {
//        NoUniqueBeanDefinitionException
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> h.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상이면, 빈 이름을 지정하자")
    void findBeanByName() {
        MemberRepository bean = h.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = h.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

}
