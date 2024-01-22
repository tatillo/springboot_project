package com.springboot.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.models.Country;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Integer>, CustomCountryJpaRepository
{
    List<Country> findByCapitalStartingWith(String capital);
    
    List<Country> findByIdGreaterThanOrderByName(Integer id);
    
    List<Country> findByCapitalIn(List<String> capitalList);
    
    List<Country> findTop3By(Sort sort);
    
    Country findTopByOrderByCapitalAsc();
    
    //jpql queries below
    /*
     * @Query("Select c from Country c where c.name == ?1 AND c.capital == ?2")
     * Country getCountryByNameCapital(String name, String capital);
     */
    @Query("Select c from Country c where name like %?1")
    Country getCountryName(String name);
    
    //TODO: try to change this to NamedQuery
    //@Query(name = "Country.namedFindCountryByCapital")
    @Query("select c from Country c where c.capital = :capital")
    List<Country> getCapitalName(@Param("capital") String capital);
    
    Page<Country> findByNameContains(String character, Pageable page);
    

}
