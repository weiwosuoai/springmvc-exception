package site.exception.dao;

import org.apache.ibatis.annotations.Param;
import site.exception.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    User selectByUserNameAndPwd(@Param("userName") String userName, @Param("pwd") String pwd);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}