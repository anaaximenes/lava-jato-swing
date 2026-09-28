package lavaJato;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoricoService {

    public static void registrarAtendimento(String veiculoInfo, List<String> servicos, String pagamento, double total) throws IOException {
        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String horaAgora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        File arquivo = new File("historico " + dataHoje + ".txt");
        int totalAtendimentosHoje = 1;

        if (arquivo.exists()) {
            try {
                long totalLinhas = Files.lines(arquivo.toPath())
                        .filter(linha -> linha.startsWith("ATENDIMENTO #"))
                        .count();
                totalAtendimentosHoje = (int) totalLinhas + 1;
            } catch (IOException ignored) {}
        }

        String registro = "___________________________________________________________________\n" +
                "ATENDIMENTO #" + totalAtendimentosHoje + " | Hora: " + horaAgora + "\n" +
                "Veículo: " + veiculoInfo + "\n" +
                "Serviços: " + String.join(", ", servicos) + "\n" +
                "Pagamento: " + pagamento + "\n" +
                String.format("Total: R$ %.2f\n", total);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            if (arquivo.length() == 0) {
                writer.write("==================================================================\n");
                writer.write("          RELATÓRIOS DIÁRIOS DE ATENDIMENTO - " + dataHoje + "\n");
                writer.write("==================================================================\n");
            }
            writer.write(registro);
        }
    }
}
