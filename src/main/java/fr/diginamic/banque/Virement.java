package fr.diginamic.banque;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "virement")
public class Virement extends Operation {

    private String beneficiaire;

    public Virement() {
    }

    public Virement(LocalDateTime date,
                    double montant,
                    String motif,
                    String beneficiaire) {

        super(date, montant, motif);
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
}