package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
    public Service service;

    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "3", "3.2", "11"})
    public void deleteStudent(String id) {
        assertEquals(0,service.deleteStudent(id));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "23", "3.2", "11"})
    public void deleteHomework(String id) {
        assertNotEquals(1,service.deleteHomework(id));
    }

    @Test
    public void findAllStudents() {
        assertNotNull(service.findAllStudents());
    }

    @Test
    public void findAllHomework() {
        assertNotNull(service.findAllHomework());
    }

    @Test
    public void findAllGrades() {
        assertNotNull(service.findAllGrades());
    }

}