package aplicacion;

public class Calificacion {

    private Alumno alumno;
    private String materia;
    private double nota;

    public Calificacion(Alumno alumno, String materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public String getMateria() {
        return materia;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return alumno.toString() + " - Materia: " + materia + " - Nota: " + nota;
    }

}