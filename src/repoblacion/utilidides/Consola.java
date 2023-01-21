package repoblacion.utilidides;

import javax.swing.JOptionPane;

import org.iesalandalus.programacion.utilidades.Entrada;

import repoblacion.modelo.Arbol;
import repoblacion.modelo.Bosque;
import repoblacion.modelo.Especie;
import repoblacion.modelo.Posicion;

public class Consola {
    public static int alto=0;
    public static int ancho=0;
    public static int poblacion=0;

    private Consola(){}

    public static int leerAnchura(){
        while(ancho<=0){
            System.out.println("introduce una anchura válida mayor que 0.");
            ancho = Entrada.entero();
        }return ancho;
    }

    public static int leerAltura(){
        while(alto<=0){
            System.out.println("introduce una altura válida mayor que 0.");
            alto = Entrada.entero();
        }return alto;
    }

    public static int leerPoblacion(){
        while(poblacion<=0){
            System.out.println("introduce una población válida mayor que 0.");
            poblacion = Entrada.entero();
        }return poblacion;
    }
}
