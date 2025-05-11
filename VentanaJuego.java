import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {
    public VentanaJuego() {
        setTitle("Memorama");
        setSize(1000, 1000); // Asegúrate de ajustar el tamaño para incluir el JLabel de turno
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
            UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 13));

            VentanaJuego ventana = new VentanaJuego();
            JuegoMemorama juego = new JuegoMemorama();

            // Crear y añadir el JLabel para mostrar el turno
            JLabel turnoLabel = new JLabel("Es el turno de: ", JLabel.CENTER);
            turnoLabel.setFont(new Font("Arial", Font.BOLD, 20));
            turnoLabel.setPreferredSize(new Dimension(400, 50));
            ventana.add(turnoLabel, BorderLayout.NORTH);

            juego.setTurnoLabel(turnoLabel);  // Asignar el JLabel al juego
            juego.iniciarJuego(ventana); // Inicia el juego pidiendo el número de jugadores

            ventana.setVisible(true);
        });
    }
}

