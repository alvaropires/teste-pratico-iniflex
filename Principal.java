import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    public static List<Funcionario> funcionarios = new ArrayList<>();
    public static Map<String, List<Funcionario>> funcionariosPorFuncaoMap = new TreeMap<>();
    public static String[][] dadosFuncionarios = {
            {"Maria","18/10/2000","2009.44","Operador"},
            {"João","12/05/1990","2284.38","Operador"},
            {"Caio","02/05/1961","9836.14","Coordenador"},
            {"Miguel","14/10/1988","19119.88","Diretor"},
            {"Alice","05/01/1995","2234.68","Recepcionista"},
            {"Heitor","19/11/1999","1582.72","Operador"},
            {"Arthur","31/03/1993","4071.84","Contador"},
            {"Laura","08/07/1994","3017.45","Gerente"},
            {"Heloísa","24/05/2003","1606.85","Eletricista"},
            {"Helena","02/09/1996","2799.93","Gerente"}
    };

    public static void main(String[] args) {

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

        for(String[] dadosFuncionario: dadosFuncionarios){
            Funcionario funcionario = new Funcionario(dadosFuncionario[0],dadosFuncionario[1],new BigDecimal(dadosFuncionario[2]),dadosFuncionario[3]);
            funcionarios.add(funcionario);
        }
        System.out.println("Lista de funcionários: \n");
        imprimirListaDeFuncionarios();

        //3.2 – Remover o funcionário “João” da lista.

        removerFuncionarioPorNome("João");

        //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        //• informação de data deve ser exibido no formato dd/mm/aaaa;
        //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.

        System.out.println("\nLista de Funcionários após remoção de funcionário 'João':\n");
        imprimirListaDeFuncionarios();

        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        aumentarSalarioFuncionariosEmPorcentagem(10.0);
        System.out.println("\nFuncionários com reajuste de 10% no salário: \n");
        imprimirListaDeFuncionarios();

        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        agruparFuncionariosPorFuncaoEmMap();

        //3.6 - Imprimir os funcionários, agrupados por função.

        System.out.println("\nFuncionários agrupados por função: \n");
        imprimirFuncionarioPorFuncao();

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        imprimirFuncionariosAniversarioMeses10e12();

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        Funcionario funcionarioMaiorIdade = funcionarioMaiorIdade();
        System.out.println("\nFuncionário com maior idade: \n");
        System.out.println("Nome: " + funcionarioMaiorIdade.getNome() + ", idade: " + funcionarioMaiorIdade.calcularIdade() + " anos.");

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.

        System.out.println("\nLista de funcionários em ordem alfabética: \n");
        organizarFuncionariosEmOrdemAlfabetica();
        imprimirListaDeFuncionarios();

        //3.11 – Imprimir o total dos salários dos funcionários.

        System.out.println("\nO valor total do salários dos funcionários é de: R$" + formatarBigDecimal(totalDoSalarioFuncionarios()));

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

        System.out.println("\nLista de funcionários e quantidade de salários mínimos: \n");

        imprimirQuantosSalariosMinimosFuncionarios();
    }

    public static void imprimirListaDeFuncionarios(){
        funcionarios.forEach(System.out::println);
    }
    public static void removerFuncionarioPorNome(String nome){
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }

    public static void aumentarSalarioFuncionariosEmPorcentagem(Double porcentagem){
        funcionarios.forEach(funcionario -> {
            var salarioAtual = funcionario.getSalario();
            funcionario.setSalario(salarioAtual.multiply(new BigDecimal(1 + porcentagem/100)));
        });
    }

    public static void agruparFuncionariosPorFuncaoEmMap(){
        for(Funcionario funcionario: funcionarios){
            String funcao = funcionario.getFuncao();
            if(!funcionariosPorFuncaoMap.containsKey(funcao)){
                funcionariosPorFuncaoMap.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncaoMap.get(funcao).add(funcionario);
        }
    }

    public static void imprimirFuncionarioPorFuncao(){
        for (Map.Entry<String, List<Funcionario>> entry: funcionariosPorFuncaoMap.entrySet()){
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario: entry.getValue()){
                System.out.println("        " + funcionario);
            }
        }
    }

    public static void imprimirFuncionariosAniversarioMeses10e12(){
        List<Funcionario> aniversariantes = new ArrayList<>();
        for(Funcionario funcionario: funcionarios){
            Month mesAniversario = LocalDate.parse(funcionario.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).getMonth();
            if(mesAniversario.equals(Month.OCTOBER) || mesAniversario.equals(Month.DECEMBER)){
                aniversariantes.add(funcionario);
            }
        }
        System.out.println("\nAniversariantes dos Meses 10 e 12: \n");
        aniversariantes.forEach(System.out::println);
    }

    public static Funcionario funcionarioMaiorIdade(){
        return funcionarios.stream().max(Comparator.comparing(Funcionario::calcularIdade)).get();
    }

    public static void organizarFuncionariosEmOrdemAlfabetica(){
        funcionarios.sort(Comparator.comparing(Pessoa::getNome));
    }

    public static BigDecimal totalDoSalarioFuncionarios(){
        BigDecimal total = BigDecimal.ZERO;
        for(Funcionario funcionario: funcionarios){
            total = total.add(funcionario.getSalario());
        }
        return total;
    }
    public static String formatarBigDecimal(BigDecimal valor){
        DecimalFormat formatador = new DecimalFormat("#,###.00");
        return formatador.format(valor);
    }

    public static void imprimirQuantosSalariosMinimosFuncionarios(){
        for (Funcionario funcionario: funcionarios){
            System.out.printf("Nome: %s , Salários Mínimos: %.1f\n", funcionario.getNome(), funcionario.calcularQuantidadeSalariosMinimos());
        }
    }
}
