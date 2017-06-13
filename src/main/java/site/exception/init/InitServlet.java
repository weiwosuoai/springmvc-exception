package site.exception.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by Allen on 2017/6/13.
 */
@Component
public class InitServlet implements InitializingBean {

	private static final Log logger = LogFactory.getLog(InitServlet.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("####################### 初始系统开始 #######################");

		logger.info("####################### 1.启动爬虫线程 #######################");
// TODO: 2017/6/13

		logger.info("####################### 2.启动翻译线程 #######################");
// TODO: 2017/6/13

		logger.info("####################### 初始系统结束 #######################");
	}
}
