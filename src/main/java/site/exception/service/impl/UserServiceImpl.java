package site.exception.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.rjeschke.txtmark.Processor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.exception.common.pojo.ServerResponse;
import site.exception.dao.*;
import site.exception.pojo.Tag;
import site.exception.pojo.User;
import site.exception.pojo.vo.*;
import site.exception.service.QuestionService;
import site.exception.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUserNameAndPwd(User user) {
		return userMapper.selectByUserNameAndPwd(user.getUsername(), DigestUtils.md5Hex(user.getPassword()));
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("fdsfds"));
	}
}
