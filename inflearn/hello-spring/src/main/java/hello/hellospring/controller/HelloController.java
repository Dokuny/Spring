package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("name","dokuny");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name,Model model){
        model.addAttribute("name",name);

        return "hello";

    }

    @GetMapping("hello-string")
    @ResponseBody // 위의 방식들처럼 템플릿엔진이 처리해주는 것이 아닌 데이터 자체가 그대로 출력된다. .
    public String helloString(@RequestParam("name") String name){

        return "hello " + name;
    }

    @GetMapping("hello-api")  // 이거는 api 방식(JSON 방식,객체반환)
    @ResponseBody // -> 객체를 반환하면  JSON으로 변환 된다
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
