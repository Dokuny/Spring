package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("프로토 1");
        PrototypeBean protoBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("프로토 2");
        PrototypeBean protoBean2 = ac.getBean(PrototypeBean.class);

        assertThat(protoBean1).isNotSameAs(protoBean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
