package site.exception.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import site.exception.dao.QuestionTagMapper;
import site.exception.dao.TagMapper;
import site.exception.pojo.Tag;
import site.exception.pojo.vo.QuestionTagRelVo;
import site.exception.service.TagService;

import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private QuestionTagMapper questionTagMapper;
	@Autowired
	private TagMapper tagMapper;

	@Override
	public PageInfo findByPagination(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		List<QuestionTagRelVo> list = questionTagMapper.selectTagsByNumDesc();

		for (QuestionTagRelVo vo : list) {
			// 标签信息
			String descriptionCh = vo.getTag().getDescriptionCh();

			int limit = 40;
			if (!StringUtils.isEmpty(descriptionCh) && descriptionCh.length() > limit) {
				vo.getTag().setDescriptionCh(descriptionCh.substring(0, limit) + " ...");
			}
		}

		// 用 PageInfo 对结果进行包装
		PageInfo<QuestionTagRelVo> pageInfo = new PageInfo<>(list);

		return pageInfo;
	}

	@Override
	public Tag findById(Integer tagId) {
		return tagMapper.selectByPrimaryKey(tagId);
	}

	@Override
	public List<QuestionTagRelVo> findHotTags() {
		return questionTagMapper.selectHotTags();
	}
}
