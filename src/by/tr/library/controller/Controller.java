package by.tr.library.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;

public class Controller {
	
	private final CommandHelper helper = new CommandHelper();
	private final static Logger log = LogManager.getRootLogger();
	public Controller(){}
	
	
	public Response doAction(Request request){
		
		String commandName = request.getCommandName();
		Command command = helper.getCommand(commandName);
		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			response = new Response();
			response.setErrorMessage("Error!!!");
			log.error("CommandException: method doAction, class Controller");
		}
		return response;
		
		
	}

}
