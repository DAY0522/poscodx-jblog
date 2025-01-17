package jblog.security;

import jblog.vo.UserVo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. Handler 종류 확인
        if(!(handler instanceof HandlerMethod)) {
            // DefaultServletRequestHandler 타입인 경우
            // DefaultServletHandler가 처리하는 경우(정적자원, /assets/**, mapping이 안되어 있는 URL)
            return true;
        }

        //2. casting
        HandlerMethod handlerMethod = (HandlerMethod)handler;

        //3. Handler에서 @Auth 가져오기
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        //4. Handler Method에서 @Auth가 없으면 클래스(타입)에서 가져오기
        if(auth == null) {
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
        }

        //5. @Auth가 없으면...
        if(auth == null) { // 인증이 필요한 함수가 아님.
            return true;
        }

        String role = auth.role();

        //6. @Auth가 붙어 있기때문에 인증(Authentication) 여부 확인
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo)session.getAttribute("authUser");

        if(authUser == null) { // 로그인이 되어있지 않은 경우
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        String name = authUser.getName();

        // 현재 들어간 블로그 id 추출
        String blogId = request.getRequestURI().split("/")[2];
        if ("ADMIN".equals(role) && !name.equals(blogId)) {
            response.sendRedirect(request.getContextPath() + "/" + blogId);
            return false;
        }

        return true;
    }

}
