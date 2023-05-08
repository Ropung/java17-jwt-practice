package com.example.study.episode.service;

import javax.servlet.http.HttpServletRequest;

import static com.example.study.episode.api.dto.EpiCommandDto.EpiAddRequestDto;
import static com.example.study.episode.api.dto.EpiCommandDto.EpiAddResponsetDto;

public interface EpiCommandService {
	
	EpiAddResponsetDto add(EpiAddRequestDto body,HttpServletRequest request);
}
