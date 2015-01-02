package controllers;
import play.Play;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator {
	@Override
	public String getUsername(Http.Context ctx){
		return ctx.session().get("username");
	}
	
	@Override
	public Result onUnauthorized(Http.Context ctx){
		ctx.session().put("url", "GET".equals(ctx.request().method()) ? ctx.request().uri() : Play.application() + "/");
		return redirect(routes.Credential.login());
	}
}
