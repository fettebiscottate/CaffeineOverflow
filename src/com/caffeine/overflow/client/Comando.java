package com.caffeine.overflow.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class Comando {

	private VerticalPanel verticalPanel = null;

	public Comando(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	public Command comando(String tipo) {
		Command cmd = null;

		if (tipo.equalsIgnoreCase("0")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final LogIn logIn = new LogIn(Comando.this.verticalPanel);
				logIn.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("1")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final RegisterUser registerUser = new RegisterUser(Comando.this.verticalPanel);
				registerUser.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("2")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final AddQuestion addQuestion = new AddQuestion(Comando.this.verticalPanel);
				addQuestion.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("3")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final ShowQuestions showQuestions = new ShowQuestions(Comando.this.verticalPanel);
				showQuestions.onModuleLoad(null, "");
			};
		} else if (tipo.equalsIgnoreCase("4")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final CaffeineOverflow main = new CaffeineOverflow();
				main.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("5")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final AppointJudge appointJudge = new AppointJudge(Comando.this.verticalPanel);
				appointJudge.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("6")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final CategoryAdmin categoryAdmin = new CategoryAdmin(Comando.this.verticalPanel);
				categoryAdmin.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("7")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final AddAnswer addAnswer = new AddAnswer(Comando.this.verticalPanel);
				addAnswer.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("8")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final DeleteQuestion deleteQuestion = new DeleteQuestion(Comando.this.verticalPanel);
				deleteQuestion.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("9")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();
				final DeleteAnswer deleteAnswer = new DeleteAnswer(Comando.this.verticalPanel);
				deleteAnswer.onModuleLoad();
			};
		} else if (tipo.equalsIgnoreCase("10")) {
			cmd = () -> {
				Comando.this.verticalPanel.clear();

				if (CurrentUser.accountType == 0) {
					final CaffeineOverflow newService0 = new CaffeineOverflow();
					newService0.onModuleLoad();
				} else if (CurrentUser.accountType == 1) {
					final JudgeProfile newService1 = new JudgeProfile(Comando.this.verticalPanel);
					newService1.onModuleLoad();
				} else if (CurrentUser.accountType == 2) {
					final AdminProfile newService2 = new AdminProfile(Comando.this.verticalPanel);
					newService2.onModuleLoad();
				} else if (CurrentUser.accountType == 3) {
					final UserProfile newService3 = new UserProfile(Comando.this.verticalPanel);
					newService3.onModuleLoad();
				} else {
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("Error"));
					popup.center();
				}
			};
		}
		return cmd;
	}
}
