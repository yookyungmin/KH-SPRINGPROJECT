package kh.spring.tools;

import com.google.gson.Gson;

import kh.spring.interfaces.TV;

public class SamsungTV implements TV{ 
	//TV인터페이스를 구현 접근제한자 public사용해야함 접근 제한자를 인터페이스보다 좁게 지정 못하기 떄문에 

	private Gson gson;
	
	
	public SamsungTV(Gson gson) {
		super();
		this.gson = gson;
	}
	
	public void checkGson() {
		System.out.println(gson);
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
		
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		
	}
	
	public SamsungTV() {}
	

	
	
}
