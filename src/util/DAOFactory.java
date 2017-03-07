/*
 * DaoFactory.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package util;

import dao.impl.EmployeeDAOJdbcImpl;

/**
 * DAO的生成工厂类
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class DAOFactory {
	/**
	 * 方式一:直接返回对象
	 * @param type
	 * @return
	 */
	public static Object getInstanceOld(String type){
		Object dao = null;
		if(type.equals("EmployeeDAO")){
			dao = new EmployeeDAOJdbcImpl();
//			dao = new EmployeeDaoHibernateImpl();
		}
		return dao;
	}
	/**
	 * 方式二、使用反射创建实例
	 */
	public static Object getInstance(String key){
		String method = ConfigUtil.getInstance(key);
		Object obj = null;
		try {
			//使用反射创建实例
			obj = Class.forName(method).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}





