package site.exception.pojo.vo;

import site.exception.pojo.AnswerDesc;
import site.exception.pojo.AnswerDescWithBLOBs;

public class AnswerDescWithBLOBsVo extends AnswerDescWithBLOBs {

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