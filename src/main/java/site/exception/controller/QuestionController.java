package site.exception.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.exception.common.pojo.ServerResponse;
import site.exception.pojo.vo.QuestionVo;
import site.exception.service.QuestionService;
import site.exception.service.TagService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页
 * Created by Allen on 2017/6/26.
 */
@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private TagService tagService;

	private static final Log logger = LogFactory.getLog(QuestionController.class);

	/**
	 * 问题详情
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String showQuestionDetail(Model model, @PathVariable Integer id, HttpServletRequest request, HttpSession session) {
		QuestionVo questionVo = questionService.findDetailById(id);
		String url = request.getRequestURL().toString();
		model.addAttribute("questionVo", questionVo);
		model.addAttribute("navbarRef", "questions");
		session.setAttribute("returnUrl", url);
		return "question-detail";
	}

	/**
	 * 问题标签点击展示
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tagged/{taggedId}", method = RequestMethod.GET)
	public String showQuestionTagged(Model model,
									 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
									 @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
									 @PathVariable Integer taggedId) {
//		model.addAttribute("pageInfo", questionService.findByPagination(pageNum, pageSize));
		model.addAttribute("pageInfo", questionService.findTaggedByPagination(pageNum, pageSize, taggedId));
		// 隐藏域使用
		model.addAttribute("taggedId", taggedId);
		model.addAttribute("navbarRef", "questions");
		model.addAttribute("tag", tagService.findById(taggedId));
		return "question-tagged";
	}

	/**
	 * 问题被阅读数+1
	 */
	@RequestMapping(value = "/{id}/view/num/increment")
	public void questionViewNumIncrement(@PathVariable Integer id) {
		questionService.viewNumIncrement(id);
	}

	/**
	 * 展示问题修改页
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateQuestion(@PathVariable Integer id, Model model) {

		QuestionVo questionVo = questionService.findSimpleDetailById(id);
		model.addAttribute("questionVo", questionVo);
		model.addAttribute("navbarRef", "questions");

		return "question-update";
	}

	/**
	 * 展示问题修改页
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updateQuestion(@PathVariable Integer id, Model model, QuestionVo questionVo, HttpSession session) {
		// 判断用户是否登录，未登录不允许编辑问题
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid == null) {
			return ServerResponse.createByErrorMessage("用户未登录,无法完善问题信息");
		}

		model.addAttribute("navbarRef", "questions");

		if (StringUtils.isEmpty(questionVo.getTitleCh()) || StringUtils.isEmpty(questionVo.getQuestionDesc().getDescriptionCh())) {
			ServerResponse.createByErrorMessage("问题标题或描述信息不能为空");
		}
		questionVo.setId(id);
		int count = questionService.updateTitleChAndDescriptionCh(questionVo, userid);

		Map<String, String> map = new HashMap<>();
		if (count > 0) {
			map.put("returnUrl", (String) session.getAttribute("returnUrl"));
			return ServerResponse.createBySuccess(map);
		}
		return ServerResponse.createBySuccessMessage("编辑失败");
	}

}
