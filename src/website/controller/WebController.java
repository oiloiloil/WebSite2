package website.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import website.dao.AccountDao;
import website.model.ResponseMessage;
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
	public ModelAndView handleError(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return new ModelAndView("404");
	}

	@RequestMapping("/create.do")
	public void handleCreateUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ResponseMessage responseMsg;
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

		if(user.check()) { // 所有欄位都正確的話才會新增這筆使用者資料
			if(!accountDao.findUser(name, passwd)) { // 沒有這位使用者
				accountDao.createUser(user);
				responseMsg = new ResponseMessage("註冊成功", "success");
			}
			else {
				responseMsg = new ResponseMessage("註冊失敗，此帳號已被註冊過", "fail");
			}
		}
		else {
			responseMsg = new ResponseMessage("註冊失敗，有欄位填入錯誤", "fail");
		}
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		json.put("message", responseMsg.getMessage());
		out.print(json.toString());
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

}
