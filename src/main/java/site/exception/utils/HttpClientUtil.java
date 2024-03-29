package site.exception.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Created by Allen on 2017/6/12.
 */
public class HttpClientUtil {

	protected static Log log = LogFactory.getLog(HttpClientUtil.class);
	protected static HttpClient httpclient = null;
	/**
	 * 连接池最大链接个数
	 */
	protected static int maxTotal = 200;
	/**
	 * 单个路由链接的最大数
	 */
	protected static int maxPerRoute = 100;
	protected static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7";

	static {
		if (httpclient == null) {
			// ~~~~~~~~~~~~~~~~~~~~
			// create httpclient
			// ~~~~~~~~~~~~~~~~~~~~
			SchemeRegistry reg = new SchemeRegistry();
			reg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
			reg.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
			ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(reg);
			cm.setMaxTotal(maxTotal);
			cm.setDefaultMaxPerRoute(maxPerRoute);

			HttpParams params =new BasicHttpParams();
			// 超时设置
			/* 从连接池中取连接的超时时间 */
			ConnManagerParams.setTimeout(params, 1000);
            /* 连接超时 */
			HttpConnectionParams.setConnectionTimeout(params, 5000);
            /* 请求超时 */
			HttpConnectionParams.setSoTimeout(params, 5000);
			httpclient = new DefaultHttpClient(cm, params);
		}
	}

	/**
	 * <pre>下載後回傳Inputstream</pre>
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static InputStream downloadAsStream(String url) throws Exception {
		InputStream is = (InputStream) download(url, null, null, false);
		return is;
	}

	/**
	 * <pre>下載後儲存到File</pre>
	 *
	 * @param url
	 * @param saveFile
	 * @throws Exception
	 */
	public static void download(String url, File saveFile) throws Exception {
		download(url, saveFile, null, false);
	}

	/**
	 * <pre>下載</pre>
	 *
	 * @param url
	 * @param saveFile
	 * @param params
	 * @param isPost
	 * @return 如果saveFile==null則回傳inputstream, 否則回傳saveFile
	 * @throws Exception
	 */
	public static Object download(final String url, final File saveFile, final Map<String, String> params,
								  final boolean isPost) throws Exception {

		boolean saveToFile = saveFile != null;

		// check dir exist ??
		if (saveToFile && saveFile.getParentFile().exists() == false) {
			saveFile.getParentFile().mkdirs();
		}

		Exception err = null;
		HttpRequestBase request = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		FileOutputStream fos = null;
		Object result = null;

		try {
			// create request
			if (isPost) {
				request = new HttpPost(url);
			} else {
				request = new HttpGet(url);
			}

			// add header & params
			addHeaderAndParams(request, params);

			// connect
			response = httpclient.execute(request);
			entity = response.getEntity();
			entity = new BufferedHttpEntity(entity);

			// get result
			if (saveToFile) {// save to disk
				fos = new FileOutputStream(saveFile);
				IOUtils.copy(entity.getContent(), fos);
				result = saveFile;
			} else { // warp to inpustream
				result = new BufferedInputStream(entity.getContent());
			}

		} catch (Exception e) {
			err = e;
		} finally {

			// close
			IOUtils.closeQuietly(fos);

			// clear
			request = null;
			response = null;
			entity = null;

			if (err != null) {
				throw err;
			}

			return result;
		}

	}

	protected static void addHeaderAndParams(final HttpRequestBase request, final Map<String, String> params) {
		// add default header
		request.addHeader("User-Agent", userAgent);

		// add params
		if (params != null) {

			// map --> HttpParams
			BasicHttpParams myParams = new BasicHttpParams();
			for (String key : params.keySet()) {
				myParams.setParameter(key, params.get(key));
			}

			request.setParams(myParams);
		}
	}

	public static HttpClient getHttpclient() {
		return httpclient;
	}

	public static void setHttpclient(HttpClient httpclient) {
		HttpClientUtil.httpclient = httpclient;
	}

	public static int getMaxTotal() {
		return maxTotal;
	}

	public static void setMaxTotal(int maxTotal) {
		HttpClientUtil.maxTotal = maxTotal;
	}

	public static int getMaxPerRoute() {
		return maxPerRoute;
	}

	public static void setMaxPerRoute(int maxPerRoute) {
		HttpClientUtil.maxPerRoute = maxPerRoute;
	}

	public static String getUserAgent() {
		return userAgent;
	}

	public static void setUserAgent(String userAgent) {
		HttpClientUtil.userAgent = userAgent;
	}
}
