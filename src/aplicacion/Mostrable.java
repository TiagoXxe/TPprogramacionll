package aplicacion;


// INTERFAZ FUNCIONAL MOSTRABLE
// Representa un método que "muestra" algo
// y Obliga a implementar un método

@FunctionalInterface
public interface Mostrable {

    // ÚNICO método abstracto → interfaz funcional
    String mostrar();
}
