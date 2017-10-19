package site.exception.service;

import com.github.pagehelper.PageInfo;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import site.exception.common.pojo.ServerResponse;
import site.exception.pojo.User;
import site.exception.pojo.vo.QuestionVo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
public interface UserService {

	User findByUserNameAndPwd(User user);

}
