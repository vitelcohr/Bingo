public class Usuario 
{
    private String nombre;
    private double saldo;

    public Usuario(String nombre) 
    {
        this.nombre = nombre;
        this.saldo = 48.0;  // Suficiente para comprar 4 sets de números (Q12.00 cada set)
    }

    public double getSaldo() 
    {
        return saldo;
    }

    public void mostrarSaldo() 
    {
        System.out.printf("Saldo actual: Q%.2f (USD: $%.2f)%n", saldo, saldo / 7.7);
    }

    public void añadirPremio(double monto) 
    {
        saldo += monto;
    }

    public void restarCosto(double monto) 
    {
        saldo -= monto;
    }
}

