package com.caffeine.overflow.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class consists of a constructor and a method to allow the user to log
 * into the site
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class LogIn {

	private VerticalPanel verticalPanel = null;

	/**
	 * Constructor for class LogIn
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public LogIn(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see Button
	 * @see Grid
	 * @see HTML
	 * @see Label
	 * @see PasswordTextBox
	 * @see PopupPanel
	 * @see RootPanel
	 * @see TextBox
	 * @see VerticalPanel
	 * @see CaffeineOverflowServiceAsync
	 * @since 1.0
	 */
	public void onModuleLoad() {
		this.verticalPanel = new VerticalPanel();
		final TextBox usernameTextBox = new TextBox();
		final PasswordTextBox passwordPasswordTextBox = new PasswordTextBox();
		final Grid loginGridPanel = new Grid(2, 2);
		loginGridPanel.setWidget(0, 0, new Label("Email:"));
		loginGridPanel.setWidget(0, 1, usernameTextBox);
		loginGridPanel.setWidget(1, 0, new Label("Password:"));
		loginGridPanel.setWidget(1, 1, passwordPasswordTextBox);
		final Button loginButton = new Button("Login");
		loginButton.addClickHandler(event -> {
			final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
			caffeineOverflow.logIn(usernameTextBox.getText(), passwordPasswordTextBox.getText(),
					new AsyncCallback<Integer>() {

						@Override
						public void onFailure(Throwable caught) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("<font color='red'>Error</font>"));
							popup.center();
						}

						@Override
						public void onSuccess(Integer response) {
							CurrentUser.email = (usernameTextBox.getText());
							CurrentUser.accountType = response;
							LogIn.this.verticalPanel.clear();
							if (response == -1) {
								final CaffeineOverflow loginData0 = new CaffeineOverflow();
								loginData0.onModuleLoad();
								PopupPanel popup0 = new PopupPanel(true);
								popup0.setWidget(new HTML("Wrong Password"));
								popup0.center();
							} else if (response == 0) {
								PopupPanel popup1 = new PopupPanel(true);
								popup1.setWidget(new HTML("Wrong User"));
								popup1.center();
							} else if (response == 1) {
								final JudgeProfile loginData1 = new JudgeProfile(LogIn.this.verticalPanel);
								loginData1.onModuleLoad();
							} else if (response == 2) {
								final AdminProfile loginData2 = new AdminProfile(LogIn.this.verticalPanel);
								loginData2.onModuleLoad();
							} else if (response == 3) {
								final UserProfile loginData3 = new UserProfile(LogIn.this.verticalPanel);
								loginData3.onModuleLoad();
							} else {
								PopupPanel popup = new PopupPanel(true);
								popup.setWidget(new HTML("Error"));
								popup.center();
							}
						}
					});
		});
		this.verticalPanel.add(new HTML("<br>"));
		this.verticalPanel.add(loginGridPanel);
		this.verticalPanel.add(loginButton);
		final Menu menu = new Menu(this.verticalPanel, 0);
		menu.onModuleLoad();
		RootPanel.get().add(this.verticalPanel);
	}
}
