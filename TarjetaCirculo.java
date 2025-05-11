import javax.swing.ImageIcon;

public class TarjetaCirculo extends Tarjeta {
    public TarjetaCirculo(String id) {
        super(id, "Círculo", "Circulo.png"); // Ruta de la imagen
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Círculo con id: " + getId());
    }
}

