package com.quorastudent.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.dto.UniversityDTO;
import com.quorastudent.repositories.UniversityRepository;

@Service
public class InfoService {

	@Autowired
	private UniversityRepository universityRepository;

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

}
