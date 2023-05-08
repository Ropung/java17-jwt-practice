package com.example.study.episode.api;

import com.example.study.episode.service.EpiCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.study.episode.api.dto.EpiCommandDto.EpiAddRequestDto;
import static com.example.study.episode.api.dto.EpiCommandDto.EpiAddResponsetDto;
@RestController
@RequiredArgsConstructor
@RequestMapping("/episode")
public class EpiCommandApi {
	
	private final EpiCommandService epiCommandService;
	@PostMapping("/add")
	public EpiAddResponsetDto add(
			@Valid EpiAddRequestDto body,
			HttpServletRequest request){
		return epiCommandService.add(body, request);
	}
}
