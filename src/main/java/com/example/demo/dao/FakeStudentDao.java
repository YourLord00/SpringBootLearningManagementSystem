package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeStudentDao implements StudentDao{

    private static List<Student> DataBase = new ArrayList<>();

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        for (Student s : DataBase) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Student> selectAllStudent() {
        return DataBase;
    }

    @Override
    public int insertStudent(Student student) {
        UUID id = UUID.randomUUID();
        DataBase.add(new Student(id, student.getName()));
        return 1;
    }

    @Override
    public int updateStudent(Student student) {

//        Optional<Student> optinalStudent = selectStudentById(student.getId());
//        if (!optinalStudent.isPresent()) {
//            return -1;
//        }
        int indexToUpdate = -1;
        for (int i = 0; i < DataBase.size(); i++) {
            if (student.getId().equals(DataBase.get(i).getId())) {
                indexToUpdate = i;
                break;
            }
        }
        if (indexToUpdate < 0) {
            return -1;
        }
        DataBase.set(indexToUpdate, student);
        return 1;
    }

    @Override
    public int deleteStudent(UUID id) {
        Optional<Student> optinalStudent = selectStudentById(id);
        if (!optinalStudent.isPresent()) {
            return -1;
        }
        DataBase.remove(optinalStudent.get());
        return 1;
    }

}
