package inflearnSpring.springCore.order;

import inflearnSpring.springCore.discount.DiscountPolicy;
import inflearnSpring.springCore.discount.FixDiscountPolicy;
import inflearnSpring.springCore.member.Member;
import inflearnSpring.springCore.member.MemberRepository;
import inflearnSpring.springCore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
