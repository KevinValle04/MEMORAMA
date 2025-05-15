import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TematicaFiguras extends Tematica {
    private final Map<String, Integer> valoresFiguras = new HashMap<>();

    public TematicaFiguras() {
        valoresFiguras.put("Cuadrado", 10);
        valoresFiguras.put("Círculo", 9);
        valoresFiguras.put("Triángulo", 8);
        valoresFiguras.put("Estrella", 7);
        valoresFiguras.put("Rombo", 6);
        valoresFiguras.put("Hexágono", 5);
    }
    @Override
    public List<Tarjeta> generarTarjetas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tarjetas.add(new Tarjeta("Círculo " + i, "Círculo", "D:\\descargas\\Memorama (1)\\src\\imagenes\\circulo.png"));
            tarjetas.add(new Tarjeta("Cuadrado " + i, "Cuadrado", "D:\\descargas\\Memorama (1)\\src\\imagenes\\cuadrado.png"));
            tarjetas.add(new Tarjeta("Triángulo " + i, "Triángulo", "D:\\descargas\\Memorama (1)\\src\\imagenes\\triangulo.png"));
            tarjetas.add(new Tarjeta("Hexágono " + i, "Hexágono", "D:\\descargas\\Memorama (1)\\src\\imagenes\\hexagono.png"));
            tarjetas.add(new Tarjeta("Estrella " + i, "Estrella", "D:\\descargas\\Memorama (1)\\src\\imagenes\\estrella.png"));
            tarjetas.add(new Tarjeta("Rombo " + i, "Rombo", "D:\\descargas\\Memorama (1)\\src\\imagenes\\rombo.png"));
        }
        return tarjetas;
    }

    public int obtenerValorFigura(String figura) {
        return valoresFiguras.getOrDefault(figura, 1); // Valor por defecto 1 si no se encuentra
    }
}