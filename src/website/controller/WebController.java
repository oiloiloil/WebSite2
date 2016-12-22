package website.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import website.dao.AccountDao;
import website.model.UserInfo;

@Controller
public class WebController {
	private String viewPage;
	private AccountDao accountDao;
	
	@Resource(name = "accountDao")
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@RequestMapping("/error.do")
	public ModelAndView handleError(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		return new ModelAndView("404");
	}
	
	@RequestMapping("/create.do")
	public ModelAndView handleCreateUser(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		
		UserInfo user = new UserInfo();
		user.setName(name);
		user.setPasswd(passwd);
		user.setPhone(phone);
		user.setEmail(email);
		user.setBirth(birth);
		if(user.check()) // 所有欄位都正確的話才會新增這筆使用者資料
			accountDao.createUser(user);
		
		return new ModelAndView("login");
	}
	
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

}
