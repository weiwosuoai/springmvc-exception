package site.exception.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.rjeschke.txtmark.Processor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.exception.dao.*;
import site.exception.pojo.*;
import site.exception.pojo.vo.*;
import site.exception.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private AnswerMapper answerMapper;
	@Autowired
	private QuestionTagMapper questionTagMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private QuestionDescMapper questionDescMapper;
	@Autowired
	private AnswerDescMapper answerDescMapper;

	@Override
	public PageInfo findByPagination(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		List<QuestionVo> list = questionMapper.selectBeAnswered();

		for (QuestionVo vo : list) {
			// 标签信息
			List<QuestionTagRelVo> qtmaps = questionTagMapper.selectTagsByPrimaryKey(vo.getId());
			for (QuestionTagRelVo tmp : qtmaps) {
				Tag tag = tagMapper.selectByPrimaryKey(tmp.getTagId());
				tmp.getTag().setId(tag.getId());
				tmp.getTag().setName(tag.getName());
			}
			vo.setQtmaps(qtmaps);
		}

		// 用 PageInfo 对结果进行包装
		PageInfo<QuestionVo> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}

	@Override
	public PageInfo findTaggedByPagination(int pageNum, int pageSize, int taggedId) {
		PageHelper.startPage(pageNum, pageSize);
//		Pagination pagination = new Pagination(pageNum, pageSize);
		List<QuestionVo> list = questionMapper.selectTaggedQuestionByTagId(taggedId);

		for (QuestionVo vo : list) {
			// 解决标题中 < > 引起的格式错误
			String titleCh = vo.getTitleCh();
			if (titleCh.contains("<")) {
				titleCh = titleCh.replace("<", "&lt;");
			}
			if (titleCh.contains(">")) {
				titleCh = titleCh.replace(">", "&gt;");
			}
			vo.setTitleCh(titleCh);

			// 标签信息
			List<QuestionTagRelVo> qtmaps = questionTagMapper.selectTagsByPrimaryKey(vo.getId());
			for (QuestionTagRelVo tmp : qtmaps) {
				Tag tag = tagMapper.selectByPrimaryKey(tmp.getTagId());
				tmp.getTag().setId(tag.getId());
				tmp.getTag().setName(tag.getName());
			}
			vo.setQtmaps(qtmaps);
		}

		// 用 PageInfo 对结果进行包装
		PageInfo<QuestionVo> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}

	@Override
	public QuestionVo findDetailById(Integer id) {
		QuestionVo vo = questionMapper.selectByPrimaryKey(id);

		// 根据问题id获取问题描述信息
		QuestionDescWithBLOBsVo questionDescWithBLOBsVo = questionDescMapper.selectLatestByQuestionId(vo.getId());
		// 解析 md 文件
		questionDescWithBLOBsVo.setDescriptionChHtml(Processor.process(questionDescWithBLOBsVo.getDescriptionCh()));
		vo.setQuestionDesc(questionDescWithBLOBsVo);

		// 标签信息
		List<QuestionTagRelVo> qtmaps = questionTagMapper.selectTagsByPrimaryKey(vo.getId());
		for (QuestionTagRelVo tmp : qtmaps) {
			Tag tag = tagMapper.selectByPrimaryKey(tmp.getTagId());
			tmp.getTag().setId(tag.getId());
			tmp.getTag().setName(tag.getName());
		}
		vo.setQtmaps(qtmaps);

		// 回答相关数据
		List<AnswerVo> answers = answerMapper.selectByQuestionId(vo.getId());

		List<AnswerVo> list = new ArrayList<>();

		for (AnswerVo answer : answers) {
			AnswerDescWithBLOBsVo answerDesc = answerDescMapper.selectLatestByAnswerId(answer.getId());
			answer.setAnswerDesc(answerDesc);

			AnswerDescWithBLOBsVo answerDescWithBLOBs = answer.getAnswerDesc();
			if (answerDescWithBLOBs != null) {
				if (StringUtils.isNotBlank(answerDescWithBLOBs.getDescriptionCh())) {
					answerDescWithBLOBs.setAnswerChHtml(Processor.process(answerDescWithBLOBs.getDescriptionCh()));
				} else if (StringUtils.isNotBlank(answerDescWithBLOBs.getDescription())) {
					answerDescWithBLOBs.setAnswerHtml(Processor.process(answerDescWithBLOBs.getDescription()));
				}
				list.add(answer);
			}
		}
		vo.setAnswers(list);
		return vo;
	}


	/**
	 * 编辑问题，查询问题的简单信息（标题和描述）
	 * @param id
	 * @return
	 */
	@Override
	public QuestionVo findSimpleDetailById(Integer id) {
		QuestionVo vo = questionMapper.selectByPrimaryKey(id);
		// 根据问题id获取问题描述信息
		QuestionDescWithBLOBsVo questionDescWithBLOBsVo = questionDescMapper.selectLatestByQuestionId(vo.getId());
		vo.setQuestionDesc(questionDescWithBLOBsVo);
		return vo;
	}

	/**
	 * 首页热门问题
	 * @return
	 */
	@Override
	public List<QuestionVo> findHotQuestions() {
		return questionMapper.selectHotQuestions();
	}

	@Override
	public int viewNumIncrement(Integer id) {
		return questionMapper.updateViewNumIncrement(id);
	}

	@Override
	public int updateTitleChAndDescriptionCh(QuestionVo questionVo, int userId) {
		// 先更新问题标题
		int count = questionMapper.updateTitleChByPrimaryKey(questionVo.getTitleCh(), questionVo.getId());
		if (count > 0) {
			// 先判断问题描述信息是否被修改，若未修改，则不插入
			QuestionDescWithBLOBs tmp = questionDescMapper.selectLatestByQuestionId(questionVo.getId());
			String descCh = questionVo.getQuestionDesc().getDescriptionCh();
			if (!descCh.trim().equals(tmp.getDescriptionCh().trim())) {
				// 在插入一条新的问题描述记录
				QuestionDescWithBLOBs q = new QuestionDescWithBLOBs();
				q.setQuestionId(questionVo.getId());
				q.setDescriptionCh(descCh);
				q.setCreateUserId(userId);
				return questionDescMapper.insert(q);
			}
			// 将该问题的状态修改为 人工校译
			return questionMapper.updateStatusByPrimaryKey(1, questionVo.getId());
		}
		return 0;
	}
}
