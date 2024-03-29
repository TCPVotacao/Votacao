package votacaocolegiado;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ProgressaoFuncional.java
//  @ Date : 08/06/2012
//  @ Author : 
//
//

public class ProgressaoFuncional extends Votacao {
	private String relatorio;
	private String parecerComissao;

	public ProgressaoFuncional(String titulo) {
		super(titulo);
		this.parecerComissao = null;
		this.relatorio = null;
		// super.tipo = 'P';
	}

	public void insereDocumentos(String relatorio, String parecerComissao) {
		this.relatorio = relatorio;
		this.parecerComissao = parecerComissao;
	}

	public String getRelatorio() {
		return this.relatorio;
	}

	public String getParecerComissao() {
		return this.parecerComissao;
	}

	public void lerVotacao() {
		super.lerVotacao();
		System.out.println("Relatório: " + this.relatorio);
		System.out.println("Parecer: " + this.parecerComissao);
	}
}
