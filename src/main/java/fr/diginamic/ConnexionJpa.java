package fr.diginamic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ConnexionJpa {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("tp-jpa");

        EntityManager em = emf.createEntityManager();

        // Recherche de la région ayant l'identifiant 1
        Region region = em.find(Region.class, 1);

        if (region != null) {
            System.out.println("Région : " + region.getNom());
        } else {
            System.out.println("Aucune région trouvée.");
        }

        TypedQuery<Livre> query =
                em.createQuery("SELECT l FROM Livre l", Livre.class);

        List<Livre> livres = query.getResultList();

        for (Livre l : livres) {
            System.out.println(l);
        }

        Client client = em.find(Client.class, 1);

        if (client != null) {

            System.out.println(client.getNom() + " " + client.getPrenom());

            for (Emprunt e : client.getEmprunts()) {

                System.out.println(

                        e.getId() + " - " +

                                e.getDateDebut() + " - " +

                                e.getDateFin() + " - " +

                                e.getDelai()

                );

            }

        }

        em.close();
        emf.close();
    }
}