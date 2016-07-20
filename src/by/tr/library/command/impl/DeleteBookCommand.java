package by.tr.library.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class DeleteBookCommand implements Command {
	
	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public Response execute(Request request) throws CommandException {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		
		Response response = new Response();
		try {
			if (request.isAdmin()){
				boolean result = service.deleteBook(request.getTitle(), request.getAuthor());
				if (result){
					response.setErrorMessage(null);
					response.setMessage("Book is deleted");
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
			log.error("ServiceException: method execute, class DeleteBookCommand");
			throw new CommandException("command message", e);
		}
		
		return response;
	}

}

