package com.springboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
/*@NamedQuery(
name = "Country.namedFindCountryByCapital",
query = "select c from Country c where c.capital = :capital")
*/
//query = "select tp from TicketPrice tp where tp.pricingCategory.pricingCategoryName = :name"
public class Country
{
    @Column(name="name")
    private String name;
    
    @Column(name="capital")
    private String capital;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getCapital()
    {
        return capital;
    }
    public void setCapital(String capital)
    {
        this.capital = capital;
    }
    public long getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

}
