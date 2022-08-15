package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional

public class ServiceStudentJPA {
    @PersistenceContext
    EntityManager entityManager;
    public void addStudent(Student student){
        Student studentToWrite;
        try {
            studentToWrite=(Student)entityManager.createQuery("SELECT s.firstName, s.lastName FROM Student s WHERE s.firstName=:firstName and s.lastName=:lastName")
                    .setParameter("firstName",student.getFirstName())
                    .setParameter("lastName",student.getlastName())
                    .getSingleResult();
            studentToWrite.setFirstName(student.getFirstName());
            studentToWrite.setlastName(student.getlastName());
        }catch (NoResultException e){
            studentToWrite=new Student(student.getFirstName(),student.getlastName(),student.getStudyGroup());
            entityManager.persist(studentToWrite);
        }
    }
    public void getStudentsByStudentGroup(String studentGroup){
        entityManager.createQuery("SELECT FROM Student s WHERE s.study_group_ident=:studentGroup ")
                .setParameter("study_goup_ident",studentGroup)
                .executeUpdate();

    }
    public void getStudent(String firstName, String lastName){
        entityManager.createQuery("SELECT FROM Student s WHERE s.firstName=:firstName and s.lastName=:lastName ")
                .setParameter("firstName",firstName)
                .setParameter("lastName",lastName)
                .executeUpdate();

    }
    public void resetStudents(){
        entityManager.createNativeQuery("DELETE from Student")
                .executeUpdate();
    }

}
