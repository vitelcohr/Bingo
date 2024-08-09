import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombreUsuario = scanner.nextLine();
        Usuario usuario = new Usuario(nombreUsuario);
        JuegoBingo juego = new JuegoBingo(usuario);

        while (true) 
        {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Jugar");
            System.out.println("2. Ver premio acumulado");
            System.out.println("3. Ver saldo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) 
            {
                case "1":
                    juego.jugar();
                    break;
                case "2":
                    juego.mostrarAcumulado();
                    break;
                case "3":
                    usuario.mostrarSaldo();
                    break;
                case "4":
                    System.out.println("Gracias por jugar. ¡Hasta la próxima!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor seleccione nuevamente.");
            }
        }
    }
}
