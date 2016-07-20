package by.tr.library.start;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.controller.Controller;

public class Main {

	public static void main(String[] args){
		Controller controller = new Controller();
		
		Request request1 = new Request();
		request1.setCommandName("ADD_BOOK_COMMAND");
		request1.setIsAdmin(true);
		request1.setTitle("Goosy");
		request1.setAuthor("Boos");
		request1.setPrice("120");
		request1.setLogin("pol");
		request1.setPassword("rol");
		
		Response response1 = controller.doAction(request1);
		
		if(response1.getErrorMessage() != null){
			System.out.println(response1.getErrorMessage());
		}else{
			System.out.println(response1.getMessage());
		}
		response1.printListBook();
	}

}
