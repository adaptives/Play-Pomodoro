package controllers;

import play.mvc.With;

@Check("admin")
@With(Secure.class)
public class UserConfirmations extends CRUD {

}
