package com.app.sample.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ExceptionPopup extends PopupPanel {

  private static ExceptionPopupUiBinder uiBinder = GWT.create(ExceptionPopupUiBinder.class);

  interface ExceptionPopupUiBinder extends UiBinder<Widget, ExceptionPopup> {
  }
  
  @UiField InlineLabel title;
  @UiField InlineLabel message;

  public ExceptionPopup(String titleString, String msg) {
    super(true, true);
    setWidget(uiBinder.createAndBindUi(this));
    setGlassEnabled(true);
    title.setText(titleString);
    message.setText(msg);
    
  }


}
