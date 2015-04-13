package model;

public class Authenticator {

	public static Session authenticateUser(User user) throws Exception{
		Session session = null;
		String email = user.getEmail();
		if(email.equals("TomJones@email.com")){
			session = new Session(user, Role.ProdManager);
		}else if(email.equals("SueSmith@email.com")){
			session = new Session(user, Role.InvManager);
		}else if(email.equals("RagnarNelson@email.com")){
			session = new Session(user, Role.Admin);
		}else
			throw new Exception("Invalid User");
		return session;
	}
	
	enum Role {
		Admin,ProdManager,InvManager
	}
}
