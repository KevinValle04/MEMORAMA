import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class JuegoMemorama {
    private List<Tarjeta> tarjetas;
    private List<JButton> botones;
    private int turnos;
    private Tarjeta primeraSeleccionada = null;
    private JButton botonPrimero = null;
    private boolean bloqueado = false;
    private int aciertos = 0;
    private int numJugadores;
    private String[] nombresJugadores;
    private int[] puntajes;
    private JLabel turnoLabel;

    public JuegoMemorama() {
        tarjetas = new ArrayList<>();
        botones = new ArrayList<>();
        turnos = 0;
        numJugadores = 0;
        nombresJugadores = new String[4];  // Para soportar hasta 4 jugadores
        puntajes = new int[4];  // Para soportar hasta 4 jugadores
        crearMazo();
        barajar();
    }

    private void crearMazo() {
        for (int i = 0; i < 2; i++) {
            tarjetas.add(new TarjetaCirculo("Círculo " + i));
            tarjetas.add(new TarjetaCuadrado("Cuadrado " + i));
            tarjetas.add(new TarjetaTriangulo("Triángulo " + i));
            tarjetas.add(new TarjetaHexagono("Hexágono " + i));
            tarjetas.add(new TarjetaEstrella("Estrella " + i));
        }
    }

    private void barajar() {
        Collections.shuffle(tarjetas);
    }

    public void iniciarJuego(JFrame frame) {
        // Preguntar por el número de jugadores
        String[] opciones = {"2", "3", "4"};
        String seleccion = (String) JOptionPane.showInputDialog(frame, "Selecciona el número de jugadores:",
                "Número de Jugadores", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            numJugadores = Integer.parseInt(seleccion);
            puntajes = new int[numJugadores];  // Asegura que el arreglo de puntajes tenga el tamaño adecuado

            // Pedir nombres de los jugadores
            for (int i = 0; i < numJugadores; i++) {
                nombresJugadores[i] = JOptionPane.showInputDialog(frame, "Ingresa el nombre del Jugador " + (i + 1));
            }

            // Mostrar tablero
            mostrarTablero(frame);
            // Mostrar el primer turno
            actualizarTurnoLabel(frame);
        }
    }

    public void mostrarTablero(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4)); // Cambia las filas y columnas si es necesario

        for (int i = 0; i < tarjetas.size(); i++) {
            JButton boton = new JButton();
            boton.setIcon(new ImageIcon("C:\\Users\\Usuario\\IdeaProjects\\Memorama\\src\\Imagenes\\Reverso.png"));
            int finalI = i;

            boton.addActionListener(e -> {
                if (bloqueado || boton.getIcon() != null && boton.getIcon().toString().equals(tarjetas.get(finalI).getIcono().toString())) {
                    return;
                }

                boton.setIcon(tarjetas.get(finalI).getIcono());

                if (primeraSeleccionada == null) {
                    primeraSeleccionada = tarjetas.get(finalI);
                    botonPrimero = boton;
                } else {
                    bloqueado = true;
                    Tarjeta segundaSeleccionada = tarjetas.get(finalI);
                    JButton botonSegundo = boton;

                    // Comparar después de medio segundo
                    javax.swing.Timer timer = new javax.swing.Timer(500, ev -> {
                        if (primeraSeleccionada.getFigura().equals(segundaSeleccionada.getFigura())) {
                            botonPrimero.setEnabled(false);
                            botonSegundo.setEnabled(false);
                            aciertos++;
                            // Asignar el punto al jugador actual (por ejemplo, en turno 0, 1, etc.)
                            puntajes[turnos % numJugadores]++;
                            if (aciertos == tarjetas.size() / 2) {
                                mostrarGanador(frame);
                            }
                        } else {
                            botonPrimero.setIcon(new ImageIcon("imagenes/reverso.png"));
                            botonSegundo.setIcon(new ImageIcon("imagenes/reverso.png"));
                        }

                        primeraSeleccionada = null;
                        botonPrimero = null;
                        bloqueado = false;
                        turnos++;
                        // Actualizar el turno
                        actualizarTurnoLabel(frame);
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            });

            botones.add(boton);
            panel.add(boton);
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void actualizarTurnoLabel(JFrame frame) {
        if (turnos < numJugadores) {
            turnoLabel.setText("Es el turno de: " + nombresJugadores[turnos % numJugadores]);
        } else {
            turnoLabel.setText("Es el turno de: " + nombresJugadores[turnos % numJugadores]);
        }
    }

    private void mostrarGanador(JFrame frame) {
        // Determinar el ganador (el jugador con más puntos)
        int maxPuntaje = -1;
        int ganador = -1;

        for (int i = 0; i < numJugadores; i++) {
            if (puntajes[i] > maxPuntaje) {
                maxPuntaje = puntajes[i];
                ganador = i;
            }
        }

        String mensaje = "El ganador es " + nombresJugadores[ganador] + " con " + maxPuntaje + " puntos!";
        JOptionPane.showMessageDialog(frame, mensaje);
    }

    // Método para inicializar el JLabel de turno
    public void setTurnoLabel(JLabel turnoLabel) {
        this.turnoLabel = turnoLabel;
    }
}
