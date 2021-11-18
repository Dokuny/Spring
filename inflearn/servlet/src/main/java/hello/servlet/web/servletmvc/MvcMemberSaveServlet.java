package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberSaveServlet",urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));   // 폼값 받기

        Member member = new Member(username,age);
        memberRepository.save(member);   // 로직 실행
        System.out.println(member.getAge());
        // 모델에 데이터를 보관한다.
        request.setAttribute("member",member);   // 데이터 저장

        request.getRequestDispatcher("/WEB-INF/views/save-result.jsp").forward(request,response); // 데이터 전달
    }
}
