package com.quorastudent.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.ResponseDTO;
import com.quorastudent.services.InfoService;

@RestController
@RequestMapping(value = "account")
public class EmailController {

	@Autowired
	private EmailService emailService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@RequestMapping(value = "activateEmail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO activateEmail(@RequestParam String q) {

		ResponseDTO responseDto = null;
		try {

			if (!ObjectUtils.isEmpty(q)) {
				emailService.activateEmail(q);
				Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
				finalMsg.put("updated", true);
				responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);

			}
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "resetPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody Map<String, String> data) {

		ResponseDTO responseDto = null;
		try {

			if (!ObjectUtils.isEmpty(data)) {
				boolean status = emailService.sendPasswordResetLink(data.get("emailid"));
				Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
				finalMsg.put("updated", status);
				responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);
			}
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	
//	This renders after the UI
	@RequestMapping(value = "validateResetPassword", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO validateResetPassword(@RequestParam String e, @RequestParam String q) {
		ResponseDTO responseDto = null;
		try {
			if (!ObjectUtils.isEmpty(e) && !ObjectUtils.isEmpty(q)) {
				boolean status = emailService.validatePasswordResetLink(e, q);
				Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
				finalMsg.put("updated", status);
				responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);
			}
		} catch (Exception em) {
			responseDto = responseDtoGeneral.getFailureResponse(em.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO validateResetPassword(@RequestBody Map<String, String> data) {
		ResponseDTO responseDto = null;
		try {
			if (!ObjectUtils.isEmpty(data) && data.containsKey("emailid") && data.containsKey("password") && data.containsKey("passwordlink")) {
				boolean status = emailService.updatePassword(data.get("emailid"), data.get("password") , data.get("passwordlink"));
				Map<String, Boolean> finalMsg = new HashMap<String, Boolean>();
				finalMsg.put("updated", status);
				responseDto = responseDtoGeneral.getSuccessResponse(finalMsg);
			}
		} catch (Exception e) {
			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}
}
