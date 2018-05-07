package fzj.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

public class JDBCSelectTest {
	
	//设计一个方法，进行分页查询
	@Test
	public void ListByLimit() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入想要看的页码：");
		int input = sc.nextInt();
		
		//每页显示5行
		int count = 5;
		//每页起始位置
		int start = (input - 1) * count;
		
		//sc.close();
		
		String sql = "select * from hero limit " + start+" " +"," + count;
		
		try(
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
			Statement s = conn.createStatement();
				
		){
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				System.out.println("姓名:"+ rs.getString("name"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取总数
	@Test
	public void GetCount() {
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(
		
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
			Statement s = conn.createStatement();
		){
			String sql = "Select count(*) from hero";
			
			ResultSet rs = s.executeQuery(sql);
			int total = 0;
			while(rs.next()) {
				total = rs.getInt(1);
				System.out.println("总计："+total+"条数据");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//SQL语句判断账号密码是否正确
	//判断账号密码的正确方式是根据账号和密码到表中去找数据，如果有数据，就表明密码正确了，如果没数据，就表明密码错误。
	@Test
	public void IsLogin() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
			Statement s = conn.createStatement();
		){
			String sql = "select * from user where username='tom' and password ='123'";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				System.out.println("登录成功");
			}else {
				System.out.println("登录失败");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//executeQuery 执行SQL查询语句
	//可以用列名，也可以用列号
	//注意： 在取第二列的数据的时候，用的是rs.get(2) ，而不是get(1). 这个是整个Java自带的api里唯二的地方，使用基1的，即2就代表第二个。
	//另一个地方是在PreparedStatement这里
	@Test
	public void ExecuteQueryDemo() {
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
			Statement s = conn.createStatement();
		){
			String sql = "select * from hero";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				System.out.println("姓名："+rs.getString("name") +"-"+"武力值："+rs.getInt(3));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}