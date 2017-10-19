package site.exception.utils;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import site.exception.pojo.vo.QuestionVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/23.
 */
public class SolrJUtil {

	public static List<QuestionVo> findByPaginationFromSolr(String q, int start, int rows) {
		try {
			String urlString = "http://localhost:8983/solr";
			SolrServer solrServer = new HttpSolrServer(urlString);

			// 请求参数构造
			SolrQuery solrQuery = new SolrQuery();

//		solrQuery.setQuery("q:tomcat不重启，清除内存");
			solrQuery.setParam("q", "q:" + q);
			solrQuery.setParam("defType", "edismax");
			solrQuery.setParam("indent", "true");
			solrQuery.setParam("qf", "title_ch^10 description_ch^1");


			// 高亮
			solrQuery.setHighlight(true);
			solrQuery.setParam("h1.f1", "title_ch description_ch");
			// 摘要字数
			solrQuery.setHighlightFragsize(200);

			// 分页
			solrQuery.setStart(start);
			solrQuery.setRows(rows);
			QueryResponse queryResponse = solrServer.query(solrQuery);

			SolrDocumentList solrDocumentList = queryResponse.getResults();

			long num = solrDocumentList.getNumFound();

			Map<String, Map<String, List<String>>> list = queryResponse.getHighlighting();

			List<QuestionVo> questionVoList = new ArrayList<>();
			for (SolrDocument solrDocument : solrDocumentList) {

				QuestionVo questionVo = new QuestionVo();

				questionVo.setIndexTotal(num);
				String id = (String) solrDocument.get("id");
				questionVo.setId(Integer.valueOf(id));
				String titleCh = (String) solrDocument.get("title_ch");
				String descriptionCh = (String) solrDocument.get("description_ch");

//			System.out.println(id);
//			System.out.println(titleCh);
//			System.out.println(descriptionCh);

				Map<String, List<String>> fieldMap = list.get(id);
				List<String> titleChList = fieldMap.get("title_ch");
				if (titleChList != null && titleChList.size() > 0) {
					questionVo.setTitleCh(titleChList.get(0).toString());
				} else {
					questionVo.setTitleCh(titleCh);
				}

//			System.out.println(titleChList.get(0));

				List<String> descriptionChList = fieldMap.get("description_ch");
				if (descriptionChList != null && descriptionChList.size() > 0) {
//					questionVo.setDescriptionCh(descriptionChList.get(0).toString().replace(">", "&gt;")
//							.replace("em&gt;", "em>")
//							.replace("script", "") + "...");
				} else {
//					questionVo.setDescriptionCh(descriptionCh.replace(">", "&gt;")
//							.replace("em&gt;", "em>")
//							.replace("script", "") + "...");
				}
				questionVoList.add(questionVo);
			}

			return questionVoList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
