package kh.spring.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;
//게시판 글쓰기 기능은
//Controller 관점에선 Service의글쓰기
//Service 관점에선 BoarDAO 의 insert+ FileDAO의 insert 기능 사용

@Service
public class MessageService {
	
	@Autowired
	private MessageDAO dao;
	//컨트롤러에서 dao를 선언할 이유가 없어짐
	
	@Transactional//dao에서는 안씀 기
	public int insert(MessageDTO dto) {
		
		
		dao.insert(dto); //게시판 글쓰기 예제
		dto.setWriter(null);//null로 되어있어서 에러 예정
		dao.insert(dto); //첨부파일 저장예쩨
		 //transactionl 적용되어서 실패가 되면 모든 작업이 실패됨
		//둘다 성공하거나 둘다 실패 되어야함 db랑 관련되거만 
		 return 0;
	}
	
	public int delete(int delete) {
		return dao.delete(delete);
	}
	
	public MessageDTO selectBySeq(int seq) {
		return dao.selectBySeq(seq);
	}

	public List<MessageDTO> selectByCon(String condition, String keyword){
		Map<String, String> param = new HashMap<>();
		param.put("condition", condition);
		param.put("keyword", keyword);
		return dao.selectByCon(param);
	}
	
	public List<MessageDTO> selectByMultiCon(String writer, String message){
		MessageDTO dto = new MessageDTO(0, writer, message);
		return dao.selectByMultiCon(dto);
	}
	
	public List<MessageDTO> selectAll(){
		return dao.selectAll();
	}
	
	
	
	
}
