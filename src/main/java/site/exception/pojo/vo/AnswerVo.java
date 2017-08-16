package site.exception.pojo.vo;

import site.exception.pojo.Answer;

/**
 * Created by Allen on 2017/7/18.
 */
public class AnswerVo extends Answer {

	private String answerChHtml;

	private String answerHtml;

	public String getAnswerChHtml() {
		return answerChHtml;
	}

	public void setAnswerChHtml(String answerChHtml) {
		this.answerChHtml = answerChHtml;
	}

	public String getAnswerHtml() {
		return answerHtml;
	}

	public void setAnswerHtml(String answerHtml) {
		this.answerHtml = answerHtml;
	}
}
