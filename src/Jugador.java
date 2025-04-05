import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 10;
    private int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];

    private Random r = new Random(); // la suerte del jugador

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll(); // limpia el panel y nos da nuevas cartas
        int posicion = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN);
            posicion -= DISTANCIA;
        }
        pnl.repaint(); // actualiza el panel
    }

    public String getGrupos() {
        String mensaje = "No se encontraron figuras";
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador > 1) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            mensaje = "Se encontraron los siguientes grupos:\n";
            int fila = 0;
            for (int contador : contadores) {
                if (contador > 1) {
                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[fila] + "\n";
                }
                fila++;
            }
        }

        return mensaje;
    }

    
    public String getEscaleras() {
        String mensaje = "No se encontraron escaleras.";
        int[][] contadores = new int[Pinta.values().length][NombreCarta.values().length];

        for (Carta c : cartas) {
            contadores[c.getPinta().ordinal()][c.getNombre().ordinal()] = 1;
        }

        boolean hayEscaleras = false;
        mensaje = "Se encontraron las siguientes escaleras:\n";

        for (int i = 0; i < Pinta.values().length; i++) {
            int contador = 0;
            String escalera = "Escalera de " + Pinta.values()[i] + ": ";

            for (int j = 0; j < NombreCarta.values().length; j++) {
                if (contadores[i][j] == 1) {
                    contador++;
                    escalera += NombreCarta.values()[j] + " ";
                } else {
                    if (contador >= 3) {
                        mensaje += escalera + "\n";
                        hayEscaleras = true;
                    }
                    contador = 0;
                    escalera = "Escalera de " + Pinta.values()[i] + ": ";
                }
            }

            if (contador >= 3) {
                mensaje += escalera + "\n";
                hayEscaleras = true;
            }
        }

        return hayEscaleras ? mensaje : "No se encontraron escaleras.";
    }

    
    public int calcularPuntaje() {
        int[] contadores = new int[NombreCarta.values().length];

      
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        int puntaje = 0;

        
        for (Carta c : cartas) {
            int indice = c.getNombre().ordinal();

            
            if (contadores[indice] == 1) {
               
                if (indice == 0 || indice >= 9) { 
                    puntaje += 10;
                } else { 
                    puntaje += (indice + 1); 
                }
            }
        }

        return puntaje;
    }

}
