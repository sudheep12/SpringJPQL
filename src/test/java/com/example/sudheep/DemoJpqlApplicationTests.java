package com.example.sudheep;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.sudheep.Sturepository.StudentRepository;
import com.example.sudheep.stuentity.Student;

@SpringBootTest
class DemoJpqlApplicationTests {

	@Autowired
	StudentRepository stuRepos;
	
	@Test
	void testFindAll()
	{
		List<Student> students = stuRepos.findAllStudents();
		students.forEach(student -> System.out.print(student.getId() + "   " + student.getFirstname() + "   " + student.getLastname() + " \n "));
	}
	@Test
	void testPartialSelect()
	{
		List<Object[]> list = stuRepos.findAllStudentsPartialRecords();
		list.forEach(l -> System.out.println(l[0] + " " + l[1]));
	}
	
	@Test
	void testFindByFirstName()
	{
		List<Student> students = stuRepos.findAllStudentsByfirstName("sai");
		students.forEach(student -> System.out.print(student.getId() + "   " + student.getFirstname() + "   " + student.getLastname() + " \n "));
	}
	
	@Test
	void testBetweenIds()
	{
		List<Student> students = stuRepos.findAllStudentsBetweenScores(2, 5);
		students.forEach(student -> System.out.print(student.getId() + "   " + student.getFirstname() + "   " + student.getLastname() + " \n "));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void testDeleteByFirstName()
	{
		stuRepos.deleteByFirstName("alex");
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void testInsert()
	{
		stuRepos.insertRecord(1,"alex","zelikovsky");
	}
	
	
	
	@Test
	void testFindAllWithPaging()
	{
		Pageable pageable = PageRequest.of(0, 3);
		List<Student> students = stuRepos.findAllStudents(pageable);
		students.forEach(student -> System.out.print(student.getId() + "   " + student.getFirstname() + "   " + student.getLastname() + " \n "));
	}

	@Test
	void testFindAllWithPagingandSorting()
	{
		Pageable pageable = PageRequest.of(1, 3, Sort.by("Id").descending());
		List<Student> students = stuRepos.findAllStudents(pageable);
		students.forEach(student -> System.out.print(student.getId() + "   " + student.getFirstname() + "   " + student.getLastname() + " \n "));
	}
}
