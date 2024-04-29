package com.mycompany.hotel.view;

import com.mycompany.hotel.exceptions.HotelException;
import com.mycompany.hotel.model.Client;
import com.mycompany.hotel.model.HotelService;
import com.mycompany.hotel.model.Room;
import com.mycompany.hotel.model.SkillWorker;
import com.mycompany.hotel.model.StatRoom;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author alex
 */
public class Interprete {

    private BufferedReader br;
    Gestor g = new Gestor();

    public Interprete() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String askComando(String message) {
        String comando = "";
        boolean error = true;
        do {
            try {
                System.out.print(message);
                comando = br.readLine().toUpperCase();
                if (comando == null) {
                    System.out.println("El comando no debe estar vacío.");
                } else {
                    error = false;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } while (error);
        return comando;
    }

    public void checkArgs(String[] argumentos, int num) throws HotelException {
        if (argumentos.length != num) {
            throw new HotelException(1);
        }
    }

    public int toInt(String string) throws HotelException {
        int num;
        try {
            num = Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            throw new HotelException(2);
        }
        if (num <= 0) {
            throw new HotelException(3);
        }
        return num;
    }

    public double toDouble(String string) throws HotelException {
        double num;
        try {
            num = Double.parseDouble(string);
        } catch (NumberFormatException ex) {
            throw new HotelException(2);
        }
        if (num <= 0) {
            throw new HotelException(3);
        }
        return num;
    }

    public void checkRoom(String id) throws HotelException {
        if (g.rooms.containsKey(id)) {
            throw new HotelException(4);
        }
    }

    public HashSet<HotelService> toService(String services) throws HotelException {
        String[] serviceParts = services.split(",");
        HashSet<HotelService> listaServicios = new HashSet<>();
        for (String service : serviceParts) {
            for (HotelService hotelService : HotelService.values()) {
                if (service.equalsIgnoreCase(String.valueOf(hotelService))) {
                    listaServicios.add(hotelService);
                }
            }
        }
        if (serviceParts.length != listaServicios.size()) {
            throw new HotelException(5);
        }
        return listaServicios;
    }

    public void addRoom(String id, String cap, String services) throws HotelException {
        checkRoom(id);
        int capInt = toInt(cap);
        HashSet<HotelService> listaServicios = toService(services);
        g.addRoom(id, capInt, listaServicios);
    }

    public void checkNif(int nif) throws HotelException {
        if (g.workers.containsKey(nif)) {
            throw new HotelException(6);
        }
    }

    public HashSet<SkillWorker> toSkill(String skills) throws HotelException {
        String[] skillsParts = skills.split(",");
        HashSet<SkillWorker> listaSkills = new HashSet<>();
        for (String skill : skillsParts) {
            for (SkillWorker skillWorker : SkillWorker.values()) {
                if (skill.equalsIgnoreCase(String.valueOf(skillWorker))) {
                    listaSkills.add(skillWorker);
                }
            }
        }
        if (skillsParts.length != listaSkills.size()) {
            throw new HotelException(7);
        }
        return listaSkills;
    }

    public void checkLengthNif(String nif) throws HotelException {
        if (nif.length() != 8) {
            throw new HotelException(8);
        }
    }

    public void addWorker(String nif, String name, String skills) throws HotelException {
        int nifInt = toInt(nif);
        checkLengthNif(nif);
        checkNif(nifInt);
        HashSet<SkillWorker> listaSkills = toSkill(skills);
        g.addWorker(nifInt, name, listaSkills);
    }

    public Room takeRoom(int people, HashSet<HotelService> listaServiciosCliente) throws HotelException {
        Room finalRoom = null;
        for (Room room: g.rooms.values()) {
            if (room.getStat().equals(StatRoom.CLEAN)) {
                if (room.getServices().containsAll(listaServiciosCliente)) {
                    if (finalRoom == null) {
                        finalRoom = room;
                    } else if (finalRoom.getCap() > room.getCap()) {
                        finalRoom = room;
                    }
                }
            }
        }
        if (finalRoom == null) {
            throw new HotelException(9);
        }
        return finalRoom;
    }
    
    public void newReservation(String nif, String people, String services) throws HotelException {
        emptyRooms();
        checkLengthNif(nif);
        int nifInt = toInt(nif);
        int peopleInt = toInt(people);
        HashSet<HotelService> listaServiciosCliente = toService(services);
        Room room = takeRoom(peopleInt, listaServiciosCliente);
        g.newReservation(nifInt, peopleInt, listaServiciosCliente, room);
    }
    
    public void infoHotel() throws HotelException {
        emptyRooms();
        g.infoHotel();
    }
    
    public void checkStatRoomBroken(Room room) throws HotelException {
        if (room.getStat().equals(StatRoom.BROKEN)) {
            throw new HotelException(12);
        }
    }
    
    public void problem(String id) throws HotelException {
        emptyRooms();
        roomsContainsId(id);
        Room room = g.rooms.get(id);
        checkStatRoomBroken(room);
        g.problem(room);
        if (room.getClient() != null) {
            Client client = room.getClient();
            room.setClient(null);
            room = takeRoom(client.getPeople(), client.getServices());
            g.newReservation(client.getNif(), client.getPeople(), client.getServices(), room);
        }
    }
    
    public void emptyRooms() throws HotelException {
        if (g.rooms.isEmpty()) {
            throw new HotelException(10);
        }
    }
    
    public void roomsContainsId(String id) throws HotelException {
        if (!g.rooms.keySet().contains(id)) {
            throw new HotelException(11);
        }
    }
    
    public void request(String id, String request) throws HotelException {
        emptyRooms();
        roomsContainsId(id);
        HashSet<SkillWorker> requests = toSkill(request);
        Room room = g.rooms.get(id);
        g.request(room, requests);
    }
    
    public void finish(String id) throws HotelException {
        emptyRooms();
        roomsContainsId(id);
        Room room = g.rooms.get(id);
        g.finish(room);
    }
    
    public void emptyRoom(Room room) throws HotelException {
        if (room.getClient() == null) {
            throw new HotelException(14);
        }
    }
    
    public boolean leave(String id, String payed) throws HotelException {
        emptyRooms();
        roomsContainsId(id);
        int payedInt = toInt(payed);
        Room room = g.rooms.get(id);
        emptyRoom(room);
        boolean menu = g.leave(room, payedInt);
        return menu;
    }
    
    public void money() throws HotelException {
        g.money();
    }
    
}
