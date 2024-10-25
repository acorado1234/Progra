import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test{
  private ArrayList<Question> preguntas;
  private int respuestasCorrectas;

  public Test(){
    this.preguntas = new ArrayList<>();
    this.respuestasCorrectas = 0;
  }
  public void agregarPregunta(Question pregunta) {
    preguntas.add(pregunta);
    }
  
  public Void realizarExamen() {
        Scanner scanner = new Scanner(System.in);
        for (Question pregunta : preguntas) {
            pregunta.mostrarPregunta();
            System.out.print("Selecciona tu respuesta: ");
            int respuesta = scanner.nextInt();
            //scanner.nextLine();

            if (pregunta.esRespuestaCorrecta(respuesta)) {
                this.respuestasCorrectas++;
            }
        }
        return null;
    }
  public int getRespuestasCorrectas(){
        return this.respuestasCorrectas;
    }
  /**
  *Carga preguntas desde el CSV y las agrega a un examen
  *@param void nombreArchivo nombre del csv
  *\
  public void cargarPreguntasDesdeCSV(String nombreArchivo) {

    String linea; 
    try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
      br.readLine();

      while ((linea = br.readLine()) != null) {
        ArrayList<String> opciones = new ArrayList<>();
        String[] datos = linea.split(",");
        String enunciado = datos[0];
        opciones.add(datos[1]);
        opciones.add(datos[2]);
        opciones.add(datos[3]);
        opciones.add(datos[4]);
        int respuestaCorrecta = Integer.parseInt(datos[5]);
        Question pregunta = new Question(enunciado, opciones, respuestaCorrecta);

        preguntas.add(pregunta);
      }
    } catch(IOException e){
      System.out.println("Error al leer el archivo: " + e.getMessage());
    }
  }
  
}
