package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.models.Country;
import com.springboot.repositories.CountryJpaRepository;

@RestController
@RequestMapping("/api")
//@EnableJpaRepositories(basePackages= {"com.springboot.repositories"})
public class CountryController
{
    @Autowired
    private CountryJpaRepository countryJpaRepo;
    
    @RequestMapping(value ="/countries")
    public String getCountries()
    {
        List<Country> countryList = countryJpaRepo.findAll();
        
        StringBuilder strCountryBuild = new StringBuilder();
        for(Country c: countryList)
        {
            strCountryBuild.append("Country ID: ");
            strCountryBuild.append(c.getId());
            strCountryBuild.append("<br>");
            strCountryBuild.append("Country Name: ");
            strCountryBuild.append(c.getName());
            strCountryBuild.append("<br>");
            strCountryBuild.append("Country Capital: ");
            strCountryBuild.append(c.getCapital());
            strCountryBuild.append("<br><br>");
        }
        return (strCountryBuild.toString());
        
    }
    
    @GetMapping(value="/view/{id}")
    public String getCountry(@PathVariable("id") int id)
    {
        Country country = countryJpaRepo.getById(id);
        
        String temp = "";
        temp = "Country name: " + country.getName();
        temp = temp + "<br>Capital name: " + country.getCapital();
        return temp;
    }
    
    @RequestMapping(value = "/country/{id}", method = { RequestMethod.GET } )
    public String getCountry2(@PathVariable("id") int id)
    {
        Country country = countryJpaRepo.getById(id);
        String temp = "";
        temp = "Country name: " + country.getName();
        temp = temp + "<br>Capital name: " + country.getCapital();
        return temp;
    }
    
    @RequestMapping(value = "/remove/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
    public ResponseEntity<Object> removeCountry(@PathVariable("id") int id)
    {
        countryJpaRepo.deleteById(id);
        return new ResponseEntity<>("Country id: " + id + " has been deleted", HttpStatus.OK);
    }
    
    //@PostMapping(value = "/create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createCountry(@RequestBody Country newCountry)
    {
        countryJpaRepo.saveAndFlush(newCountry);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/")
    public String hello()
    {
        return ("Hello from TomCat");
    }

    @GetMapping(value="/welcome")
    public String displayMessage()
    {
        return "Welcome home theresa! We are glad to have you here in the Netherlands";
    }

}
