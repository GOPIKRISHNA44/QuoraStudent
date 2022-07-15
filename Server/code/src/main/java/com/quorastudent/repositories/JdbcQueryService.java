package com.quorastudent.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.quorastudent.constants.Queries;
import com.quorastudent.dto.QuestionsFeedDto;

@Service
public class JdbcQueryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<QuestionsFeedDto> getQuestionsFeed(long unvcode) {
		return jdbcTemplate.query(Queries.FEED_QUERY, new BeanPropertyRowMapper<>(QuestionsFeedDto.class), unvcode);

	}

}
