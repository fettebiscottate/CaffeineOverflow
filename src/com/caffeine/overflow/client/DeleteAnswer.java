package com.caffeine.overflow.client;

import java.util.List;

import com.caffeine.overflow.model.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class DeleteAnswer {

	private VerticalPanel verticalPanel = null;

	public DeleteAnswer(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	public void onModuleLoad() {

		this.verticalPanel.add(new HTML("<h1>Cancella</h1>"));
		this.verticalPanel.add(new HTML("<br>"));
		final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);

		caffeineOverflow.getAllAnswers(new AsyncCallback<List<Answer>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Error</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(List<Answer> response) {
				final CellTable<Answer> answersTable = new CellTable<>(20);
				answersTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getText();
					}
				}, "Risposta");
				answersTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getUserName();
					}
				}, "Utente");
				answersTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getDay();
					}
				}, "Giorno");
				answersTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getTime();
					}
				}, "Ora");
				answersTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				final SingleSelectionModel<Answer> answerSelectionModel = new SingleSelectionModel<>();
				answersTable.setSelectionModel(answerSelectionModel);
				answerSelectionModel.addSelectionChangeHandler(event -> {
					final Answer selected = answerSelectionModel.getSelectedObject();
					if (selected != null) {
						final int id = selected.getIdAnswer();
						try {
							caffeineOverflow.removeAnswer(id, new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									PopupPanel popup = new PopupPanel(true);
									popup.setWidget(new HTML("<font color='red'>Error</font>"));
									popup.center();
								}

								@Override
								public void onSuccess(Boolean response) {
									if (response) {
										final PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Success"));
										popup.center();
										DeleteAnswer.this.verticalPanel.clear();
										final DeleteAnswer deleteAnswer = new DeleteAnswer(
												DeleteAnswer.this.verticalPanel);
										deleteAnswer.onModuleLoad();
									} else {
										final PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Error"));
										popup.center();
									}
								}
							});
						} catch (final Exception e) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML(e.toString()));
							popup.center();
						}
					}
				});

				answersTable.setRowCount(response.size(), true);
				answersTable.setRowData(0, response);
				DeleteAnswer.this.verticalPanel.add(answersTable);

			}
		});
	}
}
