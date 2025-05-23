package models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AssetTag {

    public enum Status {
        ON_STOCK,
        RENTED,
        OVERDUE,
        LOST,
        AWAITING_DISPOSAL,
        DISPOSED
    }

    private final String tag;
    private Status status;
    private final LocalDate creationDate;
    private double purchaseCost;
    private Set<String> assetTagRegistry = new HashSet<>();
    private Game game;

    public AssetTag(String tag, Status status, double purchaseCost) {
        if (tag == null || tag.isEmpty()) throw new IllegalArgumentException("Tag is required");
        if (assetTagRegistry.contains(tag)) throw new IllegalArgumentException("Tag must be unique");

        this.tag = tag;
        this.status = status;
        this.purchaseCost = purchaseCost;
        this.creationDate = LocalDate.now();
        assetTagRegistry.add(tag);
    }

    public AssetTag(String tag, Status status) {
        if (tag == null || tag.isEmpty()) throw new IllegalArgumentException("Tag is required");
        if (assetTagRegistry.contains(tag)) throw new IllegalArgumentException("Tag must be unique");

        this.tag = tag;
        this.status = status;
        this.creationDate = LocalDate.now();
        assetTagRegistry.add(tag);
    }


    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        if(game == null) throw new NullPointerException("Game is required");
        if(this.game != null) throw new RuntimeException("This Asset tag has a game already");
        if(game.getAssetTag() != this) throw new RuntimeException("This game has a different Asset tag already");
        this.game = game;
    }
    public void removeGame(Game game){
        if(game == null) throw new NullPointerException("Game is required");
        if(game.getAssetTag() != this) throw new RuntimeException("This game has a different Asset tag");
        if(this.game == game) this.game = null;
        game.destroy(this);
    }
    public String getTag() {
        return tag;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        if (status == null) throw new NullPointerException("Status is required");
        this.status = status;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public double getPurchaseCost() {
        return purchaseCost;
    }
    public void setPurchaseCost(double purchaseCost) {
        if (purchaseCost < 0) throw new IllegalArgumentException("Purchase cost can't be less than 0");
        this.purchaseCost = purchaseCost;
    }

}
