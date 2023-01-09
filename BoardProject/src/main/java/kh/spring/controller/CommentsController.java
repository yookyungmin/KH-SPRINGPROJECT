package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.CommentsDAO;
import kh.spring.dto.CommentsDTO;

@Controller
@RequestMapping("/comments/")
public class CommentsController {
	
	@Autowired
	private CommentsDAO cdao;
	@Autowired
	private BoardDAO dao;
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(CommentsDTO dto) throws Exception {
		System.out.println(dto.getWriter()+dto.getContents());
		System.out.println(dto.getParent_seq());
		
		cdao.insertcomment(dto);
		
		return "redirect:/board/detail?seq="+dto.getParent_seq();
	}
	
	
//	@RequestMapping(value="update", method=RequestMethod.POST)
	@PostMapping("update")
	public String update(CommentsDTO dto) throws Exception {
		System.out.println(dto.getContents()+ dto.getSeq()+dto.getParent_seq());

		cdao.update(dto);
		
		return "redirect:/board/detail?seq="+dto.getParent_seq();
	} //get으로 보내는데 post로 어떻게받고잇는거지?
	
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(int seq, int parent_seq) throws Exception {
		System.out.println("삭제"+seq+parent_seq);

		cdao.commentsDelete(seq);
		
		return "redirect:/board/detail?seq="+parent_seq;
	} 
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
