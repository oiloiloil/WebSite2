package website.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	private String viewPage;
	
	@RequestMapping("/error.do")
	public ModelAndView handleError(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		return new ModelAndView("404");
	}
	
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

}
