package com.example.study.util.upload;

// Another -> close(final class)
// Specific Classes -> open(무조건 해야 하긴 하지만 의미상으로는 허용. 거의 강요지만. allow inheritance : extends ...)
public sealed interface FileNameGenerator
	permits DailyFileNameGenerator /* , SexyFileNameGenerator, CuteFile... */ {
	String generateWithFullPath(String originFileName);
}
