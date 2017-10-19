package site.exception.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;
import site.exception.pojo.Tag;
import site.exception.pojo.vo.QuestionVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/6/15.
 */
public class SpiderUtil {

	/**
	 * 爬取指定标签某页的50个问题
	 *
	 * @param tagName
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public static List<QuestionVo> splideQuestionByTagName(String tagName, int pageNum) throws Exception {

		List<QuestionVo> voList = new ArrayList<>();

		// votes : 根据投票数
		Document document = Jsoup.connect("https://stackoverflow.com/questions/tagged/" + tagName + "?page=" + pageNum + "&sort=votes&pageSize=50")
				// 模拟火狐浏览器
				.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
				.get();
		Element main = document.getElementById("questions");
		Elements questions = main.select("div[class=question-summary]");

		if (questions == null || questions.size() == 0) {
			return null;
		}

		for (int i = 0; i < questions.size(); i++) {
			// 先判断这个问题是否有人回答，无则跳下一个问题
			String answerNumStr = questions.get(i).select("div[class=status unanswered]").select("strong").text();
			if (!StringUtils.isEmpty(answerNumStr)) {
				int answerNum = Integer.valueOf(answerNumStr);
				if (answerNum <= 0) {
					continue;
				}
			}

			QuestionVo questionVo = new QuestionVo();
			List<Tag> tagList = new ArrayList<>();

			Element element = questions.get(i);
			String origialId = element.attr("id").substring(17);
			// 获取到原始id
			questionVo.setOriginalId(Integer.valueOf(origialId));

			String voteUpNum = element.select("span[class=vote-count-post]").select("strong").text();
			if (StringUtils.isEmpty(voteUpNum)) {
				questionVo.setVoteUp(0);
			} else {
				questionVo.setVoteUp(Integer.valueOf(voteUpNum));
			}

			String link = element.select("div[class=summary]").select("h3").select("a").attr("href");
			questionVo.setOriginalLink("https://stackoverflow.com/" + link);

			String title = element.select("div[class=summary]").select("h3").select("a").text();
			questionVo.setTitle(title);

			// 组装标签数据
			Elements tags = element.select("a[class=post-tag]");
			for (int t = 0; t < tags.size(); t++) {
				Tag tag = new Tag();
				tag.setName(tags.get(t).text());
				tagList.add(tag);
			}
			questionVo.setTags(tagList);
			voList.add(questionVo);
		}
		return voList;
	}

	/**
	 * 爬取问题的标签
	 *
	 * @return
	 * @throws Exception
	 */
	public static List<Tag> splideQuestionTag(String link) throws Exception {

		List<QuestionVo> voList = new ArrayList<>();

		Document document = Jsoup.connect(link)
				// 模拟火狐浏览器
				.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
				.get();
		Elements main = document.select("div[class=post-taglist]");

		Elements tags = main.select("a[class=post-tag]");


		List<Tag> list = new ArrayList<>();
		for (int i = 0; i < tags.size(); i++) {
			Tag tag = new Tag();
			tag.setName(tags.get(i).text());
			list.add(tag);
		}
//		Elements questions = main.select("div[class=question-summary]");

		return list;
//		if (questions == null || questions.size() == 0) {
//			return null;
//		}
//
//		for (int i = 0; i < questions.size(); i++) {
//			// 先判断这个问题是否有人回答，无则跳下一个问题
//			String answerNumStr = questions.get(i).select("div[class=status unanswered]").select("strong").text();
//			if (!StringUtils.isEmpty(answerNumStr)) {
//				int answerNum = Integer.valueOf(answerNumStr);
//				if (answerNum <= 0) {
//					continue;
//				}
//			}
//
//
//			QuestionVo questionVo = new QuestionVo();
//			List<Tag> tagList = new ArrayList<>();
//
//			Element element = questions.get(i);
//			String origialId = element.attr("id").substring(17);
//			// 获取到原始id
//			questionVo.setOriginalId(Integer.valueOf(origialId));
//
//			String voteUpNum = element.select("span[class=vote-count-post]").select("strong").text();
//			questionVo.setVoteUp(Integer.valueOf(voteUpNum));
//
//			String link = element.select("div[class=summary]").select("h3").select("a").attr("href");
//			questionVo.setOriginalLink("https://stackoverflow.com/" + link);
//
//			String title = element.select("div[class=summary]").select("h3").select("a").text();
//			questionVo.setTitle(title);
//
//			// 组装标签数据
//			Elements tags = element.select("a[class=post-tag]");
//			for (int t = 0; t < tags.size(); t++) {
//				Tag tag = new Tag();
//				tag.setName(tags.get(t).text());
//				tagList.add(tag);
//			}
//			questionVo.setTags(tagList);
//			voList.add(questionVo);
//		}
//		return voList;
	}

	public static void main(String[] args) {
		try {
//			splideQuestionByTagName("kotlin", 63);
			splideQuestionTag("https://stackoverflow.com//questions/34950111/prefer-to-run-the-dagger-processor-over-that-class-instead-in-kotlin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
