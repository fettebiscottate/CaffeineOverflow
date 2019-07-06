package com.caffeine.overflow.client;

import java.util.ArrayList;
import java.util.List;

import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Question;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * This class consists of a constructor and a method to allow the user to create
 * an answer to a specific question
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class AddAnswer {

	private VerticalPanel verticalPanel = null;
	private VerticalPanel verticalPanel1 = null;
	private Button answerButton;
	private TextArea answerTextArea;

	/**
	 * Constructor for class AddAnswer
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public AddAnswer(final VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see VerticalPanel
	 * @see Answer
	 * @see CaffeineOverflowServiceAsync
	 * @see PopupPanel
	 * @see CellTable
	 * @see KeyboardSelectionPolicy
	 * @see Question
	 * @see TextArea
	 * @see TextColumn
	 * @see HorizontalPanel
	 * @see List
	 * @see TextBox
	 * @see Label
	 * @see Grid
	 * @see CurrentUser
	 * @see PopupPanel
	 * @since 1.0
	 */
	public void onModuleLoad() {
		this.verticalPanel1 = new VerticalPanel();
		this.verticalPanel.add(new HTML("Risposta"));
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
			public void onSuccess(final List<Question> response) {
				final CellTable<Question> questionsTable = new CellTable<>(20);
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(final Question question) {
						return question.getText();
					}
				}, "Domanda");
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(final Question question) {
						return question.getUserName();
					}
				}, "Utente");
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(final Question question) {
						return question.getDay();
					}
				}, "GIiorno");
				questionsTable.addColumn(new TextColumn<Question>() {
					@Override
					public String getValue(final Question question) {
						return question.getTime();
					}
				}, "Ora");
				questionsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				final SingleSelectionModel<Question> questionSelectionModel = new SingleSelectionModel<>();
				questionsTable.setSelectionModel(questionSelectionModel);
				questionSelectionModel.addSelectionChangeHandler(event -> {
					AddAnswer.this.verticalPanel1.clear();
					final Question selected = questionSelectionModel.getSelectedObject();
					if (selected != null) {
						final int idQuestion = selected.getIdQuestion();
						final int id = 1;
						AddAnswer.this.answerTextArea = new TextArea();
						AddAnswer.this.answerButton = new Button("Rispondi");
						final HorizontalPanel horizontalPanel1 = new HorizontalPanel();
						horizontalPanel1.add(AddAnswer.this.answerTextArea);
						final TextBox linkTextBox1 = new TextBox();
						final TextBox linkTextBox2 = new TextBox();
						final TextBox linkTextBox3 = new TextBox();
						final TextBox linkTextBox4 = new TextBox();
						final Grid answerGridPanel = new Grid(4, 2);
						answerGridPanel.setWidget(0, 0, new Label("Link 1: "));
						answerGridPanel.setWidget(0, 1, linkTextBox1);
						answerGridPanel.setWidget(1, 0, new Label("Link 2: "));
						answerGridPanel.setWidget(1, 1, linkTextBox2);
						answerGridPanel.setWidget(2, 0, new Label("Link 3: "));
						answerGridPanel.setWidget(2, 1, linkTextBox3);
						answerGridPanel.setWidget(3, 0, new Label("Link 4: "));
						answerGridPanel.setWidget(3, 1, linkTextBox4);
						final HorizontalPanel horizontalPanel3 = new HorizontalPanel();
						horizontalPanel3.add(answerGridPanel);
						final HorizontalPanel horizontalPanel2 = new HorizontalPanel();
						horizontalPanel2.add(AddAnswer.this.answerButton);
						AddAnswer.this.answerButton.addClickHandler(eventClick -> {
							final List<String> linkList = new ArrayList<>();
							linkList.add(linkTextBox1.getValue());
							linkList.add(linkTextBox2.getValue());
							linkList.add(linkTextBox3.getValue());
							linkList.add(linkTextBox4.getValue());
							final Answer insertedAnswer = new Answer(id, idQuestion,
									AddAnswer.this.answerTextArea.getValue(), CurrentUser.email, linkList);
							final CaffeineOverflowServiceAsync caffeineOverflow1 = GWT
									.create(CaffeineOverflowService.class);
							caffeineOverflow1.addAnswer(insertedAnswer, new AsyncCallback<Boolean>() {

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
										popup.setWidget(new HTML("Risposta inserita"));
										popup.center();
										AddAnswer.this.verticalPanel.clear();
										final AddAnswer addedAnswer = new AddAnswer(AddAnswer.this.verticalPanel);
										addedAnswer.onModuleLoad();
									} else {
										final PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Error"));
										popup.center();
									}
								}
							});
						});
						AddAnswer.this.verticalPanel1.add(new HTML("<br/>"));
						AddAnswer.this.verticalPanel1.add(new Label("testo: "));
						AddAnswer.this.verticalPanel1.add(horizontalPanel1);
						AddAnswer.this.verticalPanel1.add(horizontalPanel3);
						AddAnswer.this.verticalPanel1.add(horizontalPanel2);
					}
				});
				questionsTable.setRowCount(response.size(), true);
				questionsTable.setRowData(0, response);
				AddAnswer.this.verticalPanel.add(questionsTable);
				AddAnswer.this.verticalPanel.add(AddAnswer.this.verticalPanel1);
			}
		});

	}

}
