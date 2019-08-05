package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.dto.StatisticsDto;
import com.n26.service.StatisticsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/statistics")
@Slf4j
@lombok.Getter
@lombok.Setter
public class StatisticsController {

	private StatisticsService statisticsService;

	@Autowired
	public StatisticsController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	@GetMapping
	public StatisticsDto getStats() throws Exception {
		log.info(" Request received to get Statistics");

		StatisticsDto statisticsDto = getStatisticsService().getStatisticsDto();

		log.info(" statisticsDto {} ", statisticsDto);

		return statisticsDto;
	}
}
