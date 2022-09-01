package com.quorastudent.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.LeaderboardDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.InfoService;

@RestController
@RequestMapping(value = "info")
public class InfoController {

	@Autowired
	private InfoService infoService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@RequestMapping(value = "getUnvList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getUnvList() {

		ResponseDTO responseDto = null;
		try {

			Map<String, Object> finalData = infoService.getUniversitiesList();
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getInterests", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getInterests() {

		ResponseDTO responseDto = null;
		try {

			Map<String, Object> finalData = infoService.getInterests();
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getLeaderboard", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getLeaderboard(@RequestParam String unvcode) {

		ResponseDTO responseDto = null;
		try {

			List<Map<String, Object>> finalData = infoService.getLeaderboard(unvcode);
			List<Map<String, Object>> finalDatRes = new ArrayList<Map<String,Object>>();
			if (!ObjectUtils.isEmpty(finalData)) {
				int position = 1;
				for (Map<String, Object> map : finalData) {
					Map<String, Object> mapRes = new HashMap<String, Object>(map);
					mapRes.put("position", position++);
					finalDatRes.add(mapRes);
				}
			}
			responseDto = responseDtoGeneral.getSuccessResponse(finalDatRes);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
}
