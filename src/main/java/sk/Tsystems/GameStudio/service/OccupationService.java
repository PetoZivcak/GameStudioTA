package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Occupation;

import java.util.List;

public interface OccupationService {
    public List<Occupation > getOccupations();
    public void addOccupation(Occupation occupation);
    public Occupation getOccupation(String occupationName);

}
