package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quorastudent.constants.AppConstants;
import com.quorastudent.dto.AskAnEventDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.AnswerService;
import com.quorastudent.services.EventService;

@Component
@RequestMapping(value = "event")
public class EventController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private EventService eventService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@RequestMapping(value = "addAnEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO addAnswer(@RequestBody AskAnEventDTO askAnEventDTO) {

		ResponseDTO responseDto = null;
		try {
			askAnEventDTO.getAskAquestionDTO().setCtype(AppConstants.EVENTSTR);
			boolean status = eventService.saveEvent(askAnEventDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updateAnEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updateComment(@RequestBody AskAnEventDTO askAnEventDTO) {

		ResponseDTO responseDto = null;
		try {

			boolean status = eventService.updateEvent(askAnEventDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "deleteAnEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO deleteComment(@RequestBody AskAnEventDTO askAnEventDTO) {

		ResponseDTO responseDto = null;
		try {
			boolean status = eventService.deleteAnEvent(askAnEventDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	
}