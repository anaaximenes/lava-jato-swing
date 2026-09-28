package lavaJato;

public class Veiculo {
    private String cliente;
    private String placa;
    private String modelo;
    private String marca;
    private String cor;
    private String telefone;

    public Veiculo(String cliente, String placa, String modelo, String marca, String cor, String telefone) {
        this.cliente = cliente;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.telefone = telefone;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public String getCliente() { return cliente; }

    public String formatarParaLog() {
        return String.format(
                "------------------------------------------------------------------\n" +
                        "          Veiculo Cadastrado          \n" +
                        " Placa: %s | Modelo: %s | Cliente: %s \n", placa, modelo, cliente
        );
    }

    @Override
    public String toString() {
        return placa.toUpperCase() + " | " + modelo + " - " + cliente;
    }
}
