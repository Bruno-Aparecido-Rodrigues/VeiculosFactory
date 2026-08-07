package veiculos;
/**
 * Produto concreto: Hatch da família Volkswagen.
 */
public class VolksPolo implements Hatch {

    @Override
    public void exibirConsumo() {
        System.out.println("Volkswagen Polo (Hatch) | Consumo medio de 12,8 km/l na cidade.");
    }
}
