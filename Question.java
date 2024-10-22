import java.util.ArrayList;
/**
 * Clase que representa una pregunta de opción múltiples
 */
public class Question{
  private String enunciado;
  private ArrayList<String> opciones;
  private int respuestaCorrecta;

    
  /**
    * Constructor de la clase Pregunta.
    * @param enunciado El enunciado de la pregunta.
    * @param opciones Las posibles respuestas de opción múltiple.
    * @param respuestaCorrecta El índice de la respuesta correcta.
    */
  public Question(String enunciado, ArrayList<String> opciones, int respuestaCorrecta) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    /**
     * Muestra la pregunta y las opciones.
     */
    public void mostrarPregunta() {
        System.out.println(enunciado);
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
    }
    /**
     * Verifica si la respuesta proporcionada es correcta.
     * @param respuesta El índice de la respuesta seleccionada por el alumno.
     * @return true si la respuesta es correcta, false en caso contrario.
     */
  public boolean esRespuestaCorrecta(int respuesta) {
         if (respuesta == this.respuestaCorrecta) {
             return true;
         } else{
            return false;
         }
    }
}
