package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Student;
import sk.Tsystems.GameStudio.entity.StudyGroup;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional

public class ServiceStudentGroupJPA {
    @PersistenceContext
    EntityManager entityManager;

    public void addStudentGroup(StudyGroup studyGroup){
        if (studyGroup == null) {
            System.err.println("Group Not added. Study group is null.");
            return;
        }

//        if (isNotValidGroupName(studentGroupName)) {
//            return;
//        }
//
//        if (groupExistsInDatabase(studentGroupName)) {
//            return;
//        }

        StudyGroup studyGroupToWrite;
        try {
            studyGroupToWrite=(StudyGroup) entityManager.createQuery("SELECT sg.name FROM StudyGroup sg WHERE sg.name=:name ")
                    .setParameter("name",studyGroup.getName())
                    .getSingleResult();
        }catch (NoResultException e){
            studyGroupToWrite=new StudyGroup(studyGroup.getName());
            entityManager.persist(studyGroupToWrite);
        }


    }
    public List<StudyGroup>getStudentsGroups(){
       return entityManager.createQuery("SELECT sg.name FROM StudyGroup sg")
                .getResultList();
    }
    public StudyGroup getStudentGroup(String name){
     return   (StudyGroup) entityManager.createQuery("SELECT sg FROM StudyGroup sg WHERE sg.name=:name")
                .setParameter("name",name)
                .getSingleResult();
    }
    public void resetStudentGroups(){
        entityManager.createNativeQuery("DELETE FROM StudyGroup");
    }
}
