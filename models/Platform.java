package models;

public class Platform {
    public String name;
    // public backwardCompatibility;
    public String producent;
    public Type type;

    enum Type{
        STATIONARY,
        HANDHELD,
        HYBRID
    }
}
