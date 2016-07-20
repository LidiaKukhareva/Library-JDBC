package by.tr.library.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class RegisterUserCommand implements Command {

	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public Response execute(Request request) throws CommandException {
		
		String login = request.getLogin();
		String password = request.getPassword();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		
		Response response = new Response();
		try {
			boolean result = service.registration(login, password);
			if (result){
				response.setErrorMessage(null);
				response.setMessage("Registration is OK");
			}
			else{
				response.setErrorMessage("Registration fail");
				response.setMessage(null);
			}
		} catch (ServiceException e) {
			log.error("ServiceException: method execute, class RegisterUserCommand");
			throw new CommandException("command message", e);
		}
		
		return response;
	}

}
