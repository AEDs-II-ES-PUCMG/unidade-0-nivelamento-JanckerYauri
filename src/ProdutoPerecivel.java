import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {

    private static final double DESCONTO = 0.25;

    private static final int PRAZO_DESCONTO = 7;

    private LocalDate dataDeValidade;

    protected ProdutoPerecivel(String descricao, double precoCusto, double margemLucro, LocalDate validade) {
        super(descricao, precoCusto, margemLucro);

        if (validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O produto está vencido");
        }
    }
    
    @Override
    public double valorVenda() {
        double desconto = 0d;
        int diasValidade = LocalDate.now().until(dataDeValidade).getDays();
        if (diasValidade <=PRAZO_DESCONTO) {
            desconto=DESCONTO;
        }

        return (precoCusto * ( 1 + margemLucro))* (1 - desconto);
    }

    @Override
    public String gerarDadosTexto() {
        DataTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatado = String.format("%.2f", margemLucro).replace(",", ".");
        String dataFormato = formato.format(dataDeValidade);
        return String.format("2;%s;%s;%s;%s", descricao, precoFormatado, margemFormatado, dataFormato);
    }
}
