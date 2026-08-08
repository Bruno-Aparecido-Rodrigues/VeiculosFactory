package veiculos.factorymethod;
/**
 * Factory Method: centraliza a criação de objetos Veiculo,
 * evitando que o cliente use "new Carro()" ou "new Moto()" diretamente.
 */
public class VeiculoFactory {

    public Veiculo criarVeiculo(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de veiculo não pode ser nulo.");
        }

        switch (tipo.toUpperCase()) {
            case "CARRO":
                return new Carro();
            case "MOTO":
                return new Moto();
            default:
                throw new IllegalArgumentException("Tipo de veiculo desconhecido: " + tipo);
        }
    }
}
