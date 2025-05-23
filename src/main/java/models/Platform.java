package models;

import java.util.HashSet;
import java.util.Set;

public class Platform {
    private String name;
    private String producent;
    private Type type;
    private Set<Game> games = new HashSet<>();
    public Platform(String name, String producent, Type type) {
        if(name == null) throw new NullPointerException("Name is required");

        this.name = name;
        this.producent = producent;
        this.type = type;
    }
    public Set<Game> getGames() {
        return games;
    }
    public void addGame(Game game) {
        if(game == null) throw new NullPointerException("Game is required");
        if(games.contains(game)) return;
        games.add(game);
        if(!game.getPlatforms().containsKey(this.name)) game.addPlatform(this);
    }
    public void removeGame(Game game) {
        if(game == null) throw new NullPointerException("Game is required");
        if(games.remove(game)){
            if(game.getPlatforms().containsKey(this.name)) game.removePlatform(this);
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProducent() {
        return this.producent;
    }
    public void setProducent(String producent) {
        this.producent = producent;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    enum Type {STATIONARY, HANDHELD, HYBRID}

}
