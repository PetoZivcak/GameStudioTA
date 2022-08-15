package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Country;
import sk.Tsystems.GameStudio.entity.Occupation;
import sk.Tsystems.GameStudio.entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class OccupationServiceJPA implements OccupationService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Occupation> getOccupations() {


        return entityManager.createQuery("SELECT o FROM Occupation o")
                .getResultList();
    }

    @Override
    public void addOccupation(Occupation occupation) {
        Occupation occupationToWrite=null;
        try {

            occupationToWrite=(Occupation)  entityManager.createQuery("select o from Occupation o   where o.occupation=:occupation")
                    .setParameter("occupation",occupation.getOccupation())
                    .getSingleResult();


            if (occupationToWrite!=null){
                System.out.println("Povolanie "+occupation.getOccupation()+ " uz existuje");
            }

        }catch (NoResultException e){
            entityManager.persist(occupation);

        }

    }

    @Override
    public Occupation getOccupation(String occupationName) {
        try {

            return   (Occupation) entityManager.createQuery("select c from Occupation c where c.occupation= :occupation ")

                    .setParameter("occupation", occupationName)
                    .getSingleResult();



        } catch (NoResultException e) {

            // entityManager.persist(countryToWrite);

        }
        return null;
    }
}
