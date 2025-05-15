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
    private static final String RUTA_REVERSO = "D:\\descargas\\Memorama (1)\\src\\imagenes\\reverso.png";
    private Tematica tematica;
    private JPanel panelCentral;

    public JuegoMemorama(Tematica tematica) {
        this.tematica = tematica;
        tarjetas = new ArrayList<>();
        botones = new ArrayList<>();
        turnos = 0;
        numJugadores = 0;
        nombresJugadores = new String[4];  // Para soportar hasta 4 jugadores
        puntajes = new int[4];  // Para soportar hasta 4 jugadores
        crearMazo(); // ahora usará la temática para generar las tarjetas
        Collections.shuffle(tarjetas);
    }

    public void iniciarJuego(JFrame frame) {
        // Ya no pedimos la temática aquí

        tarjetas = tematica.generarTarjetas();
        Collections.shuffle(tarjetas);

        // Selección de número de jugadores
        String[] opciones = {"2", "3", "4"};
        String seleccionJugadores = (String) JOptionPane.showInputDialog(
                frame,
                "Selecciona el número de jugadores:",
                "Número de Jugadores",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccionJugadores != null) {
            numJugadores = Integer.parseInt(seleccionJugadores);
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
        panelCentral = new JPanel();  // Guarda la referencia en el atributo de la clase
        panelCentral.setLayout(new GridLayout(4, 4, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.setBackground(Color.GRAY);

        for (int i = 0; i < tarjetas.size(); i++) {
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(80, 80));
            boton.setBackground(new Color(240, 240, 240));
            boton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            boton.setFocusPainted(false);
            boton.setIcon(new ImageIcon(RUTA_REVERSO));
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

                            int puntosGanados = 1; // Valor por defecto
                            if (tematica instanceof TematicaFiguras) {
                                puntosGanados = ((TematicaFiguras) tematica).obtenerValorFigura(primeraSeleccionada.getFigura());
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "¡Pareja de " + primeraSeleccionada.getFigura() + "! Ganaste " + puntosGanados + " puntos.",
                                        "¡Buen trabajo!",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }

                            puntajes[turnos % numJugadores] += puntosGanados;
                            actualizarMarcador();

                            // Mostrar detalle especial si la temática lo tiene
                            if (tematica instanceof TematicaAnimales) {
                                ((TematicaAnimales) tematica).mostrarDatoCurioso(primeraSeleccionada.getFigura(), frame);
                            }

                            if (tematica instanceof TematicaEmojis) {
                                ((TematicaEmojis) tematica).mostrarFondoTemporal(panelCentral);
                            }

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
        turnoLabel.setText("Es el turno de: " + nombresJugadores[turnos % numJugadores]);
    }

    private void mostrarGanador(JFrame frame) {
        int maxPuntaje = -1;
        List<Integer> ganadores = new ArrayList<>();

        // Encontrar el puntaje máximo
        for (int puntaje : puntajes) {
            if (puntaje > maxPuntaje) {
                maxPuntaje = puntaje;
            }
        }

        // Buscar a todos los jugadores con ese puntaje
        for (int i = 0; i < puntajes.length; i++) {
            if (puntajes[i] == maxPuntaje) {
                ganadores.add(i);
            }
        }

        // Mostrar resultado
        if (ganadores.size() == 1) {
            int indiceGanador = ganadores.get(0);
            JOptionPane.showMessageDialog(frame, "¡Ganó " + nombresJugadores[indiceGanador] + " con " + maxPuntaje + " puntos!");
        } else {
            StringBuilder mensaje = new StringBuilder("¡Empate entre: ");
            for (int i = 0; i < ganadores.size(); i++) {
                mensaje.append(nombresJugadores[ganadores.get(i)]);
                if (i < ganadores.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append(" con ").append(maxPuntaje).append(" puntos!");
            JOptionPane.showMessageDialog(frame, mensaje.toString());
        }
    }


    public void setTurnoLabel(JLabel turnoLabel) {
        this.turnoLabel = turnoLabel;
    }

    private void crearMazo() {
        tarjetas = tematica.generarTarjetas(); // delega en la temática
    }

}
