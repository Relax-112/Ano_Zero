package exercicios_funcionarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Exercicios_Funcionarios {

   public static void main(String[] args) throws FileNotFoundException {
        final int TAMANHO = 50;
        String[] nomes = new String[TAMANHO];
        int[] vencimentos = new int[TAMANHO];
        int nElems = 0, op;
        do {
            op = menu();
            switch (op) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Fim...");
                    break;
                case 1:
                    if (nElems < TAMANHO) {
                        inserir(nomes, vencimentos, nElems);
                        nElems++;
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Atingida a capacidade máxima do vetor");
                    }
                    break;
                case 2:
                    if (nElems > 0) {
                        listar(nomes, vencimentos, nElems);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não existem funcionários");
                    }
                    break;
                case 3:
                    if (nElems > 0) {
                        atualizarVencimento(nomes, vencimentos, nElems);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não existem funcionários");
                    }
                    break;
                case 4:
                    if (nElems > 0) {
                        if (eliminar(nomes, vencimentos, nElems) == true) {
                            JOptionPane.showMessageDialog(null,
                                    "Funcionário eliminado");
                            nElems--;
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Não há qualquer funcionário com o nome introduzido");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não existem funcionários");
                    }
                    break;
                case 5:
                    if (nElems > 0) {
                        ordenar(nomes, vencimentos, nElems);
                        listar(nomes, vencimentos, nElems);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não existem funcionários");
                    }
                    break;
                case 6:
                    if (nElems < TAMANHO) {
                        nElems = carregarDados(nomes, vencimentos, nElems);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Atingida a capacidade máxima do vetor");
                    }
                    break;
                case 7:
                    gravarDados(nomes, vencimentos, nElems);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção inválida");
            }
        } while (op != 0);
    }
    private static void gravarDados(String[] nomes, int[] vencimentos, int nElems) throws FileNotFoundException {
        Formatter fichFunc = new Formatter(new File("funcionarios.txt"));
        for (int x = 0; x < nElems; x++) {
            if (x > 0) {
                fichFunc.format("\n");
            }
            fichFunc.format(nomes[x] + ":" + vencimentos[x]);
        }
        fichFunc.close();
        JOptionPane.showMessageDialog(null, "Funcionários gravados no ficheiro");
    }
    private static int carregarDados(String[] nomes, int[] vencimentos, int nElems) throws FileNotFoundException {
        Scanner fichFunc = new Scanner(new File("funcionarios.txt"));
        int copia = nElems;
        while (fichFunc.hasNextLine() == true && nElems < nomes.length) {
            String linha = fichFunc.nextLine();
            String[] vetLinha = linha.split(":");
            if (pesquisar(nomes, nElems, vetLinha[0].trim()) == -1) {
                nomes[nElems] = vetLinha[0].trim();
                vencimentos[nElems] = Integer.parseInt(vetLinha[1].trim());
                nElems++;
            }
        }
        fichFunc.close();
        JOptionPane.showMessageDialog(null, "Foram carregados " + (nElems - copia) + " funcionários");
        return nElems;
    }
    private static int menu() {
        int op;
        op = Integer.parseInt(JOptionPane.showInputDialog(
                "0 – Sair\n"
                + "1 – Inserir Funcionário\n"
                + "2 – Listar funcionários\n"
                + "3 – Atualizar vencimento de um funcionário\n"
                + "4 – Eliminar funcionário\n"
                + "5 – Mostrar informação ordenada por nome de funcionário\n"
                + "6 - Carregar dados para memória\n"
                + "7 - Gravar dados"));
        return op;
    }
    private static void inserir(String[] nomes, int[] vencimentos,
            int nElems) {
        nomes[nElems] = JOptionPane.showInputDialog("Nome?");
        vencimentos[nElems] = Integer.parseInt(
                JOptionPane.showInputDialog("Vencimento?"));
    }
    private static void listar(String[] nomes, int[] vencimentos,
            int nElems) {
        int x;
        String msg = "Listagem de funcionários\n";
        for (x = 0; x < nElems; x++) {
            msg += "\n" + nomes[x] + " - " + vencimentos[x];
        }
        JOptionPane.showMessageDialog(null, msg);
    }
    private static void atualizarVencimento(String[] nomes,
            int[] vencimentos, int nElems) {
        String nome;
        int pos;
        nome = JOptionPane.showInputDialog(
                "Qual o nome do funcionário cujo vencimento pretende alterar?");
        pos = pesquisar(nomes, nElems, nome);
        if (pos != -1) {
            vencimentos[pos] = Integer.parseInt(
                    JOptionPane.showInputDialog("“Qual o novo vencimento"));
        } else {
            JOptionPane.showMessageDialog(null,
                    "Não há qualquer funcionário com o nome introduzido");
        }
    }
    private static int pesquisar(String[] nomes, int nElems, String nome) {
        int pos = 0;
        while (pos < nElems
                && nome.equalsIgnoreCase(nomes[pos]) == false) {
            pos++;
        }
        if (pos < nElems) {
            return pos;
        } else {
            return -1;
        }
    }
    private static boolean eliminar(String[] nomes, int[] vencimentos,
            int nElems) {
        String nome;
        int pos;
        nome = JOptionPane.showInputDialog(
                "Qual o nome do funcionário que pretende eliminar”?");
        pos = pesquisar(nomes, nElems, nome);
        if (pos != -1) {
            for (int x = pos; x < nElems - 1; x++) {
                nomes[x] = nomes[x + 1];
                vencimentos[x] = vencimentos[x + 1];
            }
            return true;
        } else {
            return false;
        }
    }
    private static void ordenar(String[] nomes, int[] vencimentos, int nElems) {
        int x, y, aux1;
        String aux2;
        for (x = 0; x < nElems - 1; x++) {
            for (y = x + 1; y < nElems; y++) {
                if (nomes[y].compareToIgnoreCase(nomes[x]) < 0) {
                    aux2 = nomes[x];
                    nomes[x] = nomes[y];
                    nomes[y] = aux2;
                    aux1 = vencimentos[x];
                    vencimentos[x] = vencimentos[y];
                    vencimentos[y] = aux1;
                }
            }
        }
    }

}
