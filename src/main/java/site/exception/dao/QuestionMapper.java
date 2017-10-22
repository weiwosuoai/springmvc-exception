package site.exception.dao;

import org.apache.ibatis.annotations.Param;
import site.exception.pojo.Question;
import site.exception.pojo.vo.QuestionVo;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    QuestionVo selectByPrimaryKey(Integer id);

    Question selectByOriginalId(Integer id);

    QuestionVo selectDetailById(Integer id);

    List<Question> selectAll();
    List<QuestionVo> selectHotQuestions();

    List<Question> selectDescriptionEmpty();

    List<QuestionVo> selectBeAnswered();
    List<QuestionVo> selectBeAnsweredWithDesc();
    List<QuestionVo> selectTaggedQuestionByTagId(Integer id);

    List<Question> selectNoAnswer();

    List<Question> selectDesNotEmptyAndDesChEmpty();

    List<Question> selectDesNotEmptyAndDesChEmpty2();

    int updateByPrimaryKeySelective(Question record);

    int updateTitleChByPrimaryKey(@Param("titleCh") String titleCh, @Param("id") Integer id);
    int updateStatusByPrimaryKey(@Param("status") Integer status, @Param("id") Integer id);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    int updateViewNumIncrement(Integer id);
}