/*
 * Created on Apr 18, 2006
 * Updated by JinH on Aug 14, 2018
 * BackEnd - back end class for user authentication system
 */
 

/**
 * @author JinH
 */
public class BackEnd {
    private UsersDAO aUsersDAO = null;          // data access object
    private Filter aFilter = null;              // filter
    
    public BackEnd() {
        aUsersDAO = new UsersDAO();
        aFilter = new Filter();
    }

    public String process(FrontEnd aFrontEnd) {
        String result = null;
		
	aUsersDAO.connect();

	if (aFrontEnd.getUseFilter()) {

		String username = aFrontEnd.getUsername();
		System.out.println("Username before filtering: >" + username + "<");
		username = aFilter.filterMetaChars(username);
		System.out.println("Username after filtering: >" + username + "<");
		aFrontEnd.setUsername(username);

		String password = aFrontEnd.getPassword();
		System.out.println("Password before filtering: >" + password + "<");
		password = aFilter.filterMetaChars(password);
		System.out.println("Password after filtering: >" + password + "<");
		aFrontEnd.setPassword(password);

	}
		
	if (aFrontEnd.getUsePreparedStatement()) {
		aUsersDAO.executeSQLQueryPrepared(aFrontEnd);
	}
	else {
		aUsersDAO.executeSQLQuery(aFrontEnd);
	}
		
	result = aUsersDAO.processResultSet();
	aUsersDAO.disconnect();
	return result;
    }
}
