public class ProdutoNaoPerecivel extends Produto {

    public ProdutoNaoPerecivel(String descricao, double precoCusto, double margemLucro) {
        super(descricao, precoCusto, margemLucro);
    }

    public ProdutoNaoPerecivel(String descricao, double precocusto) {
        super(descricao, precocusto);
    }

    @Override
    public double valorVenda() {
        return precoCusto * (1 - margemLucro);
    }

    @Override
    public String gerarDadosTexto() {
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatado = String.format("%.2f", margemLucro).replace(",", ".");
        return String.format("1;%s;%s;%s", descricao, precoFormatado, margemFormatado);
    }
}
