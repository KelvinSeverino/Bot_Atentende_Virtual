

public class Main {

	private static Model model;
	
	public static void main(String[] args) {

		model = Model.getInstance();
		initializeModel(model);
		View view = new View(model);
		model.registerObserver(view); //connection Model -> View
		view.receiveUsersMessages();

	}
	
	public static void initializeModel(Model model){
		model.addIngrediente(new Ingrediente ("Cebola", 0.25));
		model.addIngrediente(new Ingrediente ("Hamburguer", 0.50));
		model.addIngrediente(new Ingrediente ("Cheddar", 0.75));
		model.addIngrediente(new Ingrediente ("Tomate", 0.30));
		model.addIngrediente(new Ingrediente ("Alface", 0.20));
		model.addIngrediente(new Ingrediente ("Queijo", 0.65));
		model.addLanche(new Lanche("X-r",5.0 , model.getIngredientes()));
		model.getIngreLanches().add(model.getIngredientes().get(0));
		model.getIngreLanches().add(model.getIngredientes().get(1));
		model.getIngreLanches().add(model.getIngredientes().get(2));
		model.addLanche(new Lanche("X-Foda-se",99.0,model.getIngreLanches()));
		model.getIngreLanches().clear();
	}
}
