package site.exception.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.exception.common.pojo.ServerResponse;
import site.exception.pojo.AnswerDescWithBLOBs;
import site.exception.pojo.vo.QuestionVo;
import site.exception.service.AnswerService;
import site.exception.service.QuestionService;
import site.exception.service.TagService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 回答controller
 * Created by Allen on 2017/6/26.
 */
@Controller
@RequestMapping(value = "/answers")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	private static final Log logger = LogFactory.getLog(AnswerController.class);


	/**
	 * 展示回答修改页
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateAnwser(@PathVariable Integer id, Model model) {
		AnswerDescWithBLOBs answerDescVo = answerService.findSimpleDetailById(id);
		model.addAttribute("answerDescVo", answerDescVo);
		model.addAttribute("navbarRef", "questions");
		return "answer-update";
	}

	/**
	 * 修改回答
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updateAnswer(@PathVariable Integer id, Model model, AnswerDescWithBLOBs answerDescVo, HttpSession session) {
		// 判断用户是否登录，未登录不允许编辑问题
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid == null) {
			return ServerResponse.createByErrorMessage("用户未登录,无法完善回答信息");
		}

		model.addAttribute("navbarRef", "questions");

		if (StringUtils.isEmpty(answerDescVo.getDescriptionCh())) {
			ServerResponse.createByErrorMessage("回答描述信息不能为空");
		}
		int count = answerService.updateDescriptionCh(answerDescVo, userid);

		Map<String, String> map = new HashMap<>();
		if (count > 0) {
			map.put("returnUrl", (String) session.getAttribute("returnUrl"));
			return ServerResponse.createBySuccess(map);
		}
		return ServerResponse.createBySuccessMessage("编辑失败");
	}

}
