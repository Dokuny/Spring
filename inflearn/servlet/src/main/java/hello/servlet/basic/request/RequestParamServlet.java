package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator().forEachRemaining(paramName->{
            System.out.println("파라미터 키 = " + paramName);
            System.out.println("파라미터 값 = " + request.getParameter(paramName));
        });
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println(username); // 만약 동일한 키로 값이 들어오면 먼저 등록된 값만 나온다.
        System.out.println(age);

        System.out.println("[이름이 같은 복수 파라미터 조회]"); // 동일한 키로 여러 값이 들어올 때 밑의 메소드를 사용
        String[] usernames = request.getParameterValues("username");
        for (String s : usernames) {
            System.out.println(s);
        }
    }
}
