package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.CommentsDTO;
import com.quorastudent.dto.CommentsRequestingDTO;
import com.quorastudent.dto.CommentsResponseListViewDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.CommentsService;

@RestController
@RequestMapping(value = "comments")
public class CommentsController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private CommentsService commentsService;

	@RequestMapping(value = "addComment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO addComment(@RequestBody CommentsDTO commentsDTO) {

		ResponseDTO responseDto = null;
		try {

			boolean status = commentsService.addComment(commentsDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updateComment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updateComment(@RequestBody CommentsDTO commentsDTO) {

		ResponseDTO responseDto = null;
		try {

			boolean status = commentsService.updateComment(commentsDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "deleteComment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO deleteComment(@RequestBody CommentsDTO commentsDTO) {

		ResponseDTO responseDto = null;
		try {

			boolean status = commentsService.deleteComment(commentsDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getCommentsList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO getCommentsList(@RequestBody CommentsRequestingDTO commentsRequestingDTO) {

		ResponseDTO responseDto = null;
		try {

			List<CommentsResponseListViewDTO> ls = commentsService.getCommentsList(commentsRequestingDTO);
			responseDto = responseDtoGeneral.getSuccessResponse(ls);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

}
