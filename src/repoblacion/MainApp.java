package repoblacion;

import repoblacion.modelo.Bosque;
import repoblacion.utilidides.Consola;

public class MainApp {
    static Bosque bosque;

    public static void main(String[] args) {
        int anchura=Consola.leerAnchura();
        int altura=Consola.leerAltura();
        int poblacion=Consola.leerPoblacion();
        try {
            bosque = new Bosque(anchura, altura, poblacion);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
