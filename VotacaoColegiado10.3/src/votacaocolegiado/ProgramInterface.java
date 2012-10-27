package votacaocolegiado;

import java.util.*;

public class ProgramInterface {

	private ArrayList<Usuario> listaUsuarios;
	private ArrayList<Votacao> listaVotacoes;
	private ArrayList<Ata> listaAtas;

	public ProgramInterface(CadastraUser cadastre) {
		this.listaUsuarios = cadastre.getListaUsuario();
		this.listaVotacoes = cadastre.getListaVotacao();
		this.listaAtas = cadastre.getListaAta();
	}

	public void execucao() {

		String nomeUs = null;
		String senhaUs = null;
		Usuario logado = null;
		int opcao = 0;
		boolean naoSaiu = true;

		Scanner scannerInt = new Scanner(System.in);
		Scanner scannerString = new Scanner(System.in);

		while (naoSaiu) {
			System.out.println("-------------------------");
			System.out.println("Para fazer login digite 1");
			System.out.println("Para sair digite 2");
			try {
				opcao = scannerInt.nextInt();
				switch (opcao) {
				case 1:
					System.out.print("Nome de usuário: ");
					nomeUs = scannerString.nextLine();
					System.out.print("Senha: ");
					senhaUs = scannerString.nextLine();
					try {
						logado = this.login(listaUsuarios, nomeUs, senhaUs);
						estaLogado(logado);
					} catch (SenhaInvalida e) {
						System.out.println("Senha invalida");
					} catch (UsuarioInexistente e) {
						System.out.println("Usuário não encontrado");
					}
					break;
				case 2:
					naoSaiu = false;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				scannerInt = new Scanner(System.in);
			}
		}

	}

	private void estaLogado(Usuario logado) {
		int opcao = 0;
		boolean estaLogado = true;
		Scanner scanner = new Scanner(System.in);

		ArrayList<Usuario.EnumAcoes> acoes = logado.getAcoes();
		while (estaLogado) {
			opcao = 0;

			System.out.println("-------Bemvindo(a) " + logado.getNome()
					+ "------");

			for (int i = 0; i < acoes.size(); i++) {
				String textoAcao = "Opção " + String.valueOf(i + 1) + ": ";
				switch (acoes.get(i)) {
				case CriarVotacao:
					textoAcao += "criar votação";
					break;
				case AbreVotacao:
					textoAcao += "abrir uma votação";
					break;
				case Votar:
					textoAcao += "votar";
					break;
				case FechaVotacao:
					textoAcao += "fechar votação e gerar ata";
					break;
				}
				System.out.println(textoAcao);
			}

			// Apos a percorrer a lista de acoes, mostra as opcoes liberadas
			// para todos os usuarios
			System.out.println("Opção " + String.valueOf(acoes.size() + 1)
					+ ": ler uma votação");
			System.out.println("Opção " + String.valueOf(acoes.size() + 2)
					+ ": ler uma ata");
			System.out.println("Opção " + String.valueOf(acoes.size() + 3)
					+ ": logoff");
			System.out.print("O que deseja fazer: ");

			try {
				opcao = scanner.nextInt();
				if (opcao >= 1 && opcao <= acoes.size()) {
					switch (acoes.get(opcao - 1)) {
					case CriarVotacao:
						if (logado instanceof Secretaria) {
							((Secretaria) logado).criarVotacao(listaVotacoes);
						} else if (logado instanceof Chefia) {
							((Chefia) logado).criarVotacao(listaVotacoes);
						}
						break;
					case Votar:
						try {
							((MembroColegiado) logado).votar(listaVotacoes);
						} catch (VotacaoNaoEncontradaException e) {
							System.out
									.println("Votação requerida não foi encontrada");
						}
						break;
					case AbreVotacao:
						try {
							((Chefia) logado).abreVotacao(listaVotacoes);
						} catch (VotacaoNaoEncontradaException e) {
							System.out
									.println("Votação requerida não foi encontrada");
						}
						break;
					case FechaVotacao:
						try {
							((Chefia) logado).fechaVotGeraAta(listaVotacoes,
									listaAtas);
						} catch (VotacaoNaoEncontradaException e) {
							System.out
									.println("Votação requerida não foi encontrada");
						}
						break;
					}
				} else {
					switch (opcao - acoes.size()) {
					case 1:
						pedeVotacaoParaLeitura();
						break;
					case 2:
						try {
							logado.lerAta(listaAtas);
						} catch (AtaNaoEncontradaException e) {
							System.out.println("Ata não encontrada");
						}
						break;

					case 3:
						return;// sai da funcão estaLogado
					default:
						System.out.println("Opção inválida");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Valor entrado é inválido");
				scanner = new Scanner(System.in);
			}
		}
	}

	private Usuario login(ArrayList<Usuario> listaUsuarios, String nome,
			String senha) throws SenhaInvalida, UsuarioInexistente {
		Usuario logado = null;

		for (int i = 0; i < listaUsuarios.size() && logado == null; i++) {
			if (listaUsuarios.get(i).getNome().equals(nome)) {
				if (listaUsuarios.get(i).senhaCorreta(senha))
					return listaUsuarios.get(i);
				else
					throw new SenhaInvalida();
			}
		}

		throw new UsuarioInexistente();
	}

	private Votacao buscaVotacao(String titulo)
			throws VotacaoNaoEncontradaException {
		for (int indice = 0; indice < listaVotacoes.size(); indice++) {
			if (listaVotacoes.get(indice).getTitulo().equals(titulo))// verifica
																		// se o
																		// título
																		// passado
																		// é
																		// igual
																		// ao da
																		// votação
																		// do
																		// índice
				return listaVotacoes.get(indice);
		}

		throw new VotacaoNaoEncontradaException();
	}

	private void pedeVotacaoParaLeitura() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Insira o nome da votação: ");
		String titulo = scanner.nextLine();
		try {
			Votacao votacaoTemp = buscaVotacao(titulo);
			votacaoTemp.lerVotacao();
		} catch (VotacaoNaoEncontradaException e) {
			System.out.println("Votação não encontrada");
		}
	}
}
