package com.laioffer.staybooking.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "reservation")
@JsonDeserialize(builder = Reservation.Builder.class)
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonProperty("checkin_date")
    private LocalDate checkinDate;


    @JsonProperty("checkout_date")
    private LocalDate checkoutDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User guest;


    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;


    public Reservation() {}


    private Reservation(Builder builder) {
        this.id = builder.id;
        this.checkinDate = builder.checkinDate;
        this.checkoutDate = builder.checkoutDate;
        this.guest = builder.guest;
        this.stay = builder.stay;
    }


    public Long getId() {
        return id;
    }


    public LocalDate getCheckinDate() {
        return checkinDate;
    }


    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }


    public User getGuest() {
        return guest;
    }


    public Reservation setGuest(User guest) {
        this.guest = guest;
        return this;
    }


    public Stay getStay() {
        return stay;
    }


    public static class Builder {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("checkin_date")
        private LocalDate checkinDate;

        @JsonProperty("checkout_date")
        private LocalDate checkoutDate;

        @JsonProperty("guest")
        private User guest;

        @JsonProperty("stay")
        private Stay stay;



        public Builder setId(Long id) {
            this.id = id;
            return this;
        }


        public Builder setCheckinDate(LocalDate checkinDate) {
            this.checkinDate = checkinDate;
            return this;
        }


        public Builder setCheckoutDate(LocalDate checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }


        public Builder setGuest(User guest) {
            this.guest = guest;
            return this;
        }


        public Builder setStay(Stay stay) {
            this.stay = stay;
            return this;
        }


        public Reservation build() {
            return new Reservation(this);
        }
    }
}
