package com.springboot.repositories;

import java.util.List;

import com.springboot.models.Country;

public interface CustomCountryJpaRepository
{
    public List<Country> getAllCountries();
}
