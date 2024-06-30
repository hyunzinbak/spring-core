package hyunzinbak.core;

import hyunzinbak.core.discount.DiscountPolicy;
import hyunzinbak.core.discount.RateDiscountPolicy;
import hyunzinbak.core.member.MemberRepository;
import hyunzinbak.core.member.MemberService;
import hyunzinbak.core.member.MemberServiceImpl;
import hyunzinbak.core.member.MemoryMemberRepository;
import hyunzinbak.core.order.OrderService;
import hyunzinbak.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    public DiscountPolicy discountPolicy() {
// return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
