/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.exceptions;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alex
 */
public class HotelException extends Exception {
    
    private static final int COMMAND = 0;
    private static final int NUM_ARGS = 1;
    private static final int STRING_NUM = 2;
    private static final int NEGATIVE_NUMBER = 3;
    private static final int ROOM_EXISTS = 4;
    private static final int NO_SERVICE = 5;
    private static final int WORKER_EXISTS = 6;
    private static final int WRONG_SKILLS = 7;
    private static final int LENGTH_NIF = 8;
    private static final int NO_ROOM_FOR_CLIENT = 9;
    private static final int NO_ROOMS = 10;
    private static final int ROOM_NO_EXISTS = 11;
    private static final int ROOM_BROKEN = 12;
    private static final int NO_WORKERS_ROOM = 13;
    private static final int NO_CLIENTS_ROOM = 14;
    private static final int NO_MONEY = 15;
    
    private final List<String> mensajes = Arrays.asList(
            "Error 01: Comando incorrecto.",
            "Error 02: Número de argumentos incorrecto.",
            "Error 03: Tipo de dato incorrecto (un número no puede contener letras).",
            "Error 04: No puede ser negativo.",
            "Error 05: La habitación ya existe.",
            "Error 06: Servicios incorrectos.",
            "Error 07: El trabajador ya existe.",
            "Error 08: Habilidades incorrectas.",
            "Error 09: El NIF debe tener 8 dígitos y no contener la letra.",
            "Error 10: No hay ninguna habitación para el cliente.",
            "Error 11: No hay habitaciones registradas.",
            "Error 12: La habitación indicada no existe.",
            "Error 13: La habitación ya está estropeada.",
            "Error 14: No hay trabajadores en la habitación indicada.",
            "Error 15: No hay clientes en la habitación indicada.",
            "Error 16: El hotel ha quebrado, ¡has perdido, mongolo!"
    );
    
    private final int code;
    
    public HotelException (int code) {
        this.code = code;
    }
    
    @Override
    public String getMessage() {
        return mensajes.get(code);
    }
}
