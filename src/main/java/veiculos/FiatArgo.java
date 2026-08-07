package veiculos;
/**
 * Produto concreto: Hatch da família Fiat.
 */
public class FiatArgo implements Hatch {

    @Override
    public void exibirConsumo() {
        System.out.println("Fiat Argo (Hatch) | Consumo medio de 13,5 km/l na cidade.");
    }
}
