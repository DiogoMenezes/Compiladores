package afd;

import java.util.Scanner;

interface Estado {
	public Estado proximo(Token palavra);
}

//interface EstadoFinal extenderá função da interface Estado
interface EstadoFinal extends Estado {
}

class Token {
	private String entrada;
	private int atual;

        //Funciona como o método Set, atribuindo a entrada o valor que será recebido por parâmetro
	public Token(String entrada) {
		this.entrada = entrada;
	}

        //Retorna a posição da String entrada , incrementando +1 sempre que for chamada
	char ler() {
		return entrada.charAt(atual++);
	}

        // Retorna true se o atributo atual for menor que o comprimento do atributo entrada
	boolean percorrerPalavra() {
		return atual < entrada.length();
	}
}

//enum é um recurso em java que serve para trabalhar com constantes
//enum EstadoAceito implementa EstadoFinal e tem Aceito como constante, que retorna Aceito se for chamado
enum EstadoAceito implements EstadoFinal {
	Aceito {
		public Estado proximo(Token palavra) {
			return Aceito;
		}
	};
}

//enum EstadoNegado implementa EstadoFinal e tem Falha como constante, que retorna Falha se for chamado
enum EstadoNegado implements EstadoFinal {
	Falha {
		public Estado proximo(Token palavra) {
			return Falha;
		}
	};
}

//enum Estados implementa Estado que tem como constantes os 7 estados
enum Estados implements Estado {
	Q0 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q1;
				case '1':
					return Q4;
				default:
					return EstadoNegado.Falha;
				}
			}
			return EstadoNegado.Falha;
		}
	},

	Q1 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q5;
				case '1':
					return Q2;
				default:
					return EstadoNegado.Falha;
				}
			}
			return EstadoNegado.Falha;
		}
	},
        Q2 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q0;
				case '1':
					return Q2;
				default:
					return EstadoNegado.Falha;
				}
			}
                        return EstadoAceito.Aceito;
		}
	},
        Q3 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q6;
				case '1':
					return Q4;
				default:
					return EstadoNegado.Falha;
				}
			}
			return EstadoNegado.Falha;
		}
	},
        Q4 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q2;
				case '1':
					return Q5;
				default:
					return EstadoNegado.Falha;
				}
			}
			return EstadoNegado.Falha;
		}
	},
        Q5 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q5;
				case '1':
					return Q3;
				default:
					return EstadoNegado.Falha;
				}
			}
			return EstadoNegado.Falha;
		}
	},
        Q6 {
		public Estado proximo(Token palavra) {
			if (palavra.percorrerPalavra()) {
				switch (palavra.ler()) {
				case '0':
					return Q5;
				case '1':
					return Q2;
				default:
					return EstadoNegado.Falha;
				}
			}
			return EstadoNegado.Falha;
		}
	};

	//public abstract Estado proximo(Token palavra);
}

public class AFD {
	public static void main(String[] args) {
		Estado e;
                String n;
                Scanner ler = new Scanner(System.in);
                    System.out.printf("Informe a palavra desejada: (Digite sair para encerrar o programa)\n");
                    n = ler.next();
                while(!(n.equals("sair"))){    
                    Token in = new Token(n);

                        for (e = Estados.Q0; !(e instanceof EstadoFinal); e = e.proximo(in)) {
                        }
                        System.out.println(e);
                         System.out.printf("Informe a palavra desejada: (Digite sair para encerrar o programa)\n");
                         n = ler.next();
                }
	}
}