package com.quorastudent.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.quorastudent.constants.Queries;
import com.quorastudent.dto.QuestionViewDTO;
import com.quorastudent.dto.QuestionsFeedDto;

@Service
public class JdbcQueryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<QuestionsFeedDto> getQuestionsFeed(long unvcode) {
		return jdbcTemplate.query(Queries.FEED_QUERY, new BeanPropertyRowMapper<>(QuestionsFeedDto.class), unvcode);

	}

	@SuppressWarnings("unchecked")
	public QuestionViewDTO findByEqidAndCtype(Long eqid, String ctype, Long userid) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("eqid", eqid);
		parameters.put("ctype", ctype);
		parameters.put("userid", userid);
		return (QuestionViewDTO) namedParameterJdbcTemplate.queryForObject(Queries.GET_QUESTION_QUERY, parameters,
				new BeanPropertyRowMapper<>(QuestionViewDTO.class));

//		return jdbcTemplate.query(Queries.GET_QUESTION_QUERY, new BeanPropertyRowMapper<>(QuestionViewDTO.class), eqid,ctype);
	}

}
