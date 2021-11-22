package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());  @Slf4j를 쓰면 생략가능

    @RequestMapping("/log-test")
    public String logTest(){
        String name ="Spring";

        System.out.println("로그");
        // {}안에 뒤에 인자값들이 치환된다.
        log.trace("trace log={}",name);  // trace가 가장 낮은 레벨의 로그, 아래 순서대로 레벨이 높아진다.
        log.debug("trace log={}",name);
        log.info("info log = {}",name);
        log.warn("info log = {}",name);
        log.error("info log = {}",name); // 가장 높은 레벨의 로그

        // @Controller 를 사용하면 리턴 값은 viewName이지만 @RestController를 사용하면 반환하는 문자열이 그대로 페이지에 출력된다.
        return "ok";
    }
}
