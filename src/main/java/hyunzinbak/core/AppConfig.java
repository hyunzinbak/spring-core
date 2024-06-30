package hyunzinbak.core;

import hyunzinbak.core.discount.DiscountPolicy;
import hyunzinbak.core.discount.RateDiscountPolicy;
import hyunzinbak.core.member.MemberRepository;
import hyunzinbak.core.member.MemberService;
import hyunzinbak.core.member.MemberServiceImpl;
import hyunzinbak.core.member.MemoryMemberRepository;
import hyunzinbak.core.order.OrderService;
import hyunzinbak.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
