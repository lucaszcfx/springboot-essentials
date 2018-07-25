package br.com.devdojo.endpoint;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("v1")
public class StudentEndPoint {

    private final StudentRepository studentDao;

    @Autowired
        public StudentEndPoint(StudentRepository studentDao)
    {
        this.studentDao = studentDao;
    }

    @GetMapping(path = "/protected/students")
        public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(studentDao.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping(path = "/protected/students/{id}")
        public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        Optional<Student> student = studentDao.findById(id);
        verifyIfStudentExists(id);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @GetMapping(path = "/protected/students/findByName/{name}")
        public ResponseEntity<?> findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentDao.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
        }

    @PostMapping(path = "/admin/students")
    @Transactional
        public ResponseEntity<?> save(@RequestBody Student student){
        studentDao.save(student);
        System.out.println(student.getEmail());
        System.out.println(student.getName());
        if(!true)
           throw new RuntimeException("Test Transactions");
        return new ResponseEntity<>(studentDao.save(student),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/admin/students/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> delete(@PathVariable("id") @RequestBody Long id) {
        verifyIfStudentExists(id);
        Long idBefore = id;
        studentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/admin/students")
        public ResponseEntity<?> update (@RequestBody Student student){
        studentDao.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        if(studentDao.findById(id) == null)
            throw new ResourceNotFoundException("Student not found for id"+ id);
    }
}
