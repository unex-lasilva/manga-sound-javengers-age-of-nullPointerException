package br.com.mangarosa.collections;

import br.com.mangarosa.model.No;

/**
 * A classe {@code ListaEncadeada} implementa uma estrutura de dados de lista encadeada simples.
 * Ela permite adicionar, remover e acessar elementos de maneira eficiente. Esta lista pode armazenar
 * qualquer tipo de objeto e oferece funcionalidades típicas de listas, como adicionar ao final,
 * inserir em posições específicas, remover elementos, verificar a presença de elementos e mais.
 *
 * <p>Os principais métodos incluem:</p>
 * <ul>
 *   <li>{@link #append(Object)} - Adiciona um elemento ao final da lista.</li>
 *   <li>{@link #insertAt(int, Object)} - Insere um elemento em uma posição específica.</li>
 *   <li>{@link #addAll(ListaEncadeada)} - Adiciona todos os elementos de outra lista à lista atual.</li>
 *   <li>{@link #remove(int)} - Remove um elemento de uma posição específica.</li>
 *   <li>{@link #clear()} - Limpa todos os elementos da lista.</li>
 *   <li>{@link #get(int)} - Retorna o elemento na posição especificada.</li>
 *   <li>{@link #isEmpty()} - Verifica se a lista está vazia.</li>
 *   <li>{@link #size()} - Retorna o número de elementos na lista.</li>
 *   <li>{@link #indexOf(Object)} - Retorna o índice da primeira ocorrência do valor especificado.</li>
 *   <li>{@link #contains(Object)} - Verifica se o valor especificado está na lista.</li>
 * </ul>
 *
 * @author Mangarosa
 * @version 1.0
 */
public class ListaEncadeada {
    private No cabeca;
    private int tamanho;

    public ListaEncadeada() {
        this.cabeca = null;
        this.tamanho = 0;
    }
    /**
     * Adiciona um elemento ao final da lista.
     *
     * @param value o valor a ser adicionado ao final da lista.
     */
    public void append(Object value) {
        No novo = new No(value);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.getProx() != null) {
                atual = atual.getProx();
            }
            atual.setProx(novo);
        }
        tamanho++;
    }

    /**
     * Insere um valor em uma posição específica na lista.
     *
     * @param position a posição onde o valor será inserido.
     * @param value o valor a ser inserido.
     * @throws IndexOutOfBoundsException se a posição fornecida for inválida.
     */
    public void insertAt(int position, Object value) {
        if (position < 0 || position > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        No novo = new No(value);
        if (position == 0) {
            novo.setProx(cabeca);
            cabeca = novo;
        } else {
            No atual = cabeca;
            for (int i = 0; i < position - 1; i++) {
                atual = atual.getProx();
            }
            novo.setProx(atual.getProx());
            atual.setProx(novo);
        }
        tamanho++;
    }

    /**
     * Adiciona todos os elementos de outra lista à lista atual.
     *
     * @param list a lista cujos elementos serão adicionados.
     */
    public void addAll(ListaEncadeada list) {
        for (int i = 0; i < list.size(); i++) {
            this.append(list.get(i));
        }
    }

    /**
     * Remove o elemento na posição especificada.
     *
     * @param position a posição do elemento a ser removido.
     * @return {@code true} se a remoção foi bem-sucedida, {@code false} caso contrário.
     */
    public boolean remove(int position) {
        if (position < 0 || position >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        if (position == 0) {
            cabeca = cabeca.getProx();
        } else {
            No atual = cabeca;
            for (int i = 0; i < position - 1; i++) {
                atual = atual.getProx();
            }
            atual.setProx(atual.getProx().getProx());
        }
        tamanho--;
        return true;
    }

    /**
     * Limpa todos os elementos da lista.
     *
     * @return {@code true} se a lista foi limpa com sucesso, {@code false} caso contrário.
     */
    public boolean clear() {
        if(tamanho == 0){
            return false;
        }
        cabeca = null;
        tamanho = 0;
        return true;
    }

    /**
     * Retorna o elemento na posição especificada.
     *
     * @param position a posição do elemento a ser retornado.
     * @return o valor armazenado na posição especificada.
     * @throws IndexOutOfBoundsException se a posição fornecida for inválida.
     */
    public Object get(int position) {
        if (position < 0 || position >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida.");
        }

        No atual = cabeca;
        for (int i = 0; i < position; i++) {
            atual = atual.getProx();
        }
        return atual.getValor();
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return {@code true} se a lista não contiver elementos, {@code false} caso contrário.
     */
    public boolean isEmpty() {
        return tamanho == 0;
    }

    /**
     * Retorna o número de elementos presentes na lista.
     *
     * @return o número de elementos na lista.
     */
    public int size() {
        return tamanho;
    }

    /**
     * Retorna o índice da primeira ocorrência do valor fornecido.
     * Se o valor não estiver na lista, retorna {@code -1}.
     *
     * @param value o valor a ser buscado.
     * @return o índice do valor, ou {@code -1} se não encontrado.
     */
    public int indexOf(Object value) {
        No atual = cabeca;
        int index = 0;
        while (atual != null) {
            if (atual.getValor().equals(value)) {
                return index;
            }
            atual = atual.getProx();
            index++;
        }
        return -1;
    }

    /**
     * Verifica se o valor fornecido está presente na lista.
     *
     * @param value o valor a ser verificado.
     * @return {@code true} se o valor estiver na lista, {@code false} caso contrário.
     */
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }
}
