package hyunzinbak.core.singleton;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hyunzinbak.core.AppConfig;
import hyunzinbak.core.member.MemberRepository;
import hyunzinbak.core.member.MemberServiceImpl;
import hyunzinbak.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참고하고 있다.
        System.out.printf("memberService -> memberRepository = %s%n", memberService.getMemberRepository());
        System.out.printf("orderService -> memberRepository = %s%n", orderService.getMemberRepository());
        System.out.printf("memberRepository = %s%n", memberRepository);

        // 모두 같은 인스턴스를 참고하고 있다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.printf("bean = %s%n", bean.getClass());
        /*
        출력: bean = class hyunzinbak.core.AppConfig$$SpringCGLIB$$0
        스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
        AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
        그 다른 클래스를 스프링 빈으로 등록한 것이다!

        @Configuration 삭제하면 아래와 같이 출력 되고 singleton 을 보장 해 주지 않는다.
        즉 @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
        출력: bean = class hyunzinbak.core.AppConfig


        * */
    }
}
