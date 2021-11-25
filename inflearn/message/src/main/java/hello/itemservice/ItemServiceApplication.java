package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

//	스프링 부트에서는 아래의 빈등록을 자동으로 해준다. 메시지 기능을 사용하기 위해선 MessageSource빈등록이 필수.
//	@Bean
//	public MessageSource messageSource(){
//		// 인터페이스인 MessageSource의 구현체인 ResourceBundleMessageSource
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//
//		// 이렇게 basename을 세팅하면 messages.properties,errors.properties를 읽어서 사용한다.
//		messageSource.setBasenames("messages","errors");
//		messageSource.setDefaultEncoding("utf-8");
//		return messageSource;
//	}
}
