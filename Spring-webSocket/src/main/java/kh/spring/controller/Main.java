package kh.spring.controller;

import com.google.common.collect.EvictingQueue;

public class Main {

	public static void main(String[] args) {
		EvictingQueue<String> queue = EvictingQueue.create(3);
		queue.add("Apple");
		queue.add("Orange");
		queue.add("mango");
		
		System.out.println(queue);
		queue.add("Grape");
		System.out.println(queue);
	}
}
