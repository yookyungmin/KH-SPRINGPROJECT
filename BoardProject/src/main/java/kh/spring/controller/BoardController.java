package kh.spring.controller;


import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.CommentsDAO;
import kh.spring.dao.FilesDAO;
import kh.spring.dao.MemberDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentsDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private CommentsDAO cdao;
	@Autowired
	private HttpSession session;
	@Autowired
	private FilesDAO fdao;
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private BoardService bservice;

	@RequestMapping("list")
	public String list(@RequestParam(defaultValue = "1") int cpage, Model model) throws Exception {
		// int cpage = Integer.parseInt(request.getParameter("cpage"));
		String navi = bservice.getPageNavi(cpage);
		List<BoardDTO> list = bservice.selectByRange(cpage * 10 - 9, cpage * 10);

		model.addAttribute("navi", navi);
		model.addAttribute("list", list);
		return "board/boardlist";
	}

//	@RequestMapping(value = "writer", method = RequestMethod.POST)
//	public String writer(String title, String contents, MultipartFile[] files) throws Exception {
//		System.out.println(title + contents);
//
//		//		int maxSize =1024*1024*10; // 10MB
//		//		String savePath = request.getServletPath().getRealPath("files");
//		//		System.out.println(savePath);
//		String id = (String) session.getAttribute("loginID");
//		//		System.out.println(files[0].getOriginalFilename());
//		//		System.out.println(files[1].getOriginalFilename());
//		int nextVal = dao.getNextval();
//		dao.insertwrite(new BoardDTO(nextVal, id, title, contents, null, 0));
//
//		/// 보드테이블
//
//		// 파일 작업
//		String realPath = session.getServletContext().getRealPath("upload");
//		File filePath = new File(realPath);
//		if (!filePath.exists()) {
//			filePath.mkdir(); // 파일업로드 폴더가 없다면 생성
//		}
//
//
//		if (files != null) { //null값이면 리턴
//			if (!files[0].getOriginalFilename().equals("")) { // 0인덱스에 파일이 없지않다면 아래 실행
//				for (MultipartFile file : files) {
//					if (file.getOriginalFilename().equals("")) {
//						continue;
//					} // null값이면 건너듸기
//					String oriName = file.getOriginalFilename();
//					// 겹치지 않게 이름을 만들어야함
//					String sysName = UUID.randomUUID() + "_" + oriName;
//					file.transferTo(new File(filePath + "/" + sysName));//
//					fdao.insertFile(new FilesDTO(0, oriName, sysName, nextVal));
//				}
//			}
//		}
//
//		return "redirect:list";
//	}

//	@RequestMapping("detail")
//	public String detail(int seq, String writer, Model model) throws Exception {
//		System.out.println("seq" + seq);
//		dao.addViewCount(seq);
//		BoardDTO list = dao.selectBoard(seq);
//		List<CommentsDTO> list2 = cdao.commentsprint(seq);
//
//		List<FilesDTO> list3 = fdao.selectFileByParent(seq);
//	
//		System.out.println(writer);
//		
//		
//		String profile_img = mdao.getProfile(writer);
//		if(profile_img !=null) {
//			model.addAttribute("mList", profile_img);
//		}else {
//			model.addAttribute("mList", "no_profile.png");
//		}
//		
//		System.out.println(profile_img);
//		
//		//model.addAttribute("mList", profile_img);
//		model.addAttribute("filelist", list3);
//		model.addAttribute("list", list);
//		model.addAttribute("comments", list2);
//
//		return "board/detailboard";
//
//	}

//	@RequestMapping("deleteboard") // 삭제
//	public String deleteBoard(int seq) throws Exception {
//		System.out.println("삭제 seq" + seq);
//		dao.boardDelete(seq);
//		return "redirect:list";
//	}
//
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	public String updateboard(BoardDTO dto) throws Exception {
//		System.out.println("수정값" + dto.getSeq());
//		System.out.println(dto.getTitle() + dto.getContents());
//		dao.update(dto);
//
//		return "redirect:/board/detail?seq=" + dto.getSeq();
//	}

	//	@RequestMapping(value="update", method=RequestMethod.POST)
	//	public String updateboard(int seq, String title, String contents) throws Exception {
	//		System.out.println(seq+title+contents+"수정값들");
	//		dao.update(new BoardDTO(seq, title, contents));
	//		
	//		return "redirect:detail";
	//	}
//	@RequestMapping("toupwriter")
//	public String upWriter(int seq, Model model) throws Exception {
//
//		BoardDTO list = dao.selectBoard(seq);
//		model.addAttribute("list", list);
//
//		return "board/upwriterForm";
//	}

	@RequestMapping("towriter")
	public String towriter() throws Exception {

		return "board/writerForm";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
