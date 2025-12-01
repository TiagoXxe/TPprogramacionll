package aplicacion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlumnoRepository repo = new AlumnoRepository();
        Menu menu = new Menu(repo, sc);

        menu.mostrarMenuPrincipal();

        sc.close();
    }
}
