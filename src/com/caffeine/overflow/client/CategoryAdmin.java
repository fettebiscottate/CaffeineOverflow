package com.caffeine.overflow.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.caffeine.overflow.model.Category;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class consists of a constructor and two method to allow the admin to
 * manage categories
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class CategoryAdmin {

	private VerticalPanel verticalPanel = null;
	private VerticalPanel verticalPanel1 = null;
	private TextBox categoryTextBox;
	private Button buttonSubCategory;
	private TextBox subCategoryTextBox;
	private Button buttonUpdate;
	private TextBox updateTextBox;
	protected List<Category> listCategories = new ArrayList<>();
	private final TreeItem root = new TreeItem();

	/**
	 * Constructor for class CategoryAdmin
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public CategoryAdmin(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This method is a recursive method to create the categories tree.
	 * 
	 * @param father
	 * @param categoriesList
	 * @param supNode
	 * @see Category
	 * @see List
	 * @see TreeItem
	 * @see Iterator
	 * @since 1.0
	 */
	private void getCategoriesTree(Category father, List<Category> categoriesList, TreeItem supNode) {
		switch (father.getName()) {
		case "null":
			supNode.setText("CATEGORIE");
			break;
		}
		for (Iterator<Category> iterator2 = categoriesList.iterator(); iterator2.hasNext();) {
			final Category category = iterator2.next();
			if (!this.listCategories.contains(category) && category.getFather().getName().equals(father.getName())) {
				final TreeItem intermediateNode = new TreeItem();
				intermediateNode.setText(category.getName());
				this.listCategories.add(category);
				for (Iterator<Category> iterator = categoriesList.iterator(); iterator.hasNext();) {
					final Category subCategory = iterator.next();
					if (subCategory.getFather().getName().equals(category.getName())) {
						this.getCategoriesTree(category, categoriesList, intermediateNode);
					}
				}
				supNode.addItem(intermediateNode);
			}
		}
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see VerticalPanel
	 * @see Menu
	 * @see HTML
	 * @see Button
	 * @see TextBox
	 * @see HorizontalPanel
	 * @see Grid
	 * @see Category
	 * @see CaffeineOverflowServiceAsync
	 * @see PopupPanel
	 * @see Tree
	 * @since
	 */
	public void onModuleLoad() {
		this.verticalPanel1 = new VerticalPanel();
		final Menu menu = new Menu(this.verticalPanel, CurrentUser.accountType);
		menu.onModuleLoad();
		this.verticalPanel.add(new HTML("<h1> Gestione Categorie </h1>"));
		this.verticalPanel.add(new HTML("</br>"));
		final Button addCategoryButton = new Button("Aggiungi categoria");
		this.categoryTextBox = new TextBox();
		final HorizontalPanel horizontalPanel1 = new HorizontalPanel();
		final Grid categoryGridPanel = new Grid(2, 2);
		categoryGridPanel.setWidget(0, 0, this.categoryTextBox);
		categoryGridPanel.setWidget(0, 1, addCategoryButton);
		horizontalPanel1.add(categoryGridPanel);
		addCategoryButton.addClickHandler(event -> {

			final Category newCategory = new Category(this.categoryTextBox.getValue());
			try {
				final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
				caffeineOverflow.addCategory(newCategory, null, new AsyncCallback<Boolean>() {

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
							popup.setWidget(new HTML("Success"));
							popup.center();
							CategoryAdmin.this.verticalPanel.clear();
							final CategoryAdmin categoryAdmin = new CategoryAdmin(CategoryAdmin.this.verticalPanel);
							categoryAdmin.onModuleLoad();
						} else {
							PopupPanel popup = new PopupPanel(true);
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
		});
		this.verticalPanel.add(horizontalPanel1);
		final Tree categoryTree = new Tree();
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
					final Category nullCategory = new Category("null");
					CategoryAdmin.this.listCategories.add(nullCategory);
					CategoryAdmin.this.getCategoriesTree(nullCategory, response, CategoryAdmin.this.root);
					CategoryAdmin.this.root.setState(true);
					categoryTree.addItem(CategoryAdmin.this.root);

				}
			});
		} catch (final Exception e) {
			PopupPanel popup = new PopupPanel(true);
			popup.setWidget(new HTML(e.toString()));
			popup.center();
		}
		this.verticalPanel1.add(new HTML("<br>"));
		categoryTree.addSelectionHandler(event -> {
			this.verticalPanel1.clear();
			if (!event.getSelectedItem().getText().equals("CATEGORIE DISPONIBILI")) {
				this.buttonSubCategory = new Button("Aggiungi sottocategoria");
				this.subCategoryTextBox = new TextBox();
				this.buttonUpdate = new Button("Modifica categoria");
				this.updateTextBox = new TextBox();
				this.updateTextBox.setText(event.getSelectedItem().getText());
				final HorizontalPanel horizontalPanel2 = new HorizontalPanel();
				horizontalPanel2.add(this.subCategoryTextBox);
				horizontalPanel2.add(this.buttonSubCategory);
				this.buttonSubCategory.addClickHandler(eventClick1 -> {
					final Category newCategory = new Category(this.subCategoryTextBox.getValue());
					final CaffeineOverflowServiceAsync caffeineOverflow1 = GWT.create(CaffeineOverflowService.class);
					caffeineOverflow1.addCategory(newCategory, event.getSelectedItem().getText(),
							new AsyncCallback<Boolean>() {

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
										popup.setWidget(new HTML("Success"));
										popup.center();
										CategoryAdmin.this.verticalPanel.clear();
										final CategoryAdmin categoryAdmin = new CategoryAdmin(
												CategoryAdmin.this.verticalPanel);
										categoryAdmin.onModuleLoad();
									} else {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Error"));
										popup.center();
									}
								}
							});

				});
				final HorizontalPanel horizontalPanel3 = new HorizontalPanel();
				horizontalPanel3.add(this.updateTextBox);
				horizontalPanel3.add(this.buttonUpdate);
				this.buttonUpdate.addClickHandler(eventClick2 -> {
					final CaffeineOverflowServiceAsync caffeineOverflow2 = GWT.create(CaffeineOverflowService.class);
					caffeineOverflow2.renameCategory(event.getSelectedItem().getText(), this.updateTextBox.getValue(),
							new AsyncCallback<Boolean>() {

								@Override
								public void onFailure(Throwable caught) {
									PopupPanel popup = new PopupPanel(true);
									popup.setWidget(new HTML("<font color='red'>Error</font>"));
									popup.center();
								}

								@Override
								public void onSuccess(Boolean result) {
									if (result) {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Success"));
										popup.center();
										CategoryAdmin.this.verticalPanel.clear();
										final CategoryAdmin categoryAdmin = new CategoryAdmin(
												CategoryAdmin.this.verticalPanel);
										categoryAdmin.onModuleLoad();
									} else {
										PopupPanel popup = new PopupPanel(true);
										popup.setWidget(new HTML("Error"));
										popup.center();
									}
								}
							});
				});
				final Grid categoryGridPanel1 = new Grid(2, 2);
				categoryGridPanel1.setWidget(0, 0, this.subCategoryTextBox);
				categoryGridPanel1.setWidget(0, 1, this.buttonSubCategory);
				this.verticalPanel1.add(new HTML("<br>"));
				this.verticalPanel1.add(categoryGridPanel1);
				final Grid categoryGridPanel2 = new Grid(2, 2);
				categoryGridPanel2.setWidget(0, 0, this.updateTextBox);
				categoryGridPanel2.setWidget(0, 1, this.buttonUpdate);
				this.verticalPanel1.add(new HTML("<br>"));
				this.verticalPanel1.add(categoryGridPanel2);
				this.verticalPanel1.add(horizontalPanel2);
				this.verticalPanel1.add(horizontalPanel3);
			}
		});
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(categoryTree);
		this.verticalPanel.add(horizontalPanel);
		this.verticalPanel.add(this.verticalPanel1);
	}
}