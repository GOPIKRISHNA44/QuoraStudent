package com.quorastudent.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.quorastudent.constants.Queries;
import com.quorastudent.dto.FeedRequestDTO;
import com.quorastudent.dto.QuestionsFeedDto;
import com.quorastudent.repositories.BlogRepository;
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

	@Autowired
	private BlogRepository blogRepository;

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
			pageTuts = questionRepository.getQuestionsOrEventFeed(feedRequestDTO.getUserid(), feedRequestDTO.getCtype(),
					feedRequestDTO.getFilterCondition(), pageRef);
			return pageTuts;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public Page<List<Map<String, Object>>> getBlogFeed(FeedRequestDTO feedRequestDTO) {
		try {
			if (ObjectUtils.isEmpty(feedRequestDTO.getFilterCondition())) {
				feedRequestDTO.setFilterCondition("");
			}
			Pageable pageRef = pageableService.getPageableRef(feedRequestDTO.getPageNumber() - 1,
					feedRequestDTO.getNumberOfPostsRequired());
			Page<List<Map<String, Object>>> pageTuts;
			pageTuts = blogRepository.getBlogFeed(feedRequestDTO.getUserid(), feedRequestDTO.getFilterCondition(),
					pageRef);
			return pageTuts;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}

	public List<Map<String, Object>> getTagRelatedQuesOrEvents(Long userid, String ctype, List<Integer> tags)
			throws Exception {
		try {
			String headerPart = Queries.GET_TAG_RELATED_QUESTIONS_HEADER_PART;
			String footerPart = Queries.GET_TAG_RELATED_QUESTIONS_FOOTER_PART;
			String conditionPart = "";
			if (!ObjectUtils.isEmpty(tags) && tags.size() > 0) {
				conditionPart += "AND ( ";
				for (int i = 0; i < tags.size(); i++) {

					String condPart = "( q.tags LIKE '%;" + String.valueOf(tags.get(i)) + ";%' )";

					if (i != tags.size() - 1) {
						condPart += " OR ";
					}
					conditionPart += condPart;
				}
				conditionPart += " )";

			}
			String finalQuery = headerPart + conditionPart + footerPart;
			List<Map<String, Object>> res = jdbcQueryService.getTagRelatedQuesOrEvents(finalQuery, userid, ctype);
			return res;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> getTopLikedBlogs(Long userid) {
		try {
			return blogRepository.getTopLikedBlogs(userid);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> getQuestionsOrEventsOfAUser(Long userid, String ctype) {
		try {
			return questionRepository.getQuestionsOrEventsOfAUser(userid, ctype, "");
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public List<Map<String, Object>> getBlogsForUser(Long userid) {
		try {
			return blogRepository.getMyBlogs(userid, "");
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
