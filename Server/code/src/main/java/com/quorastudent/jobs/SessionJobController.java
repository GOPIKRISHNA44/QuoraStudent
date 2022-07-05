package com.quorastudent.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quorastudent.repositories.SessionDetailsRepository;

@Component
@EnableScheduling
public class SessionJobController {

	@Value("${session.expiry.time}")
	private Long sessionExpiryTime;

	@Autowired
	private SessionDetailsRepository sessionDetailsRepository;

	@Scheduled(cron = "0 * * * * *")
	public void currentTime() {
		System.out.println("*********************** Running cron job ********************");
		sessionDetailsRepository.updateExpiredSession(sessionExpiryTime);
	}

}