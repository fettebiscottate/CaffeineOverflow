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
 * 
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
	 * @param verticalPanel
	 */
	public AddQuestion(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	
	private void getCategoriesTree(Category padre, List<Category> response, TreeItem nodoPadre) {

		switch (padre.getName()) {
		case "null":
			nodoPadre.setText("ELENCO CATEGORIE");
			break;
		}

		for (Iterator<Category> iterator2 = response.iterator(); iterator2.hasNext();) {
			final Category c = iterator2.next();
			if (!this.listCategories.contains(c) && c.getFather().getName().equals(padre.getName())) {
				final TreeItem nodo = new TreeItem();
				nodo.setText(c.getName());
				this.listCategories.add(c);

				for (Iterator<Category> iterator = response.iterator(); iterator.hasNext();) {
					final Category sottoC = iterator.next();
					if (sottoC.getFather().getName().equals(c.getName())) {
						this.getCategoriesTree(c, response, nodo);
					}
				}
				nodoPadre.addItem(nodo);
			}
		}
	}

	
	/**
	 * 
	 */
	public void onModuleLoad() {

		this.verticalPanel1 = new VerticalPanel();

		final Menu menu = new Menu(this.verticalPanel, CurrentUser.accountType);
		menu.onModuleLoad();
		this.verticalPanel.add(new HTML("</br>"));

		final Tree albero = new Tree();

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
					// risoluzione albero delle categorie
					AddQuestion.this.getCategoriesTree(categoriaNull, response, AddQuestion.this.root);
					AddQuestion.this.root.setState(true);
					albero.addItem(AddQuestion.this.root);

				}
			});
		} catch (final Exception e) {
			PopupPanel popup = new PopupPanel(true);
			popup.setWidget(new HTML(e.toString()));
			popup.center();
		}

		albero.addSelectionHandler(event -> {
			this.verticalPanel1.clear();
			if (!event.getSelectedItem().getText().equals("ELENCO CATEGORIE")) {

				this.verticalPanel1.add(new HTML("<br>"));

				this.questionTextArea = new TextArea();
				this.questionTextArea.getElement().setAttribute("maxlength", "300");

				final Label label1 = new Label("Link 1: ");
				final TextBox link1 = new TextBox();
				final Label label2 = new Label("Link 2: ");
				final TextBox link2 = new TextBox();
				final Label label3 = new Label("Link 3: ");
				final TextBox link3 = new TextBox();
				final Label label4 = new Label("Link 4: ");
				final TextBox link4 = new TextBox();

				final Grid grid = new Grid(4, 2);

				grid.setWidget(0, 0, label1);
				grid.setWidget(0, 1, link1);
				grid.setWidget(1, 0, label2);
				grid.setWidget(1, 1, link2);
				grid.setWidget(2, 0, label3);
				grid.setWidget(2, 1, link3);
				grid.setWidget(3, 0, label4);
				grid.setWidget(3, 1, link4);

				this.questionButton = new Button("Aggiungi");

				this.labelCategory = new Label("Categoria selezionata: " + event.getSelectedItem().getText());

				final HorizontalPanel h1 = new HorizontalPanel();
				h1.add(this.questionTextArea);

				final HorizontalPanel h3 = new HorizontalPanel();
				h3.add(grid);

				final HorizontalPanel h2 = new HorizontalPanel();
				h2.add(this.questionButton);

				// aggiungi domanda
				this.questionButton.addClickHandler(eventClick -> {
					// array link
					final ArrayList<String> listaLink = new ArrayList<>();
					listaLink.add(link1.getValue());
					listaLink.add(link2.getValue());
					listaLink.add(link3.getValue());
					listaLink.add(link4.getValue());

					final int id = 1;

					final Question nuova = new Question(id, AddQuestion.this.questionTextArea.getValue(),
							event.getSelectedItem().getText(), CurrentUser.email, listaLink);

					final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
					caffeineOverflow.addQuestion(nuova, new AsyncCallback<Boolean>() {

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
				final Label label = new Label("Inserisci il testo della domanda: ");
				this.verticalPanel1.add(label);
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(h1);
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(h3);
				this.verticalPanel1.add(new HTML("<br/>"));
				this.verticalPanel1.add(h2);
			}
		});
		final HorizontalPanel hp = new HorizontalPanel();
		hp.add(albero);
		this.verticalPanel.add(hp);
		this.verticalPanel.add(this.verticalPanel1);
	}

}