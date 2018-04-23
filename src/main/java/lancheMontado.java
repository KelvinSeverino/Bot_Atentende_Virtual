import java.util.LinkedList;
import java.util.List;

public class lancheMontado {
	private String nome;
	private Double pre�o;
	private List <Ingrediente> ingredientes = new LinkedList <> ();
	
	public lancheMontado(String nome, Double pre�o, List<Ingrediente> ingredientes) {
		super();
		this.nome = nome;
		this.pre�o = pre�o;
		this.ingredientes = ingredientes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
