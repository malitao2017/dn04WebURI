/*
 * EmployeeDaoHibernateImpl.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao.impl;

import java.util.List;

import dao.EmployeeDAO;
import entity.Employee;

/**
 * hibernate 的实现
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@Override
	public List<Employee> findAll() throws Exception {
		return null;
	}

	@Override
	public Employee findById(long id) throws Exception {
		return null;
	}

	@Override
	public void delete(long id) throws Exception {

	}

	@Override
	public void save(Employee emp) throws Exception {

	}

	@Override
	public void update(Employee emp) throws Exception {

	}

}
