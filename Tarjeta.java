import javax.swing.*;

public class Tarjeta {
    private String id;
    private String figura;
    private ImageIcon icono;

    public Tarjeta(String id, String figura, String rutaImagen) {
        this.id = id;
        this.figura = figura;
        this.icono = new ImageIcon(rutaImagen);
    }

    public String getId() {
        return id;
    }

    public String getFigura() {
        return figura;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void mostrar() {
        System.out.println("Mostrando tarjeta de " + figura + " con id: " + id);
    }
}