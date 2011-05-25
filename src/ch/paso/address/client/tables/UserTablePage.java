package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.UserForm;
import ch.paso.address.client.services.IPermissionService;
import ch.paso.address.client.services.IPermissionServiceAsync;
import ch.paso.address.client.tables.columns.AbstractButtonColumn;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.UserEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public class UserTablePage extends AbstractTablePage<UserEntity> {

	public UserTablePage() {
		super();
	}

	@Override
	protected boolean getConfiguredNewButtonVisible() {
		return true;
	}

	protected void execNewClick() {
		UserForm form = new UserForm();
		form.addCloseHandler(new CloseHandler<PopupPanel>() {

			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				reload();
			}
		});
		form.startNew();
	}

	public class UserTable extends AbstractTable<UserEntity> {

		@Override
		protected List<AbstractColumn<UserEntity, ?>> getConfiguredColumns() {
			ArrayList<AbstractColumn<UserEntity, ?>> result = new ArrayList<AbstractColumn<UserEntity, ?>>();
			result.add(new UserNameColumn());
			result.add(new EditButtonColumn());
			result.add(new DeleteButtonColumn());
			return result;
		}

		public class UserNameColumn extends AbstractStringColumn<UserEntity> {

			@Override
			public String getValue(UserEntity object) {
				return object.getUserName();
			}

			@Override
			public String getConfiguredTitle() {
				return "Username";
			}

		}

		public class EditButtonColumn extends AbstractButtonColumn<UserEntity> {

			@Override
			protected void execOnClick(int index, UserEntity object,
					String value) {
				UserForm form = new UserForm();
				form.setId(object.getId());
				form.addCloseHandler(new CloseHandler<PopupPanel>() {

					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						reload();
					}
				});
				form.startModify();

			}

			@Override
			public String getValue(UserEntity object) {
				return "Bearbeiten";
			}

		}

		public class DeleteButtonColumn extends
				AbstractButtonColumn<UserEntity> {

			@Override
			protected void execOnClick(int index, UserEntity object,
					String value) {
				IPermissionServiceAsync svc = GWT
						.create(IPermissionService.class);
				svc.deleteUser(object.getId(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						ErrorHandler.handleError("Error deleting", caught);
					}

					@Override
					public void onSuccess(Void result) {
						reload();
					}
				});
			}

			@Override
			public String getValue(UserEntity object) {
				return "Löschen";
			}
		}
	}

	@Override
	protected CellTable<UserEntity> getConfiguredTable() {
		return new UserTable();
	}

	@Override
	protected void reload() {
		IPermissionServiceAsync svc = GWT.create(IPermissionService.class);
		svc.getAllUsers(new AsyncCallback<List<UserEntity>>() {

			@Override
			public void onSuccess(List<UserEntity> result) {
				getTheTable().setRowData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorHandler.handleError("Error during reloading", caught);
			}
		});
	}
}
