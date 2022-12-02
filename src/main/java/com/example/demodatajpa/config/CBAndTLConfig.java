package com.example.demodatajpa.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;

@Configuration
public class CBAndTLConfig {
	
	@Bean
	public CircuitBreaker circuitBreaker() {
		return CircuitBreaker
				  .ofDefaults("backendService");
	}
	
	@Bean
	public TimeLimiter timeLimiter() {
		return TimeLimiter.of(Duration.ofSeconds(1));
	}
}
