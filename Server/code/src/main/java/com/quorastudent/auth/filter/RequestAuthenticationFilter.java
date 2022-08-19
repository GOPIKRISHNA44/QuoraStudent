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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.quorastudent.services.UserService;

public class RequestAuthenticationFilter implements Filter {

	@Autowired
	private UserService userService;

	protected static final List<String> ALLOWED_URL_LIST = Arrays.asList(" user/register",
			"/quoraStudent/user/login", "/quoraStudent/user/checksession", "/quoraStudent/info/getUnvList",
			"/quoraStudent/info/getInterests", "/quoraStudent/account/activateEmail");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

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
				if (ObjectUtils.isEmpty(sessionkey) || userService.checkSession(sessionkey)) {
					// ((HttpServletResponse) response).sendError(403, "Invalid SESSION");
					filterChain.doFilter(request, response);
				} else {
					filterChain.doFilter(request, response);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			//	((HttpServletResponse) response).sendError(403, "Invalid SESSION");
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// ((HttpServletResponse) response).sendError(403, "Invalid SESSION");
				filterChain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {

	}
}