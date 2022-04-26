/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProduitFrontController implements Initializable {
public static Produit connectedProduit;
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<?, ?> type_produit;
    @FXML
    private TableColumn<?, ?> prix_produit;
    @FXML
    private TableColumn<?, ?> date_expo;
    @FXML
    private TableColumn<?, ?> date_fin;
    @FXML
    private TableColumn<?, ?> disponibilite;
    @FXML
    private Button Afficher;
    @FXML
    private Label imgpathttt;
    @FXML
    private Label labelid;
 public ObservableList<Produit> list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ProduitService pss = new ProduitService();
        ArrayList<Produit> c = new ArrayList<>();
        try {
            c = (ArrayList<Produit>) pss.AfficherAllProduitByDisponibilite();
        } catch (SQLException ex) {
        }
        
        ObservableList<Produit> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
 type_produit.setCellValueFactory(new PropertyValueFactory<>("type_produit"));
        prix_produit.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        date_expo.setCellValueFactory(new PropertyValueFactory<>("date_expo"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
         disponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
  
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllProduitByDisponibilite()
            );  }
catch (Exception e) {
            System.out.println(e.getMessage());
        }
  
    }    

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
          ProduitService ps = new ProduitService();
        Produit c = new Produit(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()),

                tableview.getSelectionModel().getSelectedItem().getPrix_produit(),
                 tableview.getSelectionModel().getSelectedItem().getDate_expo(),
                tableview.getSelectionModel().getSelectedItem().getDate_fin(),
                tableview.getSelectionModel().getSelectedItem().getDisponibilite(),
                        tableview.getSelectionModel().getSelectedItem().getType_action(),
                                tableview.getSelectionModel().getSelectedItem().getImgproduit()
            
                );
        ProduitFrontController.connectedProduit = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("ProduitDetail.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();   
        
        
    }
    
}
