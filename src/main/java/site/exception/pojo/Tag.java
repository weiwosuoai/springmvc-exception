package site.exception.pojo;

import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {
    private static final long serialVersionUID = 6809008550016948981L;
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Integer createUserId;

    private String description;

    private Integer spiderPageNum;

    /**
	 * 中文描述
     */
    private String descriptionCh;
    public Integer getSpiderPageNum() {
        return spiderPageNum;
    }

    public void setSpiderPageNum(Integer spiderPageNum) {
        this.spiderPageNum = spiderPageNum;
    }

    public String getDescriptionCh() {
        return descriptionCh;
    }

    public void setDescriptionCh(String descriptionCh) {
        this.descriptionCh = descriptionCh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}