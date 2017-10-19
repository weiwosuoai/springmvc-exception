package site.exception.service;

import com.github.pagehelper.PageInfo;
import site.exception.pojo.Question;
import site.exception.pojo.vo.QuestionVo;

import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
public interface QuestionService {

	PageInfo findByPagination(int pageNum, int pageSize);
	PageInfo findTaggedByPagination(int pageNum, int pageSize, int taggedId);

	QuestionVo findDetailById(Integer id);

	QuestionVo findSimpleDetailById(Integer id);

	List<QuestionVo> findHotQuestions();

	int viewNumIncrement(Integer id);

	int updateTitleChAndDescriptionCh(QuestionVo questionVo, int userId);

}
