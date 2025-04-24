package drive.rede.com.br.model;

import drive.rede.com.br.model.ListaReproducao;
import drive.rede.com.br.model.Musica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReprodutorLista {

    private ListaReproducao listaReproducao;
    private String status;
    private Clip clip;

    public ReprodutorLista() {
        this.status = "Parado";
    }

    public ReprodutorLista(ListaReproducao listaReproducao) {
        this.listaReproducao = listaReproducao;
        this.status = "Parado";
    }

    public void setListaReproducao(ListaReproducao lista) {
        this.listaReproducao = lista;
    }

    public ListaReproducao getListaReproducao() {
        return this.listaReproducao;
    }

    public void play() {
        if (listaReproducao == null || listaReproducao.getMusicas().isEmpty()) {
            System.out.println("Lista de reprodução vazia ou não definida.");
            return;
        }

        Musica musicaAtual = listaReproducao.getMusicaAtual();

        if (musicaAtual == null) {
            System.out.println("Nenhuma música encontrada.");
            return;
        }

        try {
            File audioFile = new File(musicaAtual.getCaminho());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            status = "Tocando";

            System.out.println("Tocando: " + musicaAtual.getNome());

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            status = "Pausado";
            System.out.println("Música pausada.");
        }
    }

    public void restartMusica() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
            status = "Reiniciada";
            System.out.println("Música reiniciada.");
        }
    }

    public void restartLista() {
        if (listaReproducao != null) {
            listaReproducao.reiniciar();
            stop();
            play();
            System.out.println("Lista reiniciada.");
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            status = "Parado";
            System.out.println("Reprodução parada.");
        }
    }
}