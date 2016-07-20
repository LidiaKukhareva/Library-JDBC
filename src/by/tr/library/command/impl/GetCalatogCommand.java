package by.tr.library.command.impl;

import java.util.ArrayList;

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

public class GetCalatogCommand implements Command{

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
				ArrayList<Book> books = service.getCatalog();
				response.setErrorMessage(null);
				response.setMessage("Getting catalog is OK");
				response.setListBook(books);
			}
			else{
				response.setErrorMessage("Getting catalog fail: no access");
				response.setListBook(null);
			}
			
		} catch (ServiceException e) {
			log.error("ServiceException: method execute, class GetCatalogCommand");
			throw new CommandException("command message", e);
		}
			
		return response;
	}

}
