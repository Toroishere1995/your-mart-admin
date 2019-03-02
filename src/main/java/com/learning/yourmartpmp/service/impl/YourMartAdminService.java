package com.learning.yourmartpmp.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.yourmartpmp.constants.Constants;
import com.learning.yourmartpmp.dto.CategoryDetails;
import com.learning.yourmartpmp.dto.LoginCredentials;
import com.learning.yourmartpmp.dto.ProductDetails;
import com.learning.yourmartpmp.dto.ResponseDataCredentialsDto;
import com.learning.yourmartpmp.dto.SellerDetails;
import com.learning.yourmartpmp.service.IYourMartService;
import com.sun.corba.se.spi.orb.StringPair;

/**
 * Service class that interacts with api and handles business.
 * 
 * @author ayushsaxena
 *
 */
public class YourMartAdminService implements IYourMartService {

	@Override
	public ResponseDataCredentialsDto getAll(String type, String url, String keyword, String searchAlias, String sort,
			String ref) {
		// TODO Auto-generated method stub

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		if (checkParameterValidity(keyword)) {
			builder.queryParam("keyword", keyword);
		}
		if (checkParameterValidity(searchAlias)&& !searchAlias.equalsIgnoreCase("All")) {
			builder.queryParam("search-alias", searchAlias);
		}
		if (checkParameterValidity(sort)) {
			builder.queryParam("sort", sort);
		}
		if (checkParameterValidity(ref)) {
			builder.queryParam("ref", ref);
		}
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseDataCredentialsDto> rateResponse = restTemplate.exchange(builder.toUriString(),
				HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
				});
		ResponseDataCredentialsDto responseDto = rateResponse.getBody();

		return responseDto;
	}

	@Override
	public ResponseDataCredentialsDto authenticateAdmin(LoginCredentials credentials) {
		// TODO Auto-generated method stub
		String url = Constants.URL_PATH + "login";
		credentials.setAdmin("true");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<LoginCredentials> entity = new HttpEntity<>(credentials, getHeaders(null));
		ResponseEntity<ResponseDataCredentialsDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
				ResponseDataCredentialsDto.class);
		ResponseDataCredentialsDto responseDto = responseEntity.getBody();
		return responseDto;
	}

	@Override
	public ResponseDataCredentialsDto getEntity(String url, int id) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(url);
		ResponseEntity<ResponseDataCredentialsDto> rateResponse = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
				});
		ResponseDataCredentialsDto responseDto = rateResponse.getBody();

		return responseDto;
	}

	@Override
	public ResponseDataCredentialsDto updateEntity(String requestForType, Object entity, String token) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseDataCredentialsDto> rateResponse = null;
		String url = Constants.URL_PATH;
		switch (requestForType) {
		case "seller":
			SellerDetails updatedSellerDetails = (SellerDetails) entity;
			url += "sellers/" + updatedSellerDetails.getSellerId();

			HttpEntity<SellerDetails> httpEntitySeller = new HttpEntity<>(updatedSellerDetails, getHeaders(token));
			rateResponse = restTemplate.exchange(url, HttpMethod.PUT, httpEntitySeller,
					new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
					});
			break;
		case "product":
			ProductDetails updatedProductDetails = (ProductDetails) entity;
			url += "products/" + updatedProductDetails.getProductId();

			HttpEntity<ProductDetails> httpEntityProduct = new HttpEntity<>(updatedProductDetails, getHeaders(token));
			rateResponse = restTemplate.exchange(url, HttpMethod.PUT, httpEntityProduct,
					new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
					});
			break;
		case "category":
			CategoryDetails updatedCategoryDetails = (CategoryDetails) entity;
			url += "categories/" + updatedCategoryDetails.getCategoryId();

			HttpEntity<CategoryDetails> httpEntityCategory = new HttpEntity<>(updatedCategoryDetails,
					getHeaders(token));
			rateResponse = restTemplate.exchange(url, HttpMethod.PUT, httpEntityCategory,
					new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
					});
			break;

		}

		ResponseDataCredentialsDto responseDto = rateResponse.getBody();
		return responseDto;
	}

	@Override
	public ResponseDataCredentialsDto addEntity(Object entity, String token) {
		// TODO Auto-generated method stub'
		CategoryDetails categoryDetails = (CategoryDetails) entity;
		String url = Constants.URL_PATH + "categories";
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<CategoryDetails> httpEntity = new HttpEntity<>(categoryDetails, getHeaders(token));
		ResponseEntity<ResponseDataCredentialsDto> rateResponse = restTemplate.exchange(url, HttpMethod.POST,
				httpEntity, new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
				});
		ResponseDataCredentialsDto credentialsDto = rateResponse.getBody();
		return credentialsDto;
	}

	@Override
	public ResponseDataCredentialsDto deleteEntity(int id, String token) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();

		String url = Constants.URL_PATH + "categories/" + id;
		HttpEntity<?> httpEntity = new HttpEntity<Object>(getHeaders(token));
		ResponseEntity<ResponseDataCredentialsDto> rateResponse = restTemplate.exchange(url, HttpMethod.DELETE,
				httpEntity, new ParameterizedTypeReference<ResponseDataCredentialsDto>() {
				});
		ResponseDataCredentialsDto responseDto = rateResponse.getBody();

		return responseDto;
	}

	private HttpHeaders getHeaders(String token) {
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_JSON);
		list.add(MediaType.ALL);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(list);
		if (token != null) {
			headers.set("Authorization", token);
		}
		return headers;
	}

	@Override
	public void handleApproval(String type, String entities, String token) {
		// TODO Auto-generated method stub
		String[] idList = entities.trim().split(",");
		String url = Constants.URL_PATH + type + "s";
		ProductDetails productDetails = new ProductDetails();
		SellerDetails sellerDetails = new SellerDetails();
		ResponseDataCredentialsDto responseDto;
		for (String id : idList) {
			System.out.println(id);
			responseDto = getEntity(url + "/" + id, Integer.parseInt(id));

			ObjectMapper mapper = new ObjectMapper();

			try {
				if (type.equalsIgnoreCase("product")) {
					productDetails = mapper.readValue(mapper.writeValueAsBytes(responseDto.getData()),
							ProductDetails.class);
					productDetails.setProductStatus("APPROVED");
					responseDto = updateEntity(type, productDetails, token);
				} else {
					sellerDetails = mapper.readValue(mapper.writeValueAsBytes(responseDto.getData()),
							SellerDetails.class);
					sellerDetails.setSellerStatus("APPROVED");
					responseDto = updateEntity(type, sellerDetails, token);

				}

			} catch (JsonParseException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
	
	private boolean checkParameterValidity(String param){
		if(param!=null && !param.equalsIgnoreCase("null")){
			return true;
		}
		return false;
	}

}
