package kh.spring.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginValidator implements HandlerInterceptor {
	//로그인 했는지 안했는지 검사
	
	@Autowired
	private HttpSession session;
	 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("인터셉터 동작확인");
//		return true;
		String loginID=	(String)session.getAttribute("loginID");
	
		if(loginID!=null) return true; //로그인 한상탬
		
		response.sendRedirect("error");
		return false;
	}
	
	
	
}
