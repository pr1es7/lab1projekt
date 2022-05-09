package service;

import domain.Grade;
import domain.Homework;
import domain.Pair;
import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ServiceMockTest {
    @Mock
    StudentXMLRepository fileRepository1;
    @Mock
    HomeworkXMLRepository fileRepository2;
    @Mock
    GradeXMLRepository fileRepository3;

    Service service;

    @BeforeEach
    void setUp() {

        fileRepository1 = Mockito.mock(StudentXMLRepository.class);
        fileRepository2 = Mockito.mock(HomeworkXMLRepository.class);
        fileRepository3 = Mockito.mock(GradeXMLRepository.class);

        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);

    }
    @Test
    void updateGrade() {
        Grade testGrade = new Grade(new Pair(1, 1), 7, 1,"Cool");
        Mockito.when(fileRepository3.update(testGrade)).thenReturn(null);
        assertNotEquals(1, this.service.saveGrade("1", "1", 7, 1,"Cool"));
    }

    @Test
    void saveStudent() {
        Student testStudent = new Student("1", "Jozsi", 1);
        Mockito.when(fileRepository1.save(testStudent)).thenReturn(null);
        assertEquals(1, this.service.saveStudent("1", "Jozsi", 1));
    }

    @Test
    void deleteStudent() {
        Student testStudent = new Student("2", "Maria", 1);
        Mockito.when(fileRepository1.delete("2")).thenReturn(testStudent);
        assertEquals(1, this.service.deleteStudent("2"));
    }

    @Test
    void updateHomework() {
        Homework testHomework = new Homework("2", "Idk", 1, 2);
        Mockito.when(fileRepository2.update(testHomework)).thenReturn(testHomework);
        assertEquals(1, this.service.updateHomework("2", "Idk", 1, 2));
    }
}
