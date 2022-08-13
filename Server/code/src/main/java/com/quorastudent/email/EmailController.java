package com.quorastudent.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
			
		if(!ObjectUtils.isEmpty(q))
		{
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
}
