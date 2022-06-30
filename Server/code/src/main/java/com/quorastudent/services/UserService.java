package com.quorastudent.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.ErrorMsgs;
import com.quorastudent.dto.LoginDTO;
import com.quorastudent.dto.SessionDetailsDTO;
import com.quorastudent.dto.UserDetailsDTO;
import com.quorastudent.repositories.SessionDetailsRepository;
import com.quorastudent.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UtilityService utilityService;
	@Autowired
	private DateUtility dateUtility;

	@Autowired
	private SessionDetailsRepository sessionDetailsRepository;

	public boolean register(UserDetailsDTO userDetailsDto) throws Exception {
		try {
			if(ObjectUtils.isEmpty(userDetailsDto.getUsername()) && ObjectUtils.isEmpty(userDetailsDto.getEmailid()))
			{
				throw new Exception(ErrorMsgs.INVALIDETAILS);
			}
			List<UserDetailsDTO> usersList = null;
			if(!ObjectUtils.isEmpty(userDetailsDto.getUsername()))
			{
				
			usersList	= userRepository.findByUsername(userDetailsDto.getUsername());
			if(!ObjectUtils.isEmpty(usersList))
			{
				throw new Exception(ErrorMsgs.USERNAMEFOUND);
			}
			}
			else if(!ObjectUtils.isEmpty(userDetailsDto.getUsername()))
			{
				usersList	= userRepository.findByEmailid(userDetailsDto.getEmailid());
				throw new Exception(ErrorMsgs.EMAILIDFOUND);
			}
			userDetailsDto.setPassword(utilityService.generateEncodedPassword(userDetailsDto.getPassword()));
			System.out.println(userDetailsDto.toString());
			userRepository.save(userDetailsDto);

		} catch (Exception e) {

			throw e;
		}

		return true;
	}

	public Map<String, Object> login(LoginDTO loginDTO, HttpServletRequest request) throws Exception {
		try {
			if (!ObjectUtils.isEmpty(loginDTO)) {

				String password = utilityService.generateEncodedPassword(loginDTO.getPassword());
				List<UserDetailsDTO> userDetailsDTOList = null;
				if (!ObjectUtils.isEmpty(loginDTO.getUsername())) {
					String username = loginDTO.getUsername();
					userDetailsDTOList = userRepository.findByUsernameAndPassword(username, password);
				} else {
					String emailId = loginDTO.getEmailId();
					userDetailsDTOList = userRepository.findByEmailidAndPassword(emailId, password);
				}
				if (!ObjectUtils.isEmpty(userDetailsDTOList)) {
					SessionDetailsDTO sessionDetailsDTO = new SessionDetailsDTO();
					sessionDetailsDTO.setLoggedinat(dateUtility.getCurrentDateAndTime());
					sessionDetailsDTO.setActive(1);
					sessionDetailsDTO.setUserid(userDetailsDTOList.get(0).getUserid());
					String sessionKey = utilityService
							.encryptMsg(String.valueOf((new Date()).getTime()) + sessionDetailsDTO.getUserid());
					sessionDetailsDTO.setSessionkey(sessionKey);
					sessionDetailsRepository.save(sessionDetailsDTO);

					Map<String, Object> finalData = new HashMap<String, Object>();
					finalData.put("sessionkey", sessionKey);
					userDetailsDTOList.get(0).setPassword(null);
					finalData.put("userdetails", userDetailsDTOList.get(0));
					return finalData;

				} else {
					throw new Exception(ErrorMsgs.LOGINAUTHFAILED);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return null;
	}

	public boolean checkSession(String sessionKey) throws Exception {
		try {
			List<SessionDetailsDTO> sessionRecords = sessionDetailsRepository.findBySessionkeyAndActive(sessionKey, 1);
			if (ObjectUtils.isEmpty(sessionRecords))
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

	public boolean logout(String sessionKey) throws Exception {
		try {
			sessionDetailsRepository.updateSessionEnd(sessionKey, dateUtility.getCurrentDateAndTime());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return true;
	}

}
