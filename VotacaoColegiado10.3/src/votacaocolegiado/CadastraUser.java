/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package votacaocolegiado;

import java.util.ArrayList;

/**
 *
 */
public class CadastraUser {

    private ArrayList<Ata> listaAta;
    private ArrayList<Usuario> listaUsuario;
    private ArrayList<Votacao> listaVotacao;

    public CadastraUser() {

        listaAta = new ArrayList<Ata>();
        listaUsuario = new ArrayList<Usuario>();
        listaVotacao = new ArrayList<Votacao>();
    }



	public ArrayList<Ata> getListaAta() {
		return listaAta;
	}

	public void setListaAta(ArrayList<Ata> listaAta) {
		this.listaAta = listaAta;
	}

	public ArrayList<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}


	public ArrayList<Votacao> getListaVotacao() {
		return listaVotacao;
	}


	public void setListaVotacao(ArrayList<Votacao> listaVotacao) {
		this.listaVotacao = listaVotacao;
	}


	public void addicionaChefe(String nome, int matricula, String senha){
        Usuario chefe = new Chefia(nome, matricula, senha);
        listaUsuario.add(chefe);
    }

    public void addicionaMembro(String nome, int matricula, String senha){
        Usuario membro = new MembroColegiado(nome, matricula, senha);
        listaUsuario.add(membro);
    }

    public void addicionaSecretario(String nome, int matricula, String senha){
        Usuario secretario = new Secretaria(nome,matricula,senha);
        listaUsuario.add(secretario);
    }

    public void popularListas(){
    	addicionaMembro("membro2",1,"123");
    	addicionaMembro("membro3",1,"123");
    	addicionaSecretario("sec1",1,"123");
    	addicionaSecretario("sec2",1,"123");
    	addicionaChefe("membro1",1,"123");
    }
}
