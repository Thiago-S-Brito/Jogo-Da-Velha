import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        char[][] tabuleiro = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        char jogadorAtual = 'X';
        boolean jogoEmAndamento = true;

        while (jogoEmAndamento) {
            exibirTabuleiro(tabuleiro);
            fazerJogada(tabuleiro, jogadorAtual);
            jogoEmAndamento = !verificarVitoria(tabuleiro, jogadorAtual) && !verificarEmpate(tabuleiro);
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        exibirTabuleiro(tabuleiro);
        if (verificarVitoria(tabuleiro, 'X')) {
            System.out.println("Jogador X venceu!");
        } else if (verificarVitoria(tabuleiro, 'O')) {
            System.out.println("Jogador O venceu!");
        } else {
            System.out.println("Empate!");
        }
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    public static void fazerJogada(char[][] tabuleiro, char jogadorAtual) {
        Scanner scanner = new Scanner(System.in);
        int linha, coluna;

        do {
            System.out.print("Jogador " + jogadorAtual + ", digite a linha (0, 1, 2) e a coluna (0, 1, 2) da sua jogada: ");
            linha = scanner.nextInt();
            coluna = scanner.nextInt();
        } while (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || tabuleiro[linha][coluna] != ' ');

        tabuleiro[linha][coluna] = jogadorAtual;
    }

    public static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        // Verificar linhas, colunas e diagonais para a vitória
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true; // Linhas
            }
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true; // Colunas
            }
        }
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true; // Diagonal principal
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true; // Diagonal secundária
        }
        return false;
    }

    public static boolean verificarEmpate(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda há espaços vazios no tabuleiro
                }
            }
        }
        return true; // O tabuleiro está cheio (empate)
    }
}
