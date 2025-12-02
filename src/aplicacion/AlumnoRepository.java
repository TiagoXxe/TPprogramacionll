package aplicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlumnoRepository {

    private List<Alumno> alumnos;

    public AlumnoRepository() {
        this.alumnos = new ArrayList<>();
    }

    
    //  C  -> CREATE (agregar)
    
    public void agregarAlumno(Alumno alumno) {
        // con esto evitamis legajos duplicados
        for (Alumno a : alumnos) {
            if (a.getLegajo() == alumno.getLegajo()) {
                System.out.println("⚠ Ya existe un alumno con el legajo " + alumno.getLegajo());
                return;
            }
        }
        alumnos.add(alumno);
        System.out.println("✅ Alumno agregado correctamente.");
    }

    
    //  R  -> READ (buscar)
    
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

    
    //  U  -> UPDATE (modificar)
    
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

    
    //  D  -> DELETE (eliminar)
    
    public void eliminarPorLegajo(int legajo) throws AlumnoNoEncontradoException {
        Alumno a = buscarPorLegajo(legajo); // si no existe lanza excepción
        alumnos.remove(a);
        System.out.println("🗑 Alumno eliminado correctamente.");
    }

    
    //  Listado
    
    public void mostrarTodos() {
        if (alumnos.isEmpty()) {
            System.out.println("📭 No hay alumnos cargados.");
            return;
        }

        // Ordenar por legajo (Comparable)
        Collections.sort(alumnos);

        System.out.println("\n📋 LISTADO DE ALUMNOS");

        //  LAMBDA + forEach (método funcional de colección)
        alumnos.forEach(a -> System.out.println("- " + a));
    }

    
    //  Listar solo alumnos aprobados
    
    public void mostrarAprobados() {
        if (alumnos.isEmpty()) {
            System.out.println("📭 No hay alumnos cargados.");
            return;
        }

        boolean alguno = false;
        System.out.println("\n📋 ALUMNOS CON AL MENOS UNA MATERIA APROBADA (nota > 6)");

        for (Alumno a : alumnos) {
            if (a.estaAprobado()) {
                System.out.println("- " + a);
                alguno = true;
            }
        }

        if (!alguno) {
            System.out.println("📭 Ningún alumno tiene materias aprobadas todavía.");
        }
    }

  
    //  Calcular PROMEDIO GENERAL de notas
    
public void mostrarPromedioGeneral() {
    if (alumnos.isEmpty()) {
        System.out.println("📭 No hay alumnos cargados.");
        return;
    }

    double suma = 0;
    int contador = 0;

    // Recorremos todos los alumnos y TODAS sus calificaciones
    for (Alumno a : alumnos) {
        for (Calificacion c : a.getCalificaciones()) {
            suma += c.getNota();
            contador++;
        }
    }

    if (contador == 0) {
        System.out.println("📭 No hay calificaciones cargadas todavía.");
        return;
    }

    double promedio = suma / contador;
    System.out.printf("📊 Promedio general de notas: %.2f%n", promedio);
}
}