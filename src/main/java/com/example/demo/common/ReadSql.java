package com.example.demo.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * SQL文をファイルから読み込むクラス.
 * 
 * @author shun.m
 *
 */
@Component
public class ReadSql {

	/**
	 * SQL文をファイルから読み込むメソッド.
	 * 
	 * @param path ファイルの相対パス.
	 * @return SQL文
	 * @throws IOException
	 */
	public String readAll(final String path) throws IOException {
		return Files.lines(Paths.get(path), Charset.forName("UTF-8"))
				.collect(Collectors.joining(System.getProperty("line.separator")));
	}

}
