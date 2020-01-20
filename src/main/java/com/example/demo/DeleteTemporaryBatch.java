package com.example.demo;

import java.io.File;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteTemporaryBatch {
	
//	@Scheduled(initialDelay = 10000, fixedDelay = 10000)
	@Scheduled(cron = "0 0 * * * *", zone = "Asia/Tokyo")
	public void deleteTemporary() {
		String pathName = "/Users/atsushi/workspace-spring-tool-suite-4-4.3.1.RELEASE/hr_book/src/main/resources/static/img/temporary";
		
		File file = new File(pathName);
		
		delete(file);
	}
		
	public static void delete(File file){
		
		if (file.exists()) {
			if (file.isFile()) {
				if (file.delete()) {
					System.out.println("ファイル削除");
				}
			}else if (file.isDirectory()) {
				//ディレクトリ内のファイル取得.
				File[] files = file.listFiles();
				if (files != null) {
					//存在するファイル数分ループして再帰的に削除
					for(int i=0; i<files.length; i++) {
						
						delete(files[i]);
					}					
				}else {
					System.out.println("ファイルが存在しない");					
				}
			}
		} else {
			System.out.println("ファイルが存在しない");
		}
	}
}
