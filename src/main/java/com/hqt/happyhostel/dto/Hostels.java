package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hostels {
    private int hostelID;
    private int hostelOwnerAccountID;
    private String hostelName;
    private String address;
    private String ward;
    private String district;
    private String city;

    public Hostels(int hostelOwnerAccountID, String hostelName, String address, String ward, String district, String city) {
        this.hostelOwnerAccountID = hostelOwnerAccountID;
        this.hostelName = hostelName;
        this.address = address;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public Hostels(String hostelName, String address, String ward, String district, String city) {
        this.hostelName = hostelName;
        this.address = address;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public int getHostelID() {
        return hostelID;
    }

    public void setHostelID(int hostelID) {
        this.hostelID = hostelID;
    }

    public int getHostelOwnerAccountID() {
        return hostelOwnerAccountID;
    }

    public void setHostelOwnerAccountID(int hostelOwnerAccountID) {
        this.hostelOwnerAccountID = hostelOwnerAccountID;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
