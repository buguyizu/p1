package info.yinhua.core.data.mapper;

import info.yinhua.core.data.model.MConfig;

public interface IMConfigDao {
	MConfig select(int id);
	void insert(MConfig model);
	void insertWithId(MConfig model);
	void update(MConfig model);
}
