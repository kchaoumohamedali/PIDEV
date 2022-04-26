///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Main;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import DB.MyDB;
//import Entities.Commande;
//import Entities.Produit;
//import Services.CommandeService;
//import Services.ProduitService;
//import java.security.NoSuchAlgorithmException;
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.ZoneId;
//
//
///**
// *
// * @author bhk
// */
//public class Main {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws NoSuchAlgorithmException, ParseException {
//      
//        SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );
//      
//        
//        java.util.Date myDate = format.parse( "10/10/2009" );
//       Date datee = new Date( myDate.getTime() );
//        
//       java.util.Date myDate2 = format.parse( "12/12/1996" );
//         Date datee2 = new Date( myDate.getTime());
//     
//      
//        CommandeService commandeService = new CommandeService();
//                 
//           Commande c1= new Commande(1,100,1,100,50,datee);      
//        ProduitService produitService = new ProduitService();
//        
//                Produit p1 = new Produit("Tente","300",datee,datee,"dispo","test","image");
//
//       
//        
//        ////////////// AFFICHER ALL Produits
//   /*   try {
//            System.out.println(produitService.AfficherAllProduit());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//////////////////////////Afficher All Produits  ByDate
///*
// try {
//            System.out.println(produitService.AfficherAllProduitByDate());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//////////////////////////Afficher All ProduitBy Disponibilite
///*
//
// try {
//          
//            System.out.println(produitService.AfficherAllProduitByDisponibilite());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//////////////////////////Recherche Produit
//
//
///*
//
// try {
//            System.out.println(produitService.RecherchProduit("Loubya"));
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//
////////////////////////////// Ajouter Produit
//
///*
//           try {
//          
//         produitService.ajouterProduit(p1);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          
//          */
//          
//          
//   //////////////////// Supprimer Produit       
//         Produit p3 = new Produit(15);
//        /*   try {
//          
//         produitService.SupprimerProduit(p3);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           */
//           
//      ////////////////Modifier Produit
//        /*        Produit p4 = new Produit("Tenteee","300",datee,datee,"disepo","teeest","imaeege");
//              
//           try {
//          
//         produitService.modifierProduit(p4,p3);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           
//           */
//          
//       
//            /////////////////////Ajouter Commande
//        
//            /*
//            try { 
//            commandeService.ajouterCommande(c1);
//            
//                  } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            */
//            /////////////Afficher tous les Commande
//            
//        /*           try {
//            System.out.println(commandeService.AfficherAllCommande());
//            } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            */
//            /////////////Afficher tous les Commande par date
//            
//                /*        try {
//            System.out.println(commandeService.AfficherAllCommandeByDate());
//            } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }*/
//            
//                
//                
//                
//                   /////////////Afficher tous les Commande par produit
//            /*
//                        try {
//            System.out.println(commandeService.AfficherAllCommandeByProduct());
//            } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }*/
//            
//            
//                ////////////////////////Recherche Commande
///*
// try {
//            System.out.println(commandeService.RecherchCommande("50"));
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//                
//            ///////////////Supprimer Commande
//         /*        Commande c2 = new Commande(19);
//            try {
//            commandeService.SupprimerCommande(c2);
//            } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            */
//            
//            ///////////////Modifier Commande
//Commande c3 = new Commande(20);
// Commande c4= new Commande(1,1,1,1,1,datee);   
//            try {
//            commandeService.modifierCommande(c4,c3);
//            } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }  
//            
//  
//            
//    }
//}