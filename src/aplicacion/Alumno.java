package aplicacion;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Alumno extends Persona implements Comparable<Alumno> {

    private String carrera;
    private int legajo;

    // lista de calificaciones
    private List<Calificacion> calificaciones = new ArrayList<>();

    public Alumno(String nombre, String apellido, String dni, int legajo) {
        super(nombre, apellido, dni);
        this.legajo = legajo;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    // agregar una calificación
    public void agregarCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
    }

    // devolver copia de la lista
    public List<Calificacion> getCalificaciones() {
        return new ArrayList<>(calificaciones);
    }

    // ¿tiene al menos una materia aprobada? (nota > 6)
    public boolean estaAprobado() {
        for (Calificacion c : calificaciones) {
            if (c.getNota() > 6.0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String base = super.toString() + " - Carrera: " + carrera;
        if (!calificaciones.isEmpty()) {
            base += " - Cant. materias: " + calificaciones.size();
        }
        return base;
    }

    // Orden natural: por legajo
    @Override
    public int compareTo(Alumno otro) {
        return Integer.compare(this.legajo, otro.legajo);
    }

    //  equals y hashCode por legajo (para colecciones, búsquedas, etc.)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        Alumno alumno = (Alumno) o;
        return legajo == alumno.legajo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(legajo);
    }
}

