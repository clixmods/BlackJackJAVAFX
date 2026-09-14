package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Metier.Client;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class RepositoryClientMemoireTest {

    private RepositoryClientMemoire repository;

    @Before
    public void setUp() {
        repository = new RepositoryClientMemoire();
        repository.inserer(new Client("john", "john@mail.com", "Doe", "John", 1000, "hash", LocalDate.of(2000, 1, 1)));
    }

    @Test
    public void testRecupereParLoginEtMail() {
        assertEquals("john", repository.recupereBy("login", "john").getLogin());
        assertEquals("john", repository.recupereBy("mail", "john@mail.com").getLogin());
        assertNull(repository.recupereBy("login", "inconnu"));
    }

    @Test
    public void testInsererLoginExistant() {
        assertFalse(repository.inserer(new Client("john", "autre@mail.com", "X", "Y", 0, "hash", LocalDate.of(2000, 1, 1))));
        assertEquals(1, repository.recupereTout().size());
    }

    @Test
    public void testModifierUnClientRecupereNeModifiePasLeStockage() {
        Client client = repository.recupereBy("login", "john");
        client.retirerArgent(500);

        assertEquals(1000, repository.recupereBy("login", "john").getArgent());
    }

    @Test
    public void testMettreAJourArgent() {
        Client client = repository.recupereBy("login", "john");
        client.retirerArgent(300);
        repository.mettreAJourArgent(client);

        assertEquals(700, repository.recupereBy("login", "john").getArgent());
    }

    @Test
    public void testSupprimer() {
        repository.supprimer("john");
        assertNull(repository.recupereBy("login", "john"));
    }
}
