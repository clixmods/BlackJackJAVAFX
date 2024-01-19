package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.helper.InscriptionHelper;
import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ControleurInscription implements Controleur
{
    //region FXML

    @FXML
    private Label mailFieldDescriptor;

    @FXML
    private Label loginFieldDescriptor;

    @FXML
    private Label nameFieldDescriptor;

    @FXML
    private Label firstNameFieldDescriptor;

    @FXML
    private Label birthDateFieldDescriptor;

    @FXML
    private Label passwordFieldDescriptor;

    @FXML
    private Label passwordConfirmationFieldDescriptor;

    @FXML
    private CheckBox termsAndConditionsCheckBox;

    @FXML
    private Button CGUButton;

    @FXML
    private Button retourButton;

    public DatePicker inputDateNaissance;

    public PasswordField inputPassword2;

    public PasswordField inputPassword1;

    public TextField inputPseudo;

    public Label messageInfo;

    private String messageInfoState;

    public Button submitInscription;

    public TextField inputPrenom;

    public TextField inputNom;

    public TextField inputMail;

    private String nom;

    private String prenom;

    //endregion
    private SceneHandler sceneHandler;
    public void initialiserInscription(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        datePickerExceptionHandler();
        messageInfoState = "inscription_messageInfo_inscription";
        changerLangue();
    }

    private void datePickerExceptionHandler(){
        inputDateNaissance.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                String affichage = "";
                if (localDate == null){
                    return affichage;
                }
                else{
                    return localDate.toString();
                }
            }

            @Override
            public LocalDate fromString(String s) {
                try{
                    return LocalDate.parse(s);
                }
                catch (DateTimeParseException e){
                    return null;
                }
            }
        });
        inputDateNaissance.setOnAction(actionEvent -> {
            LocalDate dateNaissance = inputDateNaissance.getValue();
            if (dateNaissance == null){
                messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_dateNotValid"));
                messageInfoState = "inscription_messageInfo_dateNotValid";
            }
        });
    }

    public void changerLangue(){
        if (messageInfoState.equals("inscription_messageInfo_successful1")){
            afficherInscriptionReussie(nom, prenom);
        }
        else {
            messageInfo.setText(LangageManager.getInstance().getText(messageInfoState));
        }
        mailFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_mailFieldDescriptor"));
        loginFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_loginFieldDescriptor"));
        nameFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_nameFieldDescriptor"));
        firstNameFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_firstNameFieldDescriptor"));
        birthDateFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_birthDateFieldDescriptor"));
        passwordFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_passwordFieldDescriptor"));
        passwordConfirmationFieldDescriptor.setText(LangageManager.getInstance().getText("inscription_passwordConfirmationFieldDescriptor"));
        termsAndConditionsCheckBox.setText(LangageManager.getInstance().getText("inscription_termsAndConditionsCheckBox"));
        submitInscription.setText(LangageManager.getInstance().getText("inscription_submitButton"));
        CGUButton.setText(LangageManager.getInstance().getText("inscription_termsAndConditionsButton"));
        retourButton.setText(LangageManager.getInstance().getText("inscription_backButton"));
    }

    public void OnMailEnter(ActionEvent actionEvent) {
    }

    public void OnNomEnter(ActionEvent actionEvent) {
    }

    public void OnPrenomEnter(ActionEvent actionEvent) {
    }

    public void OnPseudoEnter(ActionEvent actionEvent) {
    }

    public void OnPasswordEnter(ActionEvent actionEvent) {
    }

    public void OnConfirmPasswordEnter(ActionEvent actionEvent) {
    }

    public void OnDateEnter(ActionEvent actionEvent) {
    }

    public void OnCGU(ActionEvent actionEvent){
        sceneHandler.afficherCGU();
    }

    public void OnSubmit(ActionEvent actionEvent){
        // Get inputs text
        String login = inputPseudo.getText();
        String mail = inputMail.getText();
        String nom = inputNom.getText();
        String prenom = inputPrenom.getText();
        int argent = 0;
        String password = inputPassword1.getText();
        String passwordConfirm = inputPassword2.getText();
        LocalDate dateNaissance = inputDateNaissance.getValue();

        if( StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm, dateNaissance))
        {
            afficherInscriptionReussie(nom, prenom);
            sceneHandler.afficherConnexion();
        }

    }

    private void afficherInscriptionReussie(String nom, String prenom){
        messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_successful1") +nom+" "+prenom+ LangageManager.getInstance().getText("inscription_messageInfo_successful2"));
        messageInfoState = "inscription_messageInfo_successful1";
        this.nom = nom;
        this.prenom = prenom;
    }

    public Boolean StartInscription(String login,
                                           String mail,
                                           String nom,
                                           String prenom,
                                           int argent,
                                           String password,
                                           String passwordConfirm,
                                           LocalDate dateNaissance)
    {
        ClientService clientService = ClientService.getInstance();
        Password passwordService = new Password();

        if(login.isBlank() || !InscriptionHelper.isLoginAvailable(login))
        {
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_loginNotAvailable"));
            messageInfoState = "inscription_messageInfo_loginNotAvailable";
            return false;
        }

        if(mail.isBlank() || !InscriptionHelper.isMailAvailable(mail))
        {
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_mailNotAvailable"));
            messageInfoState = "inscription_messageInfo_mailNotAvailable";
            return false;
        }
        if(!passwordService.isSecure(password))
        {
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_passwordNotSecure"));
            messageInfoState = "inscription_messageInfo_passwordNotSecure";
            return false;
        }
        if(!password.equals(passwordConfirm))
        {
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_passwordNotEquals"));
            messageInfoState = "inscription_messageInfo_passwordNotEquals";
            return false;
        }
        if(nom.isBlank() || prenom.isBlank())
        {
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_namesMissing"));
            messageInfoState = "inscription_messageInfo_namesMissing";
            return false;
        }
        if (dateNaissance == null){
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_dateNotValid"));
            messageInfoState = "inscription_messageInfo_dateNotValid";
            return false;
        }
        if(!InscriptionHelper.isMajor(dateNaissance))
        {
            messageInfo.setText(LangageManager.getInstance().getText("inscription_messageInfo_notOfAge"));
            messageInfoState = "inscription_messageInfo_notOfAge";
            return false;
        }
        if(!termsAndConditionsCheckBox.isSelected()){
            messageInfo.setText(LangageManager.getInstance().getText("inscription_termsAndConditionsNotChecked"));
            messageInfoState = "inscription_termsAndConditionsNotChecked";
            return false;
        }
        return clientService.creerClient(login, mail, nom, prenom, argent, password, dateNaissance);
    }

    public void OnRetourButton(ActionEvent actionEvent) {
        sceneHandler.afficherAccueil();
    }
}
