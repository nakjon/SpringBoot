package com.cdac.demo.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdac.demo.model.ServerResponse;
import com.cdac.demo.model.Student;
import com.cdac.demo.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	List<Student> slist;
	@Override
	public List<Student> getStudent(StudentRepo sRepo) {
		
		slist=new LinkedList<Student>();
		Iterable<Student> itr=sRepo.findAll();
		Iterator<Student> itrIterator=itr.iterator();
		while(itrIterator.hasNext()) {
			Student s=itrIterator.next();
			slist.add(s);
		}
		return slist;
	}

	@Override
	public Student getStudent(StudentRepo sRepo, int id) {
		
		Optional<Student> optional=sRepo.findById(id);
		Student s= optional.get();
		return s;
	}

	@Override
	public ServerResponse addStudent(StudentRepo sRepo, Student student) {
		
		sRepo.save(student);
		ServerResponse sr=new ServerResponse();
		sr.setStatus(200);
		sr.setMessage("record added succesfully");
		
		return sr;
	}

	@Override
	public List<Student> getStudentByCity(StudentRepo sRepo, String city) {
		List<Student>  mylist=new  LinkedList<Student>();
		slist=getStudent(sRepo);
		
		for(Student s:slist) {
			if (s.getAddress().equalsIgnoreCase(city)) {
				mylist.add(s);
			}
		}
		return mylist;
	}

	@Override
	public ServerResponse deleteStudent(StudentRepo sRepo, int id) {
		Student s=getStudent(sRepo, id);
		ServerResponse sr=new ServerResponse();
		if (s!=null) {
			sRepo.delete(s);
			sr.setMessage("record deleted succesfully");
			sr.setStatus(200);
		}else {
			sr.setMessage("no record found to be deleted");
			sr.setStatus(400);
		}
		return sr;
	}

	@Override
	public ServerResponse updateStudent(StudentRepo sRepo, Student stu) {
		ServerResponse sr=new ServerResponse();
		if(stu.getSid()==0) {
			sr.setMessage("please provide a student id");
			sr.setStatus(400);
		}
		else {
			Student s=getStudent(sRepo, stu.getSid());
			if (s!=null) {
				s.setAddress(stu.getAddress());
				s.setAge(stu.getAge());
				sRepo.save(s);
				sr.setMessage("Record updated");
				sr.setStatus(200);
			}else {
				sr.setMessage("Record not updated");
				sr.setStatus(400);
			}
		}
		return sr;
	}

	@Override
	public ServerResponse byName(StudentRepo studentRepo, String name) {
		List<Student> record=studentRepo.getStudentByName(name);
		ServerResponse sr=new ServerResponse();
		if (record.size()!=0) {
			sr.setList(record);
			sr.setMessage("record found");
			sr.setStatus(200);
		}
		else {
			sr.setMessage("record not found");
			sr.setStatus(400);
		}
		return sr;
	}

}
