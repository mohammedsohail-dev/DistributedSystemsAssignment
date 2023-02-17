package org.example.model;

public class Audio {
    private String id;
    private String artistName;
    private String trackTitle;
    private String albumTitle;
    private String trackNumber;
    private String year;
    private String numberOfReviews;
    private String numberOfCopiesSold;

    

    public String getId() {
        return this.id;
    }

    public void setId(String Id) {
        this.id = Id;
    }
    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackTitle() {
        return this.trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getAlbumTitle() {
        return this.albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getTrackNumber() {
        return this.trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumberOfReviews() {
        return this.numberOfReviews;
    }

    public void setNumberOfReviews(String numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public String getNumberOfCopiesSold() {
        return this.numberOfCopiesSold;
    }

    public void setNumberOfCopiesSold(String numberOfCopiesSold) {
        this.numberOfCopiesSold = numberOfCopiesSold;
    }
    
}
