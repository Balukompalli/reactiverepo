package com.reactive.service;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotAndColdStreamTest {

	/*
	 * same response many time.. 
	 */
	@Test
	public void testColdStream() {
		
		var numbers= Flux.range(1, 10);
		numbers.subscribe(num -> System.out.println("subscriber-1: "+num));
		numbers.subscribe(num -> System.out.println("subscriber-2: "+num));
	}
	
	/*
	 * different responses 
	 */
	
	@Test
	public void testHotStream() throws InterruptedException {
		
		var numbers= Flux.range(1, 10)
				.delayElements(Duration.ofMillis(1000))
				.log()
				;
		ConnectableFlux<Integer> publisher = numbers.publish();
		publisher.connect();
		System.out.println("publish conntected: "+publisher.connect());
		publisher.subscribe(num -> System.out.println("subscriber-1: "+num));
		Thread.sleep(4000);
		publisher.subscribe(num -> System.out.println("subscriber-2: "+num));
		Thread.sleep(10000);
	}
}
