package com.intelixcloudmessenger.producer.domain.messenger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Objects;

public class SmsPetition {

    private String petitionID;
    private Timestamp created;
    private String ipAddress;
    private String macAddress;
    private String status;
    private String customer;
    private String password;
    private String type;
    private SmsMensseger mensseger;

    @JsonCreator
    SmsPetition(
    @JsonProperty("petitionID") String petitionID,
    @JsonProperty("type") Timestamp created,
    @JsonProperty("ipAddress") String ipAddress,
    @JsonProperty("macAddress") String macAddress,
    @JsonProperty("status") String status,
    @JsonProperty("customer") String customer,
    @JsonProperty("password") String password,
    @JsonProperty("type") String type,
    @JsonProperty("messenger") SmsMensseger messenger) {
        this.petitionID = petitionID;
        this.created    = created;
        this.ipAddress  = ipAddress;
        this.macAddress = macAddress;
        this.status     = status;
        this.customer   = customer;
        this.password   = password;
        this.type       = type;
        this.mensseger = messenger;
    }

    public String getPetitionID() {
        return petitionID;
    }

    public void setPetitionID(String petitionID) {
        this.petitionID = petitionID;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SmsMensseger getMensseger() {
        return mensseger;
    }

    public void setMensseger(SmsMensseger mensseger) {
        this.mensseger = mensseger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsPetition petition = (SmsPetition) o;
        return Objects.equals(petitionID, petition.petitionID) &&
        Objects.equals(created, petition.created) &&
        Objects.equals(ipAddress, petition.ipAddress) &&
        Objects.equals(macAddress, petition.macAddress) &&
        Objects.equals(status, petition.status) &&
        Objects.equals(customer, petition.customer) &&
        Objects.equals(password, petition.password) &&
        Objects.equals(type, petition.type) &&
        Objects.equals(mensseger, petition.mensseger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petitionID, created, ipAddress, macAddress,
        status, customer, password, type, mensseger);
    }

    @Override
    public String toString() {
        return "SmsPetition{" +
        "petitionID='" + petitionID + '\'' +
        ", created=" + created +
        ", ipAddress='" + ipAddress + '\'' +
        ", macAddress='" + macAddress + '\'' +
        ", status='" + status + '\'' +
        ", customer='" + customer + '\'' +
        ", password='" + password + '\'' +
        ", type='" + type + '\'' +
        ", mensseger=" + mensseger +
        '}';
    }
}
