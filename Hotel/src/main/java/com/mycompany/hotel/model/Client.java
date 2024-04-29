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
public class Client {
    
    private int nif;
    private int people;
    private HashSet<HotelService> services;
    
    public Client(int nif, int people, HashSet<HotelService> services) {
        this.nif = nif;
        this.people = people;
        this.services = services;
    }

    public int getNif() {
        return nif;
    }

    public int getPeople() {
        return people;
    }

    public HashSet<HotelService> getServices() {
        return services;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setServices(HashSet<HotelService> services) {
        this.services = services;
    }
    
    
    
}
