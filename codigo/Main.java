import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cliente> contas = new ArrayList<>();
    private static int proximaConta = 1;

    public static void main(String[] args) {
        try (scanner) {
            while (true) {
                exibirMenu();
                int opcao = lerOpcao();
                
                if (opcao == 0) {
                    System.out.println("Saindo do sistema...");
                    break;
                }
                
                processarOpcao(opcao);
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== BANCO ===");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Listar contas");
        System.out.println("5 - Consultar saldo");
        System.out.println("0 - Sair");
    }

    private static int lerOpcao() {
        while (true) {
            try {
                System.out.print("Opção: ");
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números!");
            }
        }
    }

    private static void processarOpcao(int opcao) {
        switch(opcao) {
            case 1 -> criarConta();
            case 2 -> operacao("depositar");
            case 3 -> operacao("sacar");
            case 4 -> listarContas();
            case 5 -> consultarSaldo();
            case 0 -> System.out.println("Encerrando...");
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void criarConta() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo (Corrente/Poupança): ");
        String tipo = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();
        
        contas.add(new Cliente(nome, tipo, proximaConta++, saldo));
        System.out.println("Conta criada! Nº: " + (proximaConta-1));
    }

    private static void operacao(String tipo) {
        if(contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada!");
            return;
        }
        
        System.out.print("Nº conta: ");
        int numero = scanner.nextInt();
        System.out.print("Valor: R$");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Cliente conta = buscarConta(numero);
        if(conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        if(tipo.equals("depositar")) {
            conta.depositar(valor);
        } else {
            conta.sacar(valor);
        }
    }

    private static void listarContas() {
        if(contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada!");
            return;
        }
        contas.forEach(System.out::println);
    }

    private static void consultarSaldo() {
        System.out.print("Nº conta: ");
        Cliente conta = buscarConta(scanner.nextInt());
        scanner.nextLine();
        
        if(conta != null) {
            System.out.printf("Saldo: R$%.2f%n", conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    private static Cliente buscarConta(int numero) {
        for(Cliente c : contas) {
            if(c.getNumeroConta() == numero) {
                return c;
            }
        }
        return null;
    }
}