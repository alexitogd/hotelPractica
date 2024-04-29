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
public class Worker {
    
    private int nif;
    private String name;
    private HashSet<SkillWorker> skills;
    private Room room;
    
    
    public Worker(int nif, String name, HashSet<SkillWorker> skills) {
        this.nif = nif;
        this.name = name;
        this.skills = skills;
        this.room = null;
    }

    public int getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public HashSet<SkillWorker> getSkills() {
        return skills;
    }
    
    public Room getRoom() {
        return room;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(HashSet<SkillWorker> skills) {
        this.skills = skills;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    @Override
    public String toString() {
        String string;
        if (room == null) {
            string = "== WORKER: " + this.nif + " " + this.name + " AVAILABLE ==";
        } else {
            string = "== WORKER: " + this.nif + " " + this.name + " ROOM: " + room.getId() + " ==";
        }
        return string;
    }
    
}
