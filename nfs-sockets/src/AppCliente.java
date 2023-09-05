import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppCliente {
    private static List<String> opcoes = new ArrayList<>(){
        {
            add("Listar diretório");
            add("Renomear arquivo");
            add("Criar arquivo");
            add("Excluir arquivo");
            add("Sair");
        }
    };

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1025)) {

            System.out.println("Cliente iniciado");

            int opcao = menu();

            while(opcao < opcoes.size()) {
                PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
                pout.println(opcao);
                BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                switch (opcao) {
                    case 1 -> {
                        System.out.println(bin.readLine());
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                    case 4 -> {
                    }
                }

                opcao = menu();
            }

            System.out.println("Cliente encerrado");
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public static int menu() {
        System.out.println("\n=== Network File System ===");
        opcoes.forEach((opcao) -> {
            System.out.println((opcoes.indexOf(opcao) + 1) + " - " + opcao);
        });

        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }
}
