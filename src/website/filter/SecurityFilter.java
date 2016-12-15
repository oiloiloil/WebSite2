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
		
		// Get init parameter 
		String avoidUrls = filterConfig.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(avoidUrls, ",");
		while(token.hasMoreTokens()) {
			urls.add(token.nextToken());
		}
    }
	
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain filterChain) throws IOException, ServletException {
    	req.setCharacterEncoding("utf-8");
    	res.setCharacterEncoding("utf-8");
    	HttpServletRequest request = (HttpServletRequest)req;
    	HttpServletResponse response = (HttpServletResponse)res;
    	HttpSession session = request.getSession();
    	
    	/*
    	 * 重導向時注意，要幫filter加入error.do這個例外情況，避免導向過去後又經過filter在一次的驗證，
    	 * 導致一直重複導向，出現錯誤
    	 */
    	if(!isAvoidAction(request.getRequestURI()) && 
    			(session.getAttribute("acct") == null || session.getAttribute("Authorise") == null)) {
    		response.sendRedirect("error.do");
    		return;
    	}
    	filterChain.doFilter(req, res);
    }
    
    /*
     * 確認目前的頁面是否為要跳過filter的頁面，使用action進行確認，要跳過的action設定在web.xml裡面，
     * 	<param-name>avoid-urls</param-name>
		<param-value>login.do</param-value>
	 * 檢查requestURI是否包含要忽略的action
     */
    protected boolean isAvoidAction(String requestURI) {
    	for(String s : urls) {
    		if(requestURI.contains(s))
    			return true;
    	}
    	return false;
    }

}
