package info.yinhua.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import info.yinhua.core.data.mapper.TLogMapper;
import info.yinhua.core.data.model.TLog;

@Service
public class TLogService {

	@Autowired
	private TLogMapper logMapper;
	

	public void create(TLog log) {

		Assert.hasText(log.getLogType());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			if (auth.getPrincipal() instanceof String)
				log.setCreateUser((String) auth.getPrincipal());
			else if (auth.getPrincipal() instanceof UserDetails)
				log.setCreateUser(((UserDetails) auth.getPrincipal()).getUsername());
		}
		
		logMapper.log(log);
	}

}
