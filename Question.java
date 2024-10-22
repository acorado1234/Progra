import java.util.ArrayList;

public class Question{
  private String enunciado;
  private ArrayList<String> opciones;
  private int respuestaCorrecta;

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
}
