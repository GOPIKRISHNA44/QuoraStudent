package com.quorastudent.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.dto.FeedRequestDTO;
import com.quorastudent.dto.QuestionsFeedDto;
import com.quorastudent.repositories.JdbcQueryService;
import com.quorastudent.repositories.QuestionRepository;

@Service
public class FeedService {

	@Autowired
	private JdbcQueryService jdbcQueryService;

	@Autowired
	private PageableService pageableService;

	@Autowired
	private QuestionRepository questionRepository;

	public List<QuestionsFeedDto> getQuestionsFeed(int unvcode) throws Exception {
		// TODO Auto-generated method stub

		try {
			return jdbcQueryService.getQuestionsFeed(unvcode);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public Page<List<Map<String, Object>>> getQuestionsOrEventFeed(FeedRequestDTO feedRequestDTO) {
		try {
			if (ObjectUtils.isEmpty(feedRequestDTO.getFilterCondition())) {
				feedRequestDTO.setFilterCondition("");
			}
			Pageable pageRef = pageableService.getPageableRef(feedRequestDTO.getPageNumber() - 1,
					feedRequestDTO.getNumberOfPostsRequired());
			Page<List<Map<String, Object>>> pageTuts;
			pageTuts = questionRepository.getQuestionsOrEventFeed(feedRequestDTO.getUserid(), feedRequestDTO.getCtype(),feedRequestDTO.getFilterCondition(),
					pageRef);
			return pageTuts;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

}
