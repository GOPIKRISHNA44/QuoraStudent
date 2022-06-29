package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.LoginDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.dto.SessionDetailsDTO;
import com.quorastudent.dto.UserDetailsDTO;
import com.quorastudent.services.UserService;


@RestController
@RequestMapping(value = "user")
public class UserController {

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@Autowired
	private UserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO register(@RequestBody UserDetailsDTO userDetailsDto) {

		ResponseDTO responseDto = null;
		try {

			boolean status = userService.register(userDetailsDto);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("success", true);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
		ResponseDTO responseDto = null;
		try {
			Map<String, Object> finalData = userService.login(loginDTO, request);
			responseDto = responseDtoGeneral.getSuccessResponse(finalData);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	
	@RequestMapping(value = "checksession", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO checkSession(@RequestBody SessionDetailsDTO sessionDetailsDTO) {
		ResponseDTO responseDto = null;
		try {
			boolean isValid = userService.checkSession(sessionDetailsDTO.getSessionkey());
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("valid", isValid);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO logout(@RequestBody SessionDetailsDTO sessionDetailsDTO) {
		ResponseDTO responseDto = null;
		try {
			boolean loggedout = userService.logout(sessionDetailsDTO.getSessionkey());
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("loggedout", loggedout);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

}
