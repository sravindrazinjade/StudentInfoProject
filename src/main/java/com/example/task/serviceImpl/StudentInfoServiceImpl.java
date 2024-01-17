package com.example.task.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.models.StudentInfo;
import com.example.task.repository.StudentInfoRepository;
import com.example.task.service.StudentInfoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

	@Autowired
	private StudentInfoRepository studentInfoRepository;
// Add Student Info in database
	@Override
	public StudentInfo addStudentInfo(StudentInfo studentInfo) {

		return studentInfoRepository.save(studentInfo);
	}
// find All StudentInfo List
	@Override
	public List<StudentInfo> findAllStudentInfoStudentInfo() {

		return studentInfoRepository.findAll();
	}
//update the Student info using id if required update by name then we can do like 
	@Override
	public Optional<StudentInfo> updateStudentInfo(StudentInfo studentinfo, Integer id) {
		Optional<StudentInfo> stu = studentInfoRepository.findById(id);
		if (stu.isPresent()) {
			StudentInfo existingStudent = stu.get();

			existingStudent.setName(studentinfo.getName());
			existingStudent.setRollNo(studentinfo.getRollNo());
			existingStudent.setDivision(studentinfo.getDivision());
			existingStudent.setAddress(studentinfo.getAddress());
			existingStudent.setGender(studentinfo.getGender());
			studentInfoRepository.save(existingStudent);

			return Optional.of(existingStudent);
		} else {
			return Optional.empty();
		}

	}
// Delete the Student Info by Id
	@Override
	public StudentInfo deleteStudentInfoById(Integer id) {
		Optional<StudentInfo> optionalStudent = studentInfoRepository.findById(id);

		if (optionalStudent.isPresent()) {
			StudentInfo studentDelete = optionalStudent.get();
			studentInfoRepository.deleteById(id);
			return studentDelete;
		} else {
			throw new EntityNotFoundException("Student with ID " + id + " not found");
		}
	}
// find by ID Student Info
	@Override
	public StudentInfo findStudentInfoById(Integer id) {
		Optional<StudentInfo> optionalStudent = studentInfoRepository.findById(id);

		if (optionalStudent.isPresent()) {
			StudentInfo studentinfo = optionalStudent.get();
			studentInfoRepository.findById(id);
			return studentinfo;
		} else {
			throw new EntityNotFoundException("Student with ID " + id + " not found");
		}
	}
}