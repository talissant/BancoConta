public class Cliente {
    private String nome;
    private String tipoConta;  
    private final int numeroConta; 
    private double saldo;

    public Cliente(String nome, String tipoConta, int numeroConta, double saldo) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo");
        }
        this.nome = nome;
        this.tipoConta = tipoConta;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        saldo += valor;
        System.out.println("Depósito realizado. Novo saldo: R$" + saldo);
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        saldo -= valor;
        System.out.println("Saque realizado. Novo saldo: R$" + saldo);
    }

    public String getNome() { return nome; }
    public String getTipoConta() { return tipoConta; }
    public int getNumeroConta() { return numeroConta; }
    public double getSaldo() { return saldo; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public String toString() {
        return String.format("""
                **************************
                DADOS DO CLIENTE:
                =========================
                NOME: %s.
                TIPO DE CONTA: %s.
                SALDO INICIAL: R$%.2f.
                """, nome, tipoConta, saldo);
    }
}