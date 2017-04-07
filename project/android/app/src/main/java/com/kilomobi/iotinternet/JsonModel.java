/*
 * Copyright (c) KiloMobi - 2017.
 */

package com.kilomobi.iotinternet;

/**
 * Created by fabrice on 21/07/16.
 */
public class JsonModel {
    private String idCapteur;
    private double localNetwork;
    private double externalNetwork;
    private String timestamp;

    public JsonModel() {
    }

    public JsonModel(String idCapteur, double localNetwork, double externalNetwork, String timestamp) {
        this.idCapteur = idCapteur;
        this.localNetwork = localNetwork;
        this.externalNetwork = externalNetwork;
        this.timestamp = timestamp;
    }

    public String getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(String idCapteur) {
        this.idCapteur = idCapteur;
    }

    public double getLocalNetwork() {
        return localNetwork;
    }

    public void setLocalNetwork(double localNetwork) {
        this.localNetwork = localNetwork;
    }

    public double getExternalNetwork() {
        return externalNetwork;
    }

    public void setExternalNetwork(double externalNetwork) {
        this.externalNetwork = externalNetwork;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
