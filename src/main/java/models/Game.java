package models;

public class Game {

    private String title;
    private LanguageVersion languageVersion;
    private Genre genre;
    private double rentalPrice;
    private static double overdueFee;

    private Studio studio;
    private AssetTag assetTag;

    public Game(String title, LanguageVersion languageVersion, Genre genre, double rentalPrice, Studio studio, AssetTag assetTag) {
        this.title = title;
        this.languageVersion = languageVersion;
        this.genre = genre;
        this.rentalPrice = rentalPrice;
        setAssetTag(assetTag);
        setStudio(studio);
    }
    public Game(String title, LanguageVersion languageVersion, Genre genre, double rentalPrice, AssetTag assetTag) {
        this.title = title;
        this.languageVersion = languageVersion;
        this.genre = genre;
        this.rentalPrice = rentalPrice;
        setAssetTag(assetTag);
    }

    public void setStudio(Studio studio) {
        if(this.studio == studio) return;
        if(this.studio != null) this.studio.removeGame(this);
        this.studio = studio;
        if (studio != null) studio.addGame(this);
    }
    public Studio getStudio() {
        return studio;
    }
    public AssetTag getAssetTag() {
        return assetTag;
    }
    public void setAssetTag(AssetTag assetTag) {
        if(assetTag == null) throw new NullPointerException("Asset tag is required");
        if(this.assetTag != null) throw new RuntimeException("This game already has an Asset tag");
        if(assetTag.getGame() != null) throw new IllegalArgumentException("This Asset tag has a game already");
        this.assetTag = assetTag;
        assetTag.setGame(this);
    }
    public void destroy(AssetTag assetTag){
        if(assetTag == null) throw new NullPointerException("Asset tag is required");
        if(assetTag.getGame() == this) throw new RuntimeException("Game cannot exist without an Asset tag");
        if(assetTag.getGame() != this && this.assetTag == assetTag) this.assetTag = null;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LanguageVersion getLanguageVersion() {
        return languageVersion;
    }
    public void setLanguageVersion(LanguageVersion languageVersion) {
        this.languageVersion = languageVersion;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public double getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public static double getOverdueFee() {
        return overdueFee;
    }
    public static void setOverdueFee(double overdueFee) {
        Game.overdueFee = overdueFee;
    }
    enum Genre{
        SHOOTER,
        RPG,
        SIMULATOR,
        RACING,
        PLATFORMER,
        FIGHTING,
        ARCADE,
        MMO
    }
    enum LanguageVersion{
        EN,
        ES,
        FR,
        DE,
        JP,
        CN,
        PL
    }
}
