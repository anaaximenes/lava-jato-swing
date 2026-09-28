package lavaJato;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LavaJato {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextArea textArea1;
    private JButton cadastrarVeiculoButton;
    private JCheckBox lavaRapidoCheckBox;
    private JCheckBox higienizacaoInternaCheckBox;
    private JCheckBox polimentoCheckBox;
    private JCheckBox aspiracaoCheckBox;
    private JComboBox<String> comboBox1;
    private JCheckBox lavaCompletoCheckBox;
    private JTextField textField7;
    private JComboBox<String> comboBox2;
    private JButton FINALIZARORDEMButton;
    private JButton LIMPARButton;
    private JPanel mainPanel;

    public LavaJato() {
        textField7.setEditable(false);
        atualizarTotal();

        ActionListener checkListener = e -> atualizarTotal();
        lavaCompletoCheckBox.addActionListener(checkListener);
        aspiracaoCheckBox.addActionListener(checkListener);
        polimentoCheckBox.addActionListener(checkListener);
        higienizacaoInternaCheckBox.addActionListener(checkListener);
        lavaRapidoCheckBox.addActionListener(checkListener);

        cadastrarVeiculoButton.addActionListener(e -> cadastrarVeiculo());
        FINALIZARORDEMButton.addActionListener(e -> finalizarOrdem());
        LIMPARButton.addActionListener(e -> limparCampos());
    }

    private void cadastrarVeiculo() {
        Veiculo veiculo = new Veiculo(
                textField1.getText(),
                textField2.getText(),
                textField3.getText(),
                textField4.getText(),
                textField5.getText(),
                textField6.getText()
        );

        textArea1.append(veiculo.formatarParaLog());
        comboBox1.addItem(veiculo.toString());

        limparCamposTexto();
        atualizarTotal();
    }

    private double calcularTotal() {
        double total = 0.0;
        if (lavaCompletoCheckBox.isSelected()) total += Servico.LAVA_COMPLETO.getPreco();
        if (aspiracaoCheckBox.isSelected()) total += Servico.ASPIRACAO.getPreco();
        if (polimentoCheckBox.isSelected()) total += Servico.POLIMENTO.getPreco();
        if (higienizacaoInternaCheckBox.isSelected()) total += Servico.HIGIENIZACAO_INTERNA.getPreco();
        if (lavaRapidoCheckBox.isSelected()) total += Servico.LAVA_RAPIDO.getPreco();
        return total;
    }

    private void atualizarTotal() {
        textField7.setText(String.format("R$ %.2f", calcularTotal()));
    }

    private void finalizarOrdem() {
        String veiculo = (String) comboBox1.getSelectedItem();
        if (veiculo == null || veiculo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastre ou selecione um veículo primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> servicos = new ArrayList<>();
        if (lavaCompletoCheckBox.isSelected()) servicos.add(Servico.LAVA_COMPLETO.getDescricaoFormatada());
        if (aspiracaoCheckBox.isSelected()) servicos.add(Servico.ASPIRACAO.getDescricaoFormatada());
        if (polimentoCheckBox.isSelected()) servicos.add(Servico.POLIMENTO.getDescricaoFormatada());
        if (higienizacaoInternaCheckBox.isSelected()) servicos.add(Servico.HIGIENIZACAO_INTERNA.getDescricaoFormatada());
        if (lavaRapidoCheckBox.isSelected()) servicos.add(Servico.LAVA_RAPIDO.getDescricaoFormatada());

        double total = calcularTotal();
        if (total == 0.0) {
            JOptionPane.showMessageDialog(null, "Selecione ao menos um serviço!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String pagamento = (String) comboBox2.getSelectedItem();

        try {
            HistoricoService.registrarAtendimento(veiculo, servicos, pagamento, total);
            JOptionPane.showMessageDialog(null, "Ordem finalizada e salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparServicos();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar histórico: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCamposTexto() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }

    private void limparServicos() {
        lavaCompletoCheckBox.setSelected(false);
        aspiracaoCheckBox.setSelected(false);
        polimentoCheckBox.setSelected(false);
        higienizacaoInternaCheckBox.setSelected(false);
        lavaRapidoCheckBox.setSelected(false);
        atualizarTotal();
    }

    private void limparCampos() {
        limparCamposTexto();
        limparServicos();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LavaJato");
        frame.setContentPane(new LavaJato().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}