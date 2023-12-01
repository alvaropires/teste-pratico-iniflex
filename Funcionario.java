import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Funcionario extends Pessoa{
    private static final  BigDecimal SALARIO_MINIMO = BigDecimal.valueOf(1212);
    private BigDecimal salario;
    private String funcao;


    public Funcionario(){
    }
    public Funcionario(String nome, String dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String formatarBigDecimal(BigDecimal valor){
        DecimalFormat formatador = new DecimalFormat("#,###.00");
        return formatador.format(valor);
    }

    public long calcularIdade(){
        LocalDate dataNascimento = super.converterStringParaLocalDate(getDataNascimento());
        return ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
    }

    public double calcularQuantidadeSalariosMinimos(){
         return this.getSalario().divide(SALARIO_MINIMO, 2).doubleValue();

    }

    @Override
    public String toString() {
        return  "nome: " + super.getNome() +
                ", data de nascimento: " + super.getDataNascimento() +
                ", salário: R$" + this.formatarBigDecimal(salario) +
                ", função: " + funcao;
    }
}
