package site.exception.dao;

import org.apache.ibatis.annotations.Param;
import site.exception.pojo.QuestionDesc;
import site.exception.pojo.QuestionDescWithBLOBs;
import site.exception.pojo.vo.QuestionDescWithBLOBsVo;

public interface QuestionDescMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionDescWithBLOBs record);

    int insertSelective(QuestionDescWithBLOBs record);

    QuestionDescWithBLOBs selectByPrimaryKey(Integer id);

    QuestionDescWithBLOBsVo selectByQuestionId(Integer questionId);
    QuestionDescWithBLOBsVo selectLatestByQuestionId(Integer questionId);

    int updateByPrimaryKeySelective(QuestionDescWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QuestionDescWithBLOBs record);

    int updateByPrimaryKey(QuestionDesc record);

    int updateDescriptionChByQuestionId(@Param("descriptionCh") String descriptionCh, @Param("questionId") Integer questionId);
}