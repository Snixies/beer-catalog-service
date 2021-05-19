package Snixies.beercatalogservice.models;

public class Rating {
    private int beerId;
    private int rating;

    public Rating(int beerId, int rating) {
        this.beerId = beerId;
        this.rating = rating;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
