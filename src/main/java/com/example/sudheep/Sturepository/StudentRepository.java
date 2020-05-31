package com.example.sudheep.Sturepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.example.sudheep.stuentity.Student;

@Component
public interface StudentRepository extends CrudRepository< Student, Integer> 
{
	@Query("from Student")
	List<Student> findAllStudents();
	
	@Query("select st.firstname, st.lastname from Student st")
	List<Object[]> findAllStudentsPartialRecords();
	
	@Query("from Student where firstname=:fname")
	List<Student> findAllStudentsByfirstName(@Param("fname") String name);
	
	@Query("from Student where id >=:first and id <=:second")
	List<Student> findAllStudentsBetweenScores(@Param("first") int a, @Param("second") int b);
	
	@Modifying
	@Query("delete from Student where firstname =:fname")
	void deleteByFirstName(@Param("fname") String name);
	
	@Modifying
	@Query(value="insert into Student (id,firstname,lastname) values(:id, :fname, :lname)", nativeQuery = true)
	void insertRecord(@Param("id") int id  ,@Param("fname") String name1, @Param("lname") String name2);
	
	
	@Query("from Student")
	List<Student> findAllStudents(Pageable pageable);
}
