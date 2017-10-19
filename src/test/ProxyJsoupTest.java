
import com.sun.imageio.plugins.common.InputStreamAdapter;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.omg.CORBA.Object;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 代理 jsoup 测试
 * Created by Allen on 2017/8/23.
 */
public class ProxyJsoupTest {

	@Test
	public void testJsoup() throws Exception {
//		String ip2 = "31.173.216.163";
//		int port2 = 8080;
//
//		int times = 1;
//
//		// 设置代理
//		System.setProperty("https.proxySet", "true");
//		System.getProperties().put("https.proxyHost", ip2);
//		System.getProperties().put("https.proxyPort", port2);
//
//		while (true) {
//			try {
//				Document document = Jsoup.connect("https://stackoverflow.com/questions/20024597/mirror-the-front-facing-camera-in-android")
//						// 模拟火狐浏览器
//						.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
//						.get();
//
//				System.out.println("times: " + times);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}

	}

	/**
	 * jsoup proxy
	 * @throws Exception
	 */
	@Test
	public void testProxyJsoup() throws Exception {
//		// 获取代理 ip
//		String ip = "119.55.223.205";
//		int port = 2862;
//
//		String ip2 = "110.187.40.229";
//		int port2 = 59728;
//
//		int times = 1;
//				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip2, port2));
//				URL url = new URL("https://stackoverflow.com/questions/20024597/mirror-the-front-facing-camera-in-android");
//		while (true) {
//			try {
//
//
//				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(proxy);
//				urlConnection.connect(); // get the connection
//
//				InputStream is = urlConnection.getInputStream();
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//				StringBuffer sb = new StringBuffer();
//				String line = null;
//
//				while ((line = bufferedReader.readLine()) != null) {
//					sb.append(line);
//				}
//
//				System.out.println("result: " + sb.toString());
//				System.out.println("times: " + (times++));
//			} catch (Exception e) {
//				e.printStackTrace();
//
//				System.out.println(e.getMessage());
//			}
//		}




	}
}
