import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private static final DateTimeFormatter FORMATACAO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa() {
    }

    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = this.converterStringParaLocalDate(dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return this.converterLocalDateParaString(dataNascimento);
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = this.converterStringParaLocalDate(dataNascimento);
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", dataNascimento=" + this.converterLocalDateParaString(this.dataNascimento) + ".";
    }

    public LocalDate converterStringParaLocalDate(String dataString){
        return LocalDate.parse(dataString, FORMATACAO_DATA);
    }

    public String converterLocalDateParaString(LocalDate data){
        return data.format(FORMATACAO_DATA);
    }
}
