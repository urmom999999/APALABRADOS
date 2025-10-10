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
    private boolean acertado = false;
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
                System.out.println("- " + palabra);}*/
            //Comprobar si el recuadro esta vacio o con mas de una letra, poner en mayusuculas
            comprobarAciertoIntentos();
        });
        //UNA LETRA
        letra.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                // Tomar solo la última letra escrita
                letra.setText(newValue.substring(newValue.length() - 1));
            }
        });
        /*MODO ENTER NO FUNCIONA AL SUPERPONER EL ENTER AL TEXTO
        letra.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                comprobarAciertoIntentos();
            }
        });*/


    }

    //PREDETERMINADA Y DESPUES DE GANAR
    private void mostrarPalabraAleatoria() {
        //RESETEAR
        button.setOnAction(event -> {
            comprobarAciertoIntentos();
        });
        letrasFallidas.clear();
        errores = 0;
        textooculto.setLength(0);
        //OBTENER
        int aleatorio = (int) Math.floor(Math.random() * palabras.size());
        String palabraAleatorioa = palabras.get(aleatorio);
        //PASAR A MAYUS?
        //DIVIDIR Y _ _ _
        dividida = palabraAleatorioa.split("");
        System.out.println(Arrays.toString(dividida));
        //OBTENER ALEATORIA,MAYUSCULAS?, DIVIDIRLA POR LETRAS Y MOSTRARLA CON _ _ _
        //REVISAR

        for (int i = 0; i < dividida.length; i++) {
            textooculto.append("_");
        }
        System.out.println(textooculto.toString());
        areaCompletar.setText(textooculto.toString());
    }

    //SI ACIERTO ACTUALIZO EL ESPACIO CON LA LETRA.
    private void comprobarAciertoIntentos() {
        //Obtener el texto en el text area
        textareaObtener = letra.getText().trim().toUpperCase();
        System.out.println(textareaObtener);
        //Comprobar si el recuadro esta vacio o con mas de una letra, poner en mayusuculas
        if (textareaObtener.length() != 1 || textareaObtener.isEmpty()) {
            System.out.println("Error el texto no es el adecuado! Errores: " + errores);
            ResultTries.setText("Error el texto no es el adecuado! Errores: " + errores);
            letra.clear();
            return;
        }
        if (letrasFallidas.contains(textareaObtener)) {
            System.out.println("Letra fallida ya añadida! Errores: " + errores);
            ResultTries.setText("Letra fallida, ya añadida! Errores: " + errores);
            letra.clear();
            return;
        } else if (letrasAcertadas.contains(textareaObtener)) {
            System.out.println("Letra acertada ya añadida!  ");
            ResultTries.setText("Letra acertada, ya añadida! Errores: " + errores);
            letra.clear();
            return;
        }
        acertado = false;
        //Comprobar (for) por si coincide en alguna posicion la letra
        for (int i = 0; i < dividida.length; i++) {
            //ERRROR SEGUIR AQUI ---------------------------------------------------------------------------
            if (dividida[i].equals(textareaObtener)) {
                acertado = true;
                break;
            }
        }
        //CAMBIAR LETRA POR LA POSICIÓN _
        //COMPROBAR SI LA LETRA YA FUE UTILIZADA ARRAY UTILIZDAS
        if (acertado == true) {
            System.out.println("Acierto!");
            ResultTries.setText("Acierto!");
            letrasAcertadas.add(textareaObtener);
            //FUNCION CAMBIAR LAS LETRAS CON FOR?
            actualizarTexto();
            actualizarLetrasAcertadas();
            //VICTORIA?
//-----------------------------------------------------------------------------
            if (textooculto.toString().equals(String.join("", dividida))) {
                ResultTries.setText("Victoria!");
                System.out.println("VICTORIA!");
                victorias++;
                letrasAcertadas.clear();
                actualizarLetrasAcertadas();
                letrasFallidas.clear();
                actualizarLetrasFallidas();
                contador.setText("Contador, VICTORIAS: " + victorias + " DERROTAS: " + derrotas);
                button.setOnAction(event -> {
                    mostrarPalabraAleatoria();
                });
            }


        }
        // else if (){};
        else if (acertado == false) {

            letrasFallidas.add(textareaObtener);
            //SI NO CUADRA NINGUNA AÑADIR 1 AL CONTADOR DE ERRORES
            errores++;
            System.out.println("No se acertó! Errores: " + errores);
            ResultTries.setText("No se acertó! Errores: " + errores);
            actualizarLetrasFallidas();
            //LETRAS FALLADAS AÑADIR BARRA ------------------------------------------------------------
            if (errores >= 6) {
                System.out.println("Maximo de errores alcanzado!");
                ResultTries.setText("Maximo de errores alcanzado!");
                //REALIZAR ALGO AL LLEGAR AL MAXIMO DE ERRORES?
                letra.clear();
                letrasAcertadas.clear();
                actualizarLetrasAcertadas();
                letrasFallidas.clear();
                actualizarLetrasFallidas();
                derrotas++;
                contador.setText("Contador, VICTORIAS: " + victorias + " DERROTAS: " + derrotas);
                errores = 0;
                button.setOnAction(event -> {
                    mostrarPalabraAleatoria();
                });

            }
            ;
        }
        ;
        letra.clear();
        //Contar aciertos y errores
        //letrasFallidas(String letraCorrecta);
        //COMPROBAR SI SE GANA, separada?
    }

    ;

    private void actualizarTexto() {
        StringBuilder nuevoTexto = new StringBuilder();
        for (int i = 0; i < dividida.length; i++) {
            //SIN EQUALS NO FUNCIONA
            if (dividida[i].equals(textareaObtener) || textooculto.charAt(i) != '_') {
                nuevoTexto.append(dividida[i]);
//añadir
                if (!letrasAcertadas.contains(dividida[i])) {
                    letrasAcertadas.add(dividida[i]);
                }
            } else {
                nuevoTexto.append("_");
            }
        }
        ;
        textooculto = nuevoTexto;
        System.out.println(textooculto);
        areaCompletar.setText(textooculto.toString());
    }

    private void actualizarLetrasAcertadas() {
        //Mostrar letras acertadas en verde y errores en rojo, poner dos text area acierto error?
        //ARREGLAR
        System.out.println("LETRAS QUE SE ACERTARON: " + letrasAcertadas);
        letrasAcertadasId.setText(String.valueOf("LETRAS QUE SE ACERTARON: " + letrasAcertadas));
    }

    private void actualizarLetrasFallidas() {
        //Mostrar letras acertadas en verde y errores en rojo, poner dos text area acierto error?
        //ARREGLAR
        System.out.println("LETRAS QUE SE FALLARON: " + letrasFallidas);
        letrasFalladasId.setText(String.valueOf("LETRAS QUE SE FALLARON: " + letrasFallidas));
    }
}
/*      System.out.println("\nLista de palabras:");
            for (String palabra : datos) {
        System.out.println("- " + palabra);
            }*/







