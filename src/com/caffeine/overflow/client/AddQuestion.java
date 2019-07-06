package com.caffeine.overflow.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.caffeine.overflow.model.Category;
import com.caffeine.overflow.model.Question;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class consists of a constructor and two method to allow the user to
 * create a question of a specific category
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class AddQuestion {

	private VerticalPanel verticalPanel = null;
	private VerticalPanel verticalPanel1 = null;
	private Button questionButton;
	private TextArea questionTextArea;
	private Label labelCategory;
	protected List<Category> listCategories = new ArrayList<>();
	private final TreeItem root = new TreeItem();

	/**
	 * Constructor for class AddQuestion
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public AddQuestion(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This method is a recursive method to create the categories tree.
	 * 
	 * @param padre
	 * @param response
	 * @param nodoPadre
	 * @see Category
	 * @see List
	 * @see TreeItem
	 * @see Iterator
	 * @since 1.0
	 */
	private void getCategoriesTree(Category padre, List<Category> response, TreeItem nodoPadre) {
		switch (padre.getName()) {
		case "null":
			nodoPadre.setText("ELENCO CATEGORIE");
			break;
		}
		for (Iterator<Category> iterator2 = response.iterator(); iterator2.hasNext();) {
			final Category category = iterator2.next();
			if (!this.listCategories.contains(category) && category.getFather().getName().equals(padre.getName())) {
				final TreeItem nodo = new TreeItem();
				nodo.setText(category.getName());
				this.listCategories.add(category);
				for (Iterator<Category> iterator = response.iterator(); iterator.hasNext();) {
					final Category sottoC = iterator.next();
					if (sottoC.getFather().getName().equals(category.getName())) {
						this.getCategoriesTree(category, response, nodo);
					}
				}
				nodoPadre.addItem(nodo);
			}
		}
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see CaffeineOverflowServiceAsync
	 * @see CurrentUser
	 * @see List
	 * @see TextBox
	 * @see Label
	 * @see Grid
	 * @see Tree
	 * @see HorizontalPanel
	 * @see VerticalPanel
	 * @see Menu
	 * @see PopupPanel
	 * @see Question
	 * @see Category
	 * @see TextBox
	 * @see Button
	 * @see CaffeineOverflowServiceAsync
	 * @since 1.0
	 */
	public void onModuleLoad() {
		this.verticalPanel1 = new VerticalPanel();
		final Menu menu = new Menu(this.verticalPanel, CurrentUser.accountType);
		menu.onModuleLoad();
		this.verticalPanel.add(new HTML("</br>"));
		final Tree tree = new Tree();
		try {
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
					final Category categoriaNull = new Category("null");
					AddQuestion.this.listCategories.add(categoriaNull);
					AddQuestion.this.getCategoriesTree(categoriaNull, response, AddQuestion.this.root);
					AddQuestion.this.root.setState(true);
					tree.addItem(AddQuestion.this.root);
				}
			});
		} catch (final Exception e) {
			PopupPanel popup = new PopupPanel(true);
			popup.setWidget(new HTML(e.toString()));
			popup.center();
		}
		tree.addSelectionHandler(event -> {
			this.verticalPanel1.clear();
			if (!event.getSelectedItem().getText().equals("ELENCO CATEGORIE")) {
				this.verticalPanel1.add(new HTML("<br>"));
				this.questionTextArea = new TextArea();
				final TextBox link1 = new TextBox();
				final TextBox link2 = new TextBox();
				final TextBox link3 = new TextBox();
				final TextBox link4 = new TextBox();
				final Grid grid = new Grid(4, 2);
				grid.setWidget(0, 0, new Label("Link 1: "));
				grid.setWidget(0, 1, link1);
				grid.setWidget(1, 0, new Label("Link 2: "));
				grid.setWidget(1, 1, link2);
				grid.setWidget(2, 0, new Label("Link 3: "));
				grid.setWidget(2, 1, link3);
				grid.setWidget(3, 0, new Label("Link 4: "));
				grid.setWidget(3, 1, link4);
				this.questionButton = new Button("Aggiungi");
				this.labelCategory = new Label("Categoria selezionata: " + event.getSelectedItem().getText());
				final HorizontalPanel horizontalPanel1 = new HorizontalPanel();
				horizontalPanel1.add(this.questionTextArea);
				final HorizontalPanel horizontalPanel3 = new HorizontalPanel();
				horizontalPanel3.add(grid);
				final HorizontalPanel horizontalPanel2 = new HorizontalPanel();
				horizontalPanel2.add(this.questionButton);
				this.questionButton.addClickHandler(eventClick -> {
					final ArrayList<String> listaLink = new ArrayList<>();
					listaLink.add(link1.getValue());
					listaLink.add(link2.getValue());
					listaLink.add(link3.getValue());
					listaLink.add(link4.getValue());
					final int id = 1;
					final Question nuovaDomanda = new Question(id, AddQuestion.this.questionTextArea.getValue(),
							event.getSelectedItem().getText(), CurrentUser.email, listaLink);
					final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
					caffeineOverflow.addQuestion(nuovaDomanda, new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("<font color='red'>Error</font>"));
							popup.center();
						}

						@Override
						public void onSuccess(Boolean response) {
							if (response) {
								PopupPanel popup = new PopupPanel(true);
								popup.setWidget(new HTML("Domanda inserita"));
								popup.center();
								AddQuestion.this.verticalPanel.clear();
								final AddQuestion insDom = new AddQuestion(AddQuestion.this.verticalPanel);
								insDom.onModuleLoad();
							} else {
								PopupPanel popup = new PopupPanel(true);
								popup.setWidget(new HTML("Error"));
								popup.center();
							}
						}
					});

				});
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(this.labelCategory);
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(new Label("Inserisci il testo della domanda: "));
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(horizontalPanel1);
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(horizontalPanel3);
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(horizontalPanel2);
			}
		});
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(tree);
		this.verticalPanel.add(horizontalPanel);
		this.verticalPanel.add(this.verticalPanel1);
	}

}