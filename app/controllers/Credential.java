package controllers;
import play.mvc.Controller;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import views.html.*;

public class Credential extends Controller {

	public static Result login(){
		return ok(login.render());
	}
	
	public static class Login{	 
		@Required
		public String password;
		@Required
		public String username;
		
		public String validate(){
			if(!username.equals("admin") || !password.equals("123")){
				return "Ungueltiger Benutzer oder ungueltiges Passwort";
			}
			return null;
		}
	}
	
	public static Result authenticate(){
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if(loginForm.hasErrors()){
			return unauthorized(login.render());
		} 
		else {
			String url = session().get("url");
			session().clear();
			session("username",loginForm.get().username);
			if(url == null || url.isEmpty()){	
				return redirect(routes.Application.index());
			}
			else{
				return redirect(url);
			}
		}
	}
	
	public static Result logout(){
		session().clear();
		flash("success","Sie haben sich ausgeloggt");
		return redirect(routes.Credential.login());
	}
	
	
}
