package info.yinhua.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

}
