package com.caffeine.overflow.client;

import java.util.Iterator;
import java.util.List;

import com.caffeine.overflow.model.Answer;
import com.caffeine.overflow.model.Question;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class ShowAnswers {

	private VerticalPanel verticalPanel = null;

	public ShowAnswers(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	public void onModuleLoad(Question currentSelection) {
		this.verticalPanel.add(new HTML("<br>"));
		this.verticalPanel.add(new HTML("<br>"));
		final Grid answerGridPanel = new Grid(8, 2);
		answerGridPanel.setWidget(0, 0, new Label("Utente: "));
		answerGridPanel.setWidget(0, 1, new Label(currentSelection.getUserName()));
		answerGridPanel.setWidget(1, 0, new Label("Testo:  "));
		answerGridPanel.setWidget(1, 1, new Label(currentSelection.getText()));
		answerGridPanel.setWidget(2, 0, new Label("Data: "));
		answerGridPanel.setWidget(2, 1, new Label(currentSelection.getDay()));
		answerGridPanel.setWidget(4, 0, new Label("Link1: "));
		answerGridPanel.setWidget(4, 1, new Label(currentSelection.getLinkList().get(0)));
		answerGridPanel.setWidget(5, 0, new Label("Link2: "));
		answerGridPanel.setWidget(5, 1, new Label(currentSelection.getLinkList().get(1)));
		answerGridPanel.setWidget(6, 0, new Label("Link3: "));
		answerGridPanel.setWidget(6, 1, new Label(currentSelection.getLinkList().get(2)));
		answerGridPanel.setWidget(7, 0, new Label("Link4: "));
		answerGridPanel.setWidget(7, 1,  new Label(currentSelection.getLinkList().get(3)));
		this.verticalPanel.add(answerGridPanel);
		final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
		final int id = currentSelection.getIdQuestion();
		caffeineOverflow.orderAnswers(id, new AsyncCallback<List<Answer>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Error</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(List<Answer> response) {
				final CellTable<Answer> questionsTable = new CellTable<>(20);
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getText();
					}
				}, "Risposta");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getUserName();
					}
				}, "Utente");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getDay();
					}
				}, "Giorno");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getTime();
					}
				}, "Ora");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getJudgeEmail();
					}
				}, "Giudice");

				final TextColumn<Answer> ratingTextColumn = new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getRating();
					}
				};
				questionsTable.addColumn(ratingTextColumn, "Voto");
				ratingTextColumn.setSortable(true);
				
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getLinkList().get(0);
					}
				}, "Link");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getLinkList().get(1);
					}
				}, "Link");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getLinkList().get(2);
					}
				}, "Link");
				questionsTable.addColumn(new TextColumn<Answer>() {
					@Override
					public String getValue(Answer answer) {
						return answer.getLinkList().get(3);
					}
				}, "Link");
				final ListDataProvider<Answer> dataProvider = new ListDataProvider<>();
				dataProvider.addDataDisplay(questionsTable);
				final List<Answer> list = dataProvider.getList();
				for (Iterator<Answer> iterator = response.iterator(); iterator.hasNext();) {
					final Answer answer = iterator.next();
					list.add(answer);
				}
				final ListHandler<Answer> columnSortHandler = new ListHandler<>(list);
				columnSortHandler.setComparator(ratingTextColumn, (option1, option2) -> {
					if (option1.getRating().equals(option2.getRating())) {
						if (!option1.getDate().after(option2.getDate())) {
							return -1;
						} else {
							return 1;
						}
					} else {
						return (option2 != null && option1 != null) ? option1.getRating().compareTo(option2.getRating()) : 1;
					}
				});
				questionsTable.addColumnSortHandler(columnSortHandler);
				questionsTable.getColumnSortList().push(ratingTextColumn);
				questionsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				final SingleSelectionModel<Answer> selectionModel = new SingleSelectionModel<>();
				questionsTable.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(event -> {
					
					if (CurrentUser.accountType == 1) {
						final Answer currentSelection1 = selectionModel.getSelectedObject();
						if (currentSelection1 != null) {
							final AddRating iv = new AddRating(ShowAnswers.this.verticalPanel);
							iv.onModuleLoad(currentSelection1);
						}
					}
				});
				questionsTable.setRowCount(response.size(), true);
				questionsTable.setRowData(0, response);
				ShowAnswers.this.verticalPanel.add(questionsTable);
			}

		});
	}

}
