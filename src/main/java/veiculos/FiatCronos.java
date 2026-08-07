package veiculos;
/**
 * Produto concreto: Sedan da família Fiat.
 */
public class FiatCronos implements Sedan {

    @Override
    public void exibirPortaMalas() {
        System.out.println("Fiat Cronos (Sedan) | Porta-malas com 525 litros de capacidade.");
    }
}
