package com.example.task.service;

import java.util.List;
import java.util.Optional;

import com.example.task.models.StudentInfo;

public interface StudentInfoService {

	StudentInfo addStudentInfo(StudentInfo studentInfo);

	List<StudentInfo> findAllStudentInfoStudentInfo();

	Optional<StudentInfo> updateStudentInfo(StudentInfo studentinfo, Integer id);

	StudentInfo deleteStudentInfoById(Integer id);

	StudentInfo findStudentInfoById(Integer id);

}
