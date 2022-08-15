package sk.Tsystems.GameStudio.entity;

import javax.persistence.*;



import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long ident;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "StudyGroup.ident", nullable = false)
    private StudyGroup studyGroup;

    public Student(){

    }

    public Student(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Student(String firstName, String lastName, StudyGroup studyGroup) {
        this.firstName = firstName;
        lastName = lastName;
        this.studyGroup = studyGroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ident=" + ident +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studyGroup=" + studyGroup +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        lastName = lastName;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }
}