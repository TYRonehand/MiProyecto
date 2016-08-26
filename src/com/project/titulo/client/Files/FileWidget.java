package com.project.titulo.client.Files;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.widget.client.TextButton;
import com.project.titulo.client.GoToUrl;
import com.project.titulo.shared.CookieVerify;

public class FileWidget extends Composite {

	//uifields	
	@UiField TextButton uploadBtn;
	@UiField TextButton helpBtn;
	@UiField VerticalPanel panel;
	
	//------------------------------------
	/**
	* A simple data type that represents a contact.
   	*/
	private static class Resume {
      private final String address;
      private final Date birthday;
      private final String name;

      public Resume(String name, Date birthday, String address) {
         this.name = name;
         this.birthday = birthday;
         this.address = address;
      }
   	} 
    //data list
	private List<Resume> CONTACTS =null;
	//Create a CellTable.
	private CellTable<Resume> table = new CellTable<Resume>();
	//cookies
	private CookieVerify mycookie = new CookieVerify(false);
	
	//goto url
	public GoToUrl url = new GoToUrl();
		
	//CREATION
	private static FileWidgetUiBinder uiBinder = GWT.create(FileWidgetUiBinder.class);

	interface FileWidgetUiBinder extends
			UiBinder<Widget, FileWidget> {
	}
	public FileWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		LoadData("ASC");
	}
	

	//load data from database
	private void LoadData(String filter)
	{
		 this.CONTACTS = Arrays.asList(
			      new Resume("John", new Date(80, 4, 12), "123 Fourth Avenue"),
			      new Resume("Joe", new Date(85, 2, 22), "22 Lance Ln"),
			      new Resume("George",new Date(46, 6, 6),"1600 Pennsylvania Avenue")
			   );
		 
		 LoadTable();
	}
	
	//Create data table
	private void LoadTable()
	{
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		table.addStyleName("cellTable");
		table.setSize("100%", "");
		table.setPageStart(0);
		table.setPageSize(8);
		table.setEmptyTableWidget(new Label("No data found!"));
	  
		//simple pager
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(table);
		pager.addStyleName("pagerTable");
	  
	  
		// Add a text column to show the name.
		TextColumn<Resume> nameColumn = new TextColumn<Resume>() {
		     @Override
		     public String getValue(Resume object) {
		        return object.name;
		     }
		};
		table.addColumn(nameColumn, "Id");

	      
		// Add a date column to show the birthday.
		DateCell dateCell = new DateCell();
		Column<Resume, Date> dateColumn = new Column<Resume, Date>(dateCell) {
	         @Override
	         public Date getValue(Resume object) {
	            return object.birthday;
	         }
		};
		table.addColumn(dateColumn, "Title");

	      
		// Add a text column to show the address.
		TextColumn<Resume> addressColumn = new TextColumn<Resume>() {
	         @Override
	         public String getValue(Resume object) {
	            return object.address;
	         }
		};
		table.addColumn(addressColumn, "User");
	      
	      
		// Add a text column to show the address.
		TextColumn<Resume> totalColumn = new TextColumn<Resume>() {
	         @Override
	         public String getValue(Resume object) {
	            return object.address;
	         }
		};
		table.addColumn(totalColumn, "total");
	      
	      
		// Add a text column to show the address.
		TextColumn<Resume> creationColumn = new TextColumn<Resume>() {
	         @Override
	         public String getValue(Resume object) {
	            return object.address;
	         }
		};
		table.addColumn(creationColumn, "Creation");
	      
	      
	      
		// Add a selection model to handle user selection.
		final SingleSelectionModel<Resume> selectionModel = new SingleSelectionModel<Resume>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(
		new SelectionChangeEvent.Handler() 
		{
			public void onSelectionChange(SelectionChangeEvent event) {
				Resume selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected.name);
				}
			}
		});

		// Push the data into the widget.
		table.setRowCount(CONTACTS.size(), true);
		table.setRowData(0, CONTACTS);
		panel.setBorderWidth(0);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		panel.setWidth("100%");
		panel.add(new Label("*push item for options"));
		panel.add(table);
		panel.add(pager);
		
	}
	
	
	//click upload
	@UiHandler("uploadBtn")
	void onRegisteLinkClick(ClickEvent event) 
	{
		url.GoTo("MODALUPLOAD");
	}
	//click Help
	@UiHandler("helpBtn")
	void onHelpBtnClick(ClickEvent event) 
	{
		url.GoTo("MODALHELP");
	}
	
	
}
