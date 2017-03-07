/*
 * TestMain.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package test;

import dao.EmployeeDAO;
import dao.impl.EmployeeDAOJdbcImpl;
import util.ConfigUtil;
import util.DAOFactory;
import util.DBUtil;

/**
 * 测试类
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class TestMain {
	public static void main(String[] args) throws Exception {
		//dbutil
		System.out.println(DBUtil.getConnect());
		//dao
		EmployeeDAO dao = new EmployeeDAOJdbcImpl();
		System.out.println(dao.findById(1).getName());
		//测试properies
		System.out.println(ConfigUtil.getInstance("EmployeeDAO"));
		//使用反射创建实例
		System.out.println(((EmployeeDAO)DAOFactory.getInstance("EmployeeDAO")).findById(1).getName());
	}
}
