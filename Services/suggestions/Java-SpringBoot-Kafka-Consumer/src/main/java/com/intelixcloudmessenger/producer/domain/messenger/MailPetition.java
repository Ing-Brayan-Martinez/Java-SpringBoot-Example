package com.intelixcloudmessenger.producer.domain.messenger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MailPetition {

    @JsonProperty("petitionID")
    private String petitionID;

    @JsonProperty("created")
    private String created;

    @JsonProperty("ipAddress")
    private String ipAddress;

    @JsonProperty("macAddress")
    private String macAddress;

    @JsonProperty("status")
    private String status;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("password")
    private String password;

    @JsonProperty("type")
    private String type;

    @JsonProperty("isAttached")
    private String isAttached;

    @JsonProperty("attached")
    private String attached;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("messenger")
    private MailMessenger mensseger;

    @JsonCreator
    MailPetition() {
    }

    public String getPetitionID() {
        return petitionID;
    }

    public void setPetitionID(String petitionID) {
        this.petitionID = petitionID;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
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

    public String getIsAttached() {
        return isAttached;
    }

    public void setIsAttached(String isAttached) {
        this.isAttached = isAttached;
    }

    public String getAttached() {
        return attached;
    }

    public void setAttached(String attached) {
        this.attached = attached;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MailMessenger getMensseger() {
        return mensseger;
    }

    public void setMensseger(MailMessenger mensseger) {
        this.mensseger = mensseger;
    }

    @Override
    public String toString() {
        return "MailPetition{" +
        "petitionID='" + petitionID + '\'' +
        ", created=" + created +
        ", ipAddress='" + ipAddress + '\'' +
        ", macAddress='" + macAddress + '\'' +
        ", status='" + status + '\'' +
        ", customer='" + customer + '\'' +
        ", password='" + password + '\'' +
        ", type='" + type + '\'' +
        ", isAttached='" + isAttached + '\'' +
        ", attached='" + attached + '\'' +
        ", mensseger=" + mensseger +
        '}';
    }
}
