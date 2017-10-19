package site.exception.dao;

import site.exception.pojo.QuestionTagRel;
import site.exception.pojo.vo.QuestionTagRelVo;

import java.util.List;

public interface QuestionTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionTagRel record);

    int insertSelective(QuestionTagRel record);

    QuestionTagRel selectByPrimaryKey(Integer id);

    QuestionTagRel selectByQuestionIdAndTagId(QuestionTagRel record);

    List<QuestionTagRelVo> selectTagsByNumDesc();

    List<QuestionTagRelVo> selectTagsByPrimaryKey(Integer id);

    List<QuestionTagRelVo> selectHotTags();

    int updateByPrimaryKeySelective(QuestionTagRel record);

    int updateByPrimaryKey(QuestionTagRel record);


}