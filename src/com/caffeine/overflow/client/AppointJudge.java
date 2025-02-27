package com.caffeine.overflow.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class consists of a constructor and some methods to allow the admin to
 * appoint a judge
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class AppointJudge {

	private VerticalPanel verticalPanel = null;
	public ListBox usersListBox = new ListBox();

	/**
	 * Constructor for class AppointJudge
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public AppointJudge(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This method show the list of available users to appoint.
	 */
	public void getUsers() {
		try {
			final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
			caffeineOverflow.getUsers(new AsyncCallback<List<String>>() {

				@Override
				public void onFailure(Throwable caught) {
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("<font color='red'>Error</font>"));
					popup.center();
				}

				@Override
				public void onSuccess(List<String> response) {
					for (int currentResponse = 0; currentResponse < response.size(); currentResponse++) {
						AppointJudge.this.usersListBox.addItem(response.get(currentResponse));
					}
				}
			});
		} catch (final Exception e) {
			PopupPanel popup = new PopupPanel(true);
			popup.setWidget(new HTML(e.toString()));
			popup.center();
		}
	}

	/**
	 * This is the initialization method of the module.
	 */
	public void init() {
		this.getUsers();
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see Menu
	 * @see VerticalPanel
	 * @see ListBox
	 * @see Grid
	 * @see Button
	 * @see CaffeineOverflowServiceAsync
	 * @see PopupPanel
	 * @since 1.0
	 */
	public void onModuleLoad() {

		this.init();
		final Menu menu = new Menu(this.verticalPanel, 2);
		menu.onModuleLoad();
		this.verticalPanel.add(this.usersListBox);
		final Grid usersSelectionGridPanel = new Grid(2, 2);
		usersSelectionGridPanel.setWidget(1, 1, this.usersListBox);
		this.verticalPanel.add(usersSelectionGridPanel);
		final Button appointButton = new Button("Nomina");
		appointButton.addClickHandler(event -> {
			final String email = (this.usersListBox.getItemText(this.usersListBox.getSelectedIndex()).split(":"))[0]
					.trim();
			try {
				final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
				caffeineOverflow.appoint(email, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("<font color='red'>Error</font>"));
						popup.center();
					}

					@Override
					public void onSuccess(String response) {
						PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("Success"));
						popup.center();
						AppointJudge.this.usersListBox.removeItem(AppointJudge.this.usersListBox.getSelectedIndex());
					}
				});
			} catch (final Exception e) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML(e.toString()));
				popup.center();
			}
		});

		this.verticalPanel.add(appointButton);
	}
}
