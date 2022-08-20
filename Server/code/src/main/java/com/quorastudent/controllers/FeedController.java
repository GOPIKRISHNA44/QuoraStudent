package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.FeedRequestDTO;
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
	public ResponseDTO addComment(@RequestBody Map<String, Integer> questionsFeed) {

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

	@RequestMapping(value = "getQuestionsOrEventFeed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getQuestionsOrEventFeed(@RequestBody FeedRequestDTO feedRequestDTO) {

		ResponseDTO responseDto = null;
		try {
			Page<List<Map<String, Object>>> pageableResponse = feedService.getQuestionsOrEventFeed(feedRequestDTO);
			pageableResponse.getContent();
			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("data", pageableResponse.getContent());
			finalResp.put("totalPages", pageableResponse.getTotalPages());
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getBlogFeed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getBlogFeed(@RequestBody FeedRequestDTO feedRequestDTO) {

		ResponseDTO responseDto = null;
		try {
			Page<List<Map<String, Object>>> pageableResponse = feedService.getBlogFeed(feedRequestDTO);
			pageableResponse.getContent();
			Map<String, Object> finalResp = new HashMap<String, Object>();
			finalResp.put("data", pageableResponse.getContent());
			finalResp.put("totalPages", pageableResponse.getTotalPages());
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "getTagRelatedQuesOrEvents", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getTagRelatedQuesOrEvents(@RequestBody Map<String, Object> dataMap) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(dataMap.get("userid").toString());
			List<Integer> tags = null;
			if (dataMap.containsKey("tags")) {
				tags = (List<Integer>) dataMap.get("tags");
			}
			String ctype = dataMap.get("ctype").toString();
			List<Map<String, Object>> finalResp = feedService.getTagRelatedQuesOrEvents(userid, ctype, tags);
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "getTopLikedBlogs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getTopLikedBlogs(@RequestBody Map<String, Object> dataMap) {

		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(dataMap.get("userid").toString());
			List<Map<String, Object>> finalResp = feedService.getTopLikedBlogs(userid);
			responseDto = responseDtoGeneral.getSuccessResponse(finalResp);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "getMyQuestionsOrEvents", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getMyQuestionsOrEvents(@RequestBody FeedRequestDTO feedRequestDTO) {
		ResponseDTO responseDto = null;
		try {
			List<Map<String, Object>> pageableResponse = feedService
					.getQuestionsOrEventsOfAUser(feedRequestDTO.getUserid(), feedRequestDTO.getCtype());

			responseDto = responseDtoGeneral.getSuccessResponse(pageableResponse);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getMyBlogs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getMyBlogs(@RequestBody FeedRequestDTO feedRequestDTO) {
		ResponseDTO responseDto = null;
		try {
			List<Map<String, Object>> pageableResponse = feedService.getBlogsForUser(feedRequestDTO.getUserid());
			responseDto = responseDtoGeneral.getSuccessResponse(pageableResponse);
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
}
