package backend.main.java.models;

public class Contact
{
	private String firstname;
	private String lastname;
	private String mail;
	private String message;

	public Contact()
	{	}

	public Contact(String firstname, String lastname, String mail, String message)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
		this.message = message;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
