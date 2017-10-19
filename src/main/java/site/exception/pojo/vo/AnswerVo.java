package site.exception.pojo.vo;

import site.exception.pojo.Answer;
import site.exception.pojo.AnswerDescWithBLOBs;

/**
 * Created by Allen on 2017/7/18.
 */
public class AnswerVo extends Answer {

	private AnswerDescWithBLOBsVo answerDesc;

	public AnswerDescWithBLOBsVo getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(AnswerDescWithBLOBsVo answerDesc) {
		this.answerDesc = answerDesc;
	}
}
