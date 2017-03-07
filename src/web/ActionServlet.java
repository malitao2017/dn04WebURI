/*
 * ActionServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DAOFactory;
import dao.EmployeeDAO;
import entity.Employee;

/**
 * 业务处理的总逻辑
 * @author LT
 * @version 1.0, 2015年9月11日
 */
public class ActionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		//注意不是indexOf
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		if(path.equals("/list")){
			Connection con = null;
			try{
				EmployeeDAO dao = null;
				dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
				
				List<Employee> list = dao.findAll();
				out.println("<table border='1' width='60%' cellpadding='0' cellspacing='0'>"
						+ "<tr>"
							+ "<td>序号</td>"
							+ "<td>姓名</td>"
							+ "<td>工资</td>"
							+ "<td>年龄</td>"
							+ "<td>操作</td>"
						+ "</tr>");
				for(Employee emp :list){
					out.println("<tr>"
							+ "<td>"+emp.getId()+"</td>"
							+ "<td>"+emp.getName()+"</td>"
							+ "<td>"+emp.getSalary()+"</td>"
							+ "<td>"+emp.getAge()+"</td>"
							+ "<td><a href='del.do?id="+emp.getId()+"'>删除</a>&nbsp;"
							+ "<a href='load.do?id="+emp.getId()+"'>修改</a></td>"
							+ "</tr>");
				}
				out.println("</table>");
				out.println("<a href='addEmp.html' >添加雇员</a>");
				out.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
			}finally{
				if(con!=null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}else if(path.equals("/add")){
			try {
				String name = request.getParameter("name");
				double salary = Double.valueOf(request.getParameter("salary"));
				int age = Integer.valueOf(request.getParameter("age"));
				System.out.println("name:"+name);
				System.out.println("salaty:"+salary);
				System.out.println("age:"+age);
			
				EmployeeDAO dao = null;
				dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
				
				Employee emp = new Employee();
				emp.setName(name);
				emp.setSalary(salary);
				emp.setAge(age);
				dao.save(emp);
				//更改为重定向模式
				//重定向之前不能有out.close();
				response.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if(path.equals("/load")){
			long id = Long.parseLong(request.getParameter("id"));
			try{
				EmployeeDAO dao = null;
				dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
				
				Employee emp = dao.findById(id);
				out.println("<form action='modify.do?id="+id+"' method='post'>");
				String name = emp.getName();
				double salary = emp.getSalary();
				int age = emp.getAge();
				out.println("id:"+id+"<br/>");
				out.println("姓名:<input name='name' value='"+name+"'/><br/>");
				out.println("工资:<input name='salary' value='"+salary+"'/><br/>");
				out.println("年龄:<input name='age' value='"+age+"'/>");
				out.println("<input type='submit' value='提交'/>"
						+ "</form>");
				out.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if(path.equals("/modify")){
			long id = Long.parseLong(request.getParameter("id"));
			String name = request.getParameter("name");
			double salary = Double.parseDouble(request.getParameter("salary"));
			int age = Integer.parseInt(request.getParameter("age"));
			try{
				EmployeeDAO dao = null;
				dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
				
				Employee emp = new Employee();
				emp.setId(id);
				emp.setName(name);
				emp.setSalary(salary);
				emp.setAge(age);
				dao.update(emp);
				response.sendRedirect("list.do");
			}catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if(path.equals("/del")){
			long id = Long.parseLong(request.getParameter("id"));
			try{
				EmployeeDAO dao = null;
				dao = (EmployeeDAO) DAOFactory.getInstance("EmployeeDAO");
				dao.delete(id);
				response.sendRedirect("list.do");
			}catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
	}
}
