import java.io.*;
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

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1025);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        System.out.println("Cliente iniciado");

        String opcao = menu();
        int opcaoInt = Integer.parseInt(opcao.split(" ")[0]);

        while(opcaoInt < opcoes.size()) {
            dos.writeUTF(opcao);

            if (opcaoInt == 1) {
                System.out.println(dis.readUTF());
            }

            opcao = menu();
            opcaoInt = Integer.parseInt(opcao.split(" ")[0]);
        }

        System.out.println("Cliente encerrado");
    }

    public static String menu() {
        System.out.println("\n=== Network File System ===");
        opcoes.forEach((opcao) -> {
            System.out.println((opcoes.indexOf(opcao) + 1) + " - " + opcao);
        });

        Scanner entrada = new Scanner(System.in);
        return entrada.nextLine();
    }
}
