package com.caffeine.overflow.client;

import java.util.List;

import com.caffeine.overflow.model.Question;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
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
public class CaffeineOverflow implements EntryPoint {

	private VerticalPanel verticalPanel = null;

	@Override
	public void onModuleLoad() {

		RootPanel.get().clear();

		this.verticalPanel = new VerticalPanel();
		this.verticalPanel.add(new HTML("<h1>Domande</h1>"));
		this.verticalPanel.add(new HTML("</br>"));
		final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
		caffeineOverflow.getQuestions(new AsyncCallback<List<Question>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
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

				questionSelectionModel.addSelectionChangeHandler(event -> questionSelectionModel.addSelectionChangeHandler(event1 -> {
					final Question selected = questionSelectionModel.getSelectedObject();
					if (selected != null) {
						CaffeineOverflow.this.verticalPanel.clear();
						final ShowAnswers showAnswers = new ShowAnswers(CaffeineOverflow.this.verticalPanel);
						showAnswers.onModuleLoad(selected);
					}
				}));

				questionsTable.setRowCount(response.size(), true);
				questionsTable.setRowData(0, response);
				CaffeineOverflow.this.verticalPanel.add(questionsTable);

			}
		});
		final Menu menu = new Menu(this.verticalPanel, 0);
		menu.onModuleLoad();

		RootPanel.get().add(this.verticalPanel);
	}

}