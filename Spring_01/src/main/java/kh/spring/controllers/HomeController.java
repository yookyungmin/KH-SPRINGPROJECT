package kh.spring.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.factory.TvFactory;
import kh.spring.interfaces.TV;
import kh.spring.tools.LgTV;
import kh.spring.tools.SamsungTV;


public class HomeController {
	
	public static void main(String[] args) {
		
		
		//Spring FrameWork
		//IOC - DL / DI
		
		
//		SamsungTV tv = new SamsungTV();
//		
//		
//		tv.powerOn();
//		tv.powerOff();
//		tv.volumUp();
		
	//인터페이스 추상메서드는 구현한 클래스에서 메서드를 구현하지 않을때 에러가 발생 
		
//		LgTV tv = new LgTV();
//		
//		
//		tv.on();
//		tv.toneUp();
//		tv.off();
		
		//인터페이스 사용으로 인해 유지보수 간편해짐 , 인터페이스 다형성 
//		TV tv = new SamsungTV();
//		tv.on();
//		tv.volumeUp();
//		tv.off();
		
		//팩토리 패턴
//		TV tv = TvFactory.getTv("samsung");
//		tv.on();
//		tv.volumeUp();
//		tv.off();
		
//		//팩토리 패턴도 만족못하여 매개변수를 메인에서 가져온다
//		//메인 메서드는 코드 안에서 콜은 못하고 코드 밖에서 호출 가능
//		//ipconfig /all /
//		TV tv = TvFactory.getTv(args[0]); //메인 메서드 매개변수 배열의 0번 인덱스를 인자값으로 사용, 
		//클래스간의 관계가 많아져 복잡해져서 xml로 대체
//		tv.on();
//		tv.volumeUp();
//		tv.off();   //XML로 인해 안씀
		
		ApplicationContext ctx = new GenericXmlApplicationContext("context.xml"); 
		//스프링 컨테이너, 스프링과 관련된것들을 포함한 컨테이너 / Spring Container 생성
		//context.xml을 읽어서 context.xml안의 bean(인스턴스)들을 new한다
		
		
		//TV tv = (SamsungTV)ctx.getBean("tv"); // tv id를 가진 인스턴스를 주라
		//TV tv = ctx.getBean(TV.class); //위와 동일   타입을명시하지 않아서 유지수 편리 DL
		TV tv = ctx.getBean("tv",TV.class);
		//		Bean에 접근하기 위해 컨테이너가 제고하는 API를 이용하여 Bean을 Lookup하는것
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
		//bean은 기본으로 싱글톤이 적용되어 있어서 여러개 써도 하나만 나온다
//		tv.on();
//		tv.off(); 
		tv.volumeUp();
		TV tv1 = ctx.getBean("tv-check", TV.class);
		((SamsungTV)tv1).checkGson();
		
		
		
	}
	
}
