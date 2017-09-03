package site.exception.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.exception.pojo.Question;
import site.exception.pojo.vo.QuestionTagMapVo;
import site.exception.service.IQuestionService;
import site.exception.service.ITagService;
import site.exception.utils.SolrJUtil;
import site.exception.pojo.vo.QuestionVo;

import java.util.List;

/**
 * 首页
 * Created by Allen on 2017/6/26.
 */
@Controller
public class IndexController {

	@Autowired
	private IQuestionService questionService;
	@Autowired
	private ITagService tagService;

	private static final Log logger = LogFactory.getLog(IndexController.class);

	/**
	 * 首页信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String viewIndex(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
							@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
							Model model) {
		model.addAttribute("pageInfo", questionService.findByPagination(pageNum, pageSize));
		model.addAttribute("navbarRef", "question");
		return "index";
	}

	/**
	 * 首页信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index/search", method = RequestMethod.GET)
	public String search(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
							@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
							@RequestParam(value = "q", defaultValue = "") String q,
							Model model) {
		List<QuestionVo> list = SolrJUtil.findByPaginationFromSolr(q, (pageNum - 1)*pageSize, pageSize);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setList(list);

		if (list != null && list.size() > 0) {
			pageInfo.setTotal(list.get(0).getIndexTotal());
		}
		int pages = (int) ((pageInfo.getTotal() / pageSize) + 1);
		pageInfo.setPages(pages);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("q", q);
		model.addAttribute("navbarRef", "question");
		return "search-result";
	}

	/**
	 * 获取热门标签
	 *
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/index/hot_tags", produces = "application/json", method = RequestMethod.GET)
	public List<QuestionTagMapVo> hotTagsInfo(Model model) {
		return tagService.findHotTags();
	}

	/**
	 * 获取热门问题
	 *
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/index/hot_questions", produces = "application/json", method = RequestMethod.GET)
	public List<QuestionVo> hotQuestionsInfo(Model model) {
		return questionService.findHotQuestions();
	}

}
