
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.omg.CORBA.Object;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * solr 测试
 * Created by Allen on 2017/8/23.
 */
public class SolrJTest {

	/**
	 * solr 查询测试
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		String urlString = "http://localhost:8983/solr";
		SolrServer solrServer = new HttpSolrServer(urlString);

		// 请求参数构造
		SolrQuery solrQuery = new SolrQuery();

//		solrQuery.setQuery("q:tomcat不重启，清除内存");
		solrQuery.setParam("q", "q:tomcat 403");
		solrQuery.setParam("defType", "edismax");
		solrQuery.setParam("indent", "true");
		solrQuery.setParam("qf", "title_ch^10 description_ch^1");


		// 高亮
		solrQuery.setHighlight(true);
		solrQuery.setParam("h1.f1", "title_ch description_ch");
		// 摘要字数
		solrQuery.setHighlightFragsize(50);

		// 分页
		solrQuery.setStart(0);
		solrQuery.setRows(20);

		QueryResponse queryResponse = solrServer.query(solrQuery);

		SolrDocumentList solrDocumentList = queryResponse.getResults();

		Map<String, Map<String, List<String>>> list = queryResponse.getHighlighting();

		for (SolrDocument solrDocument : solrDocumentList) {
			String id = (String) solrDocument.get("id");
//			String titleCh = (String) solrDocument.get("title_ch");
//			String descriptionCh = (String) solrDocument.get("description_ch");

			System.out.println(id);
//			System.out.println(titleCh);
//			System.out.println(descriptionCh);

			Map<String, List<String>> fieldMap = list.get(id);
			List<String> titleChList = fieldMap.get("title_ch");

			System.out.println(titleChList.get(0));

			List<String> descriptionChList = fieldMap.get("description_ch");
			if (descriptionChList != null && descriptionChList.size() > 0) {
				System.out.println(descriptionChList.get(0));
			}
		}
	}

	/**
	 * 向 solr 中添加索引
	 * @throws Exception
	 */
	@Test
	public void addDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://116.62.170.57:8080/solr/collection1");
		// 创建一个文档对象 SolrInputDocument
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "id2");
		document.addField("title_ch", "title_ch01");
		document.addField("description_ch", "description_ch01");

		// 写入索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	/**
	 * 删除索引
	 * @throws Exception
	 */
	@Test
	public void deleteDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://116.62.170.57:8080/solr/collection1");
		// 删除文档
//		solrServer.deleteById("id1");
		solrServer.deleteByQuery("*:*");
		// 提交
		solrServer.commit();
	}


}
