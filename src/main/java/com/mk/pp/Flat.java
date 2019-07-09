package com.mk.pp;

public class Flat {
    private String id;
    private String shortHeader;
    private int price;
    private double distanceToMetro;
    private String shortDescription;
    private String shortAddress;
    private String href;
    private String shortPublishDate;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(String shortAddress) {
        this.shortAddress = shortAddress;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortHeader() {
        return shortHeader;
    }

    public void setShortHeader(String shortHeader) {
        this.shortHeader = shortHeader;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDistanceToMetro() {
        return distanceToMetro;
    }

    public void setDistanceToMetro(double distanceToMetro) {
        this.distanceToMetro = distanceToMetro;
    }

    public String getShortDescrition() {
        return shortDescription;
    }

    public void setShortDescrition(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortPublishDate() {
        return shortPublishDate;
    }

    public void setShortPublishDate(String shortPublishDate) {
        this.shortPublishDate = shortPublishDate;
    }
}
