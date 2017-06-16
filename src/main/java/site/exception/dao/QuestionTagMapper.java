package site.exception.dao;

import site.exception.pojo.QuestionTagMap;

public interface QuestionTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionTagMap record);

    int insertSelective(QuestionTagMap record);

    QuestionTagMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionTagMap record);

    int updateByPrimaryKey(QuestionTagMap record);
}