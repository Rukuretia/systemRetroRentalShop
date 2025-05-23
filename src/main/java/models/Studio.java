package models;

import java.util.HashSet;
import java.util.Set;

public class Studio {

    enum Country {US, UK, JP, DE, PL}

    private String name;
    private  Country country;

    public Studio(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    private Set<Game> games = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void addGame(Game game){
        if(game == null) throw new NullPointerException("Game is required");
        if(this.games.contains(game)) return;
        this.games.add(game);
        game.setStudio(this);
    }
    public void removeGame(Game game){
        if(game == null) throw new NullPointerException("Game is required");
        if(this.games.remove(game)) game.setStudio(null);
    }

}
