package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Country;
import sk.Tsystems.GameStudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public class CountryServiceJPA implements CountryService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Country> getCountries() {
        return entityManager
                .createQuery("select c from Country c  order by c.country desc")
                .getResultList();

    }

    @Override
    public void addCountry(Country country) {
        Country countryToWrite = null;
        try {

            countryToWrite = (Country) entityManager.createQuery("select c from Country c where c.country= :country ")

                    .setParameter("country", country.getCountry())
                    .getSingleResult();
            countryToWrite.setCountry(country.getCountry());

            System.out.println("Takato krajina uz existuje");


        } catch (NoResultException e) {
            countryToWrite = new Country(country.getCountry());
            entityManager.persist(countryToWrite);

        }
    }

    @Override
    public Country getCountry(String countryName) {
        try {

            return (Country) entityManager.createQuery("select c from Country c where c.country= :country ")

                    .setParameter("country", countryName)
                    .getSingleResult();


        } catch (NoResultException e) {

            // entityManager.persist(countryToWrite);

        }
        return null;
    }


}
