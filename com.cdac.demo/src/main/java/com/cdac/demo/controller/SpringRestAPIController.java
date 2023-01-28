package com.cdac.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.demo.model.ServerResponse;
import com.cdac.demo.model.Student;
import com.cdac.demo.repo.StudentRepo;
import com.cdac.demo.service.StudentServiceImpl;

@RestController
public class SpringRestAPIController {
       @Autowired
       private StudentRepo studentRepo;
       @Autowired
       private StudentServiceImpl serviceImpl;
       
       @GetMapping("/")
       public String home() {
    	   return "home";
       }
       
       @GetMapping("/getstudentlist")
       public List<Student> getStudentList(){
    	   return serviceImpl.getStudent(studentRepo);
       }
       
       @GetMapping("/getstudent/{sid}")
       public Student getStudent(@PathVariable String sid) {
    	   return serviceImpl.getStudent(studentRepo, Integer.parseInt(sid));
       }
       
       @PostMapping("/addstudent")
       public ServerResponse addStudent(@RequestBody Student student) {
    	   return serviceImpl.addStudent(studentRepo, student);
       }
       
       @GetMapping("/getstudentbycity/{city}")
       public List<Student> StudenByCity(@PathVariable String city){   
    	   return serviceImpl.getStudentByCity(studentRepo, city);  
       }
       
       @DeleteMapping("/deletestudent/{id}")
       public ServerResponse DeleteStudent(@PathVariable String id) {
    	   return serviceImpl.deleteStudent(studentRepo, Integer.parseInt(id));     
       }
       
       @PutMapping("/updatestudent")
       public ServerResponse UpdateStudent(@RequestBody Student student) {
    	   return serviceImpl.updateStudent(studentRepo, student);
       }
       
       @GetMapping("/getstudentbyname/{name}")
       public ServerResponse getStudentByName(@PathVariable String name){   
    	   return serviceImpl.byName(studentRepo, name);  
       }
       
       
       
}
