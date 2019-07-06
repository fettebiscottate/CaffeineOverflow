package com.caffeine.overflow.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * This class consists of a constructor and a method to allow the user to
 * register into the site
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class RegisterUser {

	private VerticalPanel verticalPanel = null;

	/**
	 * Constructor for class RegisterUser
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public RegisterUser(final VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see Date
	 * @see List
	 * @see DateTimeFormat
	 * @see Button
	 * @see Grid
	 * @see HTML
	 * @see HorizontalPanel
	 * @see Label
	 * @see ListBox
	 * @see PopupPanel
	 * @see TextBox
	 * @see VerticalPanel
	 * @see DateBox
	 * @see CaffeineOverflowServiceAsync
	 * @since 1.0
	 */
	public void onModuleLoad() {
		final Menu menu = new Menu(this.verticalPanel, 0);
		menu.onModuleLoad();
		this.verticalPanel.add(new HTML("<h1>Registrazione</h1>"));
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(new Label("* obbligatorio"));
		final TextBox userNameTextBox = new TextBox();
		final TextBox passwordTextBox = new TextBox();
		final TextBox emailTextBox = new TextBox();
		final TextBox nameTextBox = new TextBox();
		final TextBox surnameTextBox = new TextBox();
		final ListBox sexListBox = new ListBox();
		sexListBox.addItem("F");
		sexListBox.addItem("M");
		final DateBox birthDateDatetBox = new DateBox();
		final TextBox birthPlaceTextBox = new TextBox();
		final TextBox livingPlaceTextBox = new TextBox();
		final TextBox siteSocial0 = new TextBox();
		final TextBox userNameSocial0 = new TextBox();
		final TextBox siteSocial1 = new TextBox();
		final TextBox userNameSocial1 = new TextBox();
		final TextBox siteSocial2 = new TextBox();
		final TextBox userNameSocial2 = new TextBox();
		final TextBox siteSocial3 = new TextBox();
		final TextBox userNameSocial3 = new TextBox();
		final Grid registrationGridPanel = new Grid(17, 2);
		registrationGridPanel.setWidget(0, 0, new Label("Username*: "));
		registrationGridPanel.setWidget(0, 1, userNameTextBox);
		registrationGridPanel.setWidget(1, 0, new Label("Password*: "));
		registrationGridPanel.setWidget(1, 1, passwordTextBox);
		registrationGridPanel.setWidget(2, 0, new Label("Email*: "));
		registrationGridPanel.setWidget(2, 1, emailTextBox);
		registrationGridPanel.setWidget(3, 0, new Label("Nome: "));
		registrationGridPanel.setWidget(3, 1, nameTextBox);
		registrationGridPanel.setWidget(4, 0, new Label("Cognome: "));
		registrationGridPanel.setWidget(4, 1, surnameTextBox);
		registrationGridPanel.setWidget(5, 0, new Label("Sesso: "));
		registrationGridPanel.setWidget(5, 1, sexListBox);
		registrationGridPanel.setWidget(6, 0, new Label("Data di nascita: "));
		registrationGridPanel.setWidget(6, 1, birthDateDatetBox);
		registrationGridPanel.setWidget(7, 0, new Label("Luogo di nascita: "));
		registrationGridPanel.setWidget(7, 1, birthPlaceTextBox);
		registrationGridPanel.setWidget(8, 0, new Label("Indirizzo: "));
		registrationGridPanel.setWidget(8, 1, livingPlaceTextBox);
		registrationGridPanel.setWidget(9, 0, new Label("Nome Social 1: "));
		registrationGridPanel.setWidget(9, 1, siteSocial0);
		registrationGridPanel.setWidget(10, 0, new Label("Nome Utente Social 1: "));
		registrationGridPanel.setWidget(10, 1, userNameSocial0);
		registrationGridPanel.setWidget(11, 0, new Label("Nome Social 2: "));
		registrationGridPanel.setWidget(11, 1, siteSocial1);
		registrationGridPanel.setWidget(12, 0, new Label("Nome Utente Social 2: "));
		registrationGridPanel.setWidget(12, 1, userNameSocial1);
		registrationGridPanel.setWidget(13, 0, new Label("Nome Social 3: "));
		registrationGridPanel.setWidget(13, 1, siteSocial2);
		registrationGridPanel.setWidget(14, 0, new Label("Nome Utente Social 3: "));
		registrationGridPanel.setWidget(14, 1, userNameSocial2);
		registrationGridPanel.setWidget(15, 0, new Label("Nome Social 4: "));
		registrationGridPanel.setWidget(15, 1, siteSocial3);
		registrationGridPanel.setWidget(16, 0, new Label("Nome Utente Social 4: "));
		registrationGridPanel.setWidget(16, 1, userNameSocial3);
		final Button registrationButton = new Button("SignUp");
		registrationButton.addClickHandler(event -> {
			final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
			final Date day = birthDateDatetBox.getValue();
			final String dateString = dateFormat.format(day);
			final ArrayList<String> userData = new ArrayList<>();
			userData.add(userNameTextBox.getText());
			userData.add(passwordTextBox.getText());
			userData.add(emailTextBox.getText());
			userData.add(nameTextBox.getText());
			userData.add(surnameTextBox.getText());
			userData.add(sexListBox.getSelectedValue());
			userData.add(dateString);
			userData.add(birthPlaceTextBox.getText());
			userData.add(livingPlaceTextBox.getText());
			final List<String> userDataSocial = new ArrayList<>();
			userDataSocial.add(siteSocial0.getText());
			userDataSocial.add(userNameSocial0.getText());
			userDataSocial.add(siteSocial1.getText());
			userDataSocial.add(userNameSocial1.getText());
			userDataSocial.add(siteSocial2.getText());
			userDataSocial.add(userNameSocial2.getText());
			userDataSocial.add(siteSocial3.getText());
			userDataSocial.add(userNameSocial3.getText());
			final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
			caffeineOverflow.signUp(userData, userDataSocial, new AsyncCallback<String>() {

				@Override
				public void onFailure(final Throwable caught) {
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("Error"));
					popup.center();
				}

				@Override
				public void onSuccess(final String result) {
					if (!result.equals("Error")) {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("Success"));
						popup.center();
						RegisterUser.this.verticalPanel.clear();
						CurrentUser.email = emailTextBox.getText();
						CurrentUser.accountType = 3;
						final UserProfile registrationData = new UserProfile(RegisterUser.this.verticalPanel);
						registrationData.onModuleLoad();
					} else {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("Error"));
						popup.center();
					}
				}
			});
		});
		this.verticalPanel.add(horizontalPanel);
		this.verticalPanel.add(new HTML("<br>"));
		this.verticalPanel.add(registrationGridPanel);
		this.verticalPanel.add(registrationButton);
	}
}