package com.intelixcloudmessenger.producer.domain;


import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "petition")
public class Petition {

    @PrimaryKeyColumn(
        name = "id",
        ordinal = 2,
        type = PrimaryKeyType.PARTITIONED,
        ordering = Ordering.DESCENDING
    )
    private String id;

    @Column(value = "is_active")
    private String isActive;

    @Column
    private Timestamp created;

    @Column(value = "ip_address")
    private String ipAddress;

    @Column(value = "mac_address")
    private String macAddress;

    @Column
    private String status;

    @Column
    private String type;

    @Column
    private String customer;

    @Column
    private String password;

    @Column
    private String petition;

    public Petition() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPetition() {
        return petition;
    }

    public void setPetition(String petition) {
        this.petition = petition;
    }


}
