import javax.swing.ImageIcon;

public class TarjetaCirculo extends Tarjeta {
    public TarjetaCirculo(String id) {
        super(id, "Círculo", "C:\\Users\\Usuario\\IdeaProjects\\Memorama\\src\\Imagenes\\Circulo.png"); // Ruta de la imagen
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Círculo con id: " + getId());
    }
}

