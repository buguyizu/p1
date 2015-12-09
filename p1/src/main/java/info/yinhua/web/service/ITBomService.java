package info.yinhua.web.service;

import info.yinhua.web.bean.BomBean;
import info.yinhua.web.bean.PageBomBean;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

public interface ITBomService {
	
	@Secured("ROLE_USER")
	void search(PageBomBean p);
}
