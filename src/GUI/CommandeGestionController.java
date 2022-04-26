/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.MyDB;
import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import Services.ProduitService;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class CommandeGestionController implements Initializable {

    @FXML
    private TableView<Commande> tableview;
    @FXML
    private TableColumn<?, ?> produit_id;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> id_user;
    @FXML
    private TableColumn<?, ?> totale;
    @FXML
    private TableColumn<?, ?> numero;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputRech;
    @FXML
    private TextField inputnumero;
    private Button Confirmer;
    @FXML
    private ComboBox<Integer> inputProduit;
public ObservableList<Commande> list;
  
     Connection connexion;   
    @FXML
    private ComboBox<Integer> inputuser;
    @FXML
    private Button confirmer;
    @FXML
    private Label labelprix;
  public CommandeGestionController() {
        connexion = MyDB.getInstance().getConnection();
    }/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "select * from produit";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                inputProduit.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
            String req = "select * from user";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                inputuser.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        CommandeService pss = new CommandeService();
        ArrayList<Commande> c = new ArrayList<>();
        try {
            c = (ArrayList<Commande>) pss.AfficherAllCommandeByDate();
        } catch (SQLException ex) {
        }
        
        ObservableList<Commande> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
        
                
                
 produit_id.setCellValueFactory(new PropertyValueFactory<>("produit_id"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        totale.setCellValueFactory(new PropertyValueFactory<>("totale"));
         numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
  
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllCommandeByDate()
            );        
        
  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      CommandeService cs = new CommandeService();
      List<Commande> listevents = new ArrayList<>();
        listevents = cs.AfficherAllCommandeByDate();
        ObservableList<Commande> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
         CommandeService cs = new CommandeService();  
        if (event.getSource() == supp) {
            Commande e = new Commande();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
         
            cs.SupprimerCommande(e);
            resetTableData();  
        
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
         CommandeService ps = new CommandeService();
          
 //in String disponibilite, String type_action, String imgproduit) {

 
 
 
        Commande c = new Commande(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getProduit_id(),
               tableview.getSelectionModel().getSelectedItem().getPrix(),
                 tableview.getSelectionModel().getSelectedItem().getId_user(),
                tableview.getSelectionModel().getSelectedItem().getNumero(),
                tableview.getSelectionModel().getSelectedItem().getDate()   );
               
              
                
                
               
        
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         labelprix.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getPrix()));
            inputProduit.setValue(tableview.getSelectionModel().getSelectedItem().getProduit_id());
            
            inputuser.setValue(  tableview.getSelectionModel().getSelectedItem().getId_user());
  inputnumero.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getNumero()));
        
        confirmer.setVisible(true);
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
      
         CommandeService productService = new CommandeService();
  
        if (inputnumero.getText().equals("")
                
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputnumero.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
               System.out.println("///////////"+productService.findProduit(inputProduit.getValue()).getPrix_produit());              
        
 Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
		//  
//produit_id, int prix, int id_user, int totale, int numero, Date date
            Commande c = new Commande(inputProduit.getValue(),productService.findProduit(inputProduit.getValue()).getPrix_produit()
                    ,inputuser.getValue(),0
                    ,Integer.parseInt(inputnumero.getText())
                    ,sqlDate2);
        try {
            productService.ajouterCommande(c);
             resetTableData();
        } catch (SQLException ex) {
           
        }
        
    }

 

    @FXML
    private void confirmer(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
     
        CommandeService pss = new CommandeService();
 Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
		//  
    Commande c = new Commande(Integer.parseInt(labelid.getText()),inputProduit.getValue(),pss.findProduit(inputProduit.getValue()).getPrix_produit()
                    ,inputuser.getValue(),0
                    ,Integer.parseInt(inputnumero.getText())
                    ,sqlDate2);
        
        try {
            pss.modifierCommande(c);
             resetTableData();
        } catch (SQLException ex) {
           
        } 
        
        
        
    }
    
}
