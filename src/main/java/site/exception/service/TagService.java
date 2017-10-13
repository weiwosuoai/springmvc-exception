package site.exception.service;

import com.github.pagehelper.PageInfo;
import site.exception.pojo.Tag;
import site.exception.pojo.vo.QuestionTagRelVo;

import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
public interface TagService {

	List<QuestionTagRelVo> findHotTags();

	PageInfo findByPagination(int pageNum, int pageSize);

	Tag findById(Integer tagId);

}
