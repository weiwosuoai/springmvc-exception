package site.exception.dao;

import site.exception.pojo.QuestionTagMapper;

public interface QuestionTagMapperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionTagMapper record);

    int insertSelective(QuestionTagMapper record);

    QuestionTagMapper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionTagMapper record);

    int updateByPrimaryKey(QuestionTagMapper record);
}