package inflearnSpring.springCore.discount;

import inflearnSpring.springCore.member.Member;

public interface DiscountPolicy {
    /**
     * @return
     */
    int discount(Member member, int price);
}
