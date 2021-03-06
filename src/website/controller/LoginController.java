package website.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import website.model.AccountModel;

@Controller
public class LoginController {
	private String viewPage;
	private AccountModel accountModel;

	@Resource(name = "accountModel")
	public void setAccountModel(AccountModel accountModel) {
		this.accountModel = accountModel;
	}

	// 處理登入動作
	@RequestMapping("/login.do")
	public ModelAndView handleLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = req.getParameter("userName");
		String password = req.getParameter("password");
		// 檢核使用者登入帳密
		Map<String, Object> resultMap = accountModel.checkAccount(name, password);
		// 請實做：根據登入是否成功，回傳相對應ModelView
		if (resultMap != null) { // 有回傳資料代表登入成功
			HttpSession session = req.getSession();
			session.setAttribute("acct", name);
			session.setAttribute("pass", resultMap.get("Password"));
			session.setAttribute("Authorise", resultMap.get("Authorise"));

			return new ModelAndView("index", "resultMap", resultMap);
		} else // 登入失敗
			return new ModelAndView("login", "errMsg", "帳號 or 密碼輸入錯誤");
	}

	@RequestMapping("/industryList.do")
	public ModelAndView doIndustry(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return new ModelAndView("industryList");
	}

	// 清除session的資料
	@RequestMapping("/logout.do")
	public ModelAndView handleLogout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().removeAttribute("acct");
		req.getSession().removeAttribute("pass");
		req.getSession().removeAttribute("Authorise");

		return new ModelAndView("login");
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

}
