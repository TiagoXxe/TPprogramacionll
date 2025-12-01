package aplicacion;

public class Alumno extends Persona implements Comparable<Alumno> {

    private String carrera;
    private int legajo;

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

    @Override
    public String toString() {
        return super.toString() + " - Carrera: " + carrera;
    }
    @Override
    public int compareTo(Alumno otro) {
        return Integer.compare(this.legajo, otro.legajo);
    }
}
