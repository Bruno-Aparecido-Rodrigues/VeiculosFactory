package veiculos;
/**
 * Produto concreto: SUV da família Fiat.
 */
public class FiatPulse implements SUV{
    @Override
    public void exibirTracao(){
        System.out.println("Fiat Pulse (SUV) | Tracao dianteira, altura do solo de 189mm.");
    }
}
