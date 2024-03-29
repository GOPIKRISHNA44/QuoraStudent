package com.quorastudent.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.LoginDTO;
import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.dto.SessionDetailsDTO;
import com.quorastudent.dto.UpdateInterestsDTO;
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
			finalMsg.put("success", status);
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

	@RequestMapping(value = "updateInterests", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateInterests(@RequestBody UpdateInterestsDTO updateInterestsDTO) {
		ResponseDTO responseDto = null;
		try {
			boolean status = userService.updateInterests(updateInterestsDTO);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "getInterestpopupStatus", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getInterestpopupStatus(@RequestParam Long userid) {
		ResponseDTO responseDto = null;
		try {
			int status = userService.getInterestpopupStatus(userid);
			Map<String, Integer> finalMsg = new HashMap<String, Integer>();
			finalMsg.put("status", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}
	
	@RequestMapping(value = "updateAvatar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updateAvatar(@RequestBody Map<String, Object> info) {
		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			int avatarid = Integer.parseInt(info.get("avatarid").toString());
			boolean status = userService.updateAvatar(userid, avatarid);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO updatePassword(@RequestBody Map<String, Object> info) {
		ResponseDTO responseDto = null;
		try {
			Long userid = Long.parseLong(info.get("userid").toString());
			String password = info.get("password").toString();
			boolean status = userService.updatePassword(userid, password);
			Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
			finalMsg.put("updated", status);
			responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());

		}
		return responseDto;
	}

}
