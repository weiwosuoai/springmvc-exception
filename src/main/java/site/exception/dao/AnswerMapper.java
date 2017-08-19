package site.exception.dao;

import site.exception.pojo.Answer;
import site.exception.pojo.vo.AnswerVo;

import java.util.List;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);
    List<AnswerVo> selectDesEmpty();

    List<AnswerVo> selectByQuestionId(Integer id);

    List<AnswerVo> selectAnswerChEmpty();
    List<AnswerVo> selectAnswerChEmpty2();

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKeyWithBLOBs(Answer record);

    int updateByPrimaryKey(Answer record);
}