public class TarjetaCuadrado extends Tarjeta {
    public TarjetaCuadrado(String id) {
        super(id, "Cuadrado", "C:\\Users\\Usuario\\IdeaProjects\\Memorama\\src\\Imagenes\\Cuadrado.png");
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando tarjeta de Cuadrado con id: " + getId());
    }
}
