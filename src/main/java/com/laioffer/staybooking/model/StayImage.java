package com.laioffer.staybooking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;


@Entity
@Table(name = "stay_image")
public class StayImage {


    @Id
    private String url;


    @ManyToOne
    @JoinColumn(name = "stay_id")
    @JsonIgnore
    private Stay stay;


    public StayImage() {
    }


    public StayImage(String url, Stay stay) {
        this.url = url;
        this.stay = stay;
    }


    public String getUrl() {
        return url;
    }


    public StayImage setUrl(String url) {
        this.url = url;
        return this;
    }


    public Stay getStay() {
        return stay;
    }


    public StayImage setStay(Stay stay) {
        this.stay = stay;
        return this;
    }
}
