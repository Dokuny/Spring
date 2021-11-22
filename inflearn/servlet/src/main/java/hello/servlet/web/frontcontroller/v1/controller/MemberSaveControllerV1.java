package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));   // 폼값 받기

        Member member = new Member(username, age);
        memberRepository.save(member);   // 로직 실행
        System.out.println(member.getAge());
        // 모델에 데이터를 보관한다.
        request.setAttribute("member", member);   // 데이터 저장

        request.getRequestDispatcher("/WEB-INF/views/save-result.jsp").forward(request, response); // 데이터 전달
    }
}
