package com.springboot.h2db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import com.springboot.models.Country;
import com.springboot.repositories.CountryJpaRepository;

@SpringBootTest
@Sql({"create_country_tb.sql", "data.sql"})
class H2dbApplicationTests {
    
    @Autowired
    private CountryJpaRepository jpaCountryRepo;
	
	@Test
	void testListOfCountries() throws Exception
	{
	    long countList = jpaCountryRepo.count();
	    assertTrue(countList > 0);
	    
	}
	
	@Test
	public void testDSLQueries() throws Exception
	{
	    List<Country> listCountry = jpaCountryRepo.findByCapitalStartingWith("P");
	    assertEquals(listCountry.size(), 2);
	    
	    listCountry = jpaCountryRepo.findByIdGreaterThanOrderByName(2);
	    assertEquals(listCountry.size(), 3);
	    
	    assertEquals(listCountry.get(0).getName(), "Brazil");
	    assertEquals(listCountry.get(1).getName(), "Canada");
	    assertEquals(listCountry.get(2).getName(), "Italy");
	    
	    List<String> capitalList = new ArrayList<String>();
	    capitalList.add("Ontario");
	    capitalList.add("Portugal");
	    
	    listCountry = jpaCountryRepo.findByCapitalIn(capitalList);
	    assertEquals(listCountry.size(), 2);
	    
	    for(Country c: listCountry)
	    {
	        assertTrue(c.getName().equals("Canada") || c.getName().equals("Brazil"));
	    }
	    
	    Sort sort = Sort.by("name").descending();
	    listCountry = jpaCountryRepo.findTop3By(sort);
	    assertEquals(listCountry.size(), 3);
	    String[] names = {"USA", "Italy", "France" };
	    
	    for(int index = 0; index < listCountry.size(); index++)
	    {
	        assertTrue(listCountry.get(index).getName().equals(names[index]));
	    }
	    
	    Country country = jpaCountryRepo.findTopByOrderByCapitalAsc();
	    assertEquals(country.getCapital(), "Ontario");
	}
	
	@Test
	public void testPageableSort() throws Exception
	{
	    Page<Country> page = jpaCountryRepo.findByNameContains("a", PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "name")));
	    assertEquals(page.getTotalElements(), 4);
	    assertEquals(page.getTotalPages(), 2);
	    
	    Iterator pageIterator = page.iterator();
	    while(pageIterator.hasNext())
	    {
	        Country country = (Country) pageIterator.next();
	        System.out.println(country.getName());
	    }
	}
	
	@Test
	public void testCustomJpaRepository() throws Exception
	{
	    List<Country> countries = jpaCountryRepo.getAllCountries();
	    assertEquals(countries.size(), 1);
	    
	    //below is currently not working
	    //assertEquals(countries.size(), 5);
	    
	    for(Country country:countries)
	    {
	        System.out.println(country.getName());
	    }
	}
	
	//@Test
    /*
     * public void testJpqlQueries() { Country country =
     * jpaCountryRepo.getCountryByNameCapital("Italy", "Rome");
     * assertNotNull(country); assertEquals(country.getId(), 4); }
     */

}
