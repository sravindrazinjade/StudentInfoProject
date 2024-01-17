package com.example.task.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.models.StudentInfo;
import com.example.task.service.StudentInfoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin("*")
@RequestMapping("StudentInfo")
public class StudentInfoController {

	@Autowired
	private StudentInfoService studentInfoService;
// Add student Info
	@PostMapping("/addStudentInfo")
	public ResponseEntity<StudentInfo> addStudentInfo(@RequestBody StudentInfo studentInfo) {
		try {
			StudentInfo stuinfo = studentInfoService.addStudentInfo(studentInfo);
			return new ResponseEntity<StudentInfo>(stuinfo, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
// Find All Student List 
	@GetMapping("/findAllStudentInfo")
	public ResponseEntity<List<StudentInfo>> findAllStudentInfo() {
		try {
			List<StudentInfo> stuinfoList = studentInfoService.findAllStudentInfoStudentInfo();
			return new ResponseEntity<List<StudentInfo>>(stuinfoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
// Find Single Record using StudentInfo Id
	@GetMapping("/findStudentInfoById/{id}")
	public ResponseEntity<StudentInfo> findStudentInfoById(@PathVariable("id") Integer id) {
		try {
			StudentInfo studentinfo = studentInfoService.findStudentInfoById(id);
			return new ResponseEntity<StudentInfo>(studentinfo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
// Update Student Info Using ID
	@PutMapping("/updateStudentInfo/{id}")
	public ResponseEntity<Optional<StudentInfo>> updateStudentInfo(@RequestBody StudentInfo studentinfo,
			@PathVariable("id") Integer id) {
		try {
			Optional<StudentInfo> stuinfo = studentInfoService.updateStudentInfo(studentinfo, id);
			return new ResponseEntity<Optional<StudentInfo>>(stuinfo, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
//Delete the Student Info Using Id
	@DeleteMapping("/deleteStudentInfo/{id}")
	public ResponseEntity<String> deleteStudentInfoById(@PathVariable("id") Integer id) {
		try {
			StudentInfo deletedStudentInfo = studentInfoService.deleteStudentInfoById(id);
			return ResponseEntity.ok("StudentInfo Deleted Successfully");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID " + id + " not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error  for deleting student info");
		}
	}
}
