package com.moto.aiolo.motoclubproject.Model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("title")
    private String eventTitle;
    @SerializedName("local")
    private String localEvent;
    @SerializedName("horario")
    private String horarioEvent;
    @SerializedName("author")
    private String authorEvent;
    @SerializedName("lat")
    private Double lat;
    @SerializedName("lon")
    private Double lon;

    public Event(String eventTitle, String localEvent, String horarioEvent,
                 String authorEvent, Double lat, Double lon) {
        this.eventTitle = eventTitle;
        this.localEvent = localEvent;
        this.horarioEvent = horarioEvent;
        this.authorEvent = authorEvent;
        this.lat = lat;
        this.lon = lon;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getLocalEvent() {
        return localEvent;
    }

    public void setLocalEvent(String localEvent) {
        this.localEvent = localEvent;
    }

    public String getHorarioEvent() {
        return horarioEvent;
    }

    public void setHorarioEvent(String horarioEvent) {
        this.horarioEvent = horarioEvent;
    }

    public String getAuthorEvent() {
        return authorEvent;
    }

    public void setAuthorEvent(String authorEvent) {
        this.authorEvent = authorEvent;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
