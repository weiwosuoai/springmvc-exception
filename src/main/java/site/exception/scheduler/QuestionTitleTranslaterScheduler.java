package site.exception.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.exception.dao.AnswerMapper;
import site.exception.dao.QuestionMapper;
import site.exception.dao.TagMapper;
import site.exception.pojo.Question;
import site.exception.utils.TranslateUtil;

import java.util.List;

/**
 * Created by Allen on 2017/6/18.
 */
@Component("questionTitleTranslaterScheduler")
public class QuestionTitleTranslaterScheduler {

	private static final Log logger = LogFactory.getLog(QuestionTitleTranslaterScheduler.class);

	@Autowired
	private QuestionMapper questionMapper;

	public void questionTitleTranslater() {
		logger.info("----------------- 开启一个新的 #问题标题# 翻译线程 -------------");
		newQuestionTitleTranslaterThread();
	}

	/**
	 * 启动翻译线程
	 */
	private void newQuestionTitleTranslaterThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 问题描述信息翻译
				List<Question> questions = questionMapper.selectDesNotEmptyAndDesChEmpty2();
				for (Question question : questions) {
					String title = question.getTitle();
					logger.info("待翻译标题id - " + question.getId());

					try {

						title = TranslateUtil.translate(title);

						question.setTitle(null);
						question.setTitleCh(title);
						int count = questionMapper.updateByPrimaryKeySelective(question);
						if (count > 0) {
							logger.info("翻译问题标题成功 - " + title);
						} else {

							logger.error("翻译标题失败");
						}
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}
		}).start();
	}


}
