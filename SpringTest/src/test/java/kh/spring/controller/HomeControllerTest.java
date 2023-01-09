package kh.spring.controller;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebAppConfiguration //dao의 기능한개만 테스트할떈 필요없ㄷ
public class HomeControllerTest {

	private Logger logger = LoggerFactory.getLogger(HomeController.class); 
	

	@Autowired
	private WebApplicationContext wac; //스프링 컨테이너
	//테스크 코드 작성에 사용될 Spring container 인스턴스 di
	
	
	private MockMvc mockMvc;
	//Annotation 설정에 의해 만들어진 가상 Tomcat+spring 환경에 request/response 작업을 수행할수 있는 기능
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
//	@Test
//	public void Hometest() throws Exception {
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
//		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//	}

	//	@Test
//	public void insertTest() throws Exception {
//		this.mockMvc.perform(MockMvcRequestBuilders.post("/insert")
//				.param("writer", "Tom").param("message", "Junit practice"))
//		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//	}
	
	@Test
	public void insertTest() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/insert")
				.param("writer", "Tom").param("message", "Junit practice"))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void selectTest() {}


}
