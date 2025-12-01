package aplicacion;

public class Alumno extends Persona {

    private String carrera;

    public Alumno(String nombre, String apellido, int legajo, String carrera) {
        super(nombre, apellido, legajo);
        this.carrera = carrera;
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

}
