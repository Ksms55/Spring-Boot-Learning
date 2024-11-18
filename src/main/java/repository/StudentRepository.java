package repository;

import entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
//import request.InQueryRequest;

import java.util.List;
//import org.yaml.snakeyaml.events.Event;
//JpaRepository == cURLRepository + pagingandsortingRepository

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByEmailId(String emailId);
    Student findByFirstNameAndLastName(String firstName, String lastName);
    Student findByFirstNameAndEmailId(String firstName, String emailId );
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);
    List<Student> findByFirstNameIn (List<String> firstNames);
    List<Student> findByLastNameIn (List<String> lastNames);
    List<Student> findByFirstNameContains (String firstName);
    List<Student> findByLastNameStartsWith (String lastName);

    @Query("FROM Student WHERE firstName = :firstName AND lastName = :lastName")
    Student getByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying
    @Transactional
    @Query("UPDATE Student set firstName= :firstName WHERE id= :id")
    Integer updateFirstname(int id, String firstName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student WHERE firstName= :firstName")
    Integer deletebyFirstName(String firstName);
}
