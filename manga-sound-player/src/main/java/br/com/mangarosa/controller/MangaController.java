package br.com.mangarosa.controller;

import br.com.mangarosa.model.Musica;
import java.util.LinkedList;
import java.util.List;

public class MangaController {
    private List<Musica> repositorioMusica;
    private List<List<Musica>> listasReproducao;
    private List<String> nomesListas;
    private int indiceListaAtual;
    private int indiceMusicaAtual;
    private boolean tocando;

    public MangaController() {
        repositorioMusica = new LinkedList<>();
        listasReproducao = new LinkedList<>();
        nomesListas = new LinkedList<>();
        tocando = false;
        indiceListaAtual = -1;
        indiceMusicaAtual = -1;
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

    public void pausarMusica() {
        if (tocando) {
            tocando = false;
            System.out.println("Música pausada.");
        }
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
        tocando = false;
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
}

