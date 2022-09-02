package com.quorastudent.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import com.quorastudent.dto.SessionDetailsDTO;
import com.quorastudent.repositories.SessionDetailsRepository;
import com.quorastudent.services.UserService;


@Component
@Configurable
public class RequestAuthenticationFilter extends GenericFilterBean {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionDetailsRepository sessionDetailsRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected static final List<String> ALLOWED_URL_LIST = Arrays.asList(" user/register",
			"/quoraStudent/user/login", "/quoraStudent/user/checksession", "/quoraStudent/info/getUnvList",
			"/quoraStudent/info/getInterests", "/quoraStudent/account/activateEmail");

//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		 SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		HttpServletRequest request = (HttpServletRequest) req;

//		HttpSession session = request.getSession(false);
		String url = (request.getRequestURI());

		if (ALLOWED_URL_LIST.contains(url) || url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png")
				|| url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".ttf") || url.endsWith(".woff")
				|| url.endsWith(".csv")) {

			filterChain.doFilter(request, response);
		} else {
			String sessionkey = request.getHeader("sessionkey");
			try {
				boolean isLoggedIn = userService.checkSession(sessionkey);
//				System.out.print(jdbcTemplate);
//				List<SessionDetailsDTO> sessionRecords = sessionDetailsRepository.findBySessionkeyAndActive(sessionkey, 1);
//				if (!ObjectUtils.isEmpty(sessionRecords) && sessionRecords.size()>0)
//				{
//					isLoggedIn= true;
//				}
				if (ObjectUtils.isEmpty(sessionkey) || !isLoggedIn) {
					// ((HttpServletResponse) response).sendError(403, "Invalid SESSION");
					filterChain.doFilter(request, response);
				} else {
					filterChain.doFilter(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				((HttpServletResponse) response).sendError(403, "Invalid SESSION");
				 filterChain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {

	}
}