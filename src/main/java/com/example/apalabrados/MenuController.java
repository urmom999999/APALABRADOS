package com.example.apalabrados;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {
    @FXML
    private Button button;
    @FXML
    private TextArea letra;
    @FXML
    private Text areaCompletar;
    @FXML
    private Text ResultTries;
    @FXML
    private Text letrasAcertadasId;
    @FXML
    private Text letrasFalladasId;
    @FXML
    private Text contador;

    private List<String> palabras = new ArrayList<>();
    private String[] dividida;
    private List<String> letrasFallidas = new ArrayList<>();
    private List<String> letrasAcertadas = new ArrayList<>();
    private int errores = 0;
    private int victorias = 0;
    private int derrotas = 0;
    private boolean acertado=false;
    private String palabraActual;
    private StringBuilder palabraOculta;
    private String textareaObtener;
    StringBuilder textooculto = new StringBuilder();

    @FXML
    private void initialize() {


        palabras = SQLread.obtenerPalabras();
        mostrarPalabraAleatoria();
//Creo SQL para continuar
        button.setOnAction(event -> {

            //TEST
           /* System.out.println("Cargando palabras:");
            for (String palabra : palabras) {
                System.out.println("- " + palabra);
            }*/

            //Comprobar si el recuadro esta vacio o con mas de una letra, poner en mayusuculas
            comprobarAciertoIntentos();

        });




    }
    //PREDETERMINADA Y DESPUES DE GANAR
    private void mostrarPalabraAleatoria(){
        //RESETEAR
        letrasFallidas.clear();
        errores = 0;
        textooculto.setLength(0);
        //OBTENER
        int aleatorio= (int) Math.floor(Math.random()*palabras.size());
        String palabraAleatorioa =palabras.get(aleatorio);
        //PASAR A MAYUS?
        //DIVIDIR Y _ _ _
        dividida=palabraAleatorioa.split("");
        System.out.println(Arrays.toString(dividida));
        //OBTENER ALEATORIA,MAYUSCULAS?, DIVIDIRLA POR LETRAS Y MOSTRARLA CON _ _ _
        //REVISAR

for(int i=0;i<dividida.length;i++){
    textooculto.append("_");
}
        System.out.println(textooculto.toString());
        areaCompletar.setText(textooculto.toString());
    }
    //SI ACIERTO ACTUALIZO EL ESPACIO CON LA LETRA.
    private void comprobarAciertoIntentos(){
        //Obtener el texto en el text area
            textareaObtener =letra.getText().trim().toUpperCase();
            System.out.println(textareaObtener);
        //Comprobar si el recuadro esta vacio o con mas de una letra, poner en mayusuculas
if ( textareaObtener.length()!=1 || textareaObtener.isEmpty()){
    System.out.println("Error el texto no es el adecuado");
    letra.clear();
    return;
}
        if(letrasFallidas.contains(textareaObtener)){
            System.out.println("Letra ya añadida!");
            letra.clear();
            return;
        }
        letrasAcertadas.add(textareaObtener);
        //Comprobar (for) por si coincide en alguna posicion la letra
        acertado=false;
        for(int i=0;i<dividida.length;i++){
            //ERRROR SEGUIR AQUI ---------------------------------------------------------------------------
            if (dividida[i].equals(textareaObtener)){
                acertado= true;
            }}

                //CAMBIAR LETRA POR LA POSICIÓN _

            //COMPROBAR SI LA LETRA YA FUE UTILIZADA ARRAY UTILIZDAS
        if (acertado==true){
            System.out.println("Acierto!");
            //FUNCION CAMBIAR LAS LETRAS CON FOR?
            actualizarTexto();
            //VICTORIA?

        }
           // else if (){};
         if (acertado==false){
            System.out.println("No se acertó!");
             letrasFallidas.add(textareaObtener);
             //SI NO CUADRA NINGUNA AÑADIR 1 AL CONTADOR DE ERRORES
             errores++;
             actualizarLetrasFallidas();
             //LETRAS FALLADAS AÑADIR BARRA ------------------------------------------------------------
             if(errores>=6){System.out.println("Maximo de errores alcanzado!");
             //REALIZAR ALGO AL LLEGAR AL MAXIMO DE ERRORES?

                 mostrarPalabraAleatoria();
                  };
        };
        letra.clear();
        //Contar aciertos y errores
        //letrasFallidas(String letraCorrecta);
        //COMPROBAR SI SE GANA, separada?
    };
    private void actualizarTexto(){
        StringBuilder nuevoTexto = new StringBuilder();
        for(int i=0;i<dividida.length;i++){
            //SIN EQUALS NO FUNCIONA
            if(dividida[i].equals(textareaObtener) || textooculto.charAt(i) != '_'){
                nuevoTexto.append(dividida[i]);
//añadir
                if(!letrasAcertadas.contains(dividida[i])){letrasAcertadas.add(dividida[i]);}
            } else {nuevoTexto.append("_");
        }
    };
        textooculto=nuevoTexto;
        System.out.println(textooculto);
        areaCompletar.setText(textooculto.toString());}

    private void actualizarLetrasFallidas() {
        //Mostrar letras acertadas en verde y errores en rojo, poner dos text area acierto error?
        //ARREGLAR
        System.out.println("LETRAS QUE SE ACERTARON: "+ letrasFallidas);
        letrasFalladasId.setText(String.valueOf("LETRAS QUE SE FALLARON: "+letrasFallidas));
    }



private void contarVictorias(){
    //Contar numero de victorias,derrotas en un contador?

}
}
/*      System.out.println("\nLista de palabras:");
            for (String palabra : datos) {
        System.out.println("- " + palabra);
            }*/







