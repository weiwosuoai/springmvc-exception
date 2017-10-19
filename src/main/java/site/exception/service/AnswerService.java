package site.exception.service;

import com.github.pagehelper.PageInfo;
import site.exception.pojo.Answer;
import site.exception.pojo.AnswerDescWithBLOBs;
import site.exception.pojo.vo.QuestionVo;

import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
public interface AnswerService {


	AnswerDescWithBLOBs findSimpleDetailById(Integer id);

	int updateDescriptionCh(AnswerDescWithBLOBs vo, int userId);

}
