package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller {
  @FXML private TextField archivo;
  @FXML private TextField carpeta;
    @FXML private RadioButton directorio;
@FXML private TextArea agregar;
    @FXML private TextArea lecturaA;
@FXML private TextField busqueda;
@FXML private TextArea encontrado;


    //Habilita y deshabilita el TextField de directorio
    public void BotonDirectorio(ActionEvent event){

        if (directorio.isSelected()){
            carpeta.setDisable(false);
        }
        else{
            carpeta.setDisable(true);
        }
    }
    //crea archivos de texto
    public void accion(ActionEvent event){
        if (directorio.isSelected()){

            File folder=new File(carpeta.getText());
            folder.mkdir();
            String nombre=carpeta.getText()+"\\"+ archivo.getText()+".txt";
            File crear=new File(nombre);
            try {
                boolean a=crear.createNewFile();
                if(crear.exists()){
                    System.out.println("El archivo se creo correctamente");
                }
            }
            catch (IOException e){
                System.out.println("El archivo no se pudo crear"+e);
            }
        }
        else{

            String nombre=archivo.getText()+".txt";
            File crear=new File(nombre);
            try {
                boolean a=crear.createNewFile();
                if(crear.exists()){

                    System.out.println("El archivo se creo correctamente");
                }
            }
            catch (IOException e){
                System.out.println("El archivo no se pudo crear"+e);
            }
        }
    }
    //Retorna el nombre del archivo para trabajar si esta dentro de un directorio lo agrega
    public  String nombrearchivo(){
        String a;
        if (directorio.isSelected()){
            a=carpeta.getText()+"\\"+ archivo.getText()+".txt";
        }
        else{

            a=archivo.getText()+".txt";

        }
        return a;
    }
    //Elimina archivos de texto dentro o fuera de un directorio
    public void eliminar(){
        String nombre=nombrearchivo();
        File eliminar=new File(nombre);
        if (eliminar.delete()){
            System.out.println("Archivo eliminado correctamente");
        }
        else{
            System.out.println("Error al eliminar el archivo");
        }
    }
    //Activar el panel para escritura y ocultar el principal
    public void Escribir() throws Exception{
        String nom=nombrearchivo();
        FileWriter escritura=new FileWriter (nom,true);
        String informacion=agregar.getText();
escritura.write(informacion);
escritura.close();
    }
    //Activar el panel de lectura y ocultar el principal
public void leer() {
String nom=nombrearchivo();
File lectura= new File(nom);
try {
    Scanner entrada=new Scanner(lectura);
    while (entrada.hasNextLine()){
String linea=entrada.nextLine();
        System.out.println(linea);
lecturaA.setText(linea);
    }
}
catch (FileNotFoundException e){
   e.printStackTrace();
}
}
//Buscar palabras
public void Buscar() {
    String nom=nombrearchivo();
    File lectura= new File(nom);
    try {
        Scanner entrada=new Scanner(lectura);
        while (entrada.hasNextLine()){
            String linea=entrada.nextLine();
            String separar[]=linea.split(" ");
            String palabra=busqueda.getText();
           for (int x=0;x<separar.length;x++){
               if (separar[x].equals(palabra)){
                   System.out.println(linea);
                   encontrado.setText(linea);
                   break;
               }
           }
        }
    }
    catch (FileNotFoundException e){
        e.printStackTrace();
    }
}
}
