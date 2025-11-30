package aplicacion;

public interface AlumnoRepository {

    void agregarAlumno(Alumno alumno);

    Alumno buscarPorLegajo(int legajo) throws AlumnoNoEncontradoException;

    void mostrarTodos();
}