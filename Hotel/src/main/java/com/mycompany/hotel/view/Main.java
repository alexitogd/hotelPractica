/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hotel.view;

import com.mycompany.hotel.exceptions.HotelException;

/**
 *
 * @author alex
 */
public class Main {

    public static void main(String[] args) {
        
        Interprete i = new Interprete();
        
        boolean menu = true;
        do {
            try {
                String comando = i.askComando(">");
                String[] argumentos = comando.split(" ");
                switch (argumentos[0]) {
                    case "ROOM":
                        i.checkArgs(argumentos, 4);
                        i.addRoom(argumentos[1], argumentos[2], argumentos[3]);
                        break;
                    case "WORKER":
                        i.checkArgs(argumentos, 4);
                        i.addWorker(argumentos[1], argumentos[2], argumentos[3]);
                        break;
                    case "RESERVATION":
                        i.checkArgs(argumentos, 4);
                        i.newReservation(argumentos[1], argumentos[2], argumentos[3]);
                        break;
                    case "HOTEL":
                        i.checkArgs(argumentos, 1);
                        i.infoHotel();
                        break;
                    case "PROBLEM":
                        i.checkArgs(argumentos, 2);
                        i.problem(argumentos[1]);
                        break;
                    case "REQUEST":
                        i.checkArgs(argumentos, 3);
                        i.request(argumentos[1], argumentos[2]);
                        break;
                    case "FINISH":
                        i.checkArgs(argumentos, 2);
                        i.finish(argumentos[1]);
                        break;
                    case "LEAVE":
                        i.checkArgs(argumentos, 3);
                        menu = i.leave(argumentos[1], argumentos[2]);
                        if (!menu) {
                            throw new HotelException(15);
                        }
                        break;
                    case "MONEY":
                        i.checkArgs(argumentos, 1);
                        i.money();
                        break;
                    case "EXIT":
                        menu = false;
                        System.out.println("Salir del programa.");
                        break;
                    default:
                        throw new HotelException(0);
                }
                
            } catch (HotelException ex) {
                System.out.println(ex.getMessage());
            }
        } while (menu);
    }
}
