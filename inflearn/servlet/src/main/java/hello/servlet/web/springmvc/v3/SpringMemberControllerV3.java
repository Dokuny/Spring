package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public String form(){
        return "new-form";
    }

    @RequestMapping
    public String list(Model model) {

        List<Member> members = memberRepository.findAll();
       model.addAttribute("members",members);
        return "members";

    }

    @RequestMapping("/save")
    public String save(Member member,Model model){
        memberRepository.save(member);
        model.addAttribute("member",member);
        return "save-result";
    }
}
