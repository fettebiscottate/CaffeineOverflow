package com.caffeine.overflow.client;

import java.util.List;

import com.caffeine.overflow.model.Question;
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
public class DeleteQuestion {

	private VerticalPanel verticalPanel = null;

	public DeleteQuestion(final VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	public void onModuleLoad() {

		this.verticalPanel.add(new HTML("<h1>Cancella</h1>"));
		this.verticalPanel.add(new HTML("<br/>"));

		final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
		caffeineOverflow.getQuestions(new AsyncCallback<List<Question>>() {

			@Override
			public void onFailure(final Throwable caught) {
				final PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Error</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(List<Question> response) {
				final CellTable<Question> questionsTable = new CellTable<>(20);
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(Question question) {
						return question.getText();
					}
				}, "Domanda");
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(Question question) {
						return question.getUserName();
					}
				}, "Utente");
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(Question question) {
						return question.getDay();
					}
				}, "Giorno");
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(Question question) {
						return question.getTime();
					}
				}, "Ora");
				questionsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				final SingleSelectionModel<Question> questionSelectionModel = new SingleSelectionModel<>();
				questionsTable.setSelectionModel(questionSelectionModel);

				questionSelectionModel.addSelectionChangeHandler(event -> {
					final Question selected = questionSelectionModel.getSelectedObject();
					if (selected != null) {
						final int id = selected.getIdQuestion();
						try {
							caffeineOverflow.removeQuestion(id, new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(final Throwable caught) {
									final PopupPanel popup = new PopupPanel(true);
									popup.setWidget(new HTML("<font color='red'>Error</font>"));
									popup.center();
								}

								@Override
								public void onSuccess(final Boolean response) {
									if (response) {
										final PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Success"));
										popup.center();
										DeleteQuestion.this.verticalPanel.clear();
										final DeleteQuestion deleteQuestion = new DeleteQuestion(DeleteQuestion.this.verticalPanel);
										deleteQuestion.onModuleLoad();
									} else {
										final PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Error"));
										popup.center();
									}
								}
							});
						} catch (final Exception e) {
							final PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML(e.toString()));
							popup.center();
						}
					}
				});

				questionsTable.setRowCount(response.size(), true);
				questionsTable.setRowData(0, response);
				DeleteQuestion.this.verticalPanel.add(questionsTable);

			}
		});
	}
}
