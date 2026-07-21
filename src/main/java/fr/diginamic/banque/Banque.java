package fr.diginamic.banque;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "banque")
public class Banque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @OneToMany(mappedBy = "banque", cascade = CascadeType.ALL)
    private List<ClientBanque> clients = new ArrayList<>();

    public Banque() {
    }

    public Banque(String nom) {
        this.nom = nom;
    }

    public void ajouterClient(ClientBanque client){
        clients.add(client);
        client.setBanque(this);
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ClientBanque> getClients() {
        return clients;
    }

    public void setClients(List<ClientBanque> clients) {
        this.clients = clients;
    }
}