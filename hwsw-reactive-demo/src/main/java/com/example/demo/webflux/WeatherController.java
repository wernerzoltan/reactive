package com.example.demo.webflux;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
	
	private WeatherService weatherService;
	
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping(value = "/reports", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //a MediaType-ban beállítjuk hogy ne JSON-t adjon vissza, hanem stream-et
															//a JSON esetén mindegyik választ megvárja és csak utána adja vissza, stream esetén nem várunk egymásra hanem azonnal megjeleníti
	public Flux<WeatherReport> getReports() {
		return this.weatherService.getReports();
	}
	
	
	@GetMapping(value = "temperatures",  produces = MediaType.TEXT_EVENT_STREAM_VALUE))
	public Flux<String> getSimpleReports() {
		
		return this.weatherService.getReports().map(e -> e.getCode() + " - " + e.getTemperature());
		
	}
	
	
}
