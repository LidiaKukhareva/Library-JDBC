package by.tr.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.tr.library.command.Command;
import by.tr.library.command.impl.AddBookCommand;
import by.tr.library.command.impl.DeleteBookCommand;
import by.tr.library.command.impl.LoginationCommand;
import by.tr.library.command.impl.DeleteUserCommand;
import by.tr.library.command.impl.FindBookByAuthorCommand;
import by.tr.library.command.impl.FindBookByPriceCommand;
import by.tr.library.command.impl.FindBookByTitleCommand;
import by.tr.library.command.impl.GetCalatogCommand;
import by.tr.library.command.impl.RegisterUserCommand;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper(){
		commands.put(CommandName.LOGINATION_COMMAND, new LoginationCommand());
		commands.put(CommandName.ADD_BOOK_COMMAND, new AddBookCommand());
		commands.put(CommandName.REGISTER_USER, new RegisterUserCommand());
		commands.put(CommandName.GET_CATALOG, new GetCalatogCommand());
		commands.put(CommandName.FIND_BOOK_BY_TITLE, new FindBookByTitleCommand());
		commands.put(CommandName.FIND_BOOK_BY_AUTHOR, new FindBookByAuthorCommand());
		commands.put(CommandName.FIND_BOOK_BY_PRICE, new FindBookByPriceCommand());
		commands.put(CommandName.DELETE_USER, new DeleteUserCommand());
		commands.put(CommandName.DELETE_BOOK, new DeleteBookCommand());
	}
	
	public Command getCommand(String commandName){//"REGISTER_USER"
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	

}
