import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoBingo 
{
    private static final double PRECIO_POR_NUMERO = 4.0;  // Precio de cada número
    private static final double COSTO_SET_NUMEROS = 12.0;  // Costo de un set de tres números

    private Usuario usuario;
    private double premioAcumulado;
    private double saldoInicial;

    public JuegoBingo(Usuario usuario) 
    {
        this.usuario = usuario;
        this.premioAcumulado = 0.0;
        this.saldoInicial = usuario.getSaldo();  // Saldo inicial dado al usuario
    }

    public List<Integer> generarNumeros() 
    {
        Random random = new Random();
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < 3; i++) 
        {
            numeros.add(random.nextInt(901) + 100);  // Genera un número entre 100 y 1000
        }
        return numeros;
    }

    public boolean verificarGanador(List<Integer> numeros) 
    {
        int countPares = 0;
        for (int num : numeros) 
        {
            if (num % 2 == 0) 
            {
                countPares++;
            }
        }
        return countPares >= 2;
    }

    public double calcularPremio(List<Integer> numeros) 
    {
        List<Integer> pares = new ArrayList<>();
        for (int num : numeros) 
        {
            if (num % 2 == 0) 
            {
                pares.add(num);
            }
        }

        if (pares.stream().allMatch(num -> num > 700)) 
        {
            return 0.10 * pares.stream().mapToInt(Integer::intValue).sum();
        } 
        else if (pares.stream().allMatch(num -> num > 500 && num <= 700)) 
        {
            return 0.20 * pares.stream().mapToInt(Integer::intValue).sum();
        } 
        else if (pares.stream().anyMatch(num -> num > 500)) 
        {
            return 0.15 * pares.stream().mapToInt(Integer::intValue).sum();
        } 
        else if (pares.stream().allMatch(num -> num <= 500)) 
        {
            return 0.25 * pares.stream().mapToInt(Integer::intValue).sum();
        }
        return 0.0;
    }

    public void mostrarAcumulado() 
    {
        System.out.printf("Premio acumulado: Q%.2f (USD: $%.2f)%n", premioAcumulado, premioAcumulado / 7.7);
    }

    public void jugar() 
    {
        if (usuario.getSaldo() < COSTO_SET_NUMEROS) 
        {
            System.out.println("No tienes suficiente saldo para comprar más números.");
            return;
        }

        usuario.restarCosto(COSTO_SET_NUMEROS);
        List<Integer> numeros = generarNumeros();
        System.out.println("Números generados: " + numeros);

        if (verificarGanador(numeros)) 
        {
            double premio = calcularPremio(numeros);
            premioAcumulado += premio;
            usuario.añadirPremio(premio);
            System.out.printf("¡Felicidades! Ganaste Q%.2f.%n", premio);
            gestionarSaldoInicial();
        } 
        else 
        {
            System.out.println("Gracias por su participación.");
        }
    }

    public void gestionarSaldoInicial() 
    {
        if (premioAcumulado > 0) 
        {
            premioAcumulado -= saldoInicial;
            System.out.printf("Se ha descontado el saldo inicial. Premio acumulado ajustado: Q%.2f%n", premioAcumulado);
        }
    }
}
