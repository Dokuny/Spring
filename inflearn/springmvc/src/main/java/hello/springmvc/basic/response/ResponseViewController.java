package hello.springmvc.basic.response;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return modelAndView;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hi");
        return "/response/hello";
    }

    @RequestMapping("/response/hello")  // Void 반환이면 매핑이름으로 찾아본다. 이 방식 사용하지말자자
    public void responseViewV3(Model model) {
        model.addAttribute("data", "don't do it");

    }

    @RequestMapping("/response/v3")
    public String responseViewV4(Model model) {
        model.addAttribute("data", "don't do it");
        log.info("data first value = {}",model.getAttribute("data"));
        return "redirect:/response-view-v2"; // 리디렉션 쏘면 저장되어있던 모델이나 리퀘스트는 사라진다
    }




}
