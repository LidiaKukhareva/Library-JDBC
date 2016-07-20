package test.by.tr.library.controller;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.controller.Controller;

public class ControllerTest {
	
	@DataProvider(name = "dataProviderForError")
	public Object[][] createData1(){
		return new Object[][]{
			{ "No administrator's rights", "ADD_BOOK_COMMAND"},	
			{ "Logination fail", "LOGINATION_COMMAND"},
			{ "Registration fail", "REGISTER_USER"},	
			{ "Getting catalog fail: no access", "GET_CATALOG"},
			{ "Getting book fail: no access", "FIND_BOOK_BY_TITLE"},
			{ "Getting book fail: no access", "FIND_BOOK_BY_AUTHOR"},
			{ "Getting book fail: no access", "FIND_BOOK_BY_PRICE"},		
			{ "No administrator's rights", "DELETE_USER"},
		};   
	}

	
  @Test(dataProvider = "dataProviderForError")
  public void doActionErrorTest(String message, String error) {
	  Request request = new Request();
	  request.setCommandName(error);
	  Controller controller = new Controller();
	  Response response = controller.doAction(request);
	  Assert.assertEquals(message, response.getErrorMessage());
  }
  
  @DataProvider(name = "dataProvider")
	public Object[][] createData2(){
		return new Object[][]{	
			{ "Registration is OK", "REGISTER_USER"},
			{ "Logination is OK", "LOGINATION_COMMAND"},
			{ "Adding book is OK", "ADD_BOOK_COMMAND"},
			{ "Getting catalog is OK", "GET_CATALOG"},
			{ "Getting book is OK", "FIND_BOOK_BY_TITLE"},
			{ "Getting book is OK", "FIND_BOOK_BY_AUTHOR"},
			{ "Getting book is OK", "FIND_BOOK_BY_PRICE"},
			{ "Book is deleted", "DELETE_BOOK"},
			{ "User is deleted", "DELETE_USER"},
		};   
	}

	
@Test(dataProvider = "dataProvider")
public void doActionTest(String message, String command) {
	  Request request = new Request();
	  request.setCommandName(command);
	  request.setIsAdmin(true);
	  request.setLogin("logintest");
	  request.setPassword("passtest");
	  request.setTitle("tests");
	  request.setAuthor("lk");
	  request.setPrice("10");
	  Controller controller = new Controller();
	  Response response = controller.doAction(request);
	  Assert.assertEquals(message, response.getMessage());
}
}
