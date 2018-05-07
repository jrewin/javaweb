package fzj.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTest {

	//插入100个数据
	@Test
	public void Insert10K() {
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
			Statement s = conn.createStatement();
		){
			for(int i=0;i<100;i++) {
			
				String sql = "insert into hero values(null,'第"+ i +"号英雄',"+ i*10 +","+i*11+","+i*12+","+i*13+","+i*14+","+i*15+")";
				s.execute(sql);
			}
			System.out.println("100条插入完成！");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//使用try-with-resource的方式自动关闭连接
	//如果觉得上一步的关闭连接的方式很麻烦，可以参考关闭流 的方式，
	//使用try-with-resource的方式自动关闭连接，因为Connection和Statement都实现了AutoCloseable接口
	//另注：若try-with-resource报错，应调试项目右键properies-->java compiler -->改成高版本例如1.8
	@Test
	public void CloseWithTryResouect() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		try(
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");

			Statement s = conn.createStatement();
		){
			String sql = "insert into hero values(null,'赵云',96,70,69,90,70,86)";
			
			s.execute(sql);
			
			System.out.println("插入成功！");
			
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	//养成关闭资源的好习惯
	//先关闭statement
	//再关闭connection
	@Test
	public void CloseStatementAndConnection() {
		
		Connection conn = null;
		Statement s = null;
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
		
			s = conn.createStatement();
			
			String sql = "Insert into hero values(null,'张飞',98,62,35,62,26,80)";
			
			s.execute(sql);
			
			System.out.println("数据插入成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(s != null) {
				try {
					s.close();
					System.out.println("statement已关闭");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
					System.out.println("Connection已关闭");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//执行SQL语句
	@Test
	public void StatmentInsert() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//加上 ?characterEncoding=UTF-8 防止数据库出现乱码
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/servlet_demo?characterEncoding=UTF-8", "root", "root");
			
			Statement s = conn.createStatement();
			
			String sql = "Insert into hero values(null,'刘备',70,65,85,96,90,82)";
			
			s.execute(sql);
			
			System.out.println("数据插入成功！");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//创建Statement
	//Statement是用于执行SQL语句的，比如增加，删除
	@Test
	public void StatementTrain() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo",
					"root","root");
			
			// 注意：使用的是 java.sql.Statement
            // 不要不小心使用到： com.mysql.jdbc.Statement;
			Statement s = conn.createStatement();
			
			System.out.println("获取Statement：" + s);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//建立与数据库的连接
	@Test
	public void JDBCIsConnet2() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servlet_demo",
					"root", "root"); 
			
			System.out.println("连接成功！");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	// 初始化驱动
	@Test
	public void JDBCIsConnect1(){		
		try {
			//驱动类com.mysql.jdbc.Driver
            //就在 mysql-connector-java-5.0.8-bin.jar中
            //如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
			
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("数据库驱动加载成功！");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}