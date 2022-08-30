package com.quorastudent.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.quorastudent.dto.LeaderboardDTO;
import com.quorastudent.dto.UniversityDTO;
import com.quorastudent.repositories.LeaderboardRepository;
import com.quorastudent.repositories.UniversityRepository;

@Component
@EnableScheduling
public class LeaderboardJob {

	@Value("${session.expiry.time}")
	private Long sessionExpiryTime;

	@Autowired
	private LeaderboardRepository leaderboardRepository;

	@Autowired
	private UniversityRepository universityRepository;

	@Scheduled(cron = "0 0 23 * * ?")
	public void currentTime() {
		List<UniversityDTO> unvList = universityRepository.findAll();
		for (UniversityDTO universityDTO : unvList) {
			String unvcode = universityDTO.getUnvcode();
			leaderboardRepository.deleteLeaderBoardForAnUniversity(unvcode);
			List<Map<String, Object>> ldto = leaderboardRepository.getLeaderBoard(unvcode);
			List<LeaderboardDTO> ldton = new ArrayList<LeaderboardDTO>();
			int position = 1;
			for (Map<String, Object> leaderboardDTO : ldto) {
				LeaderboardDTO leaderboardDTOn = new LeaderboardDTO();
				leaderboardDTOn.setNumberoflikes(Long.parseLong(leaderboardDTO.get("numberoflikes").toString()));
				leaderboardDTOn.setPosition(position++);
				leaderboardDTOn.setUnvcode(unvcode);
				leaderboardDTOn.setUseravatarid(Integer.parseInt(leaderboardDTO.get("useravatarid").toString()));
				leaderboardDTOn.setUsername(leaderboardDTO.get("username").toString());
				ldton.add(leaderboardDTOn);
			}
			if(!ObjectUtils.isEmpty(ldto)) {
				leaderboardRepository.saveAll(ldton);	
			}
			

		}

	}

}
