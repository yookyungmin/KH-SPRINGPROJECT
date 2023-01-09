package kh.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
}) //스프링 컨테이너 실행될때 2개 xml 읽어들이기
public class MemberDAOTest {
	
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void insert() {
		dao.insertSign(new MemberDTO("ykm4608","1234","홍길동","ykm","1234","시그니엘", null, null, null, null), null);
	}
	
	
}
