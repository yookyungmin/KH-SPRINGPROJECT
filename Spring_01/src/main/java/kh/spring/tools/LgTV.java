package kh.spring.tools;

import org.springframework.context.support.GenericXmlApplicationContext;

import kh.spring.interfaces.Speaker;
import kh.spring.interfaces.TV;

public class LgTV implements TV {

	//private Speaker speaker = new GenericXmlApplicationContext().getBean(Speaker.class);
	//가능하지만 스프링컨테이너를또 new할필요 없다
	//그래서 constructor injection 사용
	
	private Speaker speaker;
	private  int price;
	
	
	
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LgTV(Speaker speaker, int price) {
		super();
		this.speaker = speaker;
		this.price = price;
	}
	public LgTV() {
		System.out.println("LG Tv 생성!");
	}
	@Override
	public void on() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void off() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		
	}

	
	
}
