package votacaocolegiado;

import java.util.ArrayList;
import java.util.Scanner;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Usuario.java
//  @ Date : 08/06/2012
//  @ Author : 
//
//


public class Usuario {
	protected String nome;
	protected int matricula;
	protected String senha;
	
	//Acoes permitidas para o usuario
	public enum EnumAcoes { 
		CriarVotacao,
		AbreVotacao,
		FechaVotacao,
		Votar
	};
	
	protected ArrayList<EnumAcoes> acoes;

	public Usuario(String nome, int matricula, String senha) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.acoes = new ArrayList<Usuario.EnumAcoes>();
	}
	
	// Os m�todos get da classe Usu�rio
	public ArrayList<EnumAcoes> getAcoes() {
		return acoes;
	}
	
	public int getMatricula() {
		return matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public boolean senhaCorreta(String senha) {
		if (senha.equals(this.senha))
			return true;
		else
			return false;
	}
	
	public void lerAta(ArrayList<Ata> listaAtas) throws AtaNaoEncontradaException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o nome da ata: ");
		String titulo = scanner.nextLine();
		
		int i;
		for (i = 0; i < listaAtas.size(); i++)
		{
			if (listaAtas.get(i).getTitulo().equals(titulo))
			{
				System.out.println(titulo);
				System.out.println(listaAtas.get(i).getTexto());
				return;
			}
		}
		
		if(i >= listaAtas.size())
			throw new AtaNaoEncontradaException();
	}
}
