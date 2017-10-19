package site.exception.pojo.vo;

import site.exception.pojo.QuestionTagRel;
import site.exception.pojo.Tag;

/**
 * Created by Allen on 2017/7/17.
 */
public class QuestionTagRelVo extends QuestionTagRel {

	private Tag tag;

	/**
	 * 标签下问题数
	 */
	private Integer questionNum;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
}
