package com.quorastudent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quorastudent.dto.QuestionsFeedDto;
import com.quorastudent.repositories.JdbcQueryService;

@Service
public class FeedService {

	@Autowired
	private JdbcQueryService jdbcQueryService;

	public List<QuestionsFeedDto> getQuestionsFeed(int unvcode) throws Exception {
		// TODO Auto-generated method stub

		try {
			return jdbcQueryService.getQuestionsFeed(unvcode);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
