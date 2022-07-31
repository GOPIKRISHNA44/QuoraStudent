package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.AskAquestionDTO;
import com.quorastudent.dto.LikedislikeDTO;
import com.quorastudent.dto.QuestionDTO;
import com.quorastudent.dto.QuestionViewDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.QuestionsService;

@RestController
@RequestMapping(value = "questions")
public class QuestionsController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private QuestionsService questionsService;

	@RequestMapping(value = "askAquestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO register(@RequestBody AskAquestionDTO askAquestionDTO) {

		ResponseDTO responseDto = null;
		try {

			boolean status = questionsService.askAquestion(askAquestionDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updwnvt", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updnvt(@RequestBody LikedislikeDTO likedislikeDTO) {
		ResponseDTO responseDto = null;
		try {

			boolean status = questionsService.updnvt(likedislikeDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getQuestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO register(@RequestBody QuestionDTO questionDTO) {

		ResponseDTO responseDto = null;
		try {

			QuestionViewDTO questionDTO1 = questionsService.getQuestionOnQuestionID(questionDTO);
			responseDto = responseDtoGeneral.getSuccessResponse(questionDTO1);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

}
