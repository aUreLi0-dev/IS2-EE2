package pe.edu.ulima.is2;

import java.util.Locale;

public class Calculadora {

    public int sumar(int a, int b){
        return a+b;
    }

    public int restar(int a, int b){
        return a-b;
    }

    public int multiplicar(int a, int b){
        return a*b;
    }

    public int dividir(int a, int b){
        if(b == 0){
            throw new IllegalArgumentException();
        }
        return a/b;
    }

    /*public void metodoConError(){  //para usar tes estáticos (spotbug)
        String texto = "hola";
        if (texto == "Hola"){
            System.out.println("Es igual");
        }
        texto = null;
        System.out.println(texto.toUpperCase());
    }*/

    public void metodoConError(){  //solucionado
        String texto = "hola";
        if (texto.equals("hola")){
            System.out.println("Es igual");
        }

        if(texto!=null){
            System.out.println(texto.toUpperCase(Locale.getDefault()));
        }

    }

    static void main() {
        System.out.println("Esta será una clase calculadora");
    }
}
