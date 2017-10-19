package site.exception.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.rjeschke.txtmark.Processor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.exception.dao.*;
import site.exception.pojo.AnswerDescWithBLOBs;
import site.exception.pojo.QuestionDescWithBLOBs;
import site.exception.pojo.Tag;
import site.exception.pojo.vo.*;
import site.exception.service.AnswerService;
import site.exception.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDescMapper answerDescMapper;


	/**
	 * 编辑问题，查询问题的简单信息（标题和描述）
	 *
	 * @param id
	 * @return
	 */
	@Override
	public AnswerDescWithBLOBs findSimpleDetailById(Integer id) {
		return answerDescMapper.selectByPrimaryKey(id);
	}


	@Override
	public int updateDescriptionCh(AnswerDescWithBLOBs vo, int userId) {
		// 先判断问题描述信息是否被修改，若未修改，则不插入
		AnswerDescWithBLOBs tmp = answerDescMapper.selectLatestById(vo.getId());
		String descCh = vo.getDescriptionCh();
		if (!descCh.trim().equals(tmp.getDescriptionCh().trim())) {
			// 在插入一条新的回答描述记录
			AnswerDescWithBLOBs a = new AnswerDescWithBLOBs();

			a.setAnswerId(tmp.getAnswerId());
			a.setDescriptionCh(descCh);
			a.setCreateUserId(userId);
			return answerDescMapper.insert(a);
		}
		return 1;
	}
}
