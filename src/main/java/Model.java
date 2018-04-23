import java.net.PasswordAuthentication;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Model implements Subject{
	
	private List<Observer> observers = new LinkedList<Observer>();
	private List<Ingrediente> ingreLanches = new LinkedList<Ingrediente>();
	private List<Lanche> lanches = new LinkedList<Lanche>();
	private List<Ingrediente> ingredientes = new LinkedList<Ingrediente>();
	private List<Lanche> lanchesComprados = new LinkedList<Lanche>();
	
	private static Model uniqueInstance;
	SendResponse sendResponse;
	private  View view;
	
	private Model(){}
	
	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyObservers(long chatId, String studentsData){
		for(Observer observer:observers){
			observer.update(chatId, studentsData);
		}
	}
	
	public void addLanche(Lanche lanche){
		this.lanches.add(lanche);
	}
	
	public void addIngrediente(Ingrediente ingrediente){
		this.ingredientes.add(ingrediente);
	}
	
	public void searchLanche(Update update){
		boolean achei = false;
		for (Lanche lanche : lanches) {
			if (lanche.getName().equals(update.message().text())) {
				this.notifyObservers(update.message().chat().id(), "Ingrediente adicionado com sucesso !");
				lanchesComprados.add(lanche);
				achei = true;
				break;
			}
		}
		if (achei == false) {
			new SendMessage(update.message().chat().id(), "Lanche não cadastrado!");
		}
		this.notifyObservers(update.message().chat().id(), "Digite 'Comprar' para adicionar mais ingrediente ao lanche.");
	}
	
	public void searchIngredientes(Update update) {
		boolean achei = false;
		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getName().equals(update.message().text())) {
				this.notifyObservers(update.message().chat().id(), "Achei");
				ingreLanches.add(ingrediente);
				achei = true;
				break;
			}
		}
		if (achei == false) {
			new SendMessage(update.message().chat().id(), "Ingrediente não cadastrado !");
		}
		this.notifyObservers(update.message().chat().id(), "Digite 'Montar' para adicionar mais ingrediente ao lanche.");
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<Ingrediente> getIngreLanches() {
		return ingreLanches;
	}

	public void setIngreLanches(List<Ingrediente> ingreLanches) {
		this.ingreLanches = ingreLanches;
	}

	public List<Lanche> getLanchesComprados() {
		return lanchesComprados;
	}

	public void setLanchesComprados(List<Lanche> lanchesComprados) {
		this.lanchesComprados = lanchesComprados;
	}
	
	

	
	
	
	
	
	
	

	

}
