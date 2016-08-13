package com.hand.SpringTransaction.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hand.SpringTransaction.entity.Student;

public class StudentMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student=new Student();
		student.setAge(rs.getInt("age"));
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setSchool(rs.getString("school"));
		student.setScore(rs.getInt("score"));
		return student;
	}

}
