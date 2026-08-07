package veiculos;
/**
 * Produto concreto: Carro.
 */
public class Carro implements Veiculo {

    @Override
    public void exibirDetalhes() {
        System.out.println("Veiculo criado: CARRO | 4 rodas | ideal para uso urbano e viagens em familia.");
    }
}
