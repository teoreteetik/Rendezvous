package ee.teoreteetik.tt.dao;

import ee.teoreteetik.tt.model.User;

public interface UserDAO{
	
	User loadById(Long id);
	
	
}
