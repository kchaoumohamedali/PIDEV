/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProduitDetailController implements Initializable {

    @FXML
    private Label type_produit;
    @FXML
    private Label prix_produit;
    @FXML
    private Label date_expo;
    @FXML
    private Label date_fin;
    @FXML
    private Label disponibilite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    type_produit.setText(ProduitFrontController.connectedProduit.getType_produit());
         
          prix_produit.setText( Integer.toString(ProduitFrontController.connectedProduit.getPrix_produit()));
                      
            disponibilite.setText( ProduitFrontController.connectedProduit.getDisponibilite());
         

        
         
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           String strDate = dateFormat.format(ProduitFrontController.connectedProduit.getDate_expo());  
           String strDate2 = dateFormat.format(ProduitFrontController.connectedProduit.getDate_fin());    
            date_expo.setText(strDate);
            
            date_fin.setText(strDate);
        
    }    
    
}
