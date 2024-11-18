package service;

import entity.Address;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;  // This is the correct one for pagination
//import org.springframework.data.domain.PageRequest;  // For creating PageRequest
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.AddressRepository;
import repository.StudentRepository;
import request.CreateStudentRequest;
import request.InQueryRequest;
import request.UpdateStudentRequest;
//import java.awt.print.Pageable;
import java.util.List;

@Service
public class studentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
            //the above statement is == to SELECT * FROM student
    }
    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student =  new Student(createStudentRequest);
        Address address = new Address();
        address.setCity(createStudentRequest.getCity());
        address.setState(createStudentRequest.getState());
        address = addressRepository.save(address);
        student.setAddress(address);
        student = studentRepository.save(student);
        //System.out.println("Generated Student ID: " + student.getId());
        return student;
    }
    public Student updateStudent (UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();
        if(updateStudentRequest.getFirstName()!= null && !updateStudentRequest.getFirstName().isEmpty()){
            student.setFirstName((updateStudentRequest.getFirstName()));
        }
        if(updateStudentRequest.getLastName()!= null && !updateStudentRequest.getLastName().isEmpty()){
            student.setLastName((updateStudentRequest.getLastName()));
        }
        if(updateStudentRequest.getEmailId()!= null && !updateStudentRequest.getEmailId().isEmpty()){
            student.setEmailId((updateStudentRequest.getEmailId()));
        }
        student = studentRepository.save(student);
        return student;
    }

    public String deleteStudent(int id){
        studentRepository.deleteById(id);
        return "Student has been deleted succesfully";
    }

    public List<Student> getByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }

    /* public List<Student> getById (int id){
        return studentRepository.findById(id);
    }--> It's an error because ID is auto generated/ incremented */

    public List<Student> getByEmailId (String emailId) {
        return studentRepository.findByEmailId(emailId);
    }

    public Student getByFirstNameAndLastName(String firstName, String lastName){
        //return studentRepository.findByFirstNameAndLastName(firstName,lastName);
        return studentRepository.getByFirstNameAndLastName(firstName,lastName);
    }

    public Student getByFirstNameAndEmailId(String firstName, String emailId){
        return studentRepository.findByFirstNameAndEmailId(firstName,emailId);
    }

    public List<Student> getByFirstNameOrLastName(String firstName, String lastName){
        return studentRepository.findByFirstNameOrLastName(firstName,lastName);
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest){
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    public List<Student> getByLastNameIn(InQueryRequest inQueryRequest){
        return studentRepository.findByLastNameIn(inQueryRequest.getLastNames());
    }

    public List<Student> getByPagination(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getBySorting(){
        Sort sort = Sort.by(Sort.Direction.DESC, "firstName","lastName");
        return studentRepository.findAll(sort);
    }

    public List<Student> getBySortingAsc(){
        Sort sort = Sort.by(Sort.Direction.ASC,"lastName");
        return studentRepository.findAll(sort);
    }

    public List<Student> getByLike(String firstName){
        return studentRepository.findByFirstNameContains(firstName);
    }

    public List<Student> getByStartsWith(String lastName){
        return studentRepository.findByLastNameStartsWith(lastName);
    }

    public Integer updateFirstname(int id, String firstName){
        return studentRepository.updateFirstname(id, firstName);
    }

    public Integer deletebyFirstName(String firstName){
        return studentRepository.deletebyFirstName(firstName);
    }
}
