package veiculos;
import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import veiculos.factorymethod.Veiculo;
import veiculos.factorymethod.VeiculoFactory;
import veiculos.abstractfact.MontadoraFactory;
import veiculos.abstractfact.Sedan;
import veiculos.abstractfact.Hatch;
import veiculos.abstractfact.SUV;
import veiculos.abstractfact.FiatFactory;
import veiculos.abstractfact.VolksFactory;

/**
 * Cliente (Main) com interface gráfica Swing.
 *
 * Parte 1 - Factory Method: os botões "Criar Carro" e "Criar Moto" usam
 * VeiculoFactory.criarVeiculo(String) para instanciar Carro/Moto sem
 * usar "new Carro()" ou "new Moto()" diretamente aqui.
 *
 * Parte 2 - Abstract Factory: o combo de montadora escolhe entre
 * FiatFactory e VolksFactory; os botões "Criar Sedan" e "Criar Hatch"
 * usam a interface MontadoraFactory para obter os produtos da família
 * selecionada, sem depender das classes concretas.
 * 
 * * Parte 3 - Extensão da família (SUV): o mercado passou a exigir um novo
 * tipo de produto SUV para todas as montadoras. Isso exigiu alterar o
 * contrato MontadoraFactory (novo método criarSUV()) e
 * atualizar FiatFactory e VolksFactory para implementá-lo, evidenciando
 * a limitação do Abstract Factory ao crescer o eixo "tipo de produto"
 * (viola o Princípio Aberto/Fechado). O botão "Criar SUV" segue o mesmo
 * fluxo de Sedan/Hatch, obtém a fábrica da montadora selecionada e pede
 * a ela o produto, sem o Main conhecer FiatPulse/TCross diretamente.
 * 
 */
public class Main extends JFrame {

    private final VeiculoFactory veiculoFactory = new VeiculoFactory();
    private final JTextArea outputArea = new JTextArea();
    private final JComboBox<String> montadoraCombo = new JComboBox<>(new String[]{"Fiat", "Volkswagen"});

    public Main() {
        super("Factory Method & Abstract Factory - Veículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(buildControlsPanel(), BorderLayout.NORTH);
        add(buildOutputPanel(), BorderLayout.CENTER);

        redirecionarSystemOutParaTextArea();
    }

    private JPanel buildControlsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // ----- Parte 1: Factory Method -----
        JPanel parte1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        parte1.setBorder(BorderFactory.createTitledBorder("Parte 1 - Factory Method (Veiculo)"));

        JButton btnCarro = new JButton("Criar Carro");
        JButton btnMoto = new JButton("Criar Moto");

        btnCarro.addActionListener(e -> criarVeiculo("CARRO"));
        btnMoto.addActionListener(e -> criarVeiculo("MOTO"));

        parte1.add(btnCarro);
        parte1.add(btnMoto);

        // ----- Parte 2: Abstract Factory -----
        JPanel parte2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        parte2.setBorder(BorderFactory.createTitledBorder("Parte 2 - Abstract Factory (Montadora)"));

        JButton btnSedan = new JButton("Criar Sedan");
        JButton btnHatch = new JButton("Criar Hatch");
        JButton btnSUV = new JButton("Criar SUV");

        btnSedan.addActionListener(e -> criarSedan());
        btnHatch.addActionListener(e -> criarHatch());
        btnSUV.addActionListener(e -> criarSUV());

        parte2.add(new JLabel("Montadora:"));
        parte2.add(montadoraCombo);
        parte2.add(btnSedan);
        parte2.add(btnHatch);
        parte2.add(btnSUV);

        panel.add(parte1);
        panel.add(parte2);
        return panel;
    }

    private JScrollPane buildOutputPanel() {
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Saída"));
        return scroll;
    }

    // ----- Parte 1: usa a fábrica, nunca "new Carro()"/"new Moto()" -----
    private void criarVeiculo(String tipo) {
        Veiculo veiculo = veiculoFactory.criarVeiculo(tipo);
        veiculo.exibirDetalhes();
    }

    // ----- Parte 2: usa a MontadoraFactory selecionada no combo -----
    private MontadoraFactory getMontadoraSelecionada() {
        String selecionada = (String) montadoraCombo.getSelectedItem();
        if ("Fiat".equals(selecionada)) {
            return new FiatFactory();
        }
        return new VolksFactory();
    }

    private void criarSedan() {
        MontadoraFactory factory = getMontadoraSelecionada();
        Sedan sedan = factory.criarSedan();
        sedan.exibirPortaMalas();
    }

    private void criarHatch() {
        MontadoraFactory factory = getMontadoraSelecionada();
        Hatch hatch = factory.criarHatch();
        hatch.exibirConsumo();
    }

    private void criarSUV() {
        MontadoraFactory factory = getMontadoraSelecionada();
        SUV suv = factory.criarSUV();
        suv.exibirTracao();
    }

    /** Redireciona System.out para a JTextArea, para exibir na GUI o que os métodos imprimem. */
    private void redirecionarSystemOutParaTextArea() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() -> outputArea.append(String.valueOf((char) b)));
            }
        };
        try {
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            System.setOut(new PrintStream(out, true));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}