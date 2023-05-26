package com.example.study.episode.api;

import com.example.study.episode.domain.Episode;
import com.example.study.episode.exception.EpisodeQueryErrorCode;
import com.example.study.episode.service.EpiQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.example.study.episode.api.dto.EpiQueryDto.EpiQueryResponsetDto;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/epi")
public class EpiQueryApi {
	
	private final EpiQueryService epiQueryService;
	
	@GetMapping(path = "/{bookId}")
	public EpiQueryResponsetDto findAllByBookId(
			@PathVariable UUID bookId,
			@PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		
		pageable = pageable.previousOrFirst();
		
		Page<Episode> episodeSearchResult = epiQueryService.findEpisode(bookId,pageable);
		List<Episode> epis = episodeSearchResult.toList();
		long lastPageNumber = episodeSearchResult.getTotalPages();
		
		if (pageable.getPageNumber() >= lastPageNumber) {
			throw EpisodeQueryErrorCode.PAGE_OUT_OF_RANGE.defaultException();
		}
		
		return EpiQueryResponsetDto.builder()
				.episodes(epis)
				.lastPage(lastPageNumber)
				.build();
	}
}
