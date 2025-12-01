package aplicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlumnoRepository {

    private List<Alumno> alumnos;

    public AlumnoRepository() {
        this.alumnos = new ArrayList<>();
    }

    // ==============================
    //  C  -> CREATE (agregar)
    // ==============================
    public void agregarAlumno(Alumno alumno) {
        // Evitar legajos duplicados
        for (Alumno a : alumnos) {
            if (a.getLegajo() == alumno.getLegajo()) {
                System.out.println("⚠ Ya existe un alumno con el legajo " + alumno.getLegajo());
                return;
            }
        }
        alumnos.add(alumno);
        System.out.println("✅ Alumno agregado correctamente.");
    }

    // ==============================
    //  R  -> READ (buscar)
    // ==============================
    public Alumno buscarPorLegajo(int legajo) throws AlumnoNoEncontradoException {
        for (Alumno a : alumnos) {
            if (a.getLegajo() == legajo) {
                return a;
            }
        }
        throw new AlumnoNoEncontradoException(
                "❌ No se encontró un alumno con el legajo: " + legajo
        );
    }

    // ==============================
    //  U  -> UPDATE (modificar)
    // ==============================
    public void modificarAlumno(int legajo,
                                String nuevoNombre,
                                String nuevoApellido,
                                String nuevoDni,
                                String nuevaCarrera) throws AlumnoNoEncontradoException {

        Alumno a = buscarPorLegajo(legajo); // si no existe lanza excepción

        a.setNombre(nuevoNombre);
        a.setApellido(nuevoApellido);
        a.setDni(nuevoDni);
        a.setCarrera(nuevaCarrera);

        System.out.println("✏ Alumno modificado correctamente.");
    }

    // ==============================
    //  D  -> DELETE (eliminar)
    // ==============================
    public void eliminarPorLegajo(int legajo) throws AlumnoNoEncontradoException {
        Alumno a = buscarPorLegajo(legajo); // si no existe lanza excepción
        alumnos.remove(a);
        System.out.println("🗑 Alumno eliminado correctamente.");
    }

    // ==============================
    //  Listado
    // ==============================
    public void mostrarTodos() {
        if (alumnos.isEmpty()) {
            System.out.println("📭 No hay alumnos cargados.");
            return;
        }

        // Si Alumno implementa Comparable<Alumno>, se ordena.
        // Si no lo implementaron, podés borrar esta línea y listo.
        Collections.sort(alumnos);

        System.out.println("\n📋 LISTADO DE ALUMNOS");
        for (Alumno a : alumnos) {
            System.out.println("- " + a);
        }
    }

    public List<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos);
    }
}
