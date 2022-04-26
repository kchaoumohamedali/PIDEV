/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;
import Entities.Commande;
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
public class CommandeService {
       Connection connexion;   
  public CommandeService() {
        connexion = MyDB.getInstance().getConnection();
    }
 
  
 // @Override
  public void ajouterCommande(Commande e) throws SQLException {
        String req = "INSERT INTO `commande` (`produit_id`,`prix`,`id_user`,`totale`,`numero`,`date`) "
                + "VALUES (?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, e.getProduit_id());
        ps.setInt(2, e.getPrix());
       ps.setInt(3, e.getId_user());
        ps.setInt(4, e.getTotale());
        ps.setInt(5, e.getNumero());
        ps.setDate(6,(java.sql.Date) (Date) e.getDate());
        ps.executeUpdate();
    }

 // @Override
     public List<Commande> AfficherAllCommande() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id")
                    , rst.getInt("produit_id")
                    , rst.getInt("prix")
                    , rst.getInt("id_user")
                      , rst.getInt("totale")
                     
                      , rst.getInt("numero")
                   
                    , rst.getDate("date"));
            Commandes.add(e);
        }
        return Commandes;
    }
     
     
     

     
     
     
     
     
         public List<Commande> AfficherAllCommandeByDate() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande order by date ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id")
                    , rst.getInt("produit_id")
                    , rst.getInt("prix")
                    , rst.getInt("id_user")
                      , rst.getInt("totale")
                     
                      , rst.getInt("numero")
                   
                    , rst.getDate("date"));
            Commandes.add(e);
        }
        return Commandes;
    }
     
             public List<Commande> AfficherAllCommandeByProduct() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande order by produit_id ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id")
                    , rst.getInt("produit_id")
                    , rst.getInt("prix")
                    , rst.getInt("id_user")
                      , rst.getInt("totale")
                     
                      , rst.getInt("numero")
                   
                    , rst.getDate("date"));
            Commandes.add(e);
        }
        return Commandes;
    } 

    
  // @Override
   public List<Commande> RecherchCommande(String Nom) throws SQLException {
   List<Commande> Commandes = new ArrayList<>();
            String req = "select  * from  commande c  where c.numero LIKE'" + Nom + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                   Commande e = new Commande(rst.getInt("id")
                    , rst.getInt("produit_id")
                    , rst.getInt("prix")
                    , rst.getInt("id_user")
                      , rst.getInt("totale")
                      , rst.getInt("numero")
                    , rst.getDate("date"));

            Commandes.add(e);
                        }
     return Commandes;
   
    }
   

 // @Override
     public void SupprimerCommande(Commande e) throws SQLException {

        String req = "DELETE FROM commande WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

   

 //  @Override
      public void modifierCommande(Commande e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE commande SET "
                + " produit_id='"+e.getProduit_id()+"'"
                 + ", prix='"+e.getPrix()+"'"
                 + ", id_user='"+  e.getId_user()+"'"
                + ", totale='"+e.getTotale()+"'"
                + ", numero  ='"+ e.getNumero()+"'"
                + ", date='"+ (java.sql.Date) (Date) e.getDate()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
      
      public Produit findProduit(int id) throws SQLException{
    
                Produit c = new Produit();
           
        String requete="select * from produit where id="+id;
       
            Statement st = connexion.createStatement();
            ResultSet rst = st.executeQuery(requete);
            while(rst.next())
            {  
              
     Produit e = new Produit(rst.getInt("id")
                    , rst.getString("type_produit")
                    , rst.getInt("prix_produit")
                    , rst.getDate("date_expo")
                      , rst.getDate("date_fin")
                      , rst.getString("disponibilite")
                      , rst.getString("type_action")
                   
                    , rst.getString("imgproduit"));
           
            
            } 
               return c;                 
      }   
      
      
      
      
      
      
      
      
      
      
      
      
}
