package by.tr.library.command.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tr.library.bean.Book;
import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class FindBookByTitleCommand implements Command{

	private final static Logger log = LogManager.getRootLogger();
	
	@Override
	public Response execute(Request request) throws CommandException{
		
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		ClientService clService = factory.getClientService();
		
		Response response = new Response();
		
		try {
			boolean valid = clService.logination(request.getLogin(), request.getPassword());
			if (valid){
				Book book = service.findBookByTitle(request.getTitle());
				if (book != null){
					response.setMessage("Getting book is OK");
				}
				else{
					response.setMessage("No such book");
				}
				response.setBook(book);
				response.setErrorMessage(null);
			}
			else{
				response.setErrorMessage("Getting book fail: no access");
			}
				
		} catch (ServiceException e) {
			log.error("ServiceException: method execute, class FindBookByTitleCommand");
			throw new CommandException("command message", e);
		}
		return response;
	}

}

