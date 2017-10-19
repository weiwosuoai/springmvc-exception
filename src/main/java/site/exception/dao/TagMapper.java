package site.exception.dao;

import site.exception.pojo.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    Tag selectByTagName(String name);

    List<Tag> selectEmptyDescription();

    List<Tag> selectEmptyDescription2();

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKeyWithBLOBs(Tag record);
    int updateByNameWithBLOBs(Tag record);

    int updateByPrimaryKey(Tag record);

    int updateByTagName(Tag record);
}