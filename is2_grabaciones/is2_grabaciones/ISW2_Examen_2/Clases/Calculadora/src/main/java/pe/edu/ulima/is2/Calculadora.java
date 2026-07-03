package pe.edu.ulima.is2;

import java.util.Locale;

public class Calculadora {

    public int sumar(int n1, int n2){
        return n1+n2;
    }

    public int restar(int n1, int n2){
        return n1-n2;
    }

    public int multiplicar(int n1, int n2){
        return n1*n2;
    }

    public int dividir(int n1, int n2){
        if(n2 == 0){
            throw new IllegalArgumentException();
        }
        return n1/n2;
    }

    public void metodoConError(){
        String texto = "Hola IS2";

        if (texto.equals("HolaIS2")){ // Siempre sale falso, porque no se compara con == los strings
            System.out.println("Es igual");
        }

        if(texto != null) {
            System.out.println(texto.toUpperCase(Locale.getDefault()));
        }
    }

    public static void main(String[] args) {
        System.out.println("Esta será la clase calculadora");
    }

    // Realmente maven no lo ejecuta directamente, hay más pasos que se tienen que hacer para hacer un despliegue continuo.
}
