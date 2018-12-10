package Habitaciones;

import java.util.Scanner;

public class Habitacion  {
   private int numeroHab;
   private int nCamas;
   private double precio = 300.0D;

   public Habitacion(Scanner leer){
        this.numeroHab=leer.nextInt();
        this.nCamas=leer.nextInt();
   }

    public Habitacion() {

    }

    public double getPrecio() {
        return precio;
    }

}
