package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("login")
	public String login() {
		session.setAttribute("loginID", "JACK");
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("error")
	public String error() {
		return "error";
	}
}
