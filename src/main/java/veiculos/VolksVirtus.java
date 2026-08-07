package veiculos;
/**
 * Produto concreto: Sedan da família Volkswagen.
 */
public class VolksVirtus implements Sedan {

    @Override
    public void exibirPortaMalas() {
        System.out.println("Volkswagen Virtus (Sedan) | Porta-malas com 521 litros de capacidade.");
    }
}
