package com.quorastudent.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.dto.InterestsDTO;
import com.quorastudent.dto.LeaderboardDTO;
import com.quorastudent.dto.UniversityDTO;
import com.quorastudent.repositories.InterestsRepository;
import com.quorastudent.repositories.LeaderboardRepository;
import com.quorastudent.repositories.UniversityRepository;

@Service
public class InfoService {

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private InterestsRepository interestsRepository;

	@Autowired
	private LeaderboardRepository leaderboardRepository;

	public Map<String, Object> getUniversitiesList() throws Exception {

		try {
			List<UniversityDTO> unvList = universityRepository.findAll();
			if (!ObjectUtils.isEmpty(unvList)) {
				Map<String, Object> univMap = new HashMap<String, Object>();
				for (UniversityDTO universityDTO : unvList) {
					univMap.put(universityDTO.getUnvcode(), universityDTO);
				}
				Map<String, Object> finalData = new HashMap<String, Object>();
				finalData.put("univ", univMap);
				return finalData;
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;

	}

	public Map<String, Object> getInterests() throws Exception {

		try {
			List<InterestsDTO> unvList = interestsRepository.findAll();
			if (!ObjectUtils.isEmpty(unvList)) {

				Map<String, Object> finalData = new HashMap<String, Object>();
				finalData.put("interests", unvList);
				return finalData;

			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;

	}

	public List<LeaderboardDTO> getLeaderboard(String unvcode) throws Exception {

		try {
			return leaderboardRepository.findByUnvcode(unvcode);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

}
