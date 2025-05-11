public class TarjetaHexagono extends Tarjeta {
    public TarjetaHexagono(String id) {
        super(id, "Hexágono", "Hexagono.png"); // Ruta de la imagen del hexágono
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Hexágono con id: " + getId());
    }
}
