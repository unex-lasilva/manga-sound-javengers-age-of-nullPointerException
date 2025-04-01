package br.com.mangarosa.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListaEncadeadaTest {

    private ListaEncadeada lista;

    @BeforeEach
    void setUp() {
        lista = new ListaEncadeada();
    }

    @Test
    void testAppend() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        assertEquals(5, lista.size(), "A lista deveria ter 5 elementos.");
        assertEquals(10, lista.get(0), "O primeiro elemento deveria ser 10.");
        assertEquals(20, lista.get(1), "O segundo elemento deveria ser 20.");
        assertEquals(30, lista.get(2), "O terceiro elemento deveria ser 30.");
        assertEquals(40, lista.get(3), "O quarto elemento deveria ser 40.");
        assertEquals(50, lista.get(4), "O quinto elemento deveria ser 50.");
    }

    @Test
    void testRemove() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        boolean result = lista.remove(0);  // Remove o elemento na posição 0
        assertTrue(result, "Deveria ter removido o elemento na posição 0.");
        assertEquals(4, lista.size(), "A lista deveria ter 4 elementos após remoção.");
        assertEquals(20, lista.get(0), "O novo primeiro elemento deveria ser 20.");
    }

    @Test
    void testRemovePosicaoInvalida() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        // Tentando remover de uma posição inválida (fora do alcance)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.remove(10);  // Posição inválida
        }, "Deveria lançar IndexOutOfBoundsException ao remover uma posição inválida.");
    }

    @Test
    void testInsertAt() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        lista.insertAt(2, 25);  // Insere na posição 2
        assertEquals(6, lista.size(), "A lista deveria ter 6 elementos após inserção.");
        assertEquals(25, lista.get(2), "O elemento na posição 2 deveria ser 25.");
        assertEquals(30, lista.get(3), "O elemento na posição 3 deveria ser 30.");
    }

    @Test
    void testInsertAtPosicaoInvalida() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        // Tentando inserir em uma posição fora do limite (posição inválida)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.insertAt(10, 60);  // Posição inválida
        }, "Deveria lançar IndexOutOfBoundsException ao tentar inserir em uma posição inválida.");
    }

    @Test
    void testInsertAtPosicaoZero() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        lista.insertAt(0, 5);  // Insere na posição 0
        assertEquals(6, lista.size(), "A lista deveria ter 6 elementos após inserção.");
        assertEquals(5, lista.get(0), "O elemento na posição 0 deveria ser 5.");
        assertEquals(10, lista.get(1), "O elemento na posição 1 deveria ser 10.");
    }

    @Test
    void testIsEmpty() {
        assertTrue(lista.isEmpty(), "A lista deveria estar vazia.");
        lista.append(10);
        lista.append(20);
        lista.append(30);
        assertFalse(lista.isEmpty(), "A lista não deveria estar vazia após adicionar elementos.");
    }

    @Test
    void testSize() {
        assertEquals(0, lista.size(), "O tamanho da lista deveria ser 0 inicialmente.");
        lista.append(10);
        lista.append(20);
        lista.append(30);
        assertEquals(3, lista.size(), "O tamanho da lista deveria ser 3 após adicionar 3 elementos.");
        lista.append(40);
        lista.append(50);
        assertEquals(5, lista.size(), "O tamanho da lista deveria ser 5 após adicionar mais 2 elementos.");
    }

    @Test
    void testAddAll() {
        ListaEncadeada outraLista = new ListaEncadeada();
        outraLista.append(60);
        outraLista.append(70);
        outraLista.append(80);
        outraLista.append(90);
        lista.append(10);
        lista.append(20);
        lista.append(30);

        lista.addAll(outraLista);
        assertEquals(7, lista.size(), "A lista deveria ter 7 elementos após adicionar todos da outra lista.");
        assertEquals(60, lista.get(3), "O quarto elemento deveria ser 60.");
        assertEquals(90, lista.get(6), "O sétimo elemento deveria ser 90.");
    }

    @Test
    void testAddAllComListaVazia() {
        ListaEncadeada outraLista = new ListaEncadeada();
        lista.append(10);
        lista.append(20);
        lista.addAll(outraLista);
        assertEquals(2, lista.size(), "A lista não deveria ser alterada ao adicionar uma lista vazia.");
    }

    @Test
    void testIndexOf() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        int index = lista.indexOf(30);
        assertEquals(2, index, "O índice do valor 30 deveria ser 2.");

        int indexInvalido = lista.indexOf(60);
        assertEquals(-1, indexInvalido, "O índice de um valor não existente deveria ser -1.");
    }

    @Test
    void testContains() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        assertTrue(lista.contains(20), "A lista deveria conter o valor 20.");
        assertFalse(lista.contains(60), "A lista não deveria conter o valor 60.");
    }

    @Test
    void testContainsComListaVazia() {
        assertFalse(lista.contains(10), "A lista vazia não deve conter nenhum valor.");
    }

    @Test
    void testClear() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        assertTrue(lista.clear(), "A lista deveria ser limpa com sucesso.");
        assertTrue(lista.isEmpty(), "A lista deveria estar vazia após o clear.");
    }

    @Test
    void testClearComListaVazia() {
        assertFalse(lista.clear(), "A lista já está vazia, não deveria fazer alterações.");
    }

    @Test
    void testGet() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        assertEquals(10, lista.get(0), "O valor na posição 0 deveria ser 10.");
        assertEquals(20, lista.get(1), "O valor na posição 1 deveria ser 20.");
        assertEquals(30, lista.get(2), "O valor na posição 2 deveria ser 30.");
        assertEquals(40, lista.get(3), "O valor na posição 3 deveria ser 40.");
        assertEquals(50, lista.get(4), "O valor na posição 4 deveria ser 50.");
    }

    @Test
    void testGetPosicaoInvalida() {
        lista.append(10);
        lista.append(20);
        lista.append(30);
        lista.append(40);
        lista.append(50);

        // Tentando acessar uma posição inválida (fora do limite)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.get(10);  // Posição inválida
        }, "Deveria lançar IndexOutOfBoundsException ao acessar uma posição inválida.");
    }

    @Test
    void testGetComListaVazia() {
        // Tentando acessar um índice em uma lista vazia
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.get(0);  // Posição inválida para lista vazia
        }, "Deveria lançar IndexOutOfBoundsException ao acessar uma posição em uma lista vazia.");
    }

    @Test
    void testRemoverDeUmaListaVazia() {
        // Tentando remover um elemento de uma lista vazia
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.remove(0);  // Posição inválida em lista vazia
        }, "Deveria lançar IndexOutOfBoundsException ao tentar remover de uma lista vazia.");
    }

    @Test
    void testInserirElementosDeTiposDiversos() {
        lista.append("Texto");
        lista.append(10);
        lista.append(5.5);
        lista.append(true);
        lista.append('A');

        assertEquals("Texto", lista.get(0), "O primeiro elemento deveria ser 'Texto'.");
        assertEquals(10, lista.get(1), "O segundo elemento deveria ser 10.");
        assertEquals(5.5, lista.get(2), "O terceiro elemento deveria ser 5.5.");
        assertEquals(true, lista.get(3), "O quarto elemento deveria ser true.");
        assertEquals('A', lista.get(4), "O quinto elemento deveria ser 'A'.");
    }
}
