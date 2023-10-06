import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import com.sun.org.apache.xpath.internal.objects.XString;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        PessoaFisica metodosPf = new PessoaFisica();

        ArrayList<PessoaFisica> listaPf = new ArrayList<>();

        System.out.println("Bem vindo ao sistema de cadastro de Pessoas Físicas e Juridicas");

        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {

            System.out.println("Escolha uma opção: 1 - Pessoa Física / 2 - Pessoa Jurídica / 0 - Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    int opcaoPf;

                    do {
                        System.out.println("Escolha uma opção: 1 - Cadastrar Pessoa Física / 2 - Listar Pessoa Física / 0 - Voltar ao menu anterior");

                        opcaoPf = scanner.nextInt();

                        switch (opcaoPf) {
                            case 1:
                                PessoaFisica novapf = new PessoaFisica();
                                Endereco novoEndPf = new Endereco();

                                System.out.print("Digite o nome da pessoa física que deseja cadastrar: ");
                                novapf.nome = scanner.next();

                                System.out.print("Digite o CPF: ");
                                novapf.cpf = scanner.next();

                                System.out.print("Digite o rendimento mensal (Digite somente numero: ");
                                novapf.rendimento = scanner.nextInt();

                                System.out.print("Digite a data de Nascimento: (dd-MM-yyyy) ");
                                LocalDate date = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                Period periodo = Period.between(date, LocalDate.now());

                                novapf.dataNasc = date;

                                if (periodo.getYears() >= 18) {
                                    System.out.println("A pessoa tem mais de 18 anos.");
                                } else {
                                    System.out.println("A pessoa tem menos de 18 anos. Retornando menu...");
                                    break;
                                }

                                System.out.print("Digite o logradouro: ");
                                novoEndPf.logradouro = scanner.next();

                                System.out.print("Digite o número: ");
                                novoEndPf.numero = scanner.next();

                                System.out.print("Este endereço é comercial? S/N: ");
                                String endCom;
                                endCom = scanner.next();

                                if (endCom.equals("S")) {
                                    novoEndPf.enderecoComercial = true;
                                } else {
                                    novoEndPf.enderecoComercial = false;
                                }

                                novapf.endereco = novoEndPf;

                                listaPf.add(novapf);

                                System.out.println("Cadastro realizado com sucesso!");

                                break;

                            case 2:

                                if (listaPf.size() > 0) {

                                    for (PessoaFisica cadaPf : listaPf) {
                                        System.out.println();
                                        System.out.println("Nome: " + cadaPf.nome);
                                        System.out.println("CPF: " + cadaPf.cpf);
                                        System.out.println("Endereco: " + cadaPf.endereco.logradouro + "," + cadaPf.endereco.numero);
                                        System.out.println("Data de Nascimento:" + cadaPf.dataNasc.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                        System.out.println("Imposto a ser pago: " + metodosPf.CalcularImposto(cadaPf.rendimento));
                                        System.out.println();
                                        System.out.println("Digite 0 para continuar");
                                        scanner.nextLine();
                                    }

                                    opcaoPf = scanner.nextInt();

                                } else {
                                    System.out.println("Lista vazia");
                                }
                                break;

                            case 0:
                                System.out.println("Voltando ao menu anterior");
                                break;

                            default:
                                System.out.println("Opção inválida, por favor digite uma opção válida");
                                break;
                        }

                    } while (opcaoPf != 0);
                    break;

                case 2:
                    break;

                case 0:
                    System.out.print("Obrigado por utilizar o nosso sistema");
                    break;

                default:
                    System.out.print("Opção inválida, por favor digite uma opção válida");
                    break;
            }
        } while (opcao != 0);

    }
}