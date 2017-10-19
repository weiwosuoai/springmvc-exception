package site.exception.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.exception.dao.QuestionMapper;
import site.exception.pojo.Question;
import site.exception.utils.TranslateUtil;

import java.util.List;

/**
 * Created by Allen on 2017/6/18.
 */
@Component("questionDesTranslaterScheduler")
public class QuestionDesTranslaterScheduler {

	private static final Log logger = LogFactory.getLog(QuestionDesTranslaterScheduler.class);

	@Autowired
	private QuestionMapper questionMapper;

	public void questionDesTranslater() {
		logger.info("----------------- 开启一个新的 #问题描述信息# 翻译线程 -------------");
		newQuestionDesTranslaterThread();
	}

	/**
	 * 启动翻译线程
	 */
	private void newQuestionDesTranslaterThread() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// 问题描述信息翻译
//				List<Question> questions = questionMapper.selectDesNotEmptyAndDesChEmpty();
//				logger.info("翻译问题描述信息开始=================");
//				for (Question question : questions) {
//					String description = question.getDescription();
//					logger.info("待翻译文本id - " + question.getId());
//
//					try {
//
//						String ts = TranslateUtil.translateStackoverflowText(description);
//
//						question.setDescription(null);
//						question.setDescriptionCh(ts);
//						int count = questionMapper.updateByPrimaryKeySelective(question);
//						if (count > 0) {
//							logger.info("翻译问题描述信息成功 - " + ts);
//						} else {
//							logger.error("翻译失败");
//						}
//					} catch (Exception e) {
//						logger.error("", e);
//
//						// 超时
////						question.setDescription(null);
////						question.setDescriptionCh("?");
////						int count = questionMapper.updateByPrimaryKeySelective(question);
////						if (count > 0) {
////							logger.info("超时，此记录下次不翻译 - " + question.getId());
////						}
//					}
//				}
//				logger.info("翻译问题描述信息结束=================");
//			}
//		}).start();
	}


}
