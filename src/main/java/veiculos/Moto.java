package veiculos;
/**
 * Produto concreto: Moto.
 */
public class Moto implements Veiculo {

    @Override
    public void exibirDetalhes() {
        System.out.println("Veiculo criado: MOTO | 2 rodas | agil no transito e economica.");
    }
}
