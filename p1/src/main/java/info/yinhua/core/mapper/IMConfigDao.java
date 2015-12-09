package info.yinhua.core.mapper;

import info.yinhua.core.db.model.MConfig;

public interface IMConfigDao {
	MConfig select(int id);
	void insert(MConfig model);
	void insertWithId(MConfig model);
	void update(MConfig model);
}
