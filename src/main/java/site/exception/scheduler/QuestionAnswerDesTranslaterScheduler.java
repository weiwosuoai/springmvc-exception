package site.exception.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.exception.dao.AnswerMapper;
import site.exception.dao.QuestionMapper;
import site.exception.pojo.Question;
import site.exception.pojo.vo.AnswerVo;
import site.exception.utils.TranslateUtil;

import java.util.List;

/**
 * Created by Allen on 2017/6/18.
 */
@Component("questionAnswerDesTranslaterScheduler")
public class QuestionAnswerDesTranslaterScheduler {

	private static final Log logger = LogFactory.getLog(QuestionAnswerDesTranslaterScheduler.class);

	@Autowired
	private AnswerMapper answerMapper;

	public void questionAnswerDesTranslater() {
		logger.info("----------------- 开启一个新的 #回答描述信息# 翻译线程 -------------");
		newQuestionAnswerTranslaterThread();
	}

	/**
	 * 启动翻译线程
	 */
	private void newQuestionAnswerTranslaterThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 回答信息翻译
//				List<AnswerVo> answers = answerMapper.selectAnswerChEmpty();
//				logger.info("回答信息翻译开始----------------");
//				for (AnswerVo answer : answers) {
//					String ans = answer.getAnswer();
//					logger.info("待翻译回答 id - " + answer.getId());
//
//					try {
//						String ts = TranslateUtil.translateStackoverflowText(ans);
//
//						answer.setAnswer(null);
//						answer.setAnswerCh(ts);
//						int count = answerMapper.updateByPrimaryKeySelective(answer);
//						if (count > 0) {
//							logger.info("翻译回答成功");
//							logger.info(ts);
//						} else {
//							logger.error("翻译失败");
//						}
//					} catch (Exception e) {
//						logger.error("回答翻译失败 - " + answer.getId() + "=====================================");
//
//						// 超时
////						answer.setDescription(null);
////						answer.setDescriptionCh("?");
////						int count = answerMapper.updateByPrimaryKeySelective(answer);
////						if (count > 0) {
////							logger.info("超时，此记录下次不翻译 - " + answer.getId());
////						}
//					}
//				}
//				logger.info("回答信息翻译结束-------------------");
			}
		}).start();
	}


}
