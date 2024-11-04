import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa un examen compuesto de varias preguntas.
 */
public class Test {
    private ArrayList<Question> preguntas;
    private int respuestasCorrectas;

    /**
     * Constructor de la clase Examen.
     */
    public Test() {
        this.preguntas = new ArrayList<>();
        this.respuestasCorrectas = 0;
    }

    /**
     * Agrega una pregunta al examen.
     * @param pregunta La pregunta a agregar.
     */
    public void agregarPregunta(Question pregunta) {
        preguntas.add(pregunta);
    }

    /**
     * Realiza el examen, permitiendo al alumno responder las preguntas.
     * @return La cantidad de respuestas correctas.
     */
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
     * Carga preguntas desde un archivo CSV y las agrega al examen.
     * @param nombreArchivo Nombre del archivo CSV.
     */
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
    
    

    /**
     * Obtiene el número total de preguntas en el examen.
     * @return El número total de preguntas.
     */
    public int obtenerNumeroPreguntas() {
        return preguntas.size();
    }
}
