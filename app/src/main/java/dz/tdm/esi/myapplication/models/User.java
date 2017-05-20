package dz.tdm.esi.myapplication.models;

import java.util.List;

/**
 * Created by Amine on 20/05/2017.
 */

public class User {
    private Long id;
    private String userName;
    private String numTel;
    private String email;
    private String mdp;
    private String  numPermis;
    private List<Vehicule> vehicules;
    private List<Dossier> dossiers;

    public User(Long id, String userName, String numTel, String email, String mdp, String numPermis) {
        this.id = id;
        this.userName = userName;
        this.numTel = numTel;
        this.email = email;
        this.mdp = mdp;
        this.numPermis = numPermis;
    }

    public User(String userName, String numTel, String email, String mdp, String numPermis) {
        this.userName = userName;
        this.numTel = numTel;
        this.email = email;
        this.mdp = mdp;
        this.numPermis = numPermis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNumPermis() {
        return numPermis;
    }

    public void setNumPermis(String numPermis) {
        this.numPermis = numPermis;
    }

    public List<Vehicule> getVehicules() {
        return vehicules;
    }

    public void setVehicules(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public List<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }
}
