package kh.spring.interfaces;

public interface TV {
		//IOC 를 위한 인터페이스 사용
		//추상 메서드의 집합
		public abstract void on(); //추상메서드 , public abstract 생략된 상태
		public abstract void off();
		public void volumeUp();
		public void volumeDown();
		
		
}
