package backend.main.java.models;

public class User {
	private int id;
	private String mail;
	private String firstname;
	private String lastname;
	private boolean newsletter;
	private String salutation;
	private String title;
	private String profilePicture;
	private String description;
	private String email;
	private String password;

	public User(){}

	public User(int id, String email, String firstname, String lastname, boolean newsletter, String salutation, String title, String profilePicture, String description ){
		this.id=id;
		this.email=email;
		this.firstname=firstname;
		this.lastname=lastname;
		this.newsletter=newsletter;
		this.salutation=salutation;
		this.title=title;
		this.profilePicture=profilePicture;
		this.description=description;
	}

	public static User getRandomUser() {
		return new User(1, "mail@mail.com", "Max", "Mustermann", true, "Hi, my name is Max", "Dr.", "/picture", "description" );
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
