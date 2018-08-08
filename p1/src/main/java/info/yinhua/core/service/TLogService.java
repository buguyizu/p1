package info.yinhua.core.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import info.yinhua.core.CommonConst;
import info.yinhua.core.context.security.UserUtil;
import info.yinhua.core.data.mapper.TLogMapper;
import info.yinhua.core.data.model.TLog;

@Service
public class TLogService {

	@Autowired
	private TLogMapper logMapper;

	public void create(TLog log) {

		Assert.hasText(log.getLogType());
		log.setCreateUser(UserUtil.getUsername());
		logMapper.log(log);
	}


	public void out(CommonConst.LogType type, String username) {

		TLog log = new TLog();
		log.setLogType(type.type());
		log.setLogContent(type.content());

		Map<String, String> param = new HashMap<String, String>();
		param.put(CommonConst.LOG_PARAM_USERNAME, username);

		log.setLogParam(param.toString());
		create(log);
	}

	public void out(CommonConst.LogType type, String username, String authority) {

		TLog log = new TLog();
		log.setLogType(type.type());
		log.setLogContent(type.content());

		Map<String, String> param = new HashMap<String, String>();
		param.put(CommonConst.LOG_PARAM_USERNAME, username);
		param.put(CommonConst.LOG_PARAM_AUTHORITY, authority);

		log.setLogParam(param.toString());
		create(log);
	}
}
