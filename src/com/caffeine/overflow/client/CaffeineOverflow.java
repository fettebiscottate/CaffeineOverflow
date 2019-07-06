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
		final Menu menu = new Menu(this.verticalPanel, 0);
		menu.onModuleLoad();
		RootPanel.get().add(this.verticalPanel);
	}

}
