package com.reactive.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;
import reactor.tools.agent.ReactorDebugAgent;

class FluxAndMonoServiceTest {

	FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();  
	
	@Test
	void testFruitFlux() {
		var fruitFlux = fluxAndMonoService.fruitFlux();
		StepVerifier.create(fruitFlux)
		.expectNext("Mango","Orange","Banana")
		.verifyComplete();
	}

	@Test
	void testFruitFluxMap() {
		var fruitFluxMap = fluxAndMonoService.fruitFluxMap();
		StepVerifier.create(fruitFluxMap)
		.expectNext("MANGO","ORANGE","BANANA")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxMapFilter() {
		var fruitFluxMapFilter = fluxAndMonoService.fruitFluxMapFliter(5);
		StepVerifier.create(fruitFluxMapFilter)
		.expectNext("ORANGE","BANANA")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxTransform() {
		var fruitFluxTransform = fluxAndMonoService.fruitFluxTransform(5);
		StepVerifier.create(fruitFluxTransform)
		.expectNext("Orange","Banana")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxTransformDefaultIfEmpty() {
		var fruitFluxTransformDefault = fluxAndMonoService.fruitFluxTransformDefaultIfEmpty(12);
		StepVerifier.create(fruitFluxTransformDefault)
		.expectNext("Default")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxTransformSwitchIfEmpty() {
		var fruitFluxTransformSwitch = fluxAndMonoService.fruitFluxTransformSwitchIfEmpty(7);
		StepVerifier.create(fruitFluxTransformSwitch)
		.expectNext("Pineapple","Jack Fruit")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxConcat() {
		var fruitFlux = fluxAndMonoService.fruitFluxConcat();
		StepVerifier.create(fruitFlux)
		.expectNext("Mango","Orange","Tomato","Lemon")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxMerge() {
		var fruitFluxMerge = fluxAndMonoService.fruitFluxMerge();
		StepVerifier.create(fruitFluxMerge)
		.expectNext("Mango","Tomato","Orange","Lemon")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxMergeWith() {
		var fruitFluxMergeWith = fluxAndMonoService.fruitFluxMergeWith();
		StepVerifier.create(fruitFluxMergeWith)
		.expectNext("Mango","Tomato","Orange","Lemon")
		.verifyComplete();
	}
	
	
	@Test
	void testFruitFluxMergeSequential() {
		var fruitFluxMergeSequential = fluxAndMonoService.fruitFluxMergeWithSequential();
		StepVerifier.create(fruitFluxMergeSequential)
		.expectNext("Mango","Orange","Tomato","Lemon")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxZip() {
		var fruitFluxZip = fluxAndMonoService.fruitFluxZip();
		StepVerifier.create(fruitFluxZip)
		.expectNext("MangoTomato","OrangeLemon")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxZipWith() {
		var fruitFluxZipWith = fluxAndMonoService.fruitFluxZipWith();
		StepVerifier.create(fruitFluxZipWith)
		.expectNext("MangoTomato","OrangeLemon")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxZipWithTuple() {
		var fruitFluxZipWithTuple = fluxAndMonoService.fruitFluxZipWithTuple();
		StepVerifier.create(fruitFluxZipWithTuple)
		.expectNext("MangoTomatoPotato","OrangeLemonBeans")
		.verifyComplete();
	}
	
	@Test
	void testFruitMonoZipWith() {
		var fruitMonoZipWith = fluxAndMonoService.fruitMonoZipWith();
		StepVerifier.create(fruitMonoZipWith)
		.expectNext("MangoTomato")
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxFlatmap() {
		var fruitFluxFlatmap = fluxAndMonoService.fruitFluxFlatmap();
		StepVerifier.create(fruitFluxFlatmap)
		.expectNextCount(17)
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxFlatmapAsync() {
		var fruitFluxFlatmapAsync = fluxAndMonoService.fruitFluxFlatmapAsync();
		StepVerifier.create(fruitFluxFlatmapAsync)
		.expectNextCount(17)
		.verifyComplete();
	}
	
	@Test
	void testFruitFluxConcatmapAsync() {
		var fruitFluxConcatmapAsync = fluxAndMonoService.fruitFluxConcatmapAsync();
		StepVerifier.create(fruitFluxConcatmapAsync)
		.expectNextCount(17)
		.verifyComplete();
	}
	
	@Test
	void testFruitMono() {
		var fruitMono = fluxAndMonoService.fruitMono();
		StepVerifier.create(fruitMono)
		.expectNext("Mango")
		.verifyComplete();
	}

	@Test
	void testFruitMonoFlatMap() {
		var fruitMonoflatMap = fluxAndMonoService.fruitMonoFlatMap();
		StepVerifier.create(fruitMonoflatMap)
		.expectNextCount(1)
		.verifyComplete();
	}
	
	@Test
	void testFruitMonoFlatMapMany() {
		var fruitMonoflatMapMany = fluxAndMonoService.fruitMonoFlatMapMany();
		StepVerifier.create(fruitMonoflatMapMany)
		.expectNextCount(5)
		.verifyComplete();
	}

	@Test
	void testFruitFluxFilterDoOn() {
		var fruitFluxFilterDoOn = fluxAndMonoService.fruitFluxFliterDoOn(5);
		StepVerifier.create(fruitFluxFilterDoOn)
		.expectNext("Orange","Banana")
		.verifyComplete();
	}
	
	@Test
	void testFruitsFluxOnErrorReturn() {
		var fruitsFluxOnErrorReturn = fluxAndMonoService.fruitsFluxOnErrorReturn();
		StepVerifier.create(fruitsFluxOnErrorReturn)
		.expectNext("Apple","Mango","Orange")
		.verifyComplete();
	}
	
	@Test
	void testFruitsFluxOnErrorContinue() {
		var fruitsFluxOnErrorContinue = fluxAndMonoService.fruitsFluxOnErrorContinue();
		StepVerifier.create(fruitsFluxOnErrorContinue)
		.expectNext("APPLE","ORANGE")
		.verifyComplete();
	}
	
	@Test
	void testFruitsFluxOnErrorMap() {
//Hooks.onOperatorDebug();
		ReactorDebugAgent.init();
		ReactorDebugAgent.processExistingClasses();
		var fruitsFluxOnErrorMap = fluxAndMonoService.fruitsFluxOnErrorMap();
		StepVerifier.create(fruitsFluxOnErrorMap)
		.expectNext("APPLE")
		.expectError(IllegalStateException.class)
		.verify();
	}
	
	@Test
	void testFruitsFluxOnError() {
		var fruitsFluxOnError = fluxAndMonoService.fruitsFluxOnError();
		StepVerifier.create(fruitsFluxOnError)
		.expectNext("APPLE")
		.expectError(RuntimeException.class)
		.verify();
	}
}

