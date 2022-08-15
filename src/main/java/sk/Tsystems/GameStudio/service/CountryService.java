package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getCountries();
    public void addCountry(Country country);

    public Country getCountry(String countryName);

}
