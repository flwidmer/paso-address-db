package ch.paso.address.client.tables;

import java.util.ArrayList;

import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

public class PersonTablePage extends Composite {

	private CellTable<PersonEntity> m_theTable;

	public PersonTablePage() {
		m_theTable = new PersonTable();

		initWidget(m_theTable);
	}

	public class PersonTable extends CellTable<PersonEntity> {
		public PersonTable() {
			addColumn(new FirstNameColumn());
			addColumn(new LastNameColumn());
			addColumn(new EditButtonColumn());
		}
			public class FirstNameColumn extends Column<PersonEntity, String>{

				public FirstNameColumn() {
					super(new TextCell());
				}

				@Override
				public String getValue(PersonEntity object) {
					return object.getFirstName();
				}
			}
			
			public class LastNameColumn extends Column<PersonEntity, String>{

				public LastNameColumn() {
					super(new TextCell());
				}

				@Override
				public String getValue(PersonEntity object) {
					return object.getLastName();
				}
				
			}
			
			public class EditButtonColumn extends Column<PersonEntity, String>{
				
				public EditButtonColumn() {
					super(new ButtonCell());
					setFieldUpdater(new FieldUpdater<PersonEntity, String>() {

						@Override
						public void update(int index, PersonEntity object,
								String value) {
							PersonForm form = new PersonForm();
							form.startModify();
						}
					});
				}

				@Override
				public String getValue(PersonEntity object) {
					return "Bearbeiten";
				}
				
			}


		
	}
	public void test() {
		ArrayList<PersonEntity> list = new ArrayList<PersonEntity>();
		for (int i = 0; i < 5; i++) {
			PersonEntity t = new PersonEntity();
			t.setFirstName("test " + i);
			t.setLastName("testLast");
			list.add(t);
		}
		m_theTable.setRowData(list);
	}
}