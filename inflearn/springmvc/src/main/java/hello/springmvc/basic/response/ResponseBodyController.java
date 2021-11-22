package hello.springmvc.basic.response;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")  // 응답의 가장 단순한 방식
    public  void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }


    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }


    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {

        HelloData helloData = new HelloData();

        helloData.setAge(20);
        helloData.setUsername("도쿤");

        return new ResponseEntity<>(helloData,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK) // 스테이터스 추가 해줌
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {

        HelloData helloData = new HelloData();

        helloData.setAge(20);
        helloData.setUsername("도쿤");

        return helloData;
    }

}
