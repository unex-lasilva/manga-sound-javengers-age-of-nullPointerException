// ListaReproducao.java
package br.com.mangarosa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma playlist de músicas.
 */
public class ListaReproducao {
    private String nome;
    private List<Musica> musicas;
    private int atual = 0;

    public ListaReproducao(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean isVazia() {
        return musicas.isEmpty();
    }

    public int tamanho() {
        return musicas.size();
    }

    public void addMusica(Musica musica) {
        musicas.add(musica);
    }

    public boolean removerMusica(int posicao) {
        if (posicao < 0 || posicao >= musicas.size()) return false;
        musicas.remove(posicao);
        if (atual >= musicas.size()) atual = musicas.size() - 1;
        return true;
    }

    public void inserirMusicaEm(int posicao, Musica musica) {
        if (posicao < 0 || posicao > musicas.size())
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        musicas.add(posicao, musica);
    }

    public Musica obterMusica(int posicao) {
        if (posicao < 0 || posicao >= musicas.size()) return null;
        return musicas.get(posicao);
    }

    public Musica getMusicaAtual() {
        if (musicas.isEmpty()) return null;
        return musicas.get(atual);
    }

    public Musica proximaMusica() {
        if (atual < musicas.size() - 1) atual++;
        return getMusicaAtual();
    }

    public Musica musicaAnterior() {
        if (atual > 0) atual--;
        return getMusicaAtual();
    }

    public void reiniciar() {
        atual = 0;
    }
    public List<Musica> getMusicas() {
        return musicas;
    }

}