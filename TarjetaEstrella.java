public class TarjetaEstrella extends Tarjeta {
    public TarjetaEstrella(String id) {
        super(id, "Estrella", "C:\\Users\\Usuario\\IdeaProjects\\Memorama\\src\\Imagenes\\Estrella.png"); // Ruta de la imagen de la estrella
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Estrella con id: " + getId());
    }
}
