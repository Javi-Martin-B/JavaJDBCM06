package postgres;

import java.util.Scanner;

public class SportsManager {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        SportDAO sportDAO = new SportDAO();
        AthleteDAO athleteDAO = new AthleteDAO();

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar deporte");
            System.out.println("2. Agregar atleta");
            System.out.println("3. Buscar atleta por nombre");
            System.out.println("4. Listar atletas por deporte");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> sportDAO.add(SportForm(scanner));
                case 2 -> athleteDAO.add(AthleteForm(scanner));
                case 3 -> athleteDAO.searchByName(scanner);
                case 4 -> athleteDAO.listBySport(scanner, sportDAO);
                case 5 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

	public static Sport SportForm(Scanner scanner) {
	    try {
	        System.out.println("Ingrese el nombre del deporte:");
	        String name = scanner.nextLine().trim();

	        if (name.isEmpty()) {
	            throw new IllegalArgumentException("El nombre del deporte no puede estar vacío.");
	        }

	        return new Sport(name);
	    } catch (Exception e) {
	        System.err.println("Error al ingresar el deporte: " + e.getMessage());
	        System.err.println("Por favor, inténtelo de nuevo.");
	        return SportForm(scanner); // Llamada recursiva para volver a intentar
	    }
    }

	public static Athlete AthleteForm(Scanner scanner) {
	    try {
	        System.out.println("Ingrese el nombre del atleta:");
	        String name = scanner.nextLine().trim();

	        if (name.isEmpty()) {
	            throw new IllegalArgumentException("El nombre del atleta no puede estar vacío.");
	        }

	        System.out.println("Ingrese el ID del deporte que practica:");
	        if (!scanner.hasNextInt()) { // Validar que sea un número
	            scanner.nextLine(); // Limpiar entrada inválida
	            throw new IllegalArgumentException("El ID del deporte debe ser un número válido.");
	        }
	        int sportId = scanner.nextInt();
	        scanner.nextLine(); // Limpiar buffer después de leer el número

	        if (sportId <= 0) {
	            throw new IllegalArgumentException("El ID del deporte debe ser un número positivo.");
	        }

	        return new Athlete(name, sportId);
	    } catch (Exception e) {
	        System.err.println("Error al ingresar al atleta: " + e.getMessage());
	        System.err.println("Por favor, inténtelo de nuevo.");
	        return AthleteForm(scanner); // Llamada recursiva para volver a intentar
	    }
	}
}