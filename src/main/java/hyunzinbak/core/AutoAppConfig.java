package hyunzinbak.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /*
        * basePackages 를 지정하지 않으면
        * package 를 기준으로 하위 패키지를 모두 탐색한다.
        * */
        basePackages = "hyunzinbak.core",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
