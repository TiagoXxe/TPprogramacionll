package aplicacion;

import java.util.Scanner;

public class Menu {

    private AlumnoRepository repo;
    private Scanner sc;

    public Menu(AlumnoRepository repo, Scanner sc) {
        this.repo = repo;
        this.sc = sc;
    }

    // ==========================
    //  MENÚ PRINCIPAL
    // ==========================
    public void mostrarMenuPrincipal() {
        int opcion = -1;

        do {
            System.out.println("\n===== SISTEMA DE ALUMNOS =====");
            System.out.println("1. Registrar alumno");
            System.out.println("2. Buscar alumno por legajo");
            System.out.println("3. Modificar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Listar todos los alumnos");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");

            String input = sc.nextLine();

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Debe ingresar un número válido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> registrarAlumno();
                case 2 -> buscarAlumno();
                case 3 -> modificarAlumno();
                case 4 -> eliminarAlumno();
                case 5 -> repo.mostrarTodos();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠ Opción inválida, intente nuevamente.");
            }

        } while (opcion != 0);
    }

    // ==========================
    //  OPCIÓN 1: ALTA ALUMNO
    // ==========================
    private void registrarAlumno() {
        System.out.println("\n🟢 REGISTRAR NUEVO ALUMNO");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        String dni = leerDniComoString("DNI (solo números): ");
        int legajo = leerEntero("Legajo: ");

        System.out.print("Carrera: ");
        String carrera = sc.nextLine();

        Alumno nuevo = new Alumno(nombre, apellido, dni, legajo);
        nuevo.setCarrera(carrera);

        repo.agregarAlumno(nuevo);
    }

    // ==========================
    //  OPCIÓN 2: BUSCAR
    // ==========================
    private void buscarAlumno() {
        System.out.println("\n🔎 BUSCAR ALUMNO");

        int legajo = leerEntero("Ingrese legajo: ");

        try {
            Alumno alumno = repo.buscarPorLegajo(legajo);
            System.out.println("✅ Alumno encontrado: " + alumno);
        } catch (AlumnoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    //  OPCIÓN 3: MODIFICAR
    // ==========================
    private void modificarAlumno() {
        System.out.println("\n✏ MODIFICAR ALUMNO");

        int legajo = leerEntero("Legajo del alumno a modificar: ");

        try {
            Alumno alumno = repo.buscarPorLegajo(legajo);
            System.out.println("Alumno actual: " + alumno);

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = sc.nextLine();

            System.out.print("Nuevo apellido: ");
            String nuevoApellido = sc.nextLine();

            String nuevoDni = leerDniComoString("Nuevo DNI (solo números): ");

            System.out.print("Nueva carrera: ");
            String nuevaCarrera = sc.nextLine();

            repo.modificarAlumno(legajo, nuevoNombre, nuevoApellido, nuevoDni, nuevaCarrera);

        } catch (AlumnoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    //  OPCIÓN 4: ELIMINAR
    // ==========================
    private void eliminarAlumno() {
        System.out.println("\n🗑 ELIMINAR ALUMNO");

        int legajo = leerEntero("Legajo del alumno a eliminar: ");

        try {
            repo.eliminarPorLegajo(legajo);
        } catch (AlumnoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    //  LECTURA SEGURA DE ENTEROS
    // ==========================
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese solo números, por favor.");
            }
        }
    }

    // ==========================
    //  LECTURA DE DNI COMO STRING
    // ==========================
    private String leerDniComoString(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String dni = sc.nextLine().trim();

            if (dni.isEmpty()) {
                System.out.println("⚠ El DNI no puede estar vacío.");
                continue;
            }

            boolean soloDigitos = true;
            for (int i = 0; i < dni.length(); i++) {
                if (!Character.isDigit(dni.charAt(i))) {
                    soloDigitos = false;
                    break;
                }
            }

            if (!soloDigitos) {
                System.out.println("⚠ El DNI debe contener solo números.");
                continue;
            }

            return dni;
        }
    }
}
