import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lanche {
	
	private String name;
	private Double preço;
	private List <Ingrediente> ingredientes = new LinkedList <> ();

	public Lanche(String name, Double preço, List<Ingrediente> list) {
		super();
		this.name = name;
		this.preço = preço;
		this.ingredientes = (List<Ingrediente>) list;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getPreço() {
		return preço;
	}

	public void setPreço(Double preço) {
		this.preço = preço;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	
}
