package com.reactive.service;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.reactive.ReactiveApplication;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxAndMonoService {
 
	public  Flux<String> fruitFlux() {
		return Flux.fromIterable(List.of("Mango","Orange","Banana")).log();
	}
	
	public  Flux<String> fruitFluxMap() {
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
			.map(t -> t.toUpperCase())
				.log();
	}
	
	public  Flux<String> fruitFluxMapFliter(int number) {
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
				.filter(s->s.length()>number)
			.map(t -> t.toUpperCase())
				.log();
	}
	

	public Flux<String> fruitFluxConcatWith() {
		var fruits = Flux.just("Mango","Orange");
		var veggies = Flux.just("Tomato","Lemon");
		return fruits.concatWith(veggies).log();
	}
	
	public Flux<String> fruitMonoConcatWith() {
		var fruits = Mono.just("Mango");
		var veggies = Mono.just("Tomato");
		return fruits.concatWith(veggies).log();
	}
	
	public  Flux<String> fruitFluxFlatmap() {
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
			.flatMap(s -> Flux.just(s.split("")))
				.log();
	}
	
	public  Flux<String> fruitFluxFlatmapAsync() {
		System.out.println("Async Flatmap starts::");
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
			.flatMap(s -> Flux.just(s.split(""))
			.delayElements(Duration.ofMillis(new Random().nextInt(1000))
					)
			)
				.log()
				;
	}
	
	public  Flux<String> fruitFluxConcatmapAsync() {
		System.out.println("Concatmap starts::");
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
			.concatMap(s -> Flux.just(s.split(""))
			.delayElements(Duration.ofMillis(new Random().nextInt(1000))
					)
			)
				.log()
				;
	}
	
	public Mono<String> fruitMono() {
		return Mono.just("Mango").log();
	}
	

	public Mono<List<String>> fruitMonoFlatMap() {
	System.out.println("Mono flatmap starts::");
		return Mono.just("Mango")
				.flatMap(s-> Mono.just(List.of(s.split(""))))
				.log();
	}
	
	public Flux<String> fruitMonoFlatMapMany() {
		System.out.println("Mono FlatMapMany starts::");
			return Mono.just("Mango")
					.flatMapMany(s-> Flux.just(s.split("")))
					.log();
		}
	
	public  Flux<String> fruitFluxTransform(int number) {
		System.out.println("transform :: ");
		Function<Flux<String>, Flux<String>> function =  data -> data.filter(s->s.length()>number);
		
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
			//	.filter(s->s.length()>number)
				.transform(function)
			//.map(t -> t.toUpperCase())
				.log();
	}
	
	public  Flux<String> fruitFluxTransformSwitchIfEmpty(int number) {
		System.out.println("fruitFluxTransformSwitchIfEmpty :: ");
		Function<Flux<String>, Flux<String>> function =  data -> data.filter(s->s.length()>number);
		
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
					.transform(function)
					.switchIfEmpty(Flux.just("Pineapple","Jack Fruit").transform(function) )
					.log();
	}
	
	public  Flux<String> fruitFluxTransformDefaultIfEmpty(int number) {
		System.out.println("Default if empty transform :: ");
		Function<Flux<String>, Flux<String>> function =  data -> data.filter(s->s.length()>number);
		
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
					.transform(function)
					.defaultIfEmpty("Default")
					.log();
	}
	
	public Flux<String> fruitFluxConcat() {
		var fruits = Flux.just("Mango","Orange");
		var veggies = Flux.just("Tomato","Lemon");
		return Flux.concat(fruits, veggies).log();
	}
	
	public Flux<String> fruitFluxMerge() {
		var fruits = Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
		var veggies = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
		return Flux.merge(fruits, veggies).log();
	}
	
	public Flux<String> fruitFluxMergeWith() {
		var fruits = Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
		var veggies = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
		return fruits.mergeWith(veggies).log();
	}
	
	public Flux<String> fruitFluxMergeWithSequential() {
		var fruits = Flux.just("Mango","Orange").delayElements(Duration.ofMillis(50));
		var veggies = Flux.just("Tomato","Lemon").delayElements(Duration.ofMillis(75));
		return Flux.mergeSequential(fruits,veggies).log();
	}
	
	public Flux<String> fruitFluxZip() {
		var fruits = Flux.just("Mango","Orange");
		var veggies = Flux.just("Tomato","Lemon");
		return Flux.zip(fruits, veggies,(fruit,veg)->fruit+veg).log();
	}

	public Flux<String> fruitFluxZipWith() {
		var fruits = Flux.just("Mango","Orange");
		var veggies = Flux.just("Tomato","Lemon");
		return fruits.zipWith(veggies,(fruit,veg)->fruit+veg).log();
	}
	
	public Flux<String> fruitFluxZipWithTuple() {
		var fruits = Flux.just("Mango","Orange");
		var veggies = Flux.just("Tomato","Lemon");
		var moreVeggies = Flux.just("Potato","Beans");
		return Flux.zip(fruits,veggies,moreVeggies)
				.map(tuples -> tuples.getT1()+tuples.getT2()+tuples.getT3()).log();
				
	}
	
	public Mono<String> fruitMonoZipWith() {
		var fruits = Mono.just("Mango");
		var veggies = Mono.just("Tomato");
		return fruits.zipWith(veggies,(fruit,veg)->fruit+veg).log();
	}
	
	public  Flux<String> fruitFluxFliterDoOn(int number) {
		return Flux.fromIterable(List.of("Mango","Orange","Banana"))
				.filter(s->s.length()>number)
				.doOnNext(s-> { System.out.println("s :: "+s);})
				.doOnSubscribe(Subscription ->{System.out.println("subscription:: "+Subscription.toString());})
				.doOnComplete(()-> System.out.println("completed:: "))
				.log();
	}
		
	public Flux<String> fruitsFluxOnErrorReturn(){
		return Flux.just("Apple","Mango")
				.concatWith(Flux.error(new RuntimeException("Exception occurred")))
				.onErrorReturn("Orange")
				.log()
				;
	}
	
	public Flux<String> fruitsFluxOnErrorContinue(){
		return Flux.just("Apple","Mango","Orange")
				.map(s->{
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occurred");
					return s.toUpperCase();
				})
				.onErrorContinue((e,fruit)->{
					System.out.println("e: "+e);
					System.out.println("fruit: "+fruit);
				})
				.log()
				;
	}
	

	public Flux<String> fruitsFluxOnErrorMap(){
		return Flux.just("Apple","Mango","Orange")
				.map(s->{
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occurred");
					return s.toUpperCase();
				})
				.onErrorMap(throwable->{
					System.out.println("throwable: "+throwable);
					return new IllegalStateException("From On ErrorMap::");
				})
				.log()
				;
	}
	
	public Flux<String> fruitsFluxOnError(){
		return Flux.just("Apple","Mango","Orange")
				.map(s->{
					if(s.equalsIgnoreCase("Mango"))
						throw new RuntimeException("Exception Occurred");
					return s.toUpperCase();
				})
				.doOnError(throwable->{
					System.out.println("throwable: "+throwable);
					
				})
				.log()
				;
	}
	
	public static void main(String[] args) {
		
		FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
		
		
		fluxAndMonoService.fruitFlux().subscribe(s->System.out.println("fruit are ::"+s));
		fluxAndMonoService.fruitFluxMap().subscribe(s->System.out.println("fruit are ::"+s));
		fluxAndMonoService.fruitFluxMapFliter(6).subscribe(s->System.out.println("Filtered fruit are ::"+s));
		fluxAndMonoService.fruitFluxFlatmap().subscribe(s->System.out.println("flatmap fruit are ::"+s));
		fluxAndMonoService.fruitFluxTransform(5).subscribe(s->System.out.println("transform fruit are ::"+s));
		fluxAndMonoService.fruitFluxTransformDefaultIfEmpty(10).subscribe(s->System.out.println("Default if emtpy transform fruit are ::"+s));
		
		fluxAndMonoService.fruitFluxTransformSwitchIfEmpty(8).subscribe(s->System.out.println("Switch if emtpy transform fruit are ::"+s));
		
		fluxAndMonoService.fruitFluxFlatmapAsync().subscribe(s->System.out.println("Aysnc flatmap fruit are ::"+s));
		fluxAndMonoService.fruitFluxConcatmapAsync().subscribe(s->System.out.println("Aysnc ConcatMap fruit are ::"+s));
		
		fluxAndMonoService.fruitMono().subscribe(t -> System.out.println("Mono -->"+t));
		fluxAndMonoService.fruitMonoFlatMap().subscribe(t -> System.out.println("Mono flatmap -->"+t));
		fluxAndMonoService.fruitMonoFlatMapMany().subscribe(t -> System.out.println("Mono flatmap many -->"+t));
		
		fluxAndMonoService.fruitFluxConcat().subscribe(s->System.out.println("concat :: "+s));
		fluxAndMonoService.fruitFluxConcatWith().subscribe(s->System.out.println("Flux concatWith :: "+s));
		fluxAndMonoService.fruitMonoConcatWith().subscribe(s->System.out.println("Mono concatWith :: "+s));
		fluxAndMonoService.fruitFluxMerge().subscribe(s->System.out.println("fruitFluxMerge :: "+s));
		fluxAndMonoService.fruitFluxMergeWith().subscribe(s->System.out.println("fruitFluxMergeWith :: "+s));
		fluxAndMonoService.fruitFluxMergeWithSequential().subscribe(s->System.out.println("fruitFluxMergeWithSequential :: "+s));
		fluxAndMonoService.fruitFluxZip().subscribe(s->System.out.println("fruitFluxZip :: "+s));
		fluxAndMonoService.fruitFluxZipWith().subscribe(s->System.out.println("fruitFluxZipWith :: "+s));
		
		fluxAndMonoService.fruitFluxZipWithTuple().subscribe(s->System.out.println("fruitFluxZipWithTuple :: "+s));
		fluxAndMonoService.fruitMonoZipWith().subscribe(s->System.out.println("fruitMonoZipWith:: "+s));
		fluxAndMonoService.fruitFluxFliterDoOn(5).subscribe(s->System.out.println("fruitFluxFliterDoOn:: "+s));

		fluxAndMonoService.fruitsFluxOnErrorReturn().subscribe(s->System.out.println("fruitsFluxOnErrorReturn:: "+s));
		fluxAndMonoService.fruitsFluxOnErrorContinue().subscribe(s->System.out.println("fruitsFluxOnErrorContinue:: "+s));		
		fluxAndMonoService.fruitsFluxOnErrorMap().subscribe(s->System.out.println("fruitsFluxOnErrorMap:: "+s));
		fluxAndMonoService.fruitsFluxOnError().subscribe(s->System.out.println("fruitsFluxOnError:: "+s));
		
	}

}
