import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer{

	
	TelegramBot bot = TelegramBotAdapter.build("441684592:AAEZnUA2CUeJXfPWfpWaGIhPq6hXEyUs4wQ");
	

	//Object that receives messages
	GetUpdatesResponse updatesResponse;
	//Object that send responses
	SendResponse sendResponse;
	//Object that manage chat actions like "typing action"
	BaseResponse baseResponse;
			
	
	int queuesIndex=0;
	
	ControllerSearch controllerSearch; //Strategy Pattern -- connection View -> Controller
	
	boolean searchBehaviour = false;
	
	private Model model;
	
	public View(Model model){
		this.model = model; 
	}
	
	public void setControllerSearch(ControllerSearch controllerSearch){ //Strategy Pattern
		this.controllerSearch = controllerSearch;
	}
	
	
	String resposta;
	public void receiveUsersMessages() {
				//infinity loop
		while (true){
			//taking the Queue of Messages
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			
			//Queue of messages
			List<Update> updates = updatesResponse.updates();

			//taking each message in the Queue
			for (Update update : updates) {
				//updating queue's index
				queuesIndex = update.updateId()+1;
				resposta = update.message().text();
				
				if(this.searchBehaviour==true){
					this.callController(update);
					
				} else if(resposta.equals("Montar")){
					setControllerSearch(new ControllerSearchIngrediente(model, this));
					int cont = 0;
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
							"Essas são as opções de ingredientes que você pode incrementar no seu lanche: "));
					for (Ingrediente ingrediente : model.getIngredientes()) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
								cont + "-)Ingrediente:" + ingrediente.getName() + "\nPreço: R$" + ingrediente.getPreco()));
						cont++;
					}
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
							"Digite o nome do respectivo ingrediente que quer adicionar ao lanche: "));
					this.searchBehaviour = true;
					
				} else if(resposta.equals("Comprar")){
					setControllerSearch(new ControllerSearchLanche(model, this));
					int cont = 0;
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
							"Essas são as opções de ingredientes que você pode incrementar no seu lanche: "));
					for (Lanche lanche : model.getLanchesComprados()) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
								cont + "-)Nome:" + lanche.getName() + "\nPreço: R$" + lanche.getName()));
						for (Ingrediente ingre : lanche.getIngredientes()) {
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Ingrediente:" +ingre.getName()));
						}
						cont++;
					}
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
							"Digite o nome do respectivo ingrediente que quer adicionar ao lanche: "));
					this.searchBehaviour = true;
				} else if (resposta.equals("Valor")) {
					double valor = 0;
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Os ingredientes do seu lanche são: "));
					for (Ingrediente ingrediente : model.getIngreLanches()) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Ingrediente" +ingrediente.getName()+ "\nPreço:R$" +ingrediente.getPreco()));
						valor += ingrediente.getPreco();
					}
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "O preço total é: " +valor));
				} else {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Opção invalida."));
				}	
			}
		}
	}
	
	public void callController(Update update){
		this.controllerSearch.search(update);
	}
	
	public void update(long chatId, String studentsData){
		sendResponse = bot.execute(new SendMessage(chatId, studentsData));
		this.searchBehaviour = false;
	}
	
	public void sendTypingMessage(Update update){
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}
	

}