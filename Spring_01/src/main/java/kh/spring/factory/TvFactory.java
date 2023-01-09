package kh.spring.factory;

import kh.spring.interfaces.TV;
import kh.spring.tools.LgTV;
import kh.spring.tools.SamsungTV;

public class TvFactory {
	public static TV getTv(String brand) { //객체 생성없이 사용
		if(brand.equals("samsung")) {  //samsung과 같아서 samsungTV(); 객체 생성
			return new SamsungTV();
		}else if(brand.equals("lg")) {
			return new LgTV();
		}
		return null; //잘못 넘어올 경우 null 리턴
	}
}
