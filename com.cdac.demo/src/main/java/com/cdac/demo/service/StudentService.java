package com.cdac.demo.service;

import java.util.List;

import com.cdac.demo.model.ServerResponse;
import com.cdac.demo.model.Student;
import com.cdac.demo.repo.StudentRepo;

public interface StudentService {

	public List<Student> getStudent(StudentRepo sRepo);
	public Student getStudent(StudentRepo sRepo,int id);
	public ServerResponse addStudent(StudentRepo sRepo,Student student);
	
	public List<Student> getStudentByCity(StudentRepo sRepo,String city);
	public ServerResponse deleteStudent(StudentRepo sRepo,int id);
	public ServerResponse updateStudent(StudentRepo sRepo,Student stu);
	
	public ServerResponse byName(StudentRepo studentRepo,String name);
}
