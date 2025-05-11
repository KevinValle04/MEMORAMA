public class TarjetaTriangulo extends Tarjeta {
    public TarjetaTriangulo(String id) {
        super(id, "Triangulo", "Triangulo.png");
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Triángulo con id: " + getId());
    }
}
