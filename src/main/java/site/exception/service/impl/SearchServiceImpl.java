package site.exception.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.rjeschke.txtmark.Processor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.exception.dao.*;
import site.exception.pojo.QuestionDescWithBLOBs;
import site.exception.pojo.Tag;
import site.exception.pojo.vo.*;
import site.exception.service.QuestionService;
import site.exception.service.SearchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class SearchServiceImpl implements SearchService {

	private static final Log logger = LogFactory.getLog(SearchServiceImpl.class);

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionDescMapper questionDescMapper;
	@Autowired
	private SolrServer solrServer;

	/**
	 * 导入所有更翻译好的问题入 solr 索引库
	 */
	@Override
	public void importAllQuestion() {
		logger.info("--- 开始查询问题并导入 solr 索引库 ---");
		List<QuestionVo> questionVoList = questionMapper.selectBeAnswered();

		try {
			// 删除所有
			solrServer.deleteByQuery("*:*");
		} catch (Exception e) {
			logger.error("", e);
		}


		for (QuestionVo vo : questionVoList) {
			try {
				logger.info("id - " + vo.getId() + "================================================");
				QuestionDescWithBLOBs qDesc = questionDescMapper.selectLatestByQuestionId(vo.getId());
				if (qDesc == null || StringUtils.isBlank(qDesc.getDescriptionCh())) {
					continue;
				}
				// 创建一个文档对象 SolrInputDocument
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", vo.getId());
				document.addField("title_ch", vo.getTitleCh());
				document.addField("description_ch", qDesc.getDescriptionCh());
				solrServer.add(document);
			} catch (Exception e) {
				logger.error("", e);
				logger.info("id - " + vo.getId() + " import failed ================================================");
			}

		}

		try {
			// 提交
			solrServer.commit();
		} catch (Exception e) {
			logger.error("", e);
		}

		logger.info("--- 导入结束 ---");
	}
}
