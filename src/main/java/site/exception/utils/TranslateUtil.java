package site.exception.utils;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

/**
 * Created by Allen on 2017/6/12.
 */
public class TranslateUtil {

	// translate.google.cn 谷歌翻译
	protected static final String URL_TEMPLATE = "https://translate.google.cn/?langpair={0}&text={1}";
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
//	public static String translate(final String text, final String target_lang) throws Exception {
//		return translate(text, AUTO, target_lang);
//	}

	/**
	 * 翻译整段文本
	 *
	 * @param text
	 * @return
	 */
	public static String translateStackoverflowText(final String text) {
		StringBuffer sb = new StringBuffer();

		Scanner scanner = new Scanner(text);
		String line = "";

		// 能够出错的次数
		int transErrorNum = 0;

		while (scanner.hasNextLine()) {
			try {
				line = scanner.nextLine();

				// process the line
				line = line.replace("\r\n", "").replace("\n", "");
				if (StringUtils.isEmpty(line)) {
					sb.append(line + "\r\n");
					continue;
				}

				if (line.contains("http")) { // pre 1
					sb.append(line + "\r\n");
					continue;
				}

				if (line.startsWith("    ")) { // pre 1
					sb.append(line + "\r\n");
					continue;
				}

				if (line.startsWith("\t")) { // pre 2 Note: \t 是制表符
					sb.append(line + "\r\n");
					continue;
				}

				if (line.startsWith("  ")) { // 超链接或图片链接
					sb.append(line + "\r\n");
					continue;
				}

				if (line.startsWith(" * ")) { // 无序列表1
					sb.append(" * " + TranslateUtil.translate(line.substring(3)) + "\r\n");
					continue;
				}

				if (line.startsWith(" - ")) { // 无序列表2
					sb.append(" * " + TranslateUtil.translate(line.substring(3)) + "\r\n");
					continue;
				}

				// 一些特殊的行导致连接超时（例如以 .java .properties 结尾的行)
				line = line.trim();
				if (line.endsWith("java") || line.endsWith("properties")
						|| line.endsWith("com") || line.endsWith("xml")) {
					sb.append(line + "\r\n");
					continue;
				}

				// code
				if (line.contains("`")) {
					line.replace("\r\n", "");
//					sb.append(line + "\n");
					String[] strArr = line.split("`");
					for (int i = 1; i <= strArr.length; i++) {
						if (i % 2 == 0) { // code 内容
							sb.append("`" + (strArr[i - 1]) + "`");
							continue;
						}

						if (!StringUtils.isEmpty(strArr[i - 1])) {
							// 对代码进行分割翻译的时候，头部会出现？
							String subLine = TranslateUtil.translate(strArr[i - 1]).replace("?", "");
							sb.append(subLine);
						}
					}
					sb.append("\r\n");
					continue;
				}

				sb.append(TranslateUtil.translate(line) + "\r\n");
			}
//			catch (NullPointerException e) {
////				sb.append(line + "\r\n");
////				allowErrorNum += 1;
//
//				e.printStackTrace();
//				throw new RuntimeException(" *** 翻译失败 nullpointerexception ***, 错误行： " + line);
//			}
			catch (Exception e) {
				// 允许错一行
				if (transErrorNum < 1) {
					sb.append(line + "\r\n");
					transErrorNum += 1;
				} else {
					e.printStackTrace();
					throw new RuntimeException(" *** 多次翻译失败 ***, 错误行：" + line);
				}

			}

		}
		scanner.close();

		// 若出错的次数大于2，则此次翻译为不通过，抛出异常，出错的原因可能是请求超时，或者翻译行的格式有问题
//		if (allowErrorNum > 2) {
//			throw new RuntimeException(" *** 多次翻译失败 ***");
//		}

		// 调用 google 翻译后存在一些不规则 md 格式替换
		return sb.toString().replace("！", "!")
				.replace("] [", "][")
				.replace("]（", "](")
				.replace("** [", "**[")
				.replace("） **", ")**")
				.replace("（", "(")
				.replace("）", ")")
				.replace("＃", "#");
	}

	/**
	 * <pre>Google翻譯</pre>
	 *
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String translate(final String text)
			throws Exception {
		InputStream is = null;
		Document doc = null;
		Element ele = null;
		try {
			// create URL string
			String url = MessageFormat.format(URL_TEMPLATE,
					URLEncoder.encode(ENGLISH + "|" + CHINESE, ENCODING),
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
		String text = " - http://quartz-scheduler.org/";
		try {
			System.out.println(translateStackoverflowText(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * <pre>Google翻譯: 簡中-->繁中</pre>
//	 *
//	 * @param text
//	 * @return
//	 * @throws Exception
//	 */
//	public static String cn2tw(final String text) throws Exception {
//		return translate(text, CHINESE, TAIWAN);
//	}
//
//	/**
//	 * <pre>Google翻譯: 繁中-->簡中</pre>
//	 *
//	 * @param text
//	 * @return
//	 * @throws Exception
//	 */
//	public static String tw2cn(final String text) throws Exception {
//		return translate(text, TAIWAN, CHINESE);
//	}
//
//	/**
//	 * <pre>Google翻譯: 英文-->繁中</pre>
//	 *
//	 * @param text
//	 * @return
//	 * @throws Exception
//	 */
//	public static String en2tw(final String text) throws Exception {
//		return translate(text, ENGLISH, TAIWAN);
//	}
//
//	/**
//	 * <pre>Google翻譯: 繁中-->英文</pre>
//	 *
//	 * @param text
//	 * @return
//	 * @throws Exception
//	 */
//	public static String tw2en(final String text) throws Exception {
//		return translate(text, TAIWAN, ENGLISH);
//	}
//
//	/**
//	 * <pre>Google翻譯: 日文-->繁中</pre>
//	 *
//	 * @param text
//	 * @return
//	 * @throws Exception
//	 */
//	public static String jp2tw(final String text) throws Exception {
//		return translate(text, JAPAN, TAIWAN);
//	}
//
//	/**
//	 * <pre>Google翻譯: 繁中-->日</pre>
//	 *
//	 * @param text
//	 * @return
//	 * @throws Exception
//	 */
//	public static String tw2jp(final String text) throws Exception {
//		return translate(text, TAIWAN, JAPAN);
//	}
}
