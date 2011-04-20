package ch.paso.address.shared.entities;

import java.io.Serializable;

public interface ICodeType extends Serializable{

	public abstract String getCodeTypeName();

	public abstract void setId(Long id);

	public abstract Long getId();

	public abstract String getText();

	public abstract void setActive(boolean active);

	public abstract boolean isActive();

	public abstract String toString();

	public abstract void setText(String text);

}