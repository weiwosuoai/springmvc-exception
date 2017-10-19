package site.exception.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.exception.common.pojo.ServerResponse;
import site.exception.pojo.User;
import site.exception.pojo.vo.QuestionVo;
import site.exception.service.QuestionService;
import site.exception.service.TagService;
import site.exception.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * 用户controller
 * Created by Allen on 2017/6/26.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private TagService tagService;

	private static final Log logger = LogFactory.getLog(UserController.class);

	/**
	 * 展示登录页
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(Model model) {
		model.addAttribute("navbarRef", "users");
		return "login";
	}

	/**
	 * 登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse login(HttpSession session, User user) {

		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			return ServerResponse.createByErrorMessage("用户名或密码不能为空");
		}

		User tmp = userService.findByUserNameAndPwd(user);
		if (tmp == null) {
			return ServerResponse.createByErrorMessage("用户名或密码错误");
		}
		// 查询数据库，id 存入 session 中
		session.setAttribute("userid", tmp.getId());
		return ServerResponse.createBySuccessMessage("登录成功");
	}


}
