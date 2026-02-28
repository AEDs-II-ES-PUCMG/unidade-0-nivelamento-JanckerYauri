import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produto {

    private static final double MARGEM_PADRAO = 0.2;

    protected String descricao;
    protected double precoCusto;
    protected double margemLucro;

    // Construtor com margem de lucro informada
    protected Produto(String desc, double precoCusto, double margemLucro) {
        init(desc, precoCusto, margemLucro);
    }

    // Construtor utilizando margem padrão
    protected Produto(String desc, double precoCusto) {
        init(desc, precoCusto, MARGEM_PADRAO);
    }

    // Método auxiliar de inicialização
    private void init(String desc, double precoCusto, double margemLucro) {
        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }

    // Calcula o valor de venda com base no custo e na margem
    public double valorVenda() {
        return precoCusto * (1 + margemLucro);
    }

    public static Produto criarDoTexto(String linha) {
        Produto novoProduto = null;
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String atributos [] = linha.split(";");

        int tipo = Integer.parseInt(atributos[0]);
        String descricao = atributos[1];
        Double preco = Double.parseDouble(atributos[2]);
        Double margem = Double.parseDouble(atributos[2]);

        if (tipo == 1) {
            novoProduto = new ProdutoNaoPerecivel(descricao, preco, margem);
            
        } else if (tipo == 2) {
            LocalDate data = LocalDate.parse(atributos[4], formatoData);

            novoProduto = new ProdutoPerecivel(descricao, preco, margem, data);
        }

        return novoProduto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "descricao='" + descricao + '\'' +
                ", precoCusto=" + precoCusto +
                ", margemLucro=" + margemLucro +
                ", valorVenda=" + valorVenda() +
                '}';
    }

    /**
     * Igualdade de produtos: caso possuam o mesmo nome/descrição.
     * 
     * @param obj Outro produto a ser comparado
     * @return booleano true/false conforme o parâmetro possua a descrição igual ou
     *         não a este produto.
     */
    @Override
    public boolean equals(Object obj) {
        Produto outro = (Produto) obj;
        return this.descricao.toLowerCase().equals(outro.descricao.toLowerCase());
    }

    public String gerarDadosTexto() {
        return String.format("");
    }
}