package com.hand.SpringTransaction.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hand.SpringTransaction.entity.Course;

public class CourseMapper implements RowMapper{
     public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Course course=new Course();
    	course.setId(rs.getInt("id"));
    	course.setSid(rs.getInt("student_id"));
    	course.setCourse(rs.getString("course"));
    	return course;
    }
}
