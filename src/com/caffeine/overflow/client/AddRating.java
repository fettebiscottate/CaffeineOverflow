package com.caffeine.overflow.client;

import com.caffeine.overflow.model.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 */
public class AddRating {

	private VerticalPanel verticalPanel = null;
	private static final String RADIO = "radioButton";
	
	/**
	 * 
	 * @param verticalPanel VerticalPanel
	 */
	public AddRating(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	public void onModuleLoad(final Answer selected) {
		final Menu menu = new Menu(this.verticalPanel, 1);
		menu.onModuleLoad();
		final VerticalPanel verticalPanel1 = new VerticalPanel();
		verticalPanel1.add(new HTML("</br>"));
		final RadioButton radioButton0 = new RadioButton(RADIO, "0");
		final RadioButton radioButton1 = new RadioButton(RADIO, "1");
		final RadioButton radioButton2 = new RadioButton(RADIO, "2");
		final RadioButton radioButton3 = new RadioButton(RADIO, "3");
		final RadioButton radioButton4 = new RadioButton(RADIO, "4");
		final RadioButton radioButton5 = new RadioButton(RADIO, "5");
		verticalPanel1.add(radioButton0);
		verticalPanel1.add(radioButton1);
		verticalPanel1.add(radioButton2);
		verticalPanel1.add(radioButton3);
		verticalPanel1.add(radioButton4);
		verticalPanel1.add(radioButton5);
		final Grid radioGridPanel = new Grid(1, 6);
		radioGridPanel.setWidget(0, 0, radioButton0);
		radioGridPanel.setWidget(0, 1, radioButton1);
		radioGridPanel.setWidget(0, 2, radioButton2);
		radioGridPanel.setWidget(0, 3, radioButton3);
		radioGridPanel.setWidget(0, 4, radioButton4);
		radioGridPanel.setWidget(0, 5, radioButton5);
		verticalPanel1.add(radioGridPanel);
		verticalPanel1.add(new HTML("</br>"));
		final Button voteButton = new Button("Vota");
		verticalPanel1.add(voteButton);
		this.verticalPanel.add(verticalPanel1);
		voteButton.addClickHandler(event -> {
			String rating = "";
			if (radioButton0.getValue()) { 
				rating = radioButton0.getText();
			} 
			if (radioButton1.getValue()) {
				rating = radioButton1.getText();
			} 
			if (radioButton2.getValue()) {
				rating = radioButton2.getText();
			}
			if (radioButton3.getValue()) {
				rating = radioButton3.getText();
			}
			if (radioButton4.getValue()) {
				rating = radioButton4.getText();
			} 
			if (radioButton5.getValue()) {
				rating = radioButton5.getText();
			}
			this.verticalPanel.add(new Label(rating));
			try {
				final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
				caffeineOverflow.createRating(selected, CurrentUser.email, rating, new AsyncCallback<Boolean>() {

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
							popup.setWidget(new HTML("Voto inserito"));
							popup.center();
							AddRating.this.verticalPanel.clear();
							final ShowQuestions showQuestions = new ShowQuestions(AddRating.this.verticalPanel);
							showQuestions.onModuleLoad(null, "");
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
	}
}
