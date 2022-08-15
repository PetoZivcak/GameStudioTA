package sk.Tsystems.GameStudio.minesweeper;

import org.springframework.beans.factory.annotation.Autowired;
import sk.Tsystems.GameStudio.entity.*;
import sk.Tsystems.GameStudio.service.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

//@Transactional
public class PlaygroundJPA {
    private String commandString = null;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    //    @PersistenceContext
//    private EntityManager entityManager;
//    @Autowired
//    private ServiceStudentJPA serviceStudentJPA;
//    @Autowired
//    private ServiceStudentGroupJPA serviceStudentGroupJPA;
    @Autowired
    private CountryService countryServiceJPA;
    @Autowired
    private OccupationService occupationServiceJPA;
    @Autowired
    private PlayerService playerServiceJPA;


    public void play() {
//        System.out.println("Opening JPA playground.");
//        Player player=new Player("Peto","Peto Zivcak",3,countryServiceJPA.getCountry("Slovensko"),occupationServiceJPA.getOccupation("Instalater"));
//        playerServiceJPA.addPlayer(player);
        System.out.println(playerServiceJPA.getPlayersByUserName("Peto"));

//        Country country = new Country("Slovensko");
//        Country country1 = new Country("Cesko");
//
//        countryServiceJPA.addCountry(country);
//        countryServiceJPA.addCountry(country1);
//
//        Occupation occupation=new Occupation("Instalater");
//        Occupation occupation1=new Occupation("Nezamestnany");
//        occupationServiceJPA.addOccupation(occupation);
//        occupationServiceJPA.addOccupation(occupation1);


//        entityManager.persist(new StudyGroup("zakladna"));
//        entityManager.persist(new StudyGroup("mierne pokrocila"));
//        entityManager.persist(new StudyGroup("pokrocila"));

//        String firstName = "Raweel";
//        String lastName = "Powick";
//        int group = 1;
//
//        List<StudyGroup> studyGroups= entityManager.createQuery("select g from StudyGroup g")
//                .getResultList();
//
//        int noOfGroups = studyGroups.size();
//
//        for(int i=0;i<noOfGroups;i++){
//            System.out.println(i+" "+studyGroups.get(i));
//        }
//
//        entityManager.persist(new Student(firstName,lastName,studyGroups.get(group)));
//
//        List<Student> students= entityManager.createQuery("select s from Student s")
//                .getResultList();
//
//        System.out.println(students);
//

                // while
//        System.out.println("Zadajte co chcete urobit addg-pridaj grupu, adds-pridajstudenta, vsg-zobraz studentske skupiny, vss-zobraz studentov, " +
//                "nsbn-najdi studenta podla mena, nsgn-najdi skupinu studentov podla mena. Alebo zadaj 'koniec' pre ukoncenie.");
//        commandString = readLine();
//
//        if (commandString == null) {
//            System.err.println("Command is null.");
//            return;
//        }
//
//        switch (commandString) {
//            case "addgsss": {
//                // handleAddGrssoupCommand
//                System.out.println("Zadajte meno grupy");
//                String studentGroupName = readLine();
//
//                StudyGroup studyGroup = new StudyGroup(studentGroupName);
//                serviceStudentGroupJPA.addStudentGroup(studyGroup);
//            }
//            case "vsg": {
//
//            }
//            case "koniec" : {
//
//            }
//
//            default: {
//
//            }
//        }
//
//        if (commandString.equals("addg")) {
//            System.out.println("Zadajte meno grupy");
//            String studentGroupName = readLine();
//            StudyGroup studyGroup = new StudyGroup(studentGroupName);
//            serviceStudentGroupJPA.addStudentGroup(studyGroup);
//        }
//
//        if (commandString.equals("vsg")) {
//            System.out.println("vypis" + serviceStudentGroupJPA.getStudentsGroups() + "koniec vypisu");
//        }
//        if (commandString.equals("adds")) {
//            System.out.println("Zadajte prve meno studenta");
//            String firstName = readLine();
//            System.out.println("Zadajte druhe meno studenta");
//            String lastName = readLine();
//            System.out.println("Zadajte meno jednej zo skupin:" + serviceStudentGroupJPA.getStudentsGroups());
//            String groupName = readLine();
//           // StudyGroup studyGroup = new StudyGroup(groupName);
//            Student student = new Student(firstName, lastName);
//
//            student.setStudyGroup(serviceStudentGroupJPA.getStudentGroup(groupName));
//            serviceStudentJPA.addStudent(student);
//        }




        /*
        String game = "minesweeper";
        String user = "JAjo";
        int ratingValue = 5;
        //entityManager.persist(new Rating(game,user,ratingValue,new Date()));
        Rating rating2Write = null;
        try{
            rating2Write = (Rating) entityManager.createQuery("select r from Rating r where r.username=:user and r.game = :game")
                    .setParameter("user",user)
                    .setParameter("game",game)
                    .getSingleResult();
            rating2Write.setRating(ratingValue);
            rating2Write.setRatedOn(new Date());
        }catch(NoResultException e){
            rating2Write = new Rating(game,user,ratingValue,new Date());
            entityManager.persist(rating2Write);
            rating2Write.setRating(1);
        }
        System.out.println(rating2Write);
         */


        /*
        entityManager.persist(new Score("minesweeper", "Stevo", 10, new Date()));
        entityManager.persist(new Score("minesweeper", "Stevoo", 100, new Date()));
        String game = "minesweeper";
        List<Score> bestScores =
                entityManager
                 .createQuery("select sc from Score sc where sc.game = :myGame order by sc.points desc")
                        .setParameter("myGame",game)
                        .getResultList();
        System.out.println(bestScores);
        */


                System.out.println("Closing JPA playground.");
    }


}