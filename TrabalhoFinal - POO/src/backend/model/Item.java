package backend.model;

import java.io.Serializable;

public interface Item extends Serializable {
	public String getNome();
	
	public String getClassName();

	
	public void setNome(String nome);
	
	@Override
	public String toString();
}
