package inflearnSpring.springCore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Configuration이 붙은 class도 자동으로 등록되어버리니 일단 배제 시켰다.
// 기존의 AppConfig 파일은 이 예시와는 별개의 것이니 제외!
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // @Bean으로 등록하는게 하나도 없다!!

}
