import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {
    public VentanaJuego() {
        setTitle("Memorama");
        setSize(400, 500); // Asegúrate de ajustar el tamaño para incluir el JLabel de turno
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaJuego ventana = new VentanaJuego();
            JuegoMemorama juego = new JuegoMemorama();

            // Crear y añadir el JLabel para mostrar el turno
            JLabel turnoLabel = new JLabel("Es el turno de: ", JLabel.CENTER);
            turnoLabel.setFont(new Font("Arial", Font.BOLD, 16));
            turnoLabel.setPreferredSize(new Dimension(400, 50));
            ventana.add(turnoLabel, BorderLayout.NORTH);

            juego.setTurnoLabel(turnoLabel);  // Asignar el JLabel al juego
            juego.iniciarJuego(ventana); // Inicia el juego pidiendo el número de jugadores

            ventana.setVisible(true);
        });
    }
}

