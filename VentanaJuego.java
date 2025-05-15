import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {
    public VentanaJuego() {
        setTitle("Memorama");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
            UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 13));

            String[] tematicas = {"Figuras", "Emojis", "Animales"};
            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona una temática:",
                    "Temática",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tematicas,
                    tematicas[0]
            );

            Tematica tematica = new TematicaFiguras();
            if ("Emojis".equals(seleccion)) {
                tematica = new TematicaEmojis();
            } else if ("Animales".equals(seleccion)) {
                tematica = new TematicaAnimales();
            }

            VentanaJuego ventana = new VentanaJuego();
            JuegoMemorama juego = new JuegoMemorama(tematica);

            JLabel turnoLabel = new JLabel("Es el turno de: ", JLabel.CENTER);
            turnoLabel.setFont(new Font("Arial", Font.BOLD, 20));
            turnoLabel.setPreferredSize(new Dimension(400, 50));
            ventana.add(turnoLabel, BorderLayout.NORTH);

            juego.setTurnoLabel(turnoLabel);
            juego.iniciarJuego(ventana);

            ventana.setVisible(true);
        });
    }
}