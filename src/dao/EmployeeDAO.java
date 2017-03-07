/*
 * EmployeeDao.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao;

import java.util.List;

import entity.Employee;

/**
 * Dao接口
 * 抛出的异常不要使用诸如 SQLException 的异常否则，
 * 此接口实现类只能实现jdbc方式，而不能使用如hibernate之类的框架
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public interface EmployeeDAO {
	public List<Employee> findAll() throws Exception;
	public Employee findById(long id) throws Exception;
	public void delete(long id) throws Exception;
	public void save(Employee emp) throws Exception;
	public void update(Employee emp) throws Exception;
}
