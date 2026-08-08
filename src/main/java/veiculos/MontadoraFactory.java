package veiculos;
/**
 * Abstract Factory: define a criação de uma família de produtos
 * relacionados (Sedan + Hatch) sem especificar suas classes concretas.
 */
public interface MontadoraFactory {
    Sedan criarSedan();
    Hatch criarHatch();
    SUV criarSUV();
}
