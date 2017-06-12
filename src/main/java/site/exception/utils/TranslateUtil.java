package site.exception.utils;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.apache.commons.io.IOUtils;

/**
 * Created by Allen on 2017/6/12.
 */
public class TranslateUtil {

	protected static final String URL_TEMPLATE = "http://translate.google.cn/?langpair={0}&text={1}";
	protected static final String ID_RESULTBOX = "result_box";
	protected static final String ENCODING = "UTF-8";

	protected static final String AUTO = "auto"; // google自動判斷來源語系
	protected static final String TAIWAN = "zh-TW"; // 繁中
	protected static final String CHINESE = "zh-CN"; // 簡中
	protected static final String ENGLISH = "en"; // 英
	protected static final String JAPAN = "ja"; // 日

	/**
	 * <pre>Google翻譯
	 * PS: 交由google自動判斷來源語系
	 * </pre>
	 *
	 * @param text
	 * @param target_lang 目標語系
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text, final String target_lang) throws Exception {
		return translate(text, AUTO, target_lang);
	}

	/**
	 * <pre>Google翻譯</pre>
	 *
	 * @param text
	 * @param src_lang 來源語系
	 * @param target_lang 目標語系
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text, final String src_lang, final String target_lang)
			throws Exception {
		InputStream is = null;
		Document doc = null;
		Element ele = null;
		try {
			// create URL string
			String url = MessageFormat.format(URL_TEMPLATE,
					URLEncoder.encode(src_lang + "|" + target_lang, ENCODING),
					URLEncoder.encode(text, ENCODING));

			// connect & download html
			is = HttpClientUtil.downloadAsStream(url);

			// parse html by Jsoup
			doc = Jsoup.parse(is, ENCODING, "");
			ele = doc.getElementById(ID_RESULTBOX);
			String result = ele.text();
			return result;

		} finally {
			IOUtils.closeQuietly(is);
			is = null;
			doc = null;
			ele = null;
		}
	}

	public static void main(String[] args) {
		String text = "The important thing here is that the parameter values are combined with the compiled statement, not an SQL string. SQL injection works by tricking the script into including malicious strings when it creates SQL to send to the database. So by sending the actual SQL separately from the parameters, you limit the risk of ending up with something you didn't intend. Any parameters you send when using a prepared statement will just be treated as strings (although the database engine may do some optimization so parameters may end up as numbers too, of course). In the example above, if the $name variable contains 'Sarah'; DELETE FROM employees the result would simply be a search for the string \"'Sarah'; DELETE FROM employees\", and you will not end up with an empty table.";
		try {
			System.out.println(translate(text, ENGLISH, CHINESE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>Google翻譯: 簡中-->繁中</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String cn2tw(final String text) throws Exception {
		return translate(text, CHINESE, TAIWAN);
	}

	/**
	 * <pre>Google翻譯: 繁中-->簡中</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String tw2cn(final String text) throws Exception {
		return translate(text, TAIWAN, CHINESE);
	}

	/**
	 * <pre>Google翻譯: 英文-->繁中</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String en2tw(final String text) throws Exception {
		return translate(text, ENGLISH, TAIWAN);
	}

	/**
	 * <pre>Google翻譯: 繁中-->英文</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String tw2en(final String text) throws Exception {
		return translate(text, TAIWAN, ENGLISH);
	}

	/**
	 * <pre>Google翻譯: 日文-->繁中</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String jp2tw(final String text) throws Exception {
		return translate(text, JAPAN, TAIWAN);
	}

	/**
	 * <pre>Google翻譯: 繁中-->日</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String tw2jp(final String text) throws Exception {
		return translate(text, TAIWAN, JAPAN);
	}
}
