package com.example.study.util.upload;

// Another -> close(final class)
// Specific Classes -> open(무조건 해야 하긴 하지만 의미상으로는 허용. 거의 강요지만. allow inheritance : extends ...)

// 스프링에서는 인터페이스 작성하고 -> 그걸로 (자동으로 빈 만들어 주는 경우) -> sealed interface면 빈을 못 만듦.
// 헷갈리면
//  우리가 만들어서(하위 클래스 구현까지) 전부 우리가 쓰면 -> Ok
//  sealed class -> 보통은 오케이. 왜냐면 클래스 만들면 우리가 상속 더 안 시키고 직접 지정한 대상까지만 쓰는 경우가 많으니까.
//  sealed interface -> 보통은 헷갈리면 안 써도 됨. 필수는 아니고 그냥 더 명시적인 설계에 쓰기 위한 문법일 뿐.
public sealed interface FileNameGenerator
	permits DailyFileNameGenerator /* , SexyFileNameGenerator, CuteFile... */ {
	String generateWithFullPath(String originFileName);
}
