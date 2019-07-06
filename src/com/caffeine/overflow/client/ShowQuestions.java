package com.caffeine.overflow.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * * This class consists of a constructor and some methods to allow the user to
 * see the questions and filter them by category
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class ShowQuestions {

	private VerticalPanel verticalPanel = null;
	private String filter;
	private String selected;
	private List<Category> subCategoriesFilterList = new ArrayList<>();
	private String path;

	/**
	 * Constructor for class ShowQuestions
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public ShowQuestions(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This method show the sub categories of a selected category
	 * 
	 * @param categoriesList
	 * @param father
	 */
	private void getSubCategories(List<Category> categoriesList, Category father) {
		if (father != null) {
			switch (father.getName()) {
			case "Base":
				return;
			}
			for (Iterator<Category> iterator = categoriesList.iterator(); iterator.hasNext();) {
				final Category category = iterator.next();
				if (category.getFather().getName().equals(father.getName())) {
					if (!category.getFather().getSubCategories().isEmpty()) {
						this.getSubCategories(categoriesList, category);
					} else {
						this.getSubCategories(categoriesList, new Category("Base"));
					}
					this.subCategoriesFilterList.add(category);
				}
			}
		} else {
			this.subCategoriesFilterList = categoriesList;
		}
	}

	/**
	 * This is the entry point method.
	 * 
	 * @param filter, the category to show
	 * @param path,   the path of the categories tree
	 * @see Category
	 * @see Question
	 * @see CellTable
	 * @see KeyboardSelectionPolicy
	 * @see TextColumn
	 * @see Button
	 * @see HTML
	 * @see HorizontalPanel
	 * @see Label
	 * @see ListBox
	 * @see PopupPanel
	 * @see VerticalPanel
	 * @see SingleSelectionModel
	 * @since 1.0
	 */
	public void onModuleLoad(final String filter, final String path) {
		this.filter = filter;
		this.path = path;
		final Label labelFilter;
		labelFilter = this.filter == null ? new Label("CATEGORIA:") : new Label("CATEGORIA: " + this.path);
		final HorizontalPanel horizontalPanel1 = new HorizontalPanel();
		horizontalPanel1.add(labelFilter);
		this.verticalPanel.add(horizontalPanel1);
		final HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		final Button filterButton = new Button("Filter");
		final ListBox selectedCategory = new ListBox();
		final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
		caffeineOverflow.getCategories(new AsyncCallback<List<Category>>() {

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Error</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(List<Category> response) {
				Category category = null;
				if (filter != null) {
					category = new Category(filter);
				}
				ShowQuestions.this.getSubCategories(response, category);
				for (Iterator<Category> iterator = ShowQuestions.this.subCategoriesFilterList.iterator(); iterator
						.hasNext();) {
					final Category category1 = iterator.next();
					selectedCategory.addItem(category1.getName());
				}
				caffeineOverflow.getQuestions(new AsyncCallback<List<Question>>() {

					@Override
					public void onFailure(Throwable caught) {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("<font color='red'>Error</font>"));
						popup.center();
					}

					@Override
					public void onSuccess(List<Question> response) {
						final List<Question> questionsList = new ArrayList<>();
						for (Iterator<Question> iterator2 = response.iterator(); iterator2.hasNext();) {
							final Question question = iterator2.next();
							if (question.getCategoryName().equals(filter)) {
								questionsList.add(question);
							} else if (!ShowQuestions.this.subCategoriesFilterList.isEmpty()) {
								horizontalPanel2.add(selectedCategory);
								horizontalPanel2.add(filterButton);
								for (Iterator<Category> iterator = ShowQuestions.this.subCategoriesFilterList
										.iterator(); iterator.hasNext();) {
									final Category subCategory = iterator.next();
									if (question.getCategoryName().equals(subCategory.getName())) {
										questionsList.add(question);
									}
								}
							}
						}
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
							final Question selected1 = questionSelectionModel.getSelectedObject();
							if (selected1 != null) {
								ShowQuestions.this.verticalPanel.clear();
								final ShowAnswers showAnswers = new ShowAnswers(ShowQuestions.this.verticalPanel);
								showAnswers.onModuleLoad(selected1);
							}
						});
						questionsTable.setRowCount(questionsList.size(), true);
						questionsTable.setRowData(0, questionsList);
						ShowQuestions.this.verticalPanel.add(questionsTable);
					}
				});
			}
		});
		filterButton.addClickHandler(event -> {
			this.selected = selectedCategory.getSelectedItemText();
			this.verticalPanel.clear();
			final ShowQuestions showQuestions = new ShowQuestions(this.verticalPanel);
			final String categoryPath = path + " -> " + this.selected;
			showQuestions.onModuleLoad(this.selected, categoryPath);
		});
		final Button resetButton = new Button("Reset");
		resetButton.addClickHandler(event -> {
			this.verticalPanel.clear();
			final ShowQuestions showQuestions = new ShowQuestions(this.verticalPanel);
			showQuestions.onModuleLoad(null, "");
		});
		this.verticalPanel.add(horizontalPanel2);
		this.verticalPanel.add(resetButton);
	}
}