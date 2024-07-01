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
    /**
     * @Bean memberService --> new MemoryMemberRepository()
     * @Bean orderService --> new MemoryMemberRepository()
     * --> 결과적으로 각각 다른 2개의 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것 처럼 보인다.
     */

    /*
    expect
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.memberRepository
        call AppConfig.orderService
        call AppConfig.memberRepository

    result
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.orderService
    **/

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
