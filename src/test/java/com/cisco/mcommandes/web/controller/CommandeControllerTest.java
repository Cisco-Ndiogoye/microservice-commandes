package com.cisco.mcommandes.web.controller;

import com.cisco.mcommandes.dao.CommandesDao;
import com.cisco.mcommandes.model.Commande;
import com.cisco.mcommandes.web.exceptions.CommandeNotFoundException;
import com.cisco.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CommandeControllerTest {

    private static Commande commande;

    @Mock
    CommandesDao commandesDao;

    @InjectMocks
    CommandeController commandeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commande = new Commande(1,1,new Date(),5,false);
}

    @Test
    @DisplayName("POST /commandes")
    void ajouterCommande() {
        Mockito.when(commandesDao.save(commande)).thenReturn(commande);
        assertThat(commandeController.ajouterCommande(commande).getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    @DisplayName("POST /commandes")
    void ajouterCommandeImpossible() {
        Mockito.when(commandesDao.save(commande)).thenReturn(null);
        Assertions.assertThrows(ImpossibleAjouterCommandeException.class, () -> {
            commandeController.ajouterCommande(commande);
        });

    }

    @Test
    @DisplayName("GET /commandes/{id}")
    void recupererUneCommande() {
        Mockito.when(commandesDao.findById(commande.getId())).thenReturn(Optional.of(commande));
        assertThat(commandeController.recupererUneCommande(commande.getId()), is(Optional.of(commande)));
    }

    @Test
    @DisplayName("GET /commandes/{id}")
    void recupererUneCommandeInexistante() {
        Mockito.when(commandesDao.findById(commande.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(CommandeNotFoundException.class, () -> {
            commandeController.recupererUneCommande(commande.getId());
        });
    }
}