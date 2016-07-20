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

public class DeleteUserCommand implements Command {
	
	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public Response execute(Request request) throws CommandException {
		
		String login = request.getLogin();
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		
		Response response = new Response();
		try {
			if (request.isAdmin()){
				boolean result = service.deleteUser(login);
				if (result){
					response.setErrorMessage(null);
					response.setMessage("User is deleted");
				}
				else{
					response.setErrorMessage("Deleting fail");
					response.setMessage(null);
				}
			}
			else{
				response.setErrorMessage("No administrator's rights");
				response.setMessage(null);
			}
		} catch (ServiceException e) {
			log.error("ServiceException: method execute, class DeleteUserCommand");
			throw new CommandException("command message", e);
		}
		
		return response;
	}

}

