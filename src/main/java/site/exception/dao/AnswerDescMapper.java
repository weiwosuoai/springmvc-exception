package site.exception.dao;

import site.exception.pojo.AnswerDesc;
import site.exception.pojo.AnswerDescWithBLOBs;
import site.exception.pojo.vo.AnswerDescWithBLOBsVo;

import java.util.List;

public interface AnswerDescMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnswerDescWithBLOBs record);

    int insertSelective(AnswerDescWithBLOBs record);

    AnswerDescWithBLOBs selectByPrimaryKey(Integer id);
    AnswerDescWithBLOBsVo selectLatestById(Integer id);
    AnswerDescWithBLOBsVo selectLatestByAnswerId(Integer answerId);

    AnswerDescWithBLOBs selectByAnswerId(Integer id);
    List<AnswerDescWithBLOBs> selectByAnswerIds(String answerIds);

    int updateByPrimaryKeySelective(AnswerDescWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AnswerDescWithBLOBs record);

    int updateByPrimaryKey(AnswerDesc record);
}