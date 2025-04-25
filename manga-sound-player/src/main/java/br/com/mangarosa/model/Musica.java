package br.com.mangarosa.model;

public class Musica {
    private String nome;
    private String artista;
    private String caminho;
    private int duracao; // duração em segundos

    public Musica(String nome, String artista, String caminho, int duracao) {
        this.nome = nome;
        this.artista = artista;
        this.caminho = caminho;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public String getCaminho() {
        return caminho;
    }

    public int getDuracao() {
        return duracao;
    }


    @Override
    public String toString() {
        return nome + " - " + artista + " (" +duracao+ ")";
    }
}
