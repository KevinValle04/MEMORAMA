import javax.swing.ImageIcon;

public abstract class Tarjeta {
    protected String id;
    protected String figura;
    protected ImageIcon icono;

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

    public abstract void mostrar();
}
