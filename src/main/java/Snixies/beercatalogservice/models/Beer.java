package Snixies.beercatalogservice.models;

public class Beer {

    private int beerId;
    private String name;
    private String description;

    public Beer(){

    }

    public Beer(int beerId, String name, String description) {
        this.beerId = beerId;
        this.name = name;
        this.description = description;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
