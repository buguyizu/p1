package info.yinhua.core.mapper;

import info.yinhua.core.db.model.TCustomer;

public interface ITCustomerDao {
	TCustomer select(int id);
	void insert(TCustomer model);
	void update(TCustomer model);
}
