package kh.spring.tools;

import kh.spring.interfaces.Speaker;

public class SonySpeaker implements Speaker{
	public void volumeUp() {
		System.out.println("sony: 볼륨을 올립니다");
	}
}
