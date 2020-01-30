package com.example.demo.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.OrderConfirmationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderConfirmationServiceTest {
	
	@Autowired
	private OrderConfirmationService orderConfirmationService;

	@Test
	public void testReturnToday() throws ParseException {
		Timestamp today = orderConfirmationService.returnToday();
	}
	
	@Test
	public void testFileInOut() throws IOException {
		String jpegId = "1000";
		String jpgId = "2000";
		String pngId = "3000";
		String jpegPath = "/img/temporary/jpeg.jpeg";
		String ipgPath = "/img/temporary/jpg.jpg";
		String pngPath = "/img/temporary/png.png";
		orderConfirmationService.fileInOut(jpegId, jpegPath);
		orderConfirmationService.fileInOut(jpgId, ipgPath);
		orderConfirmationService.fileInOut(pngId, pngPath);
	}
}

