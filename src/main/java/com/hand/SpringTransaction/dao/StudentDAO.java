package com.hand.SpringTransaction.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hand.SpringTransaction.entity.Course;
import com.hand.SpringTransaction.entity.Student;
import com.hand.SpringTransaction.mapper.CourseMapper;
import com.hand.SpringTransaction.mapper.StudentMapper;

public class StudentDAO {
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;
  private PlatformTransactionManager transactionManager;
 
  public void setDataSource(DataSource dataSource){
	  this.dataSource=dataSource;
	  this.jdbcTemplateObject=new JdbcTemplate(dataSource);
  }
  
  public void setTransactionManager(PlatformTransactionManager transactionManager){
	  this.transactionManager=transactionManager;
  }
  
  public void create(String name,Integer age,String course){
	  TransactionDefinition def=new DefaultTransactionDefinition();
	  TransactionStatus status=transactionManager.getTransaction(def);
	  try {
		  
		String sql1="insert into student(name,age) values(?,?)";
		  jdbcTemplateObject.update(sql1,name,age);
		 
		  String sql2="select MAX(id) from student";
		  int sid=jdbcTemplateObject.queryForInt(sql2);
		  
		  String sql3="insert into course(student_id,course) values(?,?)";
		  jdbcTemplateObject.update(sql3,sid,course);
		  
		  transactionManager.commit(status);
		  
		  System.out.println("student "+name+" created successfully");
	} catch (DataAccessException e) {
		transactionManager.rollback(status);
		throw e;
	}
  }
  
  public Student getStudent(Integer id){
	  String sql="select * from student where id=?";
	  Student student=jdbcTemplateObject.queryForObject(sql, new Object[]{id},new StudentMapper());
	  return student;
  }
  public List<Student> getStudents(){
	  String sql="select * from student";
	  List<Student> students=jdbcTemplateObject.query(sql, new StudentMapper());
	  return students;
  }
  public void delete (Integer id){
	  String sql="delete from student where id=?";
	  jdbcTemplateObject.update(sql,id);
	  System.out.println("student "+id+" deleted successfully");
  }
  public void updateAge(Integer id,Integer age){
	  String sql="update student set age=? where id=?";
	  jdbcTemplateObject.update(sql,age,id);
	  System.out.println("student "+id+" updated successfully");
  }
  public List<Course> getCourses() { 
	  String SQL = "select * from student, course where student.id=course.student_id"; 
	  List <Course> studentMarks = jdbcTemplateObject.query(SQL, new CourseMapper());
      return studentMarks; 
}
}
