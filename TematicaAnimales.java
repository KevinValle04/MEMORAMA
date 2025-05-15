import java.util.*;
import javax.swing.*;

public class TematicaAnimales extends Tematica {
    private Map<String, String> datosCuriosos;

    public TematicaAnimales() {
        datosCuriosos = new HashMap<>();
        datosCuriosos.put("Gato", "Los gatos pueden saltar hasta seis veces la longitud de su cuerpo.");
        datosCuriosos.put("Mono", "Algunos monos usan herramientas para obtener comida.");
        datosCuriosos.put("Pajaro", "Algunas especies de pájaros pueden imitar la voz humana.");
        datosCuriosos.put("Perro", "El olfato de un perro es hasta 100,000 veces más agudo que el de un humano.");
        datosCuriosos.put("Tigre", "Las rayas de los tigres son únicas como las huellas digitales.");
        datosCuriosos.put("Pinguino", "Los pingüinos se emparejan de por vida y reconocen a su pareja por el sonido.");
    }

    @Override
    public List<Tarjeta> generarTarjetas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tarjetas.add(new Tarjeta("Gato " + i, "Gato", "D:\\descargas\\Memorama (1)\\src\\imagenes\\Animales\\Gato.png"));
            tarjetas.add(new Tarjeta("Mono " + i, "Mono", "D:\\descargas\\Memorama (1)\\src\\imagenes\\Animales\\Mono.png"));
            tarjetas.add(new Tarjeta("Pajaro " + i, "Pajaro", "D:\\descargas\\Memorama (1)\\src\\imagenes\\Animales\\Pajaro.png"));
            tarjetas.add(new Tarjeta("Perro " + i, "Perro", "D:\\descargas\\Memorama (1)\\src\\imagenes\\Animales\\Perro.png"));
            tarjetas.add(new Tarjeta("Tigre " + i, "Tigre", "D:\\descargas\\Memorama (1)\\src\\imagenes\\Animales\\Tigre.png"));
            tarjetas.add(new Tarjeta("Pinguino " + i, "Pinguino", "D:\\descargas\\Memorama (1)\\src\\imagenes\\Animales\\Pinguino.png"));
        }
        return tarjetas;
    }

    public void mostrarDatoCurioso(String figura, JFrame frame) {
        String dato = datosCuriosos.get(figura);
        if (dato != null) {
            JOptionPane.showMessageDialog(frame, "🐾 Dato curioso sobre el " + figura + ":\n" + dato, "Dato Curioso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
