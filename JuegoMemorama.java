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
    private JPanel marcadorPanel;
    private JLabel[] etiquetasPuntaje;
    private static final String RUTA_REVERSO = "reverso.png";



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
            tarjetas.add(new TarjetaRombo("Rombo " + i)); // Nueva figura añadida
        }
    }


    private void barajar() {
        Collections.shuffle(tarjetas);
    }

    public void iniciarJuego(JFrame frame) {
        // Preguntar por número de jugadores
        String[] opciones = {"2", "3", "4"};
        String seleccion = (String) JOptionPane.showInputDialog(
                frame,
                "Selecciona el número de jugadores:",
                "Número de Jugadores",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion != null) {
            numJugadores = Integer.parseInt(seleccion);
            nombresJugadores = new String[numJugadores];
            puntajes = new int[numJugadores];
            etiquetasPuntaje = new JLabel[numJugadores];

            // Pedir nombres
            for (int i = 0; i < numJugadores; i++) {
                nombresJugadores[i] = JOptionPane.showInputDialog(
                        frame,
                        "Ingresa el nombre del Jugador " + (i + 1)
                );
            }

            // Crear panel marcador lateral
            marcadorPanel = new JPanel();
            marcadorPanel.setLayout(new BoxLayout(marcadorPanel, BoxLayout.Y_AXIS));
            marcadorPanel.setBorder(BorderFactory.createTitledBorder("Marcador"));
            marcadorPanel.setBackground(new Color(250, 250, 250));
            marcadorPanel.setPreferredSize(new Dimension(200, 0));

            for (int i = 0; i < numJugadores; i++) {
                etiquetasPuntaje[i] = new JLabel(nombresJugadores[i] + ": 0 puntos");
                etiquetasPuntaje[i].setFont(new Font("Segoe UI", Font.BOLD, 20));
                etiquetasPuntaje[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                marcadorPanel.add(etiquetasPuntaje[i]);
            }

            frame.getContentPane().add(marcadorPanel, BorderLayout.EAST);

            // Mostrar tablero y turno inicial
            mostrarTablero(frame);
            actualizarTurnoLabel(frame);
        }
    }

    private ImageIcon escalarImagen(ImageIcon original, int ancho, int alto) {
        Image imagenOriginal = original.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }


    private void actualizarMarcador() {
        for (int i = 0; i < numJugadores; i++) {
            etiquetasPuntaje[i].setText(nombresJugadores[i] + ": " + puntajes[i] + " puntos");
        }
    }


    public void mostrarTablero(JFrame frame) {
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(4, 4, 10, 10)); // espacios entre botones
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.setBackground(Color.GRAY); // Fondo limpio

        for (int i = 0; i < tarjetas.size(); i++) {
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(80, 80));
            boton.setBackground(new Color(240, 240, 240));
            boton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            boton.setFocusPainted(false);
            boton.setIcon(new ImageIcon("Reverso.png"));
            int finalI = i;

            boton.addActionListener(e -> {
                if (bloqueado || boton.getIcon() != null && boton.getIcon().toString().equals(tarjetas.get(finalI).getIcono().toString())) {
                    return;
                }

                boton.setIcon(escalarImagen(tarjetas.get(finalI).getIcono(), boton.getWidth(), boton.getHeight()));


                if (primeraSeleccionada == null) {
                    primeraSeleccionada = tarjetas.get(finalI);
                    botonPrimero = boton;
                } else {
                    bloqueado = true;
                    Tarjeta segundaSeleccionada = tarjetas.get(finalI);
                    JButton botonSegundo = boton;

                    javax.swing.Timer timer = new javax.swing.Timer(500, ev -> {
                        if (primeraSeleccionada.getFigura().equals(segundaSeleccionada.getFigura())) {
                            botonPrimero.setEnabled(false);
                            botonSegundo.setEnabled(false);
                            aciertos++;
                            puntajes[turnos % numJugadores]++;
                            actualizarMarcador();
                            if (aciertos == tarjetas.size() / 2) {
                                mostrarGanador(frame);
                            }
                        } else {
                            botonPrimero.setIcon(escalarImagen(new ImageIcon(RUTA_REVERSO), botonPrimero.getWidth(), botonPrimero.getHeight()));
                            botonSegundo.setIcon(escalarImagen(new ImageIcon(RUTA_REVERSO), botonSegundo.getWidth(), botonSegundo.getHeight()));

                            turnos++;
                        }

                        primeraSeleccionada = null;
                        botonPrimero = null;
                        bloqueado = false;
                        actualizarTurnoLabel(frame);
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            });

            botones.add(boton);
            panelCentral.add(boton);
        }

        frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
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
