package site.exception.pojo;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
    private static final long serialVersionUID = 8480143826338161421L;
    private Integer id;

    private String title;

    private String titleCh;

    private Date createTime;

    private Date updateTime;

    private Integer createUserId;

    private Integer voteUp;

    private Integer voteDown;
    private Integer viewNum;

    private Integer language;

    private Integer originalId;

	private String originalLink;

    private Integer titleTransFinished;

    private Integer answerTransFinished;

    private Integer status;

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public String getTitleCh() {
        return titleCh;
    }

    public void setTitleCh(String titleCh) {
        this.titleCh = titleCh;
    }

    public Integer getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Integer originalId) {
        this.originalId = originalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Integer getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(Integer voteUp) {
        this.voteUp = voteUp;
    }

    public Integer getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(Integer voteDown) {
        this.voteDown = voteDown;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getTitleTransFinished() {
        return titleTransFinished;
    }

    public void setTitleTransFinished(Integer titleTransFinished) {
        this.titleTransFinished = titleTransFinished;
    }

    public Integer getAnswerTransFinished() {
        return answerTransFinished;
    }

    public void setAnswerTransFinished(Integer answerTransFinished) {
        this.answerTransFinished = answerTransFinished;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}