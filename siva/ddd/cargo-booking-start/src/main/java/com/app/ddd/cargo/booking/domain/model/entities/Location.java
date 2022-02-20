package com.app.ddd.cargo.booking.domain.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {

    @Column(name = "orgin_id")
    private String unLocaCode;

    public Location() {
    }

    public Location(String unLocaCode) {
        this.unLocaCode = unLocaCode;
    }

    public String getUnLocaCode() {
        return unLocaCode;
    }

    public void setUnLocaCode(String unLocaCode) {
        this.unLocaCode = unLocaCode;
    }
}
