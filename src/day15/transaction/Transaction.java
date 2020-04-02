package day15.transaction;

public interface Transaction {
	//事务开启
	void begin(); 
	//事务提交
	void commit();
	//事务的回滚
	void rollback();
}
