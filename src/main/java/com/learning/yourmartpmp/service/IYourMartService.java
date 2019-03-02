package com.learning.yourmartpmp.service;

import com.learning.yourmartpmp.dto.LoginCredentials;
import com.learning.yourmartpmp.dto.ResponseDataCredentialsDto;

/**
 * Interface for your mart admin service.
 * 
 * @author ayushsaxena
 *
 */
public interface IYourMartService {
	/**
	 * Method to fetch all entities.
	 * 
	 * @param type
	 * @param url
	 * @param keyword
	 * @param searchAlias
	 * @param sort
	 * @param ref
	 * @return
	 */
	ResponseDataCredentialsDto getAll(String type, String url, String keyword, String searchAlias, String sort,
			String ref);

	/**
	 * Method to authenticate entity.
	 * 
	 * @param credentials
	 * @return
	 */
	ResponseDataCredentialsDto authenticateAdmin(LoginCredentials credentials);

	/**
	 * Method to fetch entities.
	 * 
	 * @param url
	 * @param id
	 * @return
	 */
	ResponseDataCredentialsDto getEntity(String url, int id);

	/**
	 * Method to update entities.
	 * 
	 * @param requestForType
	 * @param entity
	 * @param token
	 * @return
	 */
	ResponseDataCredentialsDto updateEntity(String requestForType, Object entity, String token);

	/**
	 * Method to add entities.
	 * 
	 * @param entity
	 * @param token
	 * @return
	 */
	ResponseDataCredentialsDto addEntity(Object entity, String token);

	/**
	 * Method to delete entities.
	 * 
	 * @param id
	 * @param token
	 * @return
	 */
	ResponseDataCredentialsDto deleteEntity(int id, String token);

	/**
	 * Method to handle approval of entities.
	 * 
	 * @param string
	 * @param products
	 * @param token
	 */
	void handleApproval(String string, String products, String token);
}
