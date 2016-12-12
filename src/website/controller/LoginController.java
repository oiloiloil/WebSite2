package website.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ModelAndView handleLogin(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String name = req.getParameter("userName");
		String password = req.getParameter("password");
		System.out.println("name: " + name);
		System.out.println("password: " + password);
		// 檢核使用者登入帳密
		Map<String, Object> resultMap = accountModel.checkAccount(name, password);
		
		// 請實做：根據登入是否成功，回傳相對應ModelView
		if(isUserInfoCorrect(name, password))
			return new ModelAndView("index", "name", name);
		else
			return new ModelAndView("login", "errMsg", "帳號密碼輸入錯誤");
	}
	
	private boolean isUserInfoCorrect(String name, String passwd) {
		return false;
	}

	@RequestMapping("/industryList.do")
    public ModelAndView doIndustry(HttpServletRequest req,
            HttpServletResponse resp) throws Exception {
        return new ModelAndView("industryList");
    }

    @RequestMapping("/logout.do")
	public ModelAndView handleLogout(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		arg0.getSession().removeAttribute("Authorise");
		
		return new ModelAndView("login");
		
	}
    
    public void setViewPage(String viewPage) {
    	this.viewPage = viewPage;
    }
	
}
