package votacaocolegiado;

import java.util.ArrayList;
import java.util.Scanner;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : MembroColegiado.java
//  @ Date : 08/06/2012
//  @ Author : 
//
//

/**
 * Classe MembroColegiado que é classe filha do Usuario
 * 
 */
public class MembroColegiado extends Usuario {

	public MembroColegiado(String nome, int matricula, String senha) {
		super(nome, matricula, senha);
		
		acoes.add(EnumAcoes.Votar);
	}

	public void votar(ArrayList<Votacao> listaVotacoes)
			throws VotacaoNaoEncontradaException {
		String nomeVotacao = null;
		Votacao votTemp;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite o t�tulo da vota��o: ");
		nomeVotacao = scanner.nextLine();

		int i = 0;
		for (i = 0; i < listaVotacoes.size(); i++) {
			if (listaVotacoes.get(i).getTitulo().equals(nomeVotacao))
				try {
					votTemp = listaVotacoes.get(i);
					votTemp.insereVoto(this);
					return;
				} catch (UsuarioJaVotouException e) {
					System.out.println("Voc� j� votou nessa vota��o");
					return;
				} catch (votacaoFechadaException e) {
					System.out.println("A vota��o ainda n�o est� aberta");
					return;
				}
		}
		if (i >= listaVotacoes.size())
			throw new VotacaoNaoEncontradaException();
	}
}