/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;

import Entities.Produit;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class ProduitService {
    Connection connexion;   
  public ProduitService() {
        connexion = MyDB.getInstance().getConnection();
    }
 

 // @Override
  public void ajouterProduit(Produit e) throws SQLException {
        String req = "INSERT INTO `produit` (`type_produit`,`prix_produit`,`date_expo`,`date_fin`,`disponibilite`,`type_action`,`imgproduit`) "
                + "VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, e.getType_produit());
        ps.setInt(2, e.getPrix_produit());
       ps.setDate(3,(java.sql.Date) (Date) e.getDate_expo());
        ps.setDate(4,(java.sql.Date) (Date) e.getDate_fin());
         ps.setString(5, e.getDisponibilite());
          ps.setString(6, e.getType_action());
        ps.setString(7, e.getImgproduit());
             

        ps.executeUpdate();
    }
    
 // @Override
     public List<Produit> AfficherAllProduit() throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        String req = "select * from produit ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Produit e = new Produit(rst.getInt("id")
                    , rst.getString("type_produit")
                    , rst.getInt("prix_produit")
                    , rst.getDate("date_expo")
                      , rst.getDate("date_fin")
                      , rst.getString("disponibilite")
                      , rst.getString("type_action")
                   
                    , rst.getString("imgproduit"));
            Produits.add(e);
        }
        return Produits;
    }
     
     
         public List<Produit> AfficherAllProduitByDate() throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        String req = "select * from produit order by date_expo  ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Produit e = new Produit(rst.getInt("id")
                    , rst.getString("type_produit")
                    , rst.getInt("prix_produit")
                    , rst.getDate("date_expo")
                      , rst.getDate("date_fin")
                      , rst.getString("disponibilite")
                      , rst.getString("type_action")
                   
                    , rst.getString("imgproduit"));
            Produits.add(e);
        }
        return Produits;
    }    
             
   public List<Produit> AfficherAllProduitByDisponibilite() throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        String req = "select * from produit order by disponibilite  ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Produit e = new Produit(rst.getInt("id")
                    , rst.getString("type_produit")
                    , rst.getInt("prix_produit")
                    , rst.getDate("date_expo")
                      , rst.getDate("date_fin")
                      , rst.getString("disponibilite")
                      , rst.getString("type_action")
                   
                    , rst.getString("imgproduit"));
            Produits.add(e);
        }
        return Produits;
    }   
 
    
  // @Override
   public List<Produit> RecherchProduit(String Nom) throws SQLException {
   List<Produit> Produits = new ArrayList<>();
            String req = "select  * from  produit c  where c.type_produit LIKE'" + Nom + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                 Produit e = new Produit(rst.getInt("id")
                    , rst.getString("type_produit")
                    , rst.getInt("prix_produit")
                    , rst.getDate("date_expo")
                      , rst.getDate("date_fin")
                      , rst.getString("disponibilite")
                      , rst.getString("type_action")
                   
                    , rst.getString("imgproduit"));
                  
                  
            Produits.add(e);
               
            }
     return Produits;
   
    }
   

 // @Override
     public void SupprimerProduit(Produit e) throws SQLException {  

        String req = "DELETE FROM produit WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
     
   //  @Override
   public void supp2(Produit m) throws SQLException {

        String req = "DELETE FROM produit WHERE id ="+m.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }
   
  
 //  @Override
      public void modifierProduit(Produit e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE produit SET "
                + " type_produit='"+e.getType_produit()+"'"
                 + ", prix_produit='"+e.getPrix_produit()+"'"
                 + ", date_expo='"+ (java.sql.Date) (Date) e.getDate_expo()+"'"
                 + ", date_fin='"+ (java.sql.Date) (Date) e.getDate_fin()+"'"
                + ", disponibilite='"+e.getDisponibilite()+"'"
                + ", type_action  ='"+ e.getType_action()+"'"
                + ", imgproduit='"+e.getImgproduit()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }  
}
