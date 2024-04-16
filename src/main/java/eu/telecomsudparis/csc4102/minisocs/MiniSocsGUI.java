package eu.telecomsudparis.csc4102.minisocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import java.util.ArrayList;
import java.util.List;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MiniSocsGUI extends Application {
    private MiniSocs miniSocs;

    @Override
    public void start(Stage primaryStage) {
        miniSocs = new MiniSocs("MiniSocs");

        VBox root = new VBox(10);

        Button ajouterUtilisateur = new Button("Ajouter un utilisateur");
        ajouterUtilisateur.setOnAction(e -> ajouterUtilisateurWindow());

        Button creerReseauSocial = new Button("Créer un réseau social");
        creerReseauSocial.setOnAction(e -> creerReseauSocialWindow());
        
        Button ajouterMembreRS = new Button("Ajouter un membre à un réseau social");
        ajouterMembreRS.setOnAction(e -> ajouterMembreRSWindow());
        
        Button posterMessageRS = new Button("Poster un message sur un réseau social");
        posterMessageRS.setOnAction(e -> posterMessageRSWindow());
        
        Button modererMessage = new Button("Modérer un message sur un réseau social");
        modererMessage.setOnAction(e -> modererMessageWindow());
        
        Button desactiverCompteUtilisateur = new Button("Désactiver un compte utilisateur");
        desactiverCompteUtilisateur.setOnAction(e -> desactiverCompteUtilisateurWindow());
        
        root.getChildren().addAll(ajouterUtilisateur, creerReseauSocial, ajouterMembreRS, posterMessageRS, modererMessage, desactiverCompteUtilisateur);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MiniSocs GUI");
        primaryStage.show();
    }

    private void ajouterUtilisateurWindow() {
        Stage ajouterUtilisateurStage = new Stage();
        ajouterUtilisateurStage.initModality(Modality.APPLICATION_MODAL);
        ajouterUtilisateurStage.setTitle("Ajouter un utilisateur");

        VBox root = new VBox(10);

        Label pseudoLabel = new Label("Pseudo :");
        TextField pseudoField = new TextField();
        Label nomLabel = new Label("Nom :");
        TextField nomField = new TextField();
        Label prenomLabel = new Label("Prénom :");
        TextField prenomField = new TextField();
        Label courrielLabel = new Label("Courriel :");
        TextField courrielField = new TextField();

        Label resultatLabel = new Label();

        Button executeButton = new Button("Créer l'utilisateur");
        executeButton.setOnAction(e -> {
            try {
                miniSocs.ajouterUtilisateur(pseudoField.getText(), nomField.getText(), prenomField.getText(), courrielField.getText());
                resultatLabel.setText("Utilisateur " + pseudoField.getText() + " ajouté avec succès au système.");
            } catch (Exception ex) {
            	resultatLabel.setText("Erreur : " + ex.getMessage());
            }
        });

        root.getChildren().addAll(pseudoLabel, pseudoField, nomLabel, nomField, prenomLabel, prenomField, courrielLabel, courrielField, executeButton, resultatLabel);

        Scene scene = new Scene(root, 500, 300); // largeur, hauteur
        ajouterUtilisateurStage.setScene(scene);
        ajouterUtilisateurStage.showAndWait();
    }

    private void creerReseauSocialWindow() {
        Stage creerReseauSocialStage = new Stage();
        creerReseauSocialStage.initModality(Modality.APPLICATION_MODAL);
        creerReseauSocialStage.setTitle("Créer un réseau social");

        VBox root = new VBox(10);

        Label pseudoExecLabel = new Label("Pseudo exécutant :");
        ComboBox<String> pseudoComboBox = new ComboBox<>();
        pseudoComboBox.getItems().addAll(miniSocs.listerUtilisateurs());
        
        Label nomReseauLabel = new Label("Nom du réseau :");
        TextField nomReseauField = new TextField();
        Label ouvertLabel = new Label("Ouvert :");
        CheckBox ouvertCheckBox = new CheckBox();
        Label pseudoParticulierLabel = new Label("Pseudo particulier :");
        TextField pseudoParticulierField = new TextField();

        Label resultatLabel = new Label();

        Button executeButton = new Button("Créer le réseau social");
        executeButton.setOnAction(e -> {
            try {
                miniSocs.creerReseauSocial(pseudoComboBox.getValue(), nomReseauField.getText(), ouvertCheckBox.isSelected(), pseudoParticulierField.getText());
                resultatLabel.setText("Réseau social créé avec succès: " + nomReseauField.getText() + ", modéré par " + pseudoComboBox.getValue());
            } catch (Exception ex) {
            	resultatLabel.setText("Erreur : " + ex.getMessage());
            }
        });

        root.getChildren().addAll(pseudoExecLabel, pseudoComboBox, nomReseauLabel, nomReseauField, ouvertLabel, ouvertCheckBox, pseudoParticulierLabel, pseudoParticulierField, executeButton, resultatLabel);

        Scene scene = new Scene(root, 600, 300); // Increase the size of the scene
        creerReseauSocialStage.setScene(scene);
        creerReseauSocialStage.showAndWait();   
    }
    
    private void ajouterMembreRSWindow() {
        Stage ajouterMembreRSStage = new Stage();
        ajouterMembreRSStage.initModality(Modality.APPLICATION_MODAL);
        ajouterMembreRSStage.setTitle("Ajouter un utilisateur à un réseau social");

        VBox root = new VBox(10);

        Label pseudoLabel = new Label("Sélectionner l'utilisateur à rajouter :");
        ComboBox<String> utilisateurComboBox = new ComboBox<>();
        utilisateurComboBox.getItems().addAll(miniSocs.listerUtilisateurs());
        
        Label nomReseauLabel = new Label("Sélectionner le réseau social :");
        ComboBox<String> reseauSocialComboBox = new ComboBox<>();
        reseauSocialComboBox.getItems().addAll(miniSocs.listerRS());
        
        Label pseudoParticulierLabel = new Label("Pseudo particulier :");
        TextField pseudoParticulierField = new TextField();
        Label modLabel = new Label("Devient modérateur :");
        CheckBox modCheckBox = new CheckBox();

        Label resultatLabel = new Label();

        Button executeButton = new Button("Ajouter l'utilisateur au réseau social");
        executeButton.setOnAction(e -> {
            try {
                miniSocs.ajouterMembreRS(utilisateurComboBox.getValue(), reseauSocialComboBox.getValue(), pseudoParticulierField.getText(), modCheckBox.isSelected());
                resultatLabel.setText("Utilisateur " + utilisateurComboBox.getValue() + " ajouté avec succès au réseau social : " + reseauSocialComboBox.getValue());
            } catch (Exception ex) {
                resultatLabel.setText("Erreur : " + ex.getMessage());
            }
        });

        root.getChildren().addAll(pseudoLabel, utilisateurComboBox, nomReseauLabel, reseauSocialComboBox, pseudoParticulierLabel, pseudoParticulierField, modLabel, modCheckBox, executeButton, resultatLabel);

        Scene scene = new Scene(root, 600, 300);
        ajouterMembreRSStage.setScene(scene);
        ajouterMembreRSStage.showAndWait();
    }

    private void posterMessageRSWindow() {
        Stage posterMessageRSStage = new Stage();
        posterMessageRSStage.initModality(Modality.APPLICATION_MODAL);
        posterMessageRSStage.setTitle("Poster un message sur un réseau social");

        VBox root = new VBox(10);

        Label pseudoLabel = new Label("Sélectionner l'utilisateur qui envoie le message :");
        ComboBox<String> utilisateurComboBox = new ComboBox<>();
        utilisateurComboBox.getItems().addAll(miniSocs.listerUtilisateurs());
        
        Label nomReseauLabel = new Label("Sélectionner le réseau social :");
        ComboBox<String> reseauSocialComboBox = new ComboBox<>();
        reseauSocialComboBox.getItems().addAll(miniSocs.listerRS());
        
        Label contenuLabel = new Label("Contenu du message :");
        TextField contenuField = new TextField();

        Label resultatLabel = new Label();

        Button executeButton = new Button("Envoyer le message");
        executeButton.setOnAction(e -> {
            try {
                Message m = miniSocs.posterMessageRS(utilisateurComboBox.getValue(), contenuField.getText(), reseauSocialComboBox.getValue());
                resultatLabel.setText("Message #" + m.getId() + " : " + contenuField.getText() + " - a été posté avec succès sur le réseau social : " + reseauSocialComboBox.getValue() + " par " + utilisateurComboBox.getValue() + ".");
            } catch (Exception ex) {
                resultatLabel.setText("Erreur : " + ex.getMessage());
            }
        });

        root.getChildren().addAll(pseudoLabel, utilisateurComboBox, contenuLabel, contenuField, nomReseauLabel, reseauSocialComboBox, executeButton, resultatLabel);

        Scene scene = new Scene(root, 600, 300);
        posterMessageRSStage.setScene(scene);
        posterMessageRSStage.showAndWait();
    }
    
    //     public void modererMessage(final String pseudo, final double id, final String nomReseau, final EtatMessage etat) throws OperationImpossible {

    private void modererMessageWindow() {
        Stage modererMessageStage = new Stage();
        modererMessageStage.initModality(Modality.APPLICATION_MODAL);
        modererMessageStage.setTitle("Modérer un message sur un réseau social");

        VBox root = new VBox(10);

        Label pseudoLabel = new Label("Sélectionner un utilisateur modérateur du réseau social :");
        ComboBox<String> utilisateurComboBox = new ComboBox<>();
        utilisateurComboBox.getItems().addAll(miniSocs.listerUtilisateurs());
        
        Label nomReseauLabel = new Label("Sélectionner le réseau social :");
        ComboBox<String> reseauSocialComboBox = new ComboBox<>();
        
        // Utilisez la méthode getter pour obtenir la map des réseaux sociaux
        miniSocs.getReseauxSociaux().forEach((nom, reseauSocial) -> {
            reseauSocialComboBox.getItems().add(nom);
        });
        
        Label messageLabel = new Label("Sélectionner le message :");
        ComboBox<Double> messagesComboBox = new ComboBox<>();
                
        reseauSocialComboBox.setOnAction(event -> {
            List<Message> messages = new ArrayList<>(); // Déclaration de la liste de messages
            String nomReseau = reseauSocialComboBox.getValue();
            if (nomReseau != null) {
                // Obtenez les messages pour le réseau social sélectionné
                ReseauSocial rs = miniSocs.getReseauxSociaux().get(nomReseau);
                messages.addAll(rs.getMessages());
                
                // Effacez le ComboBox des messages précédents
                messagesComboBox.getItems().clear();
                
                // Remplissez le ComboBox des messages avec les identifiants des messages
                for (Message message : messages) {
                    messagesComboBox.getItems().add(message.getId());
                }
            }
        });
        
        Label etatMessageLabel = new Label("Sélectionner l'état du message :");
        ComboBox<EtatMessage> etatMessageComboBox = new ComboBox<>();

        // Ajoutez les valeurs d'EtatMessage directement au ComboBox
        etatMessageComboBox.getItems().addAll(EtatMessage.values());

        Label resultatLabel = new Label();

        Button executeButton = new Button("Modérer le message");
        
        executeButton.setOnAction(e -> {
            try {
                double id = messagesComboBox.getValue();
                miniSocs.modererMessage(utilisateurComboBox.getValue(), id, reseauSocialComboBox.getValue(), etatMessageComboBox.getValue());
                resultatLabel.setText("Message #" + id + " modéré avec succès sur le réseau social : " + reseauSocialComboBox.getValue() + ", par " + utilisateurComboBox.getValue() + ". Nouvel état : " + etatMessageComboBox.getValue() + ".");
            } catch (Exception ex) {
                resultatLabel.setText("Erreur : " + ex.getMessage());
            }
        });

        root.getChildren().addAll(pseudoLabel, utilisateurComboBox, nomReseauLabel, reseauSocialComboBox, messageLabel, messagesComboBox, etatMessageLabel, etatMessageComboBox, executeButton, resultatLabel);

        Scene scene = new Scene(root, 600, 300);
        modererMessageStage.setScene(scene);
        modererMessageStage.showAndWait();
    }
    
    private void desactiverCompteUtilisateurWindow() {
        Stage desactiverCompteUtilisateurStage = new Stage();
        desactiverCompteUtilisateurStage.initModality(Modality.APPLICATION_MODAL);
        desactiverCompteUtilisateurStage.setTitle("Désactiver un compte utilisateur");

        VBox root = new VBox(10);

        Label pseudoLabel = new Label("Pseudo de l'utilisateur à désactiver :");
        ComboBox<String> pseudoComboBox = new ComboBox<>();
        pseudoComboBox.getItems().addAll(miniSocs.listerUtilisateurs());

        Label resultatLabel = new Label();

        Button executeButton = new Button("Désactiver le compte utilisateur");
        executeButton.setOnAction(e -> {
            try {
                miniSocs.desactiverCompteUtilisateur(pseudoComboBox.getValue());
                resultatLabel.setText("Compte utilisateur " + pseudoComboBox.getValue() + " a été désactivé avec succès.");
            } catch (Exception ex) {
            	resultatLabel.setText("Erreur : " + ex.getMessage());
            }
        });

        root.getChildren().addAll(pseudoLabel, pseudoComboBox, executeButton, resultatLabel);

        Scene scene = new Scene(root, 600, 300); // Increase the size of the scene
        desactiverCompteUtilisateurStage.setScene(scene);
        desactiverCompteUtilisateurStage.showAndWait();   
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
