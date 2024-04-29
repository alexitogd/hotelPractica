/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.view;

import com.mycompany.hotel.exceptions.HotelException;
import com.mycompany.hotel.model.Client;
import com.mycompany.hotel.model.HotelService;
import com.mycompany.hotel.model.Room;
import com.mycompany.hotel.model.SkillWorker;
import com.mycompany.hotel.model.StatRoom;
import com.mycompany.hotel.model.Worker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author alex
 */
public class Gestor {

    HashMap<String, Room> rooms = new HashMap<>();
    HashMap<Integer, Worker> workers = new HashMap<>();
    int money = 3000;

    public void addRoom(String id, int cap, HashSet<HotelService> listaServicios) {
        Room room = new Room(id, cap, listaServicios);
        rooms.put(id, room);
        System.out.println("La habitación " + id + " ha sido registrada correctamente.");
    }

    public void addWorker(int nif, String name, HashSet<SkillWorker> skills) {
        Worker worker = new Worker(nif, name, skills);
        workers.put(nif, worker);
        System.out.println("El trabajador " + name + " ha sido registrado correctamente.");
    }

    public void newReservation(int nif, int people, HashSet<HotelService> listaServicios, Room room) {
        Client client = new Client(nif, people, listaServicios);
        room.setClient(client);
        room.setStat(StatRoom.RESERVED);
        System.out.println("La habitación " + room.getId() + " ha sido reservada por el cliente " + nif);
    }

    public void infoHotel() {
        for (Room room : rooms.values()) {
            System.out.println(room);
        }
        for (Worker worker : workers.values()) {
            System.out.println(worker);
        }
    }

    public void problem(Room room) {
        room.setStat(StatRoom.BROKEN);
        System.out.println("La habitación " + room.getId() + " está estropeada.");
    }

    public void request(Room room, HashSet<SkillWorker> requests) {
        int counter = 0;
        room.setProblems(requests.size());
        for (SkillWorker skill : requests) {
            for (Worker worker : workers.values()) {
                if (counter < 1) {
                    if (worker.getSkills().contains(skill)) {
                        if (worker.getRoom() == null) {
                            worker.setRoom(room);
                            System.out.println(worker.getName() + " ha sido asignado/a a la habitación " + room.getId());
                            counter = 1;
                        }
                    }
                }
            }
            if (counter == 0) {
                System.out.println("No hay trabajadores disponibles para el servicio solicitado.");
                counter = 2;
            }
            counter = 0;
        }
    }

    public void finish(Room room) throws HotelException {
        int counter = 0;
        for (Worker worker : workers.values()) {
            if (worker.getRoom() != null && worker.getRoom().equals(room)) {
                if (!room.getStat().equals(StatRoom.RESERVED)) {
                    room.setStat(StatRoom.CLEAN);
                }
                worker.setRoom(null);
                counter += 1;
                room.restarProblem();
            }
        }
        if (counter == 0) {
            throw new HotelException(13);
        }
        System.out.println("Se han terminado los servicios en la habitación " + room.getId() + ".");
    }
    
    public boolean leave(Room room, int payed) throws HotelException {
        if (room.getProblems() > 0) {
            money = money - (payed/2);
            System.out.println("Los clientes no están satisfechos, has perdido " + payed/2 + "€");
        } else {
            money = money + payed;
            System.out.println("Los clientes se han ido satisfechos, has ganado " + payed + "€");
        }
        room.setStat(StatRoom.UNCLEAN);
        if (money <= 0) {
            return false;
        }
        return true;
    }
    
    public void money() {
        System.out.println("El hotel tiene " + money + "€ en la cuenta.");
    }
}
