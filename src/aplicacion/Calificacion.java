package aplicacion;

public class Calificacion {

    private String materia;
    private double nota;

    public Calificacion(String materia, double nota) {
        this.materia = materia;
        this.nota = nota;
    }

    public String getMateria() {
        return materia;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Materia: " + materia + " - Nota: " + nota;
    }
}