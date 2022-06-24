package com.quorastudent.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quorastudent.dto.ResponseDTO;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO register() {
		return null;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO login() {
		return null;
	}

	@RequestMapping(value = "checksession", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO checkSession() {
		return null;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO logout() {
		return null;
	}

}
