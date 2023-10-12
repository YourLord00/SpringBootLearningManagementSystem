package com.example.studentmanagement.mapper;

import com.example.studentmanagement.model.Student;
import jakarta.persistence.criteria.From;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student where name LIKE #{name}")
    List<Student> getStudentsContainStrName(@Param("name") String name);

    // Select * from student where university_class_id in
    // (Select id from university_class where year = 2023 AND number = 1);
    @Select("Select * From student where university_class_id in" +
            "(Select id From university_class where year = #{year} AND number = #{number})")
    List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);
}
