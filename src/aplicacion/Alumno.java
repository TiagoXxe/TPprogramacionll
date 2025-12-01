package aplicacion;

import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona implements Comparable<Alumno> {

    private String carrera;
    private int legajo;

    // 👉 NUEVO: lista de calificaciones
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

    // 👉 NUEVO: agregar una calificación
    public void agregarCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
    }

    // 👉 NUEVO: devolver copia de la lista (para que nadie la rompa desde afuera)
    public List<Calificacion> getCalificaciones() {
        return new ArrayList<>(calificaciones);
    }

    // 👉 NUEVO: ¿tiene al menos una materia aprobada? (nota > 6)
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
        // Ej: Gabutti, Helga (Dni...) - Carrera: TUP - 3 materias
        String base = super.toString() + " - Carrera: " + carrera;

        if (!calificaciones.isEmpty()) {
            base += " - Cant. materias: " + calificaciones.size();
        }

        return base;
    }

    @Override
    public int compareTo(Alumno otro) {
        return Integer.compare(this.legajo, otro.legajo);
    }
}
