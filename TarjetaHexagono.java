public class TarjetaHexagono extends Tarjeta {
    public TarjetaHexagono(String id) {
        super(id, "Hexágono", "C:\\Users\\Usuario\\IdeaProjects\\Memorama\\src\\Imagenes\\Hexagono.png"); // Ruta de la imagen del hexágono
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Hexágono con id: " + getId());
    }
}
