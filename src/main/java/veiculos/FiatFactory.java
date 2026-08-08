package veiculos;
/**
 * Fábrica concreta: produz a família de veículos Fiat.
 */
public class FiatFactory implements MontadoraFactory {

    @Override
    public Sedan criarSedan() {
        return new FiatCronos();
    }

    @Override
    public Hatch criarHatch() {
        return new FiatArgo();
    }

    @Override
    public SUV criarSUV(){
        return new FiatPulse();
    }
}
