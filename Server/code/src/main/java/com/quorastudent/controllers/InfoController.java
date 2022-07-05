package com.quorastudent.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
