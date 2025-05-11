public class TarjetaRombo extends Tarjeta {
    public TarjetaRombo(String id) {
        super(id, "Rombo", "Rombo.png"); // Asegúrate de tener la imagen Rombo.png en la carpeta correcta
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Rombo con id: " + getId());
    }
}

