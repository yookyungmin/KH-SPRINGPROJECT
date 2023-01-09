package kh.spring.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.common.collect.EvictingQueue;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kh.spring.configurator.WSConfigurator;



@ServerEndpoint(value="/chat", configurator = WSConfigurator.class)
public class ChatEndpoint {


	//webSocket 이 처음 연결되었을떄 실행될 함수

	//접속한 사용자 Session 을 모아두는 컬렉션
	private  static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	//static이 필요한 이유, 사용자마다 ChatEndpin는 하나씩 생성 되는데 static을붙이면 한개 list에 사용자 정보들이 담긴다 /static은 한번 생성 되기 떄문에 객체 생성없이 사요악능
	//접속자들의 정보를 저장하기 위해 Session 지역변수로 사용하면 안되고 멤버필드로 사용해야 함 컬렉션 set 필요 중복허용 x

	private static EvictingQueue<JsonObject> lately = EvictingQueue.create(30); //최근30개 까지저장
	private HttpSession hSession;     // 접속자의 HeepSession 객체를 저장할 멤버필드

	private Gson g = new Gson();
	//접속자의HttpSession객체를 저장할메서드
	//접속하는 사람마다 세션을 따로따로 만들어줘야되기떄문에 Autowired사용x
	
	@OnOpen
	public void onConnection(Session client, EndpointConfig config) { //websocket session

		System.out.println("웹 소캣 연결 확인");
		clients.add(client);
		
		this.hSession = (HttpSession)config.getUserProperties().get("hSession");
		
		System.out.println(this.hSession.getAttribute("loginID")+"가 채팅방에 입장하였습니다");//핸드쉐이킹이 끝나고넘어옴
		
		String latelyMessages = g.toJson(lately);
		System.out.println(latelyMessages);
		try {
			client.getBasicRemote().sendText(latelyMessages);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//웹소캣 연결


	@OnMessage
	public void onMessage(String msg) { //throws Exception하면 에러가 생길시 다른 사람들이 메시지 확인을못한다 그래서 try catch
		
		System.out.println("도착한 메시지"+ msg);
		

		msg = msg.replace("<", "&lt;");
		
		JsonObject data = new JsonObject();
		data.addProperty("ip", (String)this.hSession.getAttribute("IP"));
		data.addProperty("sender", (String)this.hSession.getAttribute("loginID"));
		data.addProperty("msg", msg);
		lately.add(data);
		JsonArray arr = new JsonArray();
		arr.add(data);
		synchronized (clients) {
				//for문이 동작중 사용자가 종료했을때 에러나는 상황, 동시성 오류 방지

			for(Session client : clients) {//for문 돌다가 사용자가 종료해서 @Onclose에 의해 삭제되면 동시성 오류가  생긴다 ex)10개로 for문돌다가 9개로 바뀌면 for문 돌아가는 도중 갯수는 변경되면 안된다
				try {
					client.getBasicRemote().sendText(arr.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@OnClose //연결 종료 정보제거
	public void onClose(Session client) {
		clients.remove(client);
	}
	@OnError
	public void onError(Session client, Throwable t) { //에러가 난 사용자 제거
		clients.remove(client);
	}
}
