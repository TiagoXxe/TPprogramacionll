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

        String nombre = leerTextoSoloLetras("Nombre: ");
        String apellido = leerTextoSoloLetras("Apellido: ");
        String dni = leerDniComoString("DNI (solo números): ");
        int legajo = leerEnteroPositivo("Legajo (entero positivo): ");
        String carrera = leerTextoSoloLetras("Carrera: ");

        Alumno nuevo = new Alumno(nombre, apellido, dni, legajo);
        nuevo.setCarrera(carrera);

        repo.agregarAlumno(nuevo);
    }

    // ==========================
    //  OPCIÓN 2: BUSCAR
    // ==========================
    private void buscarAlumno() {
        System.out.println("\n🔎 BUSCAR ALUMNO");

        int legajo = leerEnteroPositivo("Ingrese legajo: ");

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

        int legajo = leerEnteroPositivo("Legajo del alumno a modificar: ");

        try {
            Alumno alumno = repo.buscarPorLegajo(legajo);
            System.out.println("Alumno actual: " + alumno);

            String nuevoNombre = leerTextoSoloLetras("Nuevo nombre: ");
            String nuevoApellido = leerTextoSoloLetras("Nuevo apellido: ");
            String nuevoDni = leerDniComoString("Nuevo DNI (solo números): ");
            String nuevaCarrera = leerTextoSoloLetras("Nueva carrera: ");

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

        int legajo = leerEnteroPositivo("Legajo del alumno a eliminar: ");

        try {
            repo.eliminarPorLegajo(legajo);
        } catch (AlumnoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    //  LECTURA ENTERO POSITIVO
    // ==========================
    private int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = sc.nextLine().trim();

            try {
                int valor = Integer.parseInt(input);
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("⚠ El número debe ser mayor que cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese solo números enteros.");
            }
        }
    }

    // ==========================
    //  LECTURA DE TEXTO SOLO LETRAS
    // ==========================
    private String leerTextoSoloLetras(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("⚠ El campo no puede estar vacío.");
                continue;
            }

            boolean valido = true;
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                // Aceptamos letras y espacio
                if (!Character.isLetter(c) && c != ' ') {
                    valido = false;
                    break;
                }
            }

            if (!valido) {
                System.out.println("⚠ Solo se permiten letras y espacios.");
                continue;
            }

            return texto;
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

            // Opcional: validar longitud (7 u 8 dígitos)
            if (dni.length() < 7 || dni.length() > 8) {
                System.out.println("⚠ El DNI debe tener entre 7 y 8 dígitos.");
                continue;
            }

            return dni;
        }
    }
}
