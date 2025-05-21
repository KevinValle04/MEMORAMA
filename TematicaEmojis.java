import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TematicaEmojis extends Tematica {
    @Override
    public List<Tarjeta> generarTarjetas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tarjetas.add(new Tarjeta("Sonrisa " + i, "Sonrisa", "D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\sonrisa.png"));
            tarjetas.add(new Tarjeta("Triste " + i, "Triste", "D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\triste.png"));
            tarjetas.add(new Tarjeta("Enojado " + i, "Enojado", "D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\enojado.png"));
            tarjetas.add(new Tarjeta("Sorpresa " + i, "Sorpresa", "D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\sorpresa.png"));
            tarjetas.add(new Tarjeta("Guiño " + i, "Guiño", "D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\guiño.png"));
            tarjetas.add(new Tarjeta("Cool " + i, "Cool", "D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\cool.png"));
        }
        return tarjetas;
    }

    public void mostrarFondoTemporal(JPanel panelCentral) {
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setOpaque(true);
        panelCentral.repaint();

        panelCentral.setLayout(new BorderLayout());
        JLabel fondo = new JLabel(new ImageIcon("D:\\descargas\\Memorama (1)\\src\\imagenes\\emojis\\fondoEmojis.jpg"));
        fondo.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(fondo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();

        // Restaurar fondo en 1.5 segundos
        Timer timer = new Timer(1500, e -> {
            panelCentral.remove(fondo);
            panelCentral.setLayout(new GridLayout(4, 4, 10, 10));
            panelCentral.setBackground(Color.GRAY);
            panelCentral.revalidate();
            panelCentral.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public int puntosPorAcierto() {
        return new Random().nextInt(3) + 1; // Devuelve 1, 2 o 3
    }

    public int puntosPorError() {
        return 1; // o 2 si quieres que duela más 😅
    }



}