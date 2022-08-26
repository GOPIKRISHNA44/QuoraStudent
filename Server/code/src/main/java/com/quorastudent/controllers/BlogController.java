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

import com.quorastudent.dto.BlogDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.BlogService;

@Component
@RequestMapping(value = "blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();
	
	



	@RequestMapping(value = "saveBlog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO saveBlog(@RequestBody BlogDTO blogDTO) {

		ResponseDTO responseDto = null;
		try {
			
			boolean status = blogService.saveBlog(blogDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);


		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
	
	@RequestMapping(value = "updateBlog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updateBlog(@RequestBody BlogDTO blogDTO) {

		ResponseDTO responseDto = null;
		try {
			
			boolean status = blogService.updateBlog(blogDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);


		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
	
	@RequestMapping(value = "deleteBlog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO deleteBlog(@RequestBody BlogDTO blogDTO) {

		ResponseDTO responseDto = null;
		try {
			
			boolean status = blogService.deleteBlog(blogDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
	
	

}
