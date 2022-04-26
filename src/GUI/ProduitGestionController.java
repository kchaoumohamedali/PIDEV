/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Services.ProduitService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProduitGestionController implements Initializable {

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
    private TableColumn<?, ?> type_action;
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
    private TextField inputtype_produit;
    @FXML
    private Button Confirmer;
    @FXML
    private Button Timage;
    @FXML
    private ImageView imgajoutincours;
 public ObservableList<Produit> list;
    @FXML
    private TextField inputprix_produit;
    @FXML
    private TextField inputtype_action;
    @FXML
    private DatePicker inputdate_expo;
    @FXML
    private ComboBox<String> inputdisponibilite;
    @FXML
    private DatePicker inputdatedispo;
    @FXML
    private Hyperlink Front;
    @FXML
    private Hyperlink Commande;
    @FXML
    private Button pdf2;
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
        
        
           
        
         inputdisponibilite.getItems().add("Disponible");
                inputdisponibilite.getItems().add("non disponible");
 type_produit.setCellValueFactory(new PropertyValueFactory<>("type_produit"));
        prix_produit.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        date_expo.setCellValueFactory(new PropertyValueFactory<>("date_expo"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
         disponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        type_action.setCellValueFactory(new PropertyValueFactory<>("type_action"));
  
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllProduitByDisponibilite()
            );        
        
        
   FilteredList<Produit> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Produit>) Produits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Produits.getType_produit().toLowerCase().contains(lower)) {
                            return true;
                        }
                        if(Produits.getDisponibilite().toLowerCase().contains(lower)){
                            return true ; 
                        }

                        return false;
                    });
                });
                SortedList<Produit> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      ProduitService cs = new ProduitService();
      List<Produit> listevents = new ArrayList<>();
        listevents = cs.AfficherAllProduitByDisponibilite();
        ObservableList<Produit> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
           if (event.getSource() == supp) {
            Produit e = new Produit();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          ProduitService cs = new ProduitService();
            cs.supp2(e);
             TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Supprimé un nouveau Produit");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            resetTableData();  
        
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
        ProduitService ps = new ProduitService();
          
 //in String disponibilite, String type_action, String imgproduit) {

        Produit c = new Produit(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getType_produit(),
               tableview.getSelectionModel().getSelectedItem().getPrix_produit(),
                 tableview.getSelectionModel().getSelectedItem().getDate_expo(),
                tableview.getSelectionModel().getSelectedItem().getDate_fin(),
                tableview.getSelectionModel().getSelectedItem().getDisponibilite(),
                tableview.getSelectionModel().getSelectedItem().getType_action(),
                tableview.getSelectionModel().getSelectedItem().getImgproduit() );
                
                
               
        
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         
            inputtype_produit.setText(tableview.getSelectionModel().getSelectedItem().getType_produit());
            
            inputprix_produit.setText( Integer.toString( tableview.getSelectionModel().getSelectedItem().getPrix_produit()));
  inputdisponibilite.setValue(tableview.getSelectionModel().getSelectedItem().getDisponibilite());
  inputtype_action.setText(tableview.getSelectionModel().getSelectedItem().getType_action());
           java.sql.Date r;
        r = new java.sql.Date(tableview.getSelectionModel().getSelectedItem().getDate_expo().getTime());
        LocalDate date = r.toLocalDate();
      java.sql.Date r2;
        r = new java.sql.Date(tableview.getSelectionModel().getSelectedItem().getDate_fin().getTime());
        LocalDate date2 = r.toLocalDate();
inputdate_expo.setValue(  date);
      inputdatedispo.setValue(date2);
           Confirmer.setVisible(true);   
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
         ProduitService productService = new ProduitService();
  
        if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") || inputtype_action.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") || inputtype_produit.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
                             
           java.util.Date date2
                = java.util.Date.from(this.inputdatedispo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                 
                   java.util.Date date3
                = java.util.Date.from(this.inputdate_expo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate3 = new java.sql.Date(date2.getTime());
// Date date_expo, Date date_fin, String disponibilite, String type_action, String imgproduit) {

            Produit c = new Produit(inputtype_produit.getText(),
                   Integer.parseInt(inputprix_produit.getText()),sqlDate2,sqlDate3,
                    
                    
                  inputdisponibilite.getValue(), inputtype_action.getText(), Timage.getText()                  );
        try {
            productService.ajouterProduit(c);
             resetTableData();
              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Ajouté un nouveau Produit");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
              sendMail("mohamedali.kchaou@esprit.tn");
        } catch (SQLException ex) {
           
        } catch (MessagingException ex) {
            Logger.getLogger(ProduitGestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
@FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
    @FXML
    private void Confirmer(ActionEvent event) throws NoSuchAlgorithmException {
         ProduitService productService = new ProduitService();
  
        if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") || inputtype_action.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") || inputtype_produit.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
                             
           java.util.Date date2
                = java.util.Date.from(this.inputdatedispo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                 
                   java.util.Date date3
                = java.util.Date.from(this.inputdate_expo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate3 = new java.sql.Date(date2.getTime());
// Date date_expo, Date date_fin, String disponibilite, String type_action, String imgproduit) {

            Produit c = new Produit(Integer.parseInt(labelid.getText()),inputtype_produit.getText(),
                  Integer.parseInt(  inputprix_produit.getText()),sqlDate2,sqlDate3,
                    
                    
                  inputdisponibilite.getValue(), inputtype_action.getText(), Timage.getText()                  );
        try {
            productService.modifierProduit(c);
             resetTableData();
        } catch (SQLException ex) {
           
        }
        
        
        
        
    }

    @FXML
    private void Front(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void Commande(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("CommandeGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

   private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        ;
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\moham\\Desktop\\ListProduit.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Produits"));
        
        PdfPTable pTable = new PdfPTable(1);
       
     //   pTable.addCell("NomEvent");
     
        
        tableview.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getType_produit()));
           
            //pTable.addCell(t.getNomEvent());
          //  pTable.addCell(t.getDescriptionEvent());
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                System.out.println(ex);
            }
        });
        
        
        d.close();
        Desktop.getDesktop().open(new File("C:\\Users\\moham\\Desktop\\ListProduit.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            

        }
        
        
        
    }
     public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "viatores10@gmail.com";
        String password = "viatores5683@";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Vous Avez Ajouté un nouveau Produit");
            message.setText("\"Chère Mademoiselle/Monsieur , \\n Il y a des nouveaux produits que vous devez voir. \\n \" +\n" +
"                    \"\\n Veuillez consulter la liste suivante : \\n \" +\n" +
"                    \"♣ sac de couchage :c'est un sac de chouchage qui devient comme un metlas et une couverture pour un campeur de se coucher \\n\" +\n" +
"                    \"\\n\" +\n" +
"                    \"♣ velos : est généralement utilisé pour les rondonnées \\n\" +\n" +
"                    \"♣ lampe :la lampe pour offrir la luminosite a un campeur dans la nuit\\n \" +\n" +
"                    \"♣ chaise: est généralement utilisé pour se detendre \" +\n" +
"                    \"\\n \\n J'espère que vous les avez aimés et appréciés. \\n \\n En attente de votre réponse \\n Cordialement \\n Produit E-CAMPIX \"");
            return message;
        } catch (MessagingException ex) {
        }
        return null;
    }   
}
