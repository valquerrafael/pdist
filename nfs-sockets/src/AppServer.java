import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AppServer {
    private static String FILES_DIRECTORY = System.getProperty("user.dir") + "/files";

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

    private static List<String> readdir() throws IOException {
        System.out.println("abc");
        Stream<Path> files = Files.list(Paths.get(FILES_DIRECTORY));
        List<String> filesString = new ArrayList<>();
        for (Path file : files.toList()) {
            filesString.add(file.getFileName().toString());
        }
        return filesString;
    }

    private static void rename(String nomeAnterior, String nomeNovo) throws IOException {
        Path file = Paths.get(FILES_DIRECTORY + "/" + nomeAnterior);
        Files.move(file, file.resolveSibling(nomeNovo));
    }

    private static void create(String nomeArquivo) throws IOException {
        Path file = Paths.get(FILES_DIRECTORY + "/" + nomeArquivo);
        Files.createFile(file);
    }

    private static void remove(String nomeArquivo) throws IOException {
        Path file = Paths.get(FILES_DIRECTORY + "/" + nomeArquivo);
        Files.delete(file);
    }
}
