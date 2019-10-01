/*
 * Created on Apr 18, 2006
 * Updated by JinH on Aug 14, 2018
 * 
 * FrontEnd - class to represent web system front-end for user-based authentication system
 */
 
/**
 * @author JinH
 */
public class FrontEnd {
	private String username;				// username parameter
	private String password;				// password parameter
	private boolean usePreparedStatement;	// whether to use prepared statement
	private boolean useFilter;				// whether to use metacharater filtering on u/p
	private BackEnd aBackEnd;				// reference to back end
	
	public FrontEnd () {
		// nothing right now
	}
	
	public FrontEnd (BackEnd aBackEnd) {
		this.aBackEnd = aBackEnd;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean getUsePreparedStatement() {
		return usePreparedStatement;
	}

	public boolean getUseFilter() {
		return useFilter;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsePreparedStatement (boolean usePrepared) {
		this.usePreparedStatement = usePrepared;
	}
	
	public void setUseFilter (boolean useFilter) {
		this.useFilter = useFilter;
	}

	public String processInput(String username, String password, boolean usePrepared, 
								boolean useFilter) {
		String result = null;

		setUsername(username);
		setPassword(password);
		setUsePreparedStatement(usePrepared);
		setUseFilter(useFilter);
		
		result = aBackEnd.process(this);
		
		return result;
	}
}