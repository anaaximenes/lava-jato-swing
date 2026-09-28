package lavaJato;

public enum Servico {
    LAVA_COMPLETO("Lava Completo", 80.0),
    ASPIRACAO("Aspiração", 20.0),
    POLIMENTO("Polimento", 150.0),
    HIGIENIZACAO_INTERNA("Higienização Interna", 90.0),
    LAVA_RAPIDO("Lava Rápido", 50.0);

    private final String nome;
    private final double preco;

    Servico(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    public String getDescricaoFormatada() {
        return String.format("%s: R$ %.2f", nome, preco);
    }
}