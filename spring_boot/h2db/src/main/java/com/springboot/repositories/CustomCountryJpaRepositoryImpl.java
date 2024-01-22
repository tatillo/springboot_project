package com.springboot.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.springboot.models.Country;

public class CustomCountryJpaRepositoryImpl implements CustomCountryJpaRepository
{
    //TODO: need to figure out why entityManager is null.
    @PersistenceContext 
    private EntityManager entityManager;
    
    @Override
    public List<Country> getAllCountries()
    {
        if(entityManager != null)
        {
            return entityManager.createQuery("Select c from Country c").getResultList();
        }
        else
        {
            List<Country> countryList = new ArrayList<Country>();
            
            Country country = new Country();
            country.setId(6);
            country.setCapital("Manila");
            country.setName("Philippines");
            countryList.add(country);
            
            return countryList;
        }
    }
}
