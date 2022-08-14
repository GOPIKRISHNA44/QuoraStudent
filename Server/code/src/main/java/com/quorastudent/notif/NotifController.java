package com.quorastudent.notif;

import java.util.HashMap;
import java.util.List;
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

@RestController
@RequestMapping(value = "notif")
public class NotifController {

	@Autowired
	private NotifService notifService;

	ResponseDTO responseDtoGeneral = new ResponseDTO();

	@RequestMapping(value = "getNotifs", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO getNotifs(@RequestParam Long userid) {
		ResponseDTO responseDto = null;
		try {
			if (!ObjectUtils.isEmpty(userid)) {
				List<NotifDTO> notifList = notifService.getNotifications(userid);
				responseDto = responseDtoGeneral.getSuccessResponse(notifList);
			}
		} catch (Exception e) {

			responseDto = responseDtoGeneral.getFailureResponse(e.getMessage());
		}
		return responseDto;
	}

	@RequestMapping(value = "deleteNotifs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody Map<String, Object> data) {

		ResponseDTO responseDto = null;
		try {
			if (!ObjectUtils.isEmpty(data)) {
				List<Integer> ids = (List<Integer>) data.get("ids");
				boolean status = notifService.deleteSeenNotifications(Long.parseLong(data.get("userid").toString()),
						ids);
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
