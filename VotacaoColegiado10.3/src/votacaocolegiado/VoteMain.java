/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package votacaocolegiado;

/**
 *
 *
 */
public class VoteMain {

    public static void main(String[] args) {
    	CadastraUser cadastro = new CadastraUser();
    	cadastro.popularListas();

    	ProgramInterface programa = new ProgramInterface(cadastro);
    	programa.execucao();
    }
}
