package kh.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
}) //스프링 컨테이너 실행될때 2개 xml 읽어들이기
@WebAppConfiguration //dao의 기능한개만 테스트할떈 필요없다
public class MemberControllerTest {
	
	private Logger logger = LoggerFactory.getLogger(MemberControllerTest.class); 
	//근원지 셋팅//logger를 사용해 기록을 남기면 여러상황에 대응 가능, sysout대신 사용
	
	@Autowired
	private WebApplicationContext wac; //스프링 컨테이너
	//테스크 코드 작성에 사용될 Spring container 인스턴스 di
	
	private MockMvc mockMvc;
	//Annotation 설정에 의해 만들어진 가상 Tomcat+spring 환경에 request/response 작업을 수행할수 있는 기능
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		logger.info("세팅 완료");
	}
	
//	@Test
//	public void MemberTest()  throws Exception{
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
//		.andDo(MockMvcResultHandlers.print())
//		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
//	}

	
//	@Test
//	public void insertTest() throws Exception {
//		this.mockMvc.perform(MockMvcRequestBuilders.post("/member/signup")
//				.param("id", "ykmykm46080")
//				.param("pw", "akvkvk123!")
//				.param("name", "유경민")
//				.param("phone", "01065848444")
//				.param("email", "ykmykm4608@naver.com")
//				.param("zipcode", "136")
//				.param("address1", "시그네일")
//				.param("address1", "시그네일 5층")
//				.param("signup_date", "2022")
//				.param("profile_img", "profile"))
//		
//				
//		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
//	}
	
	@Test
	public void myPageTest() throws Exception{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("loginID", "ykm123456789"); //세션이 필요한경우
		
		try {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/member/mypage").session(session))
		
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
			logger.info("테스트 성공");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("테스트 실패");
		}
		}

}
