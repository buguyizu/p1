package info.yinhua.web.service.impl;

import info.yinhua.core.data.model.MCode;
import info.yinhua.core.data.mapper.MCodeMapper;
import info.yinhua.web.service.IMCodeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MCodeService implements IMCodeService {

	@Autowired
	private MCodeMapper mCodeMapper;
	
	public MCode getCode(String type, String cd) {
		if (type == null || cd == null)
			return null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("f_type", type);
		map.put("f_code", cd);
		List<MCode> list = mCodeMapper.getList(map);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public List<MCode> getList(String type) {
		if (type == null)
			return null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("f_type", type);
		return mCodeMapper.getList(map);
	}
}
