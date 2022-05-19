package backend.main.java.models;

public class Commentary {

	private int id;
	private String commentText;
	private int userId;
	private String firstname;
	private String lastname;
	private String profilePicture;


	public Commentary(){}

	public Commentary(int id, String commentText, int userId, String firstname, String lastname, String profilePicture)
	{
		this.id = id;
		this.commentText = commentText;
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.profilePicture = profilePicture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}
