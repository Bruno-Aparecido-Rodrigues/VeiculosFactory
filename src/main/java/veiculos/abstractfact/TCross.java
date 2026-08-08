package veiculos.abstractfact;
/**
 * Produto concreto: SUV da família Volkswagen.
 */
public class TCross implements SUV {

    @Override
    public void exibirTracao() {
        System.out.println("Volkswagen T-Cross (SUV) | Tracao dianteira, altura do solo de 205mm.");
    }
}