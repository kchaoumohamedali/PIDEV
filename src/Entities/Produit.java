/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Administrateur
 */
public class Produit {
    private int id ;
    private String type_produit;
    private int prix_produit;
    private Date date_expo;
     private Date date_fin;
        private String disponibilite;
     private String type_action;
     private String imgproduit;

    public Produit() {
    }

    public Produit(int id, String type_produit, int prix_produit, Date date_expo, Date date_fin, String disponibilite, String type_action, String imgproduit) {
        this.id = id;
        this.type_produit = type_produit;
        this.prix_produit = prix_produit;
        this.date_expo = date_expo;
        this.date_fin = date_fin;
        this.disponibilite = disponibilite;
        this.type_action = type_action;
        this.imgproduit = imgproduit;
    }

    public Produit(int id) {
        this.id = id;
    }

    public Produit(String type_produit, int prix_produit, Date date_expo, Date date_fin, String disponibilite, String type_action, String imgproduit) {
        this.type_produit = type_produit;
        this.prix_produit = prix_produit;
        this.date_expo = date_expo;
        this.date_fin = date_fin;
        this.disponibilite = disponibilite;
        this.type_action = type_action;
        this.imgproduit = imgproduit;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", type_produit=" + type_produit + ", prix_produit=" + prix_produit + ", date_expo=" + date_expo + ", date_fin=" + date_fin + ", disponibilite=" + disponibilite + ", type_action=" + type_action + ", imgproduit=" + imgproduit + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

    public int getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(int prix_produit) {
        this.prix_produit = prix_produit;
    }

    

    public Date getDate_expo() {
        return date_expo;
    }

    public void setDate_expo(Date date_expo) {
        this.date_expo = date_expo;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getType_action() {
        return type_action;
    }

    public void setType_action(String type_action) {
        this.type_action = type_action;
    }

    public String getImgproduit() {
        return imgproduit;
    }

    public void setImgproduit(String imgproduit) {
        this.imgproduit = imgproduit;
    }

                    
    
    
    
}
