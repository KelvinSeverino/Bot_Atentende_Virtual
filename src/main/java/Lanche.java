import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lanche {
	
	private String name;
	private Double pre�o;
	private List <Ingrediente> ingredientes = new LinkedList <> ();

	public Lanche(String name, Double pre�o, List<Ingrediente> list) {
		super();
		this.name = name;
		this.pre�o = pre�o;
		this.ingredientes = (List<Ingrediente>) list;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getPre�o() {
		return pre�o;
	}

	public void setPre�o(Double pre�o) {
		this.pre�o = pre�o;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	
}
