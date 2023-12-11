package com.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reactive.service.FluxAndMonoService;

import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(ReactiveApplication.class, args);
	}
}
