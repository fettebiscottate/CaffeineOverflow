package com.caffeine.overflow.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class consists of a constructor and a method to allow a user to see its
 * informations
 *
 * @author Giacomo Minello
 * @author Matteo Tramontano
 * @author Davide Menetto
 * @version 1.0
 * @see EntryPoint
 */
public class UserProfile implements EntryPoint {

	private VerticalPanel verticalPanel = null;

	/**
	 * Constructor for class UserProfile
	 * 
	 * @param verticalPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	public UserProfile(VerticalPanel verticalPanel) {
		this.verticalPanel = verticalPanel;
	}

	/**
	 * This is the entry point method.
	 * 
	 * @see HTML
	 * @see HorizontalPanel
	 * @see PopupPanel
	 * @see RootPanel
	 * @see VerticalPanel
	 * @since 1.0
	 */
	@Override
	public void onModuleLoad() {
		this.verticalPanel.add(new HTML("UTENTE"));
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		try {
			final CaffeineOverflowServiceAsync caffeineOverflow = GWT.create(CaffeineOverflowService.class);
			caffeineOverflow.getUser(CurrentUser.email, new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("<font color='red'>Error</font>"));
					popup.center();
				}

				@Override
				public void onSuccess(String userInformation) {
					final String[] userData = userInformation.split("\n");
					final StringBuilder bld = new StringBuilder();
					bld.append("<br>");
					bld.append("<h1>Profilo</h1>");
					bld.append(userData[0]);
					bld.append("<br>");
					bld.append(userData[1]);
					bld.append("<br>");
					bld.append(userData[2]);
					bld.append("<br>");
					bld.append(userData[3]);
					bld.append("<br>");
					bld.append(userData[4]);
					bld.append("<br>");
					bld.append(userData[5]);
					bld.append("<br>");
					bld.append(userData[6]);
					bld.append("<br>");
					bld.append(userData[7]);
					bld.append("<br>");
					horizontalPanel.add(new HTML(bld.toString()));
					UserProfile.this.verticalPanel.add(horizontalPanel);
				}
			});
		} catch (final Exception e) {
			PopupPanel popup = new PopupPanel(true);
			popup.setWidget(new HTML(e.toString()));
			popup.center();
		}
		final Menu menu = new Menu(this.verticalPanel, CurrentUser.accountType);
		menu.onModuleLoad();
		RootPanel.get().add(this.verticalPanel);
	}
}