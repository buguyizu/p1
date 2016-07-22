package info.yinhua.core.data.mapper;

import info.yinhua.core.data.model.TCustomer;

public interface ITCustomerDao {
	TCustomer select(int id);
	void insert(TCustomer model);
	void update(TCustomer model);
}
