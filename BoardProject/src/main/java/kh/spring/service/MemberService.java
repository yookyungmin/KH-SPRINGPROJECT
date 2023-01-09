package kh.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.commons.CommonMethod;
import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Service
public class MemberService {

		@Autowired
		private MemberDAO dao;
		

	
	public int insertSign(MemberDTO dto) {
		
		dto.setPw(CommonMethod.getSha256(dto.getPw()));
		return dao.insertSign(dto);

		
	}
	
	public boolean isLoginOk(String id, String pw){
		System.out.println(id+pw);
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		
		System.out.println(param.get("id"));
		System.out.println(param.get("pw"));
		return dao.isLoginOk(param);
	}
	
	
	public boolean idCheck(String id) {
		return dao.idCheck(id);
	}
	
	
	public MemberDTO selectMypage(String id) throws Exception {
		 	
		 return dao.selectMypage(id);
				 
	 }
	
	public int deleteMember(String id ) {
		return dao.deleteMember(id);
	}
	
	public int myUpdate(MemberDTO dto) {
		dto.setPw(CommonMethod.getSha256(dto.getPw()));
		return dao.myPageUpdate(dto);
	}
}
