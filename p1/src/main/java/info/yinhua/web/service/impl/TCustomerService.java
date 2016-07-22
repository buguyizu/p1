package info.yinhua.web.service.impl;

import info.yinhua.core.data.mapper.ITCustomerDao;
import info.yinhua.core.data.model.TCustomer;
import info.yinhua.web.service.ITCustomerService;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCustomerService implements ITCustomerService {
	
	@Autowired
	private ITCustomerDao tCustomerDao;

	public void save() {
		TCustomer model = new TCustomer();
//		try {
//			model.setId(Integer.parseInt(License.getCorpNo()));
//			model.setName(License.getCorpNm());
//			model.setBk(random(0));
//			model.setDateStart(DateUtils.parseDate(License.getLicenseStart(), "yyyy/MM/dd", "yyyy-MM-dd"));
//			model.setDateEnd(DateUtils.parseDate(License.getLicenseExpiry(), "yyyy/MM/dd", "yyyy-MM-dd"));
//			model.setMac(License.getServerMac());
//			model.setPwPg(random(1));
//			model.setPwYh(random(2));
			model.setPwPg("3dd8nibzlb");
			model.setPwYh("3dd8niclp7");
//		} catch (ParseException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
		
		tCustomerDao.insert(model);
	}
	
	private String random(int type) {
		String ret = Long.toString(System.nanoTime());
		BigInteger i = new BigInteger(ret);
		switch (type) {
		case 1:
			//PW_PG
		case 2:
			//PW_YH
			Random r = new Random();
			i = i.add(BigInteger.valueOf(r.nextLong()));
			ret = i.toString(Character.MAX_RADIX);
			break;

		default:
			//PW_BK
			BigInteger j = i.remainder(new BigInteger("100000000000"));//12位
			ret = StringUtils.leftPad(j.toString(), 12, '9') ;
			break;
		}
		
		return ret;
	}

}
