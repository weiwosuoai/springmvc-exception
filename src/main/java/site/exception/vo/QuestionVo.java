package site.exception.vo;

import site.exception.pojo.Question;
import site.exception.pojo.QuestionTagMap;
import site.exception.pojo.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Allen on 2017/6/16.
 */
public class QuestionVo extends Question implements Serializable {
	private static final long serialVersionUID = 2807918490355436452L;

	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
