import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1025)) {
            System.out.println("Server iniciado\n");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String entrada = bin.readLine();

                int opcao = Integer.parseInt(entrada.split(" ")[0]);

                switch (opcao) {
                    case 1 -> {
                        List<String> arquivos = readdir();
                        StringBuilder listagem = new StringBuilder();

                        for(String arquivo : arquivos) {
                            listagem.append(arquivo).append(" ");
                        }

                        pout.println(listagem);
                    }
                    case 2 -> rename(entrada.split(" ")[1], entrada.split(" ")[2]);
                    case 3 -> create(entrada.split(" ")[1]);
                    case 4 -> remove(entrada.split(" ")[1]);
                }

                socket.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
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
