package com.hand.SpringTransaction;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.SpringTransaction.dao.StudentDAO;
import com.hand.SpringTransaction.entity.Course;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
    	StudentDAO studentDAO=(StudentDAO) context.getBean("studentDAO");
//    	studentDAO.create("rio", 25, "how to do a bj");
//    	studentDAO.create("nana", 1, "how to do a bj");
//    	studentDAO.create("asina", 20, "how to do a bj");
    	List<Course> courses=studentDAO.getCourses();
    	for(int i=0;i<courses.size();i++){
    		System.out.println(courses.get(i).getSid()+"  "+courses.get(i).getCourse());
    	}
    }
}
