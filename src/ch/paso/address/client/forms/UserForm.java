package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.UserForm.InformationBox.PasswordField;
import ch.paso.address.client.forms.UserForm.InformationBox.UsernameField;
import ch.paso.address.client.forms.UserForm.PermissionBox.PermissionTableField;
import ch.paso.address.client.forms.fields.AbstractButton;
import ch.paso.address.client.forms.fields.AbstractGroupBox;
import ch.paso.address.client.forms.fields.AbstractSmartField;
import ch.paso.address.client.forms.fields.AbstractTableField;
import ch.paso.address.client.forms.fields.AbstractTextField;
import ch.paso.address.client.services.IPermissionService;
import ch.paso.address.client.services.IPermissionServiceAsync;
import ch.paso.address.client.tables.AbstractTable;
import ch.paso.address.client.tables.columns.AbstractButtonColumn;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.PermissionCodeType;
import ch.paso.address.shared.entities.UserEntity;
import ch.paso.address.shared.permission.IPermission;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class UserForm extends AbstractForm {

	private UsernameField m_usernameField;
	private PasswordField m_passwordField;
	private PermissionTableField m_permissionTableField;
	private Long m_id;

	@Override
	protected List<Widget> getConfiguredFields() {
		ArrayList<Widget> result = new ArrayList<Widget>();
		result.add(new InformationBox());
		result.add(new PermissionBox());
		return result;
	}

	@Override
	protected List<Button> getConfiguredButtons() {
		ArrayList<Button> result = new ArrayList<Button>();
		result.add(new OkButton());
		result.add(new CancelButton());
		return result;
	}

	@Override
	protected String getConfiguredTitle() {
		return "Benutzer";
	}

	public class InformationBox extends AbstractGroupBox {

		@Override
		protected List<Widget> getConfiguredFields() {
			setUsernameField(new UsernameField());
			setPasswordField(new PasswordField());
			ArrayList<Widget> result = new ArrayList<Widget>();
			result.add(getUsernameField());
			result.add(getPasswordField());
			return result;
		}

		@Override
		protected int getConfiguredNumberOfColumns() {
			return 1;
		}

		public class UsernameField extends AbstractTextField {
			@Override
			protected String getConfiguredLabel() {
				return "Username";
			}
		}

		public class PasswordField extends AbstractTextField {
			@Override
			protected String getConfiguredLabel() {
				return "Password";
			}

			@Override
			protected boolean getConfiguredPasswordField() {
				return true;
			}

		}
	}

	public class PermissionBox extends AbstractGroupBox {

		@Override
		protected int getConfiguredNumberOfColumns() {
			return 1;
		}

		@Override
		protected List<Widget> getConfiguredFields() {
			ArrayList<Widget> result = new ArrayList<Widget>();
			setPermissionTableField(new PermissionTableField());
			result.add(getPermissionTableField());
			result.add(new AddPermissionBox());
			return result;
		}

		public class PermissionTableField extends
				AbstractTableField<Permission> {

			@Override
			protected String getConfiguredLabel() {
				return "Berechtigungen";
			}

			@Override
			protected AbstractTable<Permission> getConfiguredTable() {
				return new PermissionTable();
			}

			public void addNewPermission(String permission) {
				Permission p = new Permission();
				p.setName(permission);
				p.setLevel(IPermission.PERMISSION_ALL);
				if (!getTheTable().contains(p)) {
					getTheTable().AddRow(p);
				}
			}

			public class PermissionTable extends AbstractTable<Permission> {


				public class NameColumn extends
						AbstractStringColumn<Permission> {

					@Override
					public String getValue(Permission object) {
						return object.getName();
					}

				}

				public class DeleteColumn extends
						AbstractButtonColumn<Permission> {

					@Override
					protected void execOnClick(int index, Permission object,
							String value) {
						getTheTable().removeRow(index);
					}

					@Override
					protected String getConfiguredLabel() {
						return "Entfernen";
					}
				}

				@Override
				protected List<AbstractColumn<Permission, ?>> getConfiguredColumns() {
					ArrayList<AbstractColumn<Permission, ?>> result = new ArrayList<AbstractColumn<Permission, ?>>();
					result.add(new NameColumn());
					result.add(new DeleteColumn());
					return result;
				}

			}

			public void fillPermissionTable(List<Permission> values) {
				getTheTable().addRows(values);
			}

			public List<Permission> getPermissionTableList() {
				return getTheTable().getRowsInternal();
			}
		}

		public class AddPermissionBox extends AbstractGroupBox {
			public class PermissionChooser extends
					AbstractSmartField<PermissionCodeType> {

				@Override
				protected PermissionCodeType getConfiguredPrototype() {
					return new PermissionCodeType();
				}

			}

			private PermissionChooser m_permissionChooser;

			public class AddPermissionButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return "Hinzufügen";
				}

				@Override
				protected String getConfiguredWidth() {
					return "100px";
				}

				@Override
				protected ClickHandler getConfiguredClickHandler() {
					return new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							if (!getPermissionChooser().getValueString()
									.isEmpty())
								getPermissionTableField()
										.addNewPermission(
												getPermissionChooser()
														.getValueString());
						}
					};
				}
			}

			@Override
			protected List<Widget> getConfiguredFields() {
				ArrayList<Widget> result = new ArrayList<Widget>();
				m_permissionChooser = new PermissionChooser();
				result.add(getPermissionChooser());
				result.add(new AddPermissionButton());
				return result;
			}

			public PermissionChooser getPermissionChooser() {
				return m_permissionChooser;
			}
		}
	}

	public class NewHandler implements IHandler {

		@Override
		public void execStore() {
			IPermissionServiceAsync svc = GWT.create(IPermissionService.class);
			svc.storeUser(exportFormData(), new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ErrorHandler.handleError("Error Storing User", caught);
				}

				@Override
				public void onSuccess(Void result) {
					hide();
				}
			});
		}

		@Override
		public void execLoad() {
			// Do nothing
		}

	}

	public void startNew() {
		startHandler(new NewHandler());
	}

	public class ModifyHandler implements IHandler {

		@Override
		public void execStore() {
			IPermissionServiceAsync svc = GWT.create(IPermissionService.class);
			svc.storeUser(exportFormData(), new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					hide();
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorHandler.handleError("Error storing user", caught);

				}
			});

		}

		@Override
		public void execLoad() {
			IPermissionServiceAsync svc = GWT.create(IPermissionService.class);
			svc.loadUserEntity(getId(), new AsyncCallback<UserEntity>() {

				@Override
				public void onSuccess(UserEntity result) {
					importFormData(result);
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorHandler.handleError("Error loading user", caught);
				}
			});
		}

	}

	public void startModify() {
		startHandler(new ModifyHandler());
	}

	private UserEntity exportFormData() {
		UserEntity u = new UserEntity();
		u.setUserName(getUsernameField().getValue());
		u.setPassword(getPasswordField().getValue());
		u.setPermissions(getPermissionTableField().getPermissionTableList());
		u.setId(getId());
		return u;
	}

	private void importFormData(UserEntity u) {
		getUsernameField().setValue((u.getUserName()));
		getPasswordField().setValue((u.getPassword()));
		getPermissionTableField().fillPermissionTable(u.getPermissions());
		setId(u.getId());
	}

	public void setUsernameField(UsernameField usernameField) {
		m_usernameField = usernameField;
	}

	public UsernameField getUsernameField() {
		return m_usernameField;
	}

	public void setPasswordField(PasswordField passwordField) {
		m_passwordField = passwordField;
	}

	public PasswordField getPasswordField() {
		return m_passwordField;
	}

	public void setPermissionTableField(
			PermissionTableField permissionTableField) {
		m_permissionTableField = permissionTableField;
	}

	public PermissionTableField getPermissionTableField() {
		return m_permissionTableField;
	}

	public void setId(Long id) {
		m_id = id;
	}

	public Long getId() {
		return m_id;
	}
}
