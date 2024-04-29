/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author alex
 */
public class Room {
    
    private String id;
    private int cap;
    private StatRoom stat;
    private HashSet<HotelService> services;
    private Client client;
    private int problems;
    
    public Room(String id, int cap, HashSet<HotelService> services) {
        this.id = id;
        this.cap = cap;
        this.services = services;
        this.stat = StatRoom.CLEAN;
        this.client = null;
        problems = 0;
    }

    public String getId() {
        return id;
    }

    public int getCap() {
        return cap;
    }

    public StatRoom getStat() {
        return stat;
    }

    public HashSet<HotelService> getServices() {
        return services;
    }

    public Client getClient() {
        return client;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void setStat(StatRoom stat) {
        this.stat = stat;
    }

    public void setServices(HashSet<HotelService> services) {
        this.services = services;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getProblems() {
        return problems;
    }

    public void setProblems(int problems) {
        this.problems = problems;
    }
    
    public void restarProblem() {
        this.problems -= 1;
    }
    
    @Override
    public String toString() {
        String string;
        if (this.client != null) {
            string = "== ROOM: " + this.id + " CUSTOMER: " + client.getNif() + " (" + client.getPeople() + ") ==";
        }
        else {
            string = "== ROOM: " + this.id + " " + this.stat + " ==";
        }
        return string;
    } 
}
