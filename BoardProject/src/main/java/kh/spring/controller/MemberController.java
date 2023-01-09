package kh.spring.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.commons.CommonMethod;
import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	@Autowired
	private MemberService service;
	
//	@Autowired
//	private CommonMethod common;
//	
	@Autowired
	private HttpSession session;
	
	//request, response는 autowired못하고 session은 autowired 가능 autowired는 톰캣실행될떄 인스턴스생성되기때문
	
	@RequestMapping(value="login", method=RequestMethod.POST)  //post전송만받겠다
	public String login(String id, String pw, HttpSession session) throws Exception{
		
		System.out.println(id+":"+pw);
		
		String pwd = CommonMethod.getSha256(pw);
		boolean result = service.isLoginOk(id, pwd);
		if(result) {
			session.setAttribute("loginID", id);
		}
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout() throws Exception {
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	
	
	@RequestMapping("/toSignup")  //회원가입 이동
	public String toSignup() {
	
		return "member/signup";
	}
	
	
	
	
	@ResponseBody  //redirect, forward 둘다 아니고 값그대로를 리턴
	@RequestMapping(value="/idcheck", produces="text/html;charset=utf8") //한글도 return
	public String idCheck(String id) throws Exception {//중복id체크
		System.out.println(id);
		boolean result;
	
			result = service.idCheck(id);
			if(result) {
				System.out.println(result);
				return "true";
				
			}else {
				System.out.println(result);
				return "false";
			} //2번쨰 방법
	
			
		//리다이렉트, 포워드 둘다 안된다 
		//return String.valueOf(result); //1번쨰 방법
		//return "한글 결과"; //f12관리자 모드에서 ?? 로나온다
		//return "true";
	}
	
	@RequestMapping(value="signup", method=RequestMethod.POST)   //회원가입
	public String signup(MemberDTO dto, MultipartFile file) throws Exception {
			System.out.println("넘어온값"+dto);
			System.out.println(file);
		
			
			String realPath=session.getServletContext().getRealPath("/resources/profile");
			File filePath=new File(realPath);
			if(!filePath.exists()) {
				filePath.mkdir(); //파일업로드 폴더가 없다면 생성
			}
			
			if(file.getSize()!=0) {//파일 사이즈가0이 아니라면 
				String oriName=file.getOriginalFilename();
				//겹치지 않게 이름을 만들어야함
				String sysName=UUID.randomUUID()+"_"+oriName;
				file.transferTo(new File(filePath+"/"+sysName));
				System.out.println("파일있을떄");
				
				dto.setProfile_img(sysName);
			}
			
			int result = service.insertSign(dto);
		
			
			
		
		return "redirect:/";
	}
	
	@RequestMapping("/mypage")   //마이페이지 이동
	public String mypage(Model model) throws Exception{
	
			String id= (String)session.getAttribute("loginID");
			
				MemberDTO dto = service.selectMypage(id);
				model.addAttribute("list", dto);
				
				System.out.println("테스트 성공"+dto.getName()+dto.getId());
		return "member/mypage";
	}
	
//	@RequestMapping(value="update", method=RequestMethod.POST)   //업데이트
//	public String mypage(MemberDTO dto) throws Exception{
//		System.out.println(dto.getPhone()+dto.getPw());
//			int result = dao.update(dto);
//			System.out.println(result);
//		return "redirect:mypage";
//	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)   //업데이트
	public String mypage(MemberDTO dto, MultipartFile file) throws Exception{
		System.out.println(dto.getPhone()+dto.getPw());
			
		System.out.println(file);
			
			String realPath=session.getServletContext().getRealPath("/resources/profile");
			File filePath=new File(realPath);
			if(!filePath.exists()) {
				filePath.mkdir(); //파일업로드 폴더가 없다면 생성
			}
			
			if(file.getSize()!=0) {//파일 사이즈가0이 아니라면 
				String oriName=file.getOriginalFilename();
				//겹치지 않게 이름을 만들어야함
				String sysName=UUID.randomUUID()+"_"+oriName;
				file.transferTo(new File(filePath+"/"+sysName));
				System.out.println("파일있을떄");
				dto.setProfile_img(sysName);
			}
			int result = service.myUpdate(dto);
			
			
		return "redirect:mypage";
	}
//	
	
	@RequestMapping("memberOut")
	public String memberOut() {
		String id= (String)session.getAttribute("loginID");
		
		service.deleteMember(id);
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("error")
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
