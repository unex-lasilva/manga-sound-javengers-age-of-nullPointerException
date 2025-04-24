
package br.com.mangarosa;
import br.com.mangarosa.controller.MangaController;
import br.com.mangarosa.model.Musica;
import br.com.mangarosa.model.ListaReproducao;
import br.com.mangarosa.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MangaSoundApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MangaController controller = new MangaController();
        List<Musica> repositorio = new ArrayList<>();
        List<ListaReproducao> playlists = new ArrayList<>();
        Player player;

        while (true) {
            System.out.println("\n MangaSound - Menu Principal");
            System.out.println("1. Adicionar Música ao Repositório");
            System.out.println("2. Criar Lista de Reprodução");
            System.out.println("3. Editar Lista de Reprodução");
            System.out.println("4. Executar Lista de Reprodução");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            int opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    System.out.print("Caminho do arquivo .wav: ");
                    Path src = Paths.get(sc.nextLine());
                    Path dst = Paths.get("repositorio", src.getFileName().toString());
                    try {
                        Files.createDirectories(dst.getParent());
                        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println(" Erro ao copiar arquivo: " + e.getMessage());
                        break;
                    }
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Artista: ");
                    String artista = sc.nextLine();
                    System.out.print("Duração (segundos): ");
                    int duracao = Integer.parseInt(sc.nextLine());
                    Musica m = new Musica(titulo, artista, dst.toString(), duracao);
                    repositorio.add(m);
                    controller.adicionarMusica(titulo, dst.toString(), artista);
                    break;

                case 2:
                    System.out.print("Nome da nova lista: ");
                    String nomeLista = sc.nextLine();
                    ListaReproducao lr = new ListaReproducao(nomeLista);
                    playlists.add(lr);
                    controller.criarListaReproducao(nomeLista);
                    break;

                case 3:
                    if (playlists.isEmpty()) {
                        System.out.println(" Não há listas para editar.");
                        break;
                    }
                    System.out.println("Listas de Reprodução:");
                    for (int i = 0; i < playlists.size(); i++) {
                        System.out.println(i + ": " + playlists.get(i).getNome());
                    }
                    System.out.print("Escolha o número da lista: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx < 0 || idx >= playlists.size()) {
                        System.out.println(" Lista inválida.");
                        break;
                    }
                    ListaReproducao playlistSelecionada  = playlists.get(idx);
                    System.out.println("1. Adicionar música");
                    System.out.println("2. Mover música");
                    System.out.println("3. Remover música");
                    System.out.println("4. Excluir lista");
                    System.out.print("Escolha a ação: ");
                    int action = Integer.parseInt(sc.nextLine());
                    switch (action) {
                        case 1:
                            if (repositorio.isEmpty()) {
                                System.out.println("⚠ Repositório vazio.");
                                break;
                            }
                            System.out.println("Repositório:");
                            for (int i = 0; i < repositorio.size(); i++) {
                                System.out.println(i + ": " + repositorio.get(i));
                            }
                            System.out.print("Índice da música: ");
                            int indiceMusica  = Integer.parseInt(sc.nextLine());
                            playlistSelecionada .addMusica(repositorio.get(indiceMusica ));
                            System.out.println(" Música adicionada à playlist.");
                            break;
                        case 2:
                            for (int i = 0; i < playlistSelecionada .tamanho(); i++) {
                                System.out.println(i + ": " + playlistSelecionada .obterMusica(i));
                            }
                            System.out.print("Índice origem: ");
                            int from = Integer.parseInt(sc.nextLine());
                            System.out.print("Índice destino: ");
                            int to = Integer.parseInt(sc.nextLine());
                            Musica mov = playlistSelecionada .obterMusica(from);
                            playlistSelecionada .removerMusica(from);
                            playlistSelecionada .inserirMusicaEm(to, mov);
                            System.out.println(" Música movida.");
                            break;
                        case 3:
                            for (int i = 0; i < playlistSelecionada .tamanho(); i++) {
                                System.out.println(i + ": " + playlistSelecionada .obterMusica(i));
                            }
                            System.out.print("Índice da música: ");
                            int rem = Integer.parseInt(sc.nextLine());
                            playlistSelecionada .removerMusica(rem);
                            System.out.println(" Música removida.");
                            break;
                        case 4:
                            String nm = playlistSelecionada .getNome();
                            playlists.remove(idx);
                            controller.excluirListaReproducao(nm);
                            System.out.println(" Lista '" + nm + "' excluída.");
                            break;
                        default:
                            System.out.println(" Ação inválida.");
                    }
                    break;

                case 4:
                    if (playlists.isEmpty()) {
                        System.out.println(" Não há listas para reproduzir.");
                        break;
                    }
                    System.out.println("Listas: ");
                    for (int i = 0; i < playlists.size(); i++) {
                        System.out.println(i + ": " + playlists.get(i).getNome());
                    }
                    System.out.print("Escolha: ");
                    int psel = Integer.parseInt(sc.nextLine());
                    ListaReproducao play = playlists.get(psel);
                    player = new Player(play);
                    try {
                        player.tocar();
                    } catch (Exception e) {
                        System.out.println(" Erro: " + e.getMessage());
                        break;
                    }
                    boolean playing = true;
                    while (playing) {
                        System.out.print("[P]-Pausar [C]-Continuar [N]-Next [B]-Back [R]-Reiniciar [E]-Encerrar: ");
                        String cmd = sc.nextLine().trim().toUpperCase();
                        try {
                            switch (cmd) {
                                case "P": player.pausar(); break;
                                case "C": player.continuar(); break;
                                case "N": player.proxima(); break;
                                case "B": player.anterior(); break;
                                case "R": player.reiniciar(); break;
                                case "E": player.parar(); playing = false; break;
                                default: System.out.println(" Inválido.");
                            }
                        } catch (Exception e) {
                            System.out.println(" Erro de reprodução: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    System.out.println(" Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println(" Opção inválida.");
            }
        }
    }
}
