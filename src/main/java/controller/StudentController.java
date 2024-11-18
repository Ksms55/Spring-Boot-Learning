package controller;

//import org.springframework.beans.factory.annotation.Value;
import entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMethod;
//import response.studentResponse;
import repository.StudentRepository;
import request.CreateStudentRequest;
import request.InQueryRequest;
import request.UpdateStudentRequest;
import response.studentResponse;
import service.studentService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;


@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    studentService StudentService;

    @GetMapping("/getAll")
    /*
     public List<student> getAllStudents() {
     * return  studentService.getAllStudents();
    */
    public List<studentResponse> getAllStudents() {
        List<Student> studentList = StudentService.getAllStudents();
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @PostMapping("/create")
    public studentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Student student = StudentService.createStudent(createStudentRequest);
        return new studentResponse(student);
    }

    @PutMapping("/update")
    public studentResponse updateStudent (@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        Student student =  StudentService.updateStudent(updateStudentRequest);
        return new studentResponse(student);
    }

    /*@DeleteMapping("/delete")
    public String deleteStudent(@RequestParam int id){
        return StudentService.deleteStudent(id);
    }*/
    //--> this is using @RequestParam - allows you to extract parameters sent as part of the URL query string
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id){
        return StudentService.deleteStudent(id);
    }
    //--> this is using @PathVariable - it's commonly used in controller methods to access parameters passed as part of the URL in HTTP requests

    @GetMapping("/getByFirstName/{firstName}")
    public List<studentResponse> getByFirstName (@PathVariable String firstName){
        List<Student> studentList = StudentService.getByFirstName(firstName);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    /* @GetMapping("/getById/{id}")
    public List<studentResponse> getById (@PathVariable int id){
        List<Student> studentList = StudentService.getById(id);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
            return StudentResponseList;
    } --> Because Id is auto incremented */
    @GetMapping("/getByEmailId/{emailId}")
    public List<studentResponse> getByEmailId (@PathVariable String emailId) {
        List<Student> studentList = StudentService.getByEmailId(emailId);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }
    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public studentResponse getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        return new studentResponse(StudentService.getByFirstNameAndLastName(firstName,lastName));
    }

    @GetMapping("getByFirstNameAndEmailId/{firstName}/{emailId}")
    public studentResponse getByFirstNameAndEmailId(@PathVariable String firstName, @PathVariable String emailId){
        return new studentResponse(StudentService.getByFirstNameAndEmailId(firstName,emailId));
    }

    @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<studentResponse> getByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName){
        List<Student> studentList = StudentService.getByFirstNameOrLastName(firstName,lastName);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @GetMapping("getByFirstNameIn")
    public List<studentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
        List<Student> studentList = StudentService.getByFirstNameIn(inQueryRequest);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @GetMapping("/getByLastNameIn")
    public List<studentResponse> getByLastNameIn(@RequestBody InQueryRequest inQueryRequest){
        List<Student> studentList = StudentService.getByLastNameIn(inQueryRequest);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
            return StudentResponseList;
    }

    @GetMapping("/getByPagination")
    public List<studentResponse> getByPagination(@RequestParam int page, @RequestParam int size){
        List<Student> studentList=StudentService.getByPagination(page,size);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @GetMapping("/getBySorting")
    public List<studentResponse> getBySorting() {
        List<Student> studentList =  StudentService.getBySorting();
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @GetMapping("/getBySortingAsc")
    public List<studentResponse> getBySortingAsc(){
        List<Student> studentList = StudentService.getBySortingAsc();
        List<studentResponse> StudentResponseList =  new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @GetMapping("/getByLike/{firstName}")
    public List<studentResponse> getByLike(@PathVariable String firstName){
        List<Student> studentList = StudentService.getByLike(firstName);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @GetMapping("/getByStartsWith/{lastName}")
    public List<studentResponse> getByStartsWith(@PathVariable String lastName){
        List<Student> studentList = StudentService.getByStartsWith(lastName);
        List<studentResponse> StudentResponseList = new ArrayList<studentResponse>();
        studentList.stream().forEach(student -> {
            StudentResponseList.add(new studentResponse(student));
        });
        return StudentResponseList;
    }

    @PutMapping("/updateFirstname/{id}/{firstName}")
    public String updateFirstname(@PathVariable int id, @PathVariable String firstName){
        return StudentService.updateFirstname(id, firstName) + " Student(s) affected";
    }

    @DeleteMapping("//{firstName}")
    public String deletebyFirstName( @PathVariable String firstName){
        return StudentService.deletebyFirstName(firstName) + " Student(s) affected";
    }
}
