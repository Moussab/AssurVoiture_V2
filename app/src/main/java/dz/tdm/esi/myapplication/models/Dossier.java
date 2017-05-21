package dz.tdm.esi.myapplication.models;

/**
 * Created by Amine on 20/05/2017.
 */

public class Dossier {

    private String numeroPermis;
    private String nomConducteur;
    private String vehiculeConducteur;
    private String matriculeConducteur;
    private String date;
    private String type;
    private EtatDossier etat;
    private String imageURL;
    private String videoURL;
    private String montant;
    private String nomAdversaire;
    private String numPermisAdversaire;
    private String vehiculeAdversaire;
    private String matriculeAdversaire;
    private int id;

    public Dossier(int id,String numeroPermis, String nomConducteur, String vehiculeConducteur, String matriculeConducteur,
                   String date, String type, EtatDossier etat, String imageURL, String videoURL, String montant,
                   String nomAdversaire, String numPermisAdversaire, String vehiculeAdversaire, String matriculeAdversaire) {
        this.id = id;
        this.numeroPermis = numeroPermis;
        this.nomConducteur = nomConducteur;
        this.vehiculeConducteur = vehiculeConducteur;
        this.matriculeConducteur = matriculeConducteur;
        this.date = date;
        this.type = type;
        this.etat = etat;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.montant = montant;
        this.nomAdversaire = nomAdversaire;
        this.numPermisAdversaire = numPermisAdversaire;
        this.vehiculeAdversaire = vehiculeAdversaire;
        this.matriculeAdversaire = matriculeAdversaire;
    }
    public Dossier(String numeroPermis, String nomConducteur, String vehiculeConducteur, String matriculeConducteur,
                   String date, String type, EtatDossier etat, String imageURL, String videoURL, String montant,
                   String nomAdversaire, String numPermisAdversaire, String vehiculeAdversaire, String matriculeAdversaire) {

        this.numeroPermis = numeroPermis;
        this.nomConducteur = nomConducteur;
        this.vehiculeConducteur = vehiculeConducteur;
        this.matriculeConducteur = matriculeConducteur;
        this.date = date;
        this.type = type;
        this.etat = etat;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
        this.montant = montant;
        this.nomAdversaire = nomAdversaire;
        this.numPermisAdversaire = numPermisAdversaire;
        this.vehiculeAdversaire = vehiculeAdversaire;
        this.matriculeAdversaire = matriculeAdversaire;
    }


    public Dossier(String numeroPermis, String date, String type, EtatDossier etat) {
        this.numeroPermis = numeroPermis;
        this.date = date;
        this.type = type;
        this.etat = etat;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(String numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

    public String getNomConducteur() {
        return nomConducteur;
    }

    public void setNomConducteur(String nomConducteur) {
        this.nomConducteur = nomConducteur;
    }

    public String getVehiculeConducteur() {
        return vehiculeConducteur;
    }

    public void setVehiculeConducteur(String vehiculeConducteur) {
        this.vehiculeConducteur = vehiculeConducteur;
    }

    public String getMatriculeConducteur() {
        return matriculeConducteur;
    }

    public void setMatriculeConducteur(String matriculeConducteur) {
        this.matriculeConducteur = matriculeConducteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EtatDossier getEtat() {
        return etat;
    }

    public void setEtat(EtatDossier etat) {
        this.etat = etat;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getNomAdversaire() {
        return nomAdversaire;
    }

    public void setNomAdversaire(String nomAdversaire) {
        this.nomAdversaire = nomAdversaire;
    }

    public String getNumPermisAdversaire() {
        return numPermisAdversaire;
    }

    public void setNumPermisAdversaire(String numPermisAdversaire) {
        this.numPermisAdversaire = numPermisAdversaire;
    }

    public String getVehiculeAdversaire() {
        return vehiculeAdversaire;
    }

    public void setVehiculeAdversaire(String vehiculeAdversaire) {
        this.vehiculeAdversaire = vehiculeAdversaire;
    }

    public String getMatriculeAdversaire() {
        return matriculeAdversaire;
    }

    public void setMatriculeAdversaire(String matriculeAdversaire) {
        this.matriculeAdversaire = matriculeAdversaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


