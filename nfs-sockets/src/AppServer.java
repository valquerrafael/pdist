import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppServer {
    private static List<String> arquivos = new ArrayList<>() {
        {
            add("arquivo1.arq");
            add("arquivo2.arq");
            add("arquivo3.arq");
        }
    };
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1025);
        System.out.println("Server iniciado\n");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado");

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while (true) {
            String entrada = dis.readUTF();

            int opcao = Integer.parseInt(entrada.split(" ")[0]);

            switch (opcao) {
                case 1 -> {
                    List<String> arquivos = readdir();
                    StringBuilder listagem = new StringBuilder();

                    for (String arquivo : arquivos) {
                        listagem.append(arquivo).append(" ");
                    }

                    dos.writeUTF(listagem.toString());
                }
                case 2 -> rename(entrada.split(" ")[1], entrada.split(" ")[2]);
                case 3 -> create(entrada.split(" ")[1]);
                case 4 -> remove(entrada.split(" ")[1]);
            }
        }
    }

    private static List<String> readdir() {
        return arquivos;
    }

    private static void rename(String nomeAnterior, String nomeNovo) {
        arquivos.set(arquivos.indexOf(nomeAnterior), nomeNovo);
    }

    private static void create(String nomeArquivo) {
        arquivos.add(nomeArquivo);
    }

    private static void remove(String nomeArquivo) {
        arquivos.remove(nomeArquivo);
    }
}
