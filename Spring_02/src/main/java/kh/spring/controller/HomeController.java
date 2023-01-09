package kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;


@Controller
public class HomeController {
	@Autowired
	private MessageDAO dao;
	


	//spring 의 철학중 한가지 :POJO
	@RequestMapping("/")
	public String home() {
		System.out.println("안녕");
		return "home";
	}
	
	
	

//	@RequestMapping("param")
//	public String param(HttpServletRequest request) { //요청 값이 있을때 작성해주면 된다
//		String writer = request.getParameter("writer");
//		String message = request.getParameter("message");
//		
//		System.out.println("인자 동작확인"+writer+":"+message);
//		return "home";
//	}
	
//	@RequestMapping("param")
//	public String param(String writer, String message) { //jsp의 name속성 이름과 변수 이름이 동일해야 한다
//		System.out.println("인자 동작확인"+writer+":"+message);
//		return "home";
//	}
//	
	////dispatcherservlet에서 기본생성자를 만들고 나서 setter메서드에 접근하기 떄문에 기본생성자 세터 메서드 필수
	//기본생성자가 없어도 매개변수가 있는 생성자가 있얻 셋팅가능
	
//	@RequestMapping("insert")
//	public String param(MessageDTO dto) { 
//		try {
//		int result = dao.insert(dto);
//		System.out.println("결과 : " +result);
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		
//		System.out.println("인자 동작확인"+dto.getWriter()+":"+dto.getMessage());
//		return "home";
//
//	}


}
