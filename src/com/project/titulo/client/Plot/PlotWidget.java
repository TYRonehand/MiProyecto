package com.project.titulo.client.Plot;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.project.titulo.client.GoToUrl;
import com.project.titulo.client.ServerService;
import com.project.titulo.client.ServerServiceAsync;
import com.project.titulo.shared.ErrorVerify;
import com.project.titulo.shared.model.UserFile;

public class PlotWidget extends Composite {

	private String IDUSER=null;
	
	//uifields 
	@UiField VerticalPanel panel;
	@UiField VerticalPanel gridItems;
	@UiField VerticalPanel formItems;
	@UiField SimpleCheckBox linesGrid;	
	@UiField SimpleCheckBox pointsGrid;	
	//------------------------------------
	
	
	//Create a CellTable.
	private CellTable<UserFile> table = null;
	//goto url
	public GoToUrl url = new GoToUrl();
	//RPC
	private final ServerServiceAsync serverService = GWT.create(ServerService.class);
	

	//------------------------------------
	//creation binder
	private static PlotWidgetUiBinder uiBinder = GWT.create(PlotWidgetUiBinder.class);
	//binder widget
	interface PlotWidgetUiBinder extends UiBinder<Widget, PlotWidget> {
	}

	//------------------------------------
	
	//initializator
	public PlotWidget(String iduser) 
	{
		//get id user
		this.IDUSER=iduser;
		//properties
		initWidget(uiBinder.createAndBindUi(this));
		formItems.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		gridItems.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		
		//table
		LoadFilesData();
	}
	
	//load data from database
	private void LoadFilesData()
	{
		serverService.getUserFiles(this.IDUSER, new AsyncCallback<List<UserFile>>(){

			@Override
			public void onFailure(Throwable caught) {
				ErrorVerify.getErrorAlert("offline");
			}

			@Override
			public void onSuccess(List<UserFile> result) {
				LoadTable(result);
			}});
	}
	
	//Create data table
	private void LoadTable(List<UserFile> DATAINFO)
	{
		panel.clear();
		table = new CellTable<UserFile>();
		
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		table.addStyleName("cellTable");
		table.setSize("100%", "25px");
		table.setPageStart(0);
		table.setPageSize(8);
		table.setEmptyTableWidget(new Label("No data found!"));
	  
		//simple pager
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(table);
		pager.addStyleName("pagerTable");
		
		// Add a text column to show the name.
		TextColumn<UserFile> nameColumn = new TextColumn<UserFile>() {
		     @Override
		     public String getValue(UserFile object) {
		        return object.getTitle();
		     }
		};
		table.addColumn(nameColumn, "Name");

		// Add a text column to show the name.
		TextColumn<UserFile> creationColumn = new TextColumn<UserFile>() {
		     @Override
		     public String getValue(UserFile object) {
		        return object.getCreation();
		     }
		};
		table.addColumn(creationColumn, "Added");
	      
		// Add a selection model to handle user selection.
		final SingleSelectionModel<UserFile> selectionModel = new SingleSelectionModel<UserFile>();
			table.setSelectionModel(selectionModel);
			selectionModel.addSelectionChangeHandler(
			new SelectionChangeEvent.Handler() 
			{
				public void onSelectionChange(SelectionChangeEvent event) {
					UserFile selected = selectionModel.getSelectedObject();
					if (selected != null) {
						Window.alert("You selected: " + selected.getIddatafile());
					}
				}
			});

		// Push the data into the widget.
		table.setRowCount(DATAINFO.size(), true);
		table.setRowData(0, DATAINFO);
		panel.setBorderWidth(0);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		panel.setWidth("350");
		panel.add(new Label("*push item for options"));
		panel.add(table);
		panel.add(pager);
		Button reload = new Button("Reload Table");
		reload.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				 LoadFilesData();
			}}
		);
		panel.add(reload);
		

		
		
	}

}
