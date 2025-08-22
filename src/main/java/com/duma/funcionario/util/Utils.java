package com.duma.funcionario.util;

public class Utils {

    // Remove acentos e força maiúsculo
    public static String toUpper(String val) {
        if (val == null) return null;
        String valor = val.toUpperCase();
        StringBuilder retorno = new StringBuilder();
        String entrada = "ÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙÄËÏÖÜÃÕÇ";
        String saida   = "AEIOUAEIOUAEIOUAEIOUAOC";
        for (int a = 0; a < valor.length(); a++) {
            int b = entrada.indexOf(valor.charAt(a));
            if (b < 0) {
                retorno.append(valor.charAt(a));
            } else {
                retorno.append(saida.charAt(b));
            }
        }
        return retorno.toString();
    }

    public static String pesquisaFonetica(String nome) {
        if (nome == null) return null;

        String vNom = toUpper(nome);

        // Regras de substituição
        vNom = vNom.replace("PH", "F");
        vNom = vNom.replace("SH", "C");
        vNom = vNom.replace("CH", "C");
        vNom = vNom.replace("QU", "C");
        vNom = vNom.replace("H", "");
        vNom = vNom.replace("SSA", "CA");
        vNom = vNom.replace("SSE", "CE");
        vNom = vNom.replace("SSI", "CI");
        vNom = vNom.replace("SSO", "CO");
        vNom = vNom.replace("SSU", "CU");
        vNom = vNom.replace("GUI", "G");
        vNom = vNom.replace("SS", "S");
        vNom = vNom.replace("SC", "C");
        vNom = vNom.replace("CS", "C");

        vNom = " " + trocaDuploCaracter(vNom) + " ";
        vNom = vNom.replace("AA", "A");
        vNom = vNom.replace("WA", "VA");
        vNom = vNom.replace("WE", "VE");
        vNom = vNom.replace("WI", "VI");
        vNom = vNom.replace("WO", "VO");
        vNom = vNom.replace("WU", "VU");

        vNom = vNom.replace("W", "L");
        vNom = vNom.replace("K", "C");
        vNom = vNom.replace("Y", "I");
        vNom = vNom.replace("E", "I");
        vNom = vNom.replace(" SE", " CE");
        vNom = vNom.replace(" SI", " CI");
        vNom = vNom.replace(" S", " Y");
        vNom = vNom.replace("S", "Z");
        vNom = vNom.replace(" Y", " S");
        vNom = vNom.replace("N", "M");
        vNom = vNom.replace("OU", "U");
        vNom = vNom.replace("AI", "A");
        vNom = vNom.replace("OM ", "AO ");
        vNom = vNom.replace("AM ", "AO ");
        vNom = vNom.replace("AN ", "AO ");
        vNom = vNom.replace("IO", "U");
        vNom = vNom.replace("O", "U");
        vNom = vNom.replace("IU", "IL");
        vNom = vNom.replace("GI", "JI");
        vNom = vNom.replace("GE", "JE");
        vNom = vNom.replace("I ", "A ");
        vNom = vNom.replace("DI", "D");
        vNom = vNom.replace("PI", "P");
        vNom = vNom.replace("BI", "B");
        vNom = vNom.replace("X ", " ");
        vNom = vNom.replace("X", "C");

        // Troca "L" por "R" quando não for precedido de vogal
        vNom = trocaLporR(vNom);

        vNom = trocaDuploCaracter(vNom.trim());
        vNom = vNom.replace(" ", "  ");
        vNom = " " + vNom + " ";

        return vNom;
    }

    // Elimina caracteres duplos consecutivos
    private static String trocaDuploCaracter(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        StringBuilder sb = new StringBuilder();
        char prev = 0;
        for (char c : texto.toCharArray()) {
            if (c != prev) {
                sb.append(c);
            }
            prev = c;
        }
        return sb.toString();
    }

    // Troca "L" por "R" se não for precedido de vogal
    private static String trocaLporR(String texto) {
        StringBuilder sb = new StringBuilder(texto);
        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.charAt(i) == 'L') {
                String prox = "" + sb.charAt(i) + sb.charAt(i + 1);
                if (!(prox.equals("LA") || prox.equals("LI") || prox.equals("LU"))) {
                    sb.setCharAt(i, 'R');
                }
            }
        }
        return sb.toString();
    }

    // Teste
    public static void main(String[] args) {
        System.out.println(pesquisaFonetica("marcos martins duma"));
        System.out.println(pesquisaFonetica("CHAVES"));
        System.out.println(pesquisaFonetica("vinicius"));
        System.out.println(pesquisaFonetica("vinicios"));
    }
}
