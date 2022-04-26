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
public class Commande {
    
   private int id;
   
   private int produit_id ;
    
 private int prix;
  private int id_user;
  private int totale; 
  private int numero ; 
  private Date date;

    public Commande(int id, int produit_id, int prix, int id_user, int totale, int numero, Date date) {
        this.id = id;
        this.produit_id = produit_id;
        this.prix = prix;
        this.id_user = id_user;
        this.totale = totale;
        this.numero = numero;
        this.date = date;
    }

    public Commande(int produit_id, int prix, int id_user, int totale, int numero, Date date) {
        this.produit_id = produit_id;
        this.prix = prix;
        this.id_user = id_user;
        this.totale = totale;
        this.numero = numero;
        this.date = date;
    }

    public Commande() {
    }

   

    public Commande(int id) {
        this.id = id;
    }

   

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", produit_id=" + produit_id + ", prix=" + prix + ", id_user=" + id_user + ", totale=" + totale + ", numero=" + numero + ", date=" + date + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
       
  
  
  
  
  
}