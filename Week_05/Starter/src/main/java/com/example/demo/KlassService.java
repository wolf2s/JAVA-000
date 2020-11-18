package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlassService {

    List<Student> students;

    public List<Student> getStudents() {
        if(students==null){
            students = new ArrayList<>();
        }
        return students;
    }

    public void dong() {
        System.out.println(this.getStudents());
    }

}
