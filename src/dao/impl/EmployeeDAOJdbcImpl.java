/*
 * EmployeeDaoImpl.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import dao.EmployeeDAO;
import entity.Employee;

/**
 * jdbc实现DAO接口
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class EmployeeDAOJdbcImpl implements EmployeeDAO {
	@Override
	public List<Employee> findAll() throws Exception {
		Connection con = DBUtil.getConnect();
		Statement sta = con.createStatement();
		ResultSet rs = sta.executeQuery("select * from t_emp");
		List<Employee> list = new ArrayList<Employee>();
		while(rs.next()){
			Employee emp = new Employee();
			emp.setId(rs.getLong("id"));
			emp.setName(rs.getString("name"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setAge(rs.getInt("age"));
			list.add(emp);
		}
		DBUtil.close(con);
		return list;
	}
	@Override
	public Employee findById(long id) throws Exception {
		Connection con = DBUtil.getConnect();
		PreparedStatement sta = con.prepareStatement("select * from t_emp where id=?");
		sta.setLong(1, id);
		ResultSet rs = sta.executeQuery();
		Employee emp = null; 
		while(rs.next()){
			emp = new Employee();
			emp.setId(id);
			emp.setName(rs.getString("name"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setAge(rs.getInt("age"));
		}
		DBUtil.close(con);
		return emp;
	}
	@Override
	public void delete(long id) throws Exception {
		Connection con = DBUtil.getConnect();
		PreparedStatement sta = con.prepareStatement("delete from t_emp where id = ?");
		sta.setLong(1, id);
		sta.executeUpdate();
		DBUtil.close(con);
	}
	@Override
	public void save(Employee emp) throws Exception {
		Connection con = DBUtil.getConnect();
		PreparedStatement sta = con.prepareStatement("insert into t_emp(name,salary,age) values(?,?,?)");
		sta.setString(1, emp.getName());
		sta.setDouble(2, emp.getSalary());
		sta.setInt(3, emp.getAge());
		sta.executeUpdate();
		DBUtil.close(con);
	}
	@Override
	public void update(Employee emp) throws Exception {
		Connection con = DBUtil.getConnect();
		PreparedStatement sta = con.prepareStatement("update t_emp set name=?,salary=?,age=? where id=?");
		sta.setString(1, emp.getName());
		sta.setDouble(2, emp.getSalary());
		sta.setInt(3, emp.getAge());
		sta.setLong(4, emp.getId());
		sta.executeUpdate();
		DBUtil.close(con);
	}
}
