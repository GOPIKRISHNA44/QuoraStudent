package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quorastudent.dto.AnswerDTO;
import com.quorastudent.dto.AnswerRequestingDTO;
import com.quorastudent.dto.AnswerResponseListViewDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.AnswerService;

@Component
@RequestMapping(value = "answer")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@RequestMapping(value = "addAnswer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO addAnswer(@RequestBody AnswerDTO answerDTO) {

		ResponseDTO responseDto = null;
		try {
			boolean status = answerService.saveAnswer(answerDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);

			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updateAnswer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updateComment(@RequestBody AnswerDTO answerDTO) {

		ResponseDTO responseDto = null;
		try {

			boolean status = answerService.updateAnswer(answerDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "deleteAnswer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO deleteComment(@RequestBody AnswerDTO answerDTO) {

		ResponseDTO responseDto = null;
		try {
			boolean status = answerService.deleteAnswer(answerDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getAnswersForQuestionOrEntity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getAnswersForQuestionOrEntity(@RequestBody AnswerRequestingDTO answerRequestingDTO) {

		ResponseDTO responseDto = null;
		try {
			List<AnswerResponseListViewDTO> ls = answerService.getAnswersForQuestionOrEntity(answerRequestingDTO);
			responseDto = responseDtoGeneral.getSuccessResponse(ls);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

}
