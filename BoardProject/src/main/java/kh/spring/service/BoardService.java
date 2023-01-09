package kh.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Service
public class BoardService {

		
		//boardDAO INSER FILEDAO INSERT
		//서비스레이어가 적업의 원자성을 조치하기 적합하다
		
		@Autowired
		private BoardDAO dao;
		
		
		
		
		public List<BoardDTO> selectByRange(int start, int end) throws Exception{ // 한페이지에 출력
			Map<String, Object> param= new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			return dao.selectByRange(param);
			   
		}
		
		public int getRecordCount() throws Exception{ //게시글 갯수반환
			
			return dao.getRecordCount();  //한줄 뽑겠다
			
			}
		
		public String getPageNavi(int currentPage) throws Exception {
			
			return dao.getPageNavi(currentPage);
		}
		
		
	
		}



