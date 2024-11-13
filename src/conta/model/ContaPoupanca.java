package conta.model;

public class ContaPoupanca extends Conta{

	private int aniversario;

	public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
		super(numero, agencia, tipo, titular, saldo);
		this.aniversario = aniversario;
	}
	
	public ContaPoupanca(ContaDTO dto) {		
		super(dto.numero(), dto.agencia(), 1,dto.titular(), dto.saldo(), dto.totalTransacoes(),
				dto.extrato().stream().map(e-> new Transacao(e)).toList());
		
		this.aniversario = 0;
	}

	public int getAniversario() {
		return aniversario;
	}

	public void setAniversario(int aniversario) {
		this.aniversario = aniversario;
	}
	
    @Override
	public void visualizar() {
		super.visualizar();
		System.out.printf("Aniversário da conta: %d\n\n", this.aniversario);
	}
}