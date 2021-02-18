package com.cisco.mcommandes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class CommandeTest {

    private static Commande commande;
    private static final Date date = new Date();

    @BeforeEach
    void setUp() {
        commande = new Commande(1,1,date,5,false);
    }

    @Test
    void getId() {
        assertThat(commande.getId(), is(1));
    }

    @Test
    void setId() {
        commande.setId(2);
        assertThat(commande.getId(), not(1));
    }

    @Test
    void getProductId() {
        assertThat(commande.getProductId(), is(1));
    }

    @Test
    void setProductId() {
        commande.setProductId(2);
        assertThat(commande.getProductId(), not(1));
    }

    @Test
    void getDateCommande() {
        assertThat(commande.getDateCommande(), is(date));
    }

    @Test
    void setDateCommande() {
        Date newDate = new Date();
        commande.setDateCommande(newDate);
        assertThat(commande.getDateCommande(), is(newDate));
    }

    @Test
    void getQuantite() {
        assertThat(commande.getQuantite(), is(5));
    }

    @Test
    void setQuantite() {
        commande.setQuantite(4);
        assertThat(commande.getQuantite(), is(4));
    }

    @Test
    void getCommandePayee() {
        assertThat(commande.getCommandePayee(), is(false));
    }

    @Test
    void setCommandePayee() {
        commande.setCommandePayee(true);
        assertThat(commande.getCommandePayee(), not(false));
    }

    @Test
    void testToString() {
        Assertions.assertTrue(commande.toString().contains("commande{"));
    }
}