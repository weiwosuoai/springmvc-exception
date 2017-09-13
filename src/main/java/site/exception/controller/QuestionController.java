package site.exception.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.exception.pojo.vo.QuestionVo;
import site.exception.service.IQuestionService;
import site.exception.service.ITagService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 首页
 * Created by Allen on 2017/6/26.
 */
@Controller
public class QuestionController {

	@Autowired
	private IQuestionService questionService;
	@Autowired
	private ITagService tagService;

	private static final Log logger = LogFactory.getLog(QuestionController.class);

	/**
	 * 首页信息
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/{id}/detail", method = RequestMethod.GET)
	public String viewQuestionDetail(Model model, @PathVariable Integer id, HttpServletResponse response) {

		PrintWriter writer = null;
		try {
			response.setContentType("text/html; charset=gbk");
			writer = response.getWriter();

			QuestionVo questionVo = questionService.findDetailById(id);
			if (questionVo.getAnswers() == null || questionVo.getAnswers().size() == 0) {
				writer.print("非常抱歉！该问题，后台君正在努力翻译中，暂时还不能查看哦...");
				return null;
			}
			model.addAttribute("questionVo", questionVo);
			model.addAttribute("navbarRef", "question");
			return "question-detail";
		} catch (Exception e) {
			logger.error(e);
			if (writer != null) {
				writer.print("非常抱歉！该问题，后台君正在努力翻译中，暂时还不能查看哦...");
			}
		}
		return null;
	}

	/**
	 * 首页信息
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/tagged/{tagId}", method = RequestMethod.GET)
	public String viewQuestionTagged(Model model,
									 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
									 @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
									 @PathVariable Integer tagId) {
//		model.addAttribute("pageInfo", questionService.findByPagination(pageNum, pageSize));
		model.addAttribute("pageInfo", questionService.findTaggedByPagination(pageNum, pageSize, tagId));
		model.addAttribute("navbarRef", "question");
		model.addAttribute("tag", tagService.findById(tagId));
		return "question-tagged";
	}

	/**
	 * 问题被阅读数+1
	 */
	@RequestMapping(value = "/question/{id}/view_num/increment")
	public void questionViewNumIncrement(@PathVariable Integer id) {
		questionService.viewNumIncrement(id);
	}

}
