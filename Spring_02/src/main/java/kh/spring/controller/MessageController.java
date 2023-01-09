package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;



import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;
import kh.spring.services.MessageService;

@Controller
@RequestMapping("/message/")
public class MessageController {
//	@Autowired
//	private MessageDAO dao;	
	
	@Autowired
	private MessageService service;
	
	@RequestMapping("toInput")  
	public String toInput() {//페이지 이동 목적
		return "message/inputForm";
	}
	
	@RequestMapping("insert")
	public String param(MessageDTO dto) throws Exception{ //jsp의 name속성 이름과 변수 이름이 동일해야 한다
	
		int result = service.insert(dto);
		System.out.println("결과 : " +result);
		
		
		System.out.println("인자 동작확인"+dto.getWriter()+":"+dto.getMessage());
		//return "redirect:home"; //404에러 ㅏ발생
		return "redirect:/"; 
		//forward로 하면 request정보를 들고 이동하기떄문에 redirect를 써야됨 
		//redirect 는 클라이언트의 주소창을 바꾸라는 얘기인데 jsp가 들어가면 web/inf 보안 설정에 의해 접속이 안됨
		//redirect를 썼다면 맵핑 목록주에 하나로 써야 함
		
		
		//redirect: 클라이언트가 서버에게 페이지 변경 명령 , 주소창에 입력하고 엔터와 같다 //404 에러 발생
		//redirect 가 붙으면 viewresolver는 동작을 안한다
		
		//forward :서버가 페이지 변경, request요청정보를 가지고 이동

	}
	
	@RequestMapping("delete")
	public String param(int seq) throws Exception {
		System.out.println("넘어온값"+seq);
		
	
			int result = service.delete(seq);
			System.out.println("결과"+result);
		
		return "redirect:/message/getList";
	}
	
	@RequestMapping("selectByCon")
	public String selectByCon(String condition, String keyword) throws Exception{
		
			System.out.println(condition+keyword);
		List<MessageDTO> list = service.selectByCon(condition, keyword);
		
		for(MessageDTO dto : list) {
			System.out.println(dto.getSeq()+dto.getWriter()+dto.getMessage());
		}
		return "redirect:/";
	}
	@RequestMapping("selectByMulticon")
	public String selectByMulticon(String writer, String message) throws Exception{
		List<MessageDTO> list = service.selectByMultiCon(writer, message);
		
		for(MessageDTO dto : list) {
			System.out.println(dto.getSeq()+dto.getWriter()+dto.getMessage());
		}
		
		return "redirect:/";
	}
	@RequestMapping("getList")
	public String selectAll(Model model) throws Exception{ //값넣어서 포워드 하려면 foward
		
		List<MessageDTO> list= service.selectAll();
		model.addAttribute("list", list);
	
		return "message/list";
	}
	
	@RequestMapping("selectBySeq")
	public String SelectBySeq(int seq) throws Exception{
		MessageDTO dto = service.selectBySeq(seq);
		
		System.out.println(dto.getSeq()+dto.getWriter()+dto.getMessage());
		
		return "redirect:/";
	}
	
//	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
//	@ExceptionHandler(Null.class) //Excetpion 나눌떄
//	public String exceptionHandler(Exception e) {
//		e.printStackTrace();
//		return "error";
//	}
}
