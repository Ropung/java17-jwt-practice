package com.example.study.book.service;

import com.example.study.book.api.dto.BookAddDto.BookAddRequestDto;
import com.example.study.book.domain.Book;
import com.example.study.book.repository.BookRepository;
import com.example.study.member.repository.MemberRepository;
import com.example.study.upload.service.LocalUploadImageService;
import com.example.study.util.jwt.JwtPayloadParser;
import com.example.study.util.jwt.JwtPayloadParserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public final class DefaultBookCommandService implements BookCommandService {
	
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final JwtPayloadParserBuilder jwtPayloadParserBuilder;
	private final LocalUploadImageService localUploadImageService;
	
	
	
	@Override
	public boolean add(BookAddRequestDto dto, MultipartFile file ,HttpServletRequest request) {
		
		JwtPayloadParser payloadParser = jwtPayloadParserBuilder.buildWith(request);
		
		String email = payloadParser.subject();
		
		// 유저 아이디
		UUID memberId = memberRepository.findIdByEmail(email)
				.orElseThrow(IllegalStateException::new)
				.id();
		
		Book book = Book.builder()
				.memberId(memberId)
				.genreId(dto.genreId())
				.title(dto.title())
				.description(dto.description())
				//로컬에 파일 저장하고 url 반환받은 값 디비에 저장
				.coverUrl(localUploadImageService.upload(file).url())
				.isbn(dto.isbn())
				.cip(dto.cip())
				.build();
		bookRepository.save(book);
		
		return true;
	}
	

}
