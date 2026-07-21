package fr.diginamic.banque;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("tp-jpa");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // ==========================
        // BANQUE
        // ==========================

        Banque banque = new Banque("Banque Diginamic");

        // ==========================
        // ADRESSES
        // ==========================

        Adresse adresse1 = new Adresse(
                10,
                "Rue Victor Hugo",
                34000,
                "Montpellier");

        Adresse adresse2 = new Adresse(
                25,
                "Avenue Jean Jaurès",
                31000,
                "Toulouse");

        // ==========================
        // CLIENTS
        // ==========================

        ClientBanque client1 = new ClientBanque(
                "Dupont",
                "Jean",
                LocalDate.of(1990, 5, 10),
                adresse1);

        ClientBanque client2 = new ClientBanque(
                "Martin",
                "Sophie",
                LocalDate.of(1995, 8, 20),
                adresse2);

        banque.ajouterClient(client1);
        banque.ajouterClient(client2);

        // ==========================
        // COMPTE PARTAGÉ
        // ==========================

        Compte compteCommun = new Compte(
                "CPT001",
                3500);

        client1.ajouterCompte(compteCommun);
        client2.ajouterCompte(compteCommun);

        // ==========================
        // LIVRET A
        // ==========================

        LivretA livretA = new LivretA(
                "LIV001",
                8000,
                2.5);

        client1.ajouterCompte(livretA);

        // ==========================
        // ASSURANCE VIE
        // ==========================

        AssuranceVie assuranceVie =
                new AssuranceVie(
                        "AV001",
                        15000,
                        LocalDate.of(2035,12,31),
                        4.2);

        client1.ajouterCompte(assuranceVie);

        // ==========================
        // OPERATIONS
        // ==========================

        Operation operation1 =
                new Operation(
                        LocalDateTime.now(),
                        -80,
                        "Courses");

        Operation operation2 =
                new Operation(
                        LocalDateTime.now(),
                        1200,
                        "Salaire");

        compteCommun.ajouterOperation(operation1);
        compteCommun.ajouterOperation(operation2);

        // ==========================
        // VIREMENTS
        // ==========================

        Virement virement1 =
                new Virement(
                        LocalDateTime.now(),
                        -250,
                        "Loyer",
                        "Propriétaire");

        Virement virement2 =
                new Virement(
                        LocalDateTime.now(),
                        500,
                        "Remboursement",
                        "Paul");

        compteCommun.ajouterOperation(virement1);
        compteCommun.ajouterOperation(virement2);

        // ==========================
        // PERSISTENCE
        // ==========================

        em.persist(banque);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Insertion terminée !");
    }
}