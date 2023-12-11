package com.reactive.service;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

	/*
	 * backpressure
	 */
	@Test
	public void testBackPressure() {
		var numbers = Flux.range(1, 100).log();
		//numbers.subscribe(num-> System.out.println("num: "+num));
		numbers.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				// TODO Auto-generated method stub
				//super.hookOnSubscribe(subscription);
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("value :"+value);
				if(value == 3)
					cancel();
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				// TODO Auto-generated method stub
			//	super.hookOnComplete();
				System.out.println("completed..");
			}
			
		});
	}

	/*
	 * backpressure Drop
	 */
	@Test
	public void testBackPressureDrop() {
		var numbers = Flux.range(1, 100).log();
		//numbers.subscribe(num-> System.out.println("num: "+num));
		numbers
		.onBackpressureDrop(t -> {
			System.out.println("dropped value are :: "+t);	
		})
		.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				// TODO Auto-generated method stub
				//super.hookOnSubscribe(subscription);
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("value :"+value);
				if(value == 3)
				hookOnCancel();
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				// TODO Auto-generated method stub
			//	super.hookOnComplete();
				System.out.println("completed..");
			}
			
		});
	}

	/*
	 * backpressure Buffer
	 */
	@Test
	public void testBackPressureBuffer() {
		var numbers = Flux.range(1, 100).log();
		//numbers.subscribe(num-> System.out.println("num: "+num));
		numbers
		.onBackpressureBuffer(10,  num-> System.out.println("buffered value:: "+num))
		.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				// TODO Auto-generated method stub
				//super.hookOnSubscribe(subscription);
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("value :"+value);
				if(value == 3)
				hookOnCancel();
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				super.hookOnError(throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				// TODO Auto-generated method stub
			//	super.hookOnComplete();
				System.out.println("completed..");
			}
			
		});
	}
	
	
	/*
	 * backpressure error
	 */
	@Test
	public void testBackPressureError() {
		var numbers = Flux.range(1, 100).log();
		//numbers.subscribe(num-> System.out.println("num: "+num));
		numbers
		.onBackpressureError()
		.subscribe(new BaseSubscriber<Integer>() {

			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				// TODO Auto-generated method stub
				//super.hookOnSubscribe(subscription);
				request(3);
			}

			@Override
			protected void hookOnNext(Integer value) {
				// TODO Auto-generated method stub
				System.out.println("value :"+value);
				if(value == 3)
				hookOnCancel();
			}

			@Override
			protected void hookOnError(Throwable throwable) {
				// TODO Auto-generated method stub
				//super.hookOnError(throwable);
				System.out.println("throwable:: "+throwable);
			}

			@Override
			protected void hookOnCancel() {
				// TODO Auto-generated method stub
				super.hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				// TODO Auto-generated method stub
			//	super.hookOnComplete();
				System.out.println("completed..");
			}
			
		});
	}
}
