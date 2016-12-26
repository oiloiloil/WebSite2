package website.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	private FilterConfig filterConfig;
	private List<String> urls;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		urls = new ArrayList<String>();

		// Get init parameter，抓取web.xml裡有哪些action執行時不要進行filter的檢查
		String avoidUrls = filterConfig.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(avoidUrls, ",");
		while (token.hasMoreTokens()) {
			urls.add(token.nextToken());
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();

		/*
		 * 重導向時注意，確認導向後的 action 是否有在排除清單裡面，避免導向過去後又經過filter再一次的驗證， 導致一直重複導向，出現錯誤
		 */
		if (!isAvoidAction(request.getRequestURI())
				&& (session.getAttribute("acct") == null || session.getAttribute("Authorise") == null)) {
			response.sendRedirect("error.do");
			return;
		}
		filterChain.doFilter(req, res);
	}

	/*
	 * 檢查requestURI是否包含要忽略的action
	 */
	protected boolean isAvoidAction(String requestURI) {
		for (String s : urls) {
			if (requestURI.contains(s))
				return true;
		}
		return false;
	}

}
