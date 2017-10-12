package site.exception.pojo.vo;

import site.exception.pojo.Question;
import site.exception.pojo.QuestionDescWithBLOBs;
import site.exception.pojo.Tag;

import java.util.List;

/**
 * Created by Allen on 2017/7/17.
 */
public class QuestionVo extends Question {

	/**
	 * 查询solr记录总数
	 */
	private Long indexTotal;

	/**
	 * 问题的答案数
	 */
	private Integer answerNum;

	/**
	 * 是否存在被采纳的回答
	 */
	private Integer hasAccepteAnswer;

	private QuestionDescWithBLOBs questionDesc;

	private List<QuestionTagRelVo> qtmaps;

	private List<AnswerVo> answers;

	private List<Tag> tags;

	public QuestionDescWithBLOBs getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(QuestionDescWithBLOBs questionDesc) {
		this.questionDesc = questionDesc;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<QuestionTagRelVo> getQtmaps() {
		return qtmaps;
	}

	public void setQtmaps(List<QuestionTagRelVo> qtmaps) {
		this.qtmaps = qtmaps;
	}

	public List<AnswerVo> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerVo> answers) {
		this.answers = answers;
	}

	public Long getIndexTotal() {
		return indexTotal;
	}

	public void setIndexTotal(Long indexTotal) {
		this.indexTotal = indexTotal;
	}

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public Integer getHasAccepteAnswer() {
		return hasAccepteAnswer;
	}

	public void setHasAccepteAnswer(Integer hasAccepteAnswer) {
		this.hasAccepteAnswer = hasAccepteAnswer;
	}
}
