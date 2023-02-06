package repoblacion.utilidides;

import org.iesalandalus.programacion.utilidades.Entrada;

import repoblacion.modelo.Bosque;

public class Consola {
    public static int alto=0;
    public static int ancho=0;
    public static int poblacion=0;

    private Consola(){}

    public static int leerAnchura(){
        while(ancho<=0){
            System.out.print("introduce una anchura válida mayor que 0: ");
            ancho = Entrada.entero();
        }return ancho;
    }

    public static int leerAltura(){
        while(alto<=0){
            System.out.print("introduce una altura válida mayor que 0: ");
            alto = Entrada.entero();
        }return alto;
    }

    public static int leerPoblacion(){
        while(poblacion<=0){
            System.out.print("introduce una población válida mayor que 0: ");
            poblacion = Entrada.entero();
        }return poblacion;
    }

}
