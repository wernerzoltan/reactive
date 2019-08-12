package com.example.demo.webflux;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
public class WeatherService {
	
	private WebClient webclient;
	
	public WeatherService(WebClient.Builder builder) {
		
		this.webclient = builder.baseUrl("http://localhost:8081").build(); //ezzel hozzuk létre a webclient-et
		
	}

	public Flux<WeatherReport> getReports() {
		return Flux.merge(
		getReport("BCN"),
		getReport("NCY"),
		getReport("BUD")
		);
				
	}
	
	private Mono<WeatherReport> getReport(String code) {
		
		//itt aMVC környezetben hívjuk meg a WebCLient-et
		Mono<WeatherReport> report = webClient.get().uri("/" + code).retrive().bodyToMono(WeatherReport.class)
				.doOnNext(n -> Logger.getLogger("WeatherService").info("Received: " + code);   //logolni lehet ezzel);
						
		return report;
		
	}
	
	
}
