package com.example.study.episode.service;


import com.example.study.book.repository.BookRepository;
import com.example.study.book.repository.projection.BookCoverUrlProjection;
import com.example.study.episode.domain.Episode;
import com.example.study.episode.domain.type.EpisodeStatus;
import com.example.study.episode.repository.EpisodeRepository;
import com.example.study.member.repository.MemberRepository;
import com.example.study.util.jwt.JwtPayloadParser;
import com.example.study.util.jwt.JwtPayloadParserBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

import static com.example.study.episode.api.dto.EpiCommandDto.EpiAddRequestDto;
import static com.example.study.episode.api.dto.EpiCommandDto.EpiAddResponsetDto;

@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultEpiCommandService implements EpiCommandService{
	
	private final JwtPayloadParserBuilder jwtPayloadParserBuilder;
	private final MemberRepository memberRepository;
	private final EpisodeRepository episodeRepository;
	private final BookRepository bookRepository;
	
	@Override
	public EpiAddResponsetDto add(EpiAddRequestDto dto, HttpServletRequest request) {
		
		
		JwtPayloadParser payloadParser = jwtPayloadParserBuilder.buildWith(request);
		
		String email = payloadParser.subject();
		String nickname = payloadParser.claims().get("nickname", String.class);
		
		// 유저 아이디
		UUID memberId = memberRepository.findIdByEmail(email)
				.orElseThrow(IllegalStateException::new)
				.id();
		
		// 책 커버
		Optional<BookCoverUrlProjection> optionalCoverUrl = bookRepository.findCoverUrlById(dto.bookId());
		
		String coverUrl = optionalCoverUrl.isPresent() ?
				optionalCoverUrl.get().coverUrl()
				: "";
		
		Episode episode = Episode.builder()
				.bookId(dto.bookId())
				.memberId(memberId)
				.nickname(nickname)
				.title(dto.title())
				.content(dto.content())
				.review(dto.review())
				.coverUrl(coverUrl)
				.viewCount(0)
				.status(EpisodeStatus.UPLOADED)
				.build();
		episodeRepository.save(episode);
		
		return EpiAddResponsetDto.builder()
				.success(true)
				.build();
	}
	

}
