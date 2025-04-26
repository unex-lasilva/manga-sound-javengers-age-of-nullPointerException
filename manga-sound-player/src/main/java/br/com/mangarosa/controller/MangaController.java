package br.com.mangarosa.controller;

import br.com.mangarosa.model.ListaReproducao;
import br.com.mangarosa.model.Musica;
import br.com.mangarosa.model.ReprodutorLista;
import javax.sound.sampled.Clip;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class MangaController {
    private Player player;
    private List<Musica> repositorioMusica;
    private List<List<Musica>> listasReproducao;
    private List<String> nomesListas;
    private int indiceListaAtual;
    private int indiceMusicaAtual;
    private boolean tocando;
    private ReprodutorLista reprodutorLista;

    public MangaController() {
        repositorioMusica = new LinkedList<>();
        listasReproducao = new LinkedList<>();
        nomesListas = new LinkedList<>();
        tocando = false;
        indiceListaAtual = -1;
        indiceMusicaAtual = -1;
    }

    public void startPlayer(ListaReproducao lista) throws Exception {
        if (player != null) {
            player.parar();
        }
        player = new Player(lista);
        player.tocar();
    }

    public void pausarMusica() {
        if (player != null) {
            player.pausar();
        } else {
            System.out.println("Nenhuma reprodução ativa para pausar.");
        }
    }

    public void continuarMusica() {
        if (player != null) {
            try {
                player.continuar();
            } catch (Exception e) {
                System.out.println("Erro ao continuar: " + e.getMessage());
            }
        }
    }

    public void proximaMusica() {
        if (player != null) {
            try {
                player.proxima();
            } catch (Exception e) {
                System.out.println("Erro ao avançar: " + e.getMessage());
            }
        }
    }

    public void musicaAnterior() {
        if (player != null) {
            try {
                player.anterior();
            } catch (Exception e) {
                System.out.println("Erro ao voltar: " + e.getMessage());
            }
        }
    }

    public void reiniciarMusica() {
        if (player != null) {
            try {
                player.reiniciar();
            } catch (Exception e) {
                System.out.println("Erro ao reiniciar: " + e.getMessage());
            }
        }
    }

    public void adicionarMusica(String titulo, String path, String nomeArtista) {
        Musica musica = new Musica(titulo, nomeArtista, path, 180); // duração padrão
        repositorioMusica.add(musica);
        System.out.println("Música adicionada: " + musica);
    }

    public void criarListaReproducao(String titulo) {
        nomesListas.add(titulo);
        listasReproducao.add(new LinkedList<>());
        System.out.println("Lista criada: " + titulo);
    }

    public void excluirListaReproducao(String titulo) {
        int index = nomesListas.indexOf(titulo);
        if (index >= 0) {
            listasReproducao.remove(index);
            nomesListas.remove(index);
            System.out.println("Lista removida: " + titulo);
        }
    }

    public void adicionarMusicaListaReproducao(String tituloMusica, String tituloLista) {
        Musica musica = buscarMusica(tituloMusica);
        int indexLista = nomesListas.indexOf(tituloLista);
        if (musica != null && indexLista >= 0) {
            listasReproducao.get(indexLista).add(musica);
            System.out.println("Música adicionada à lista: " + tituloLista);
        }
    }

    public void adicionarMusicaListaReproducaoEmPosicao(String tituloMusica, String tituloLista, int posicao) {
        Musica musica = buscarMusica(tituloMusica);
        int indexLista = nomesListas.indexOf(tituloLista);
        if (musica != null && indexLista >= 0 && posicao >= 0) {
            listasReproducao.get(indexLista).add(posicao, musica);
            System.out.println("Música adicionada na posição " + posicao + " da lista " + tituloLista);
        }
    }

    public void removerMusicaListaReproducao(String tituloMusica, String tituloLista) {
        int indexLista = nomesListas.indexOf(tituloLista);
        if (indexLista >= 0) {
            List<Musica> lista = listasReproducao.get(indexLista);
            lista.removeIf(m -> m.getNome().equalsIgnoreCase(tituloMusica));
            System.out.println("Música removida da lista: " + tituloLista);
        }
    }

    public void removerMusicaListaReproducaoEmPosicao(String tituloLista, int posicao) {
        int indexLista = nomesListas.indexOf(tituloLista);
        if (indexLista >= 0 && posicao >= 0 && posicao < listasReproducao.get(indexLista).size()) {
            listasReproducao.get(indexLista).remove(posicao);
            System.out.println("Música na posição " + posicao + " removida da lista " + tituloLista);
        }
    }

    public void reproduzirListaDeReproducao(String tituloLista) {
        indiceListaAtual = nomesListas.indexOf(tituloLista);
        indiceMusicaAtual = 0;
        tocando = true;
        executarMusica();
    }

    public void executarMusica() {
        if (tocando && indiceListaAtual >= 0) {
            List<Musica> lista = listasReproducao.get(indiceListaAtual);
            if (indiceMusicaAtual < lista.size()) {
                System.out.println("Tocando: " + lista.get(indiceMusicaAtual));
            }
        }
    }

    public void voltarMusica() {
        if (indiceMusicaAtual > 0) {
            indiceMusicaAtual--;
            executarMusica();
        }
    }

    public void passarMusica() {
        List<Musica> lista = listasReproducao.get(indiceListaAtual);
        if (indiceMusicaAtual < lista.size() - 1) {
            indiceMusicaAtual++;
            executarMusica();
        }
    }

    public void reiniciarLista() {
        indiceMusicaAtual = 0;
        System.out.println("Lista reiniciada.");
        executarMusica();
    }

    public void pararLista() {
        if (player != null) {
            player.pausar();
        } else {
            System.out.println("Nenhuma reprodução ativa para pausar.");
        }
        indiceListaAtual = -1;
        indiceMusicaAtual = -1;
        System.out.println("Reprodução parada.");
    }

    private Musica buscarMusica(String titulo) {
        for (Musica m : repositorioMusica) {
            if (m.getNome().equalsIgnoreCase(titulo)) {
                return m;
            }
        }
        System.out.println("Música não encontrada: " + titulo);
        return null;
    }

    public static class Player {
        private ListaReproducao lista;
        private Clip clip;
        private AudioInputStream stream;
        private Long frameAtual;
        private String status = "stop";

        public Player(ListaReproducao lista) {
            this.lista = lista;
        }

        public void tocar() throws Exception {
            Musica m = lista.getMusicaAtual();
            if (m == null) {
                System.out.println("Nenhuma música disponível na lista.");
                return;
            }
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            File f = new File(m.getCaminho());
            stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            status = "play";
            System.out.println(" Tocando: " + m);
        }

        public void pausar() {
            if (clip != null && status.equals("play")) {
                frameAtual = clip.getMicrosecondPosition();
                clip.stop();
                status = "pause";
                System.out.println(" Música pausada.");
            }
        }

        public void continuar() throws Exception {
            if (clip != null && status.equals("pause")) {
                clip.setMicrosecondPosition(frameAtual);
                clip.start();
                status = "play";
                System.out.println(" Continuando música.");
            }
        }

        public void parar() {
            if (clip != null) {
                clip.stop();
                clip.close();
                status = "stop";
                System.out.println(" Música parada.");
            }
        }

        public void proxima() throws Exception {
            parar();
            lista.proximaMusica();
            tocar();
        }

        public void anterior() throws Exception {
            if (clip != null) {
                long posUS = clip.getMicrosecondPosition();
                if (posUS > 10_000_000L) {
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    status = "play";
                    System.out.println(" Música reiniciada.");
                    return;
                }
            }
            parar();
            lista.musicaAnterior();
            tocar();
        }

        public void reiniciar() throws Exception {
            parar();
            lista.reiniciar();
            tocar();
        }
    }
}
