package com.quorastudent.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.QuestionsFeedDto;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.FeedService;

@RestController
@RequestMapping(value = "feed")
public class FeedController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private FeedService feedService;

	@RequestMapping(value = "getQuestionsFeed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO addComment(@RequestBody Map<String,Integer> questionsFeed) {

		ResponseDTO responseDto = null;
		try {
			
			int unvcode = questionsFeed.get("unvcode");
			List<QuestionsFeedDto> ls = feedService.getQuestionsFeed(unvcode);
			responseDto = responseDtoGeneral.getSuccessResponse(ls);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	
}
