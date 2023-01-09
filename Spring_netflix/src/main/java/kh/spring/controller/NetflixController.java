package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.NetflixDAO;
import kh.spring.dto.NetflixDTO;

@Controller
@RequestMapping("/netflix/")
public class NetflixController {
	@Autowired
	private NetflixDAO dao;
	
	@RequestMapping("toInput")
	public String toInput() {
		return "netflix/newmovie";
	}
	
	@RequestMapping("insert")
	public String insert(NetflixDTO dto) {
		System.out.println("넘어온값 "+dto);
		try {
			int result= dao.insert(dto);
			System.out.println("결과"+result);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/";
	}
	@RequestMapping("getList")
	public String selectAll(Model model) {
		
		try {
			List<NetflixDTO> list = dao.selectAll();
			System.out.println(list);
			model.addAttribute("list",list);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "netflix/list";
	}
	
	@RequestMapping("delete") //삭제
	public String delete(int seq) {
			System.out.println(seq);
		try {
			
			int result = dao.delete(seq);
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "redirect:/";
	}
}
