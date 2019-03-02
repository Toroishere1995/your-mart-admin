package com.learning.yourmartpmp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.yourmartpmp.constants.Constants;
import com.learning.yourmartpmp.dto.CategoryDetails;
import com.learning.yourmartpmp.dto.LoginCredentials;
import com.learning.yourmartpmp.dto.ProductDetails;
import com.learning.yourmartpmp.dto.ProductImagesCredentialsDto;
import com.learning.yourmartpmp.dto.ResponseDataCredentialsDto;
import com.learning.yourmartpmp.dto.SellerDetails;
import com.learning.yourmartpmp.dto.StatusData;
import com.learning.yourmartpmp.dto.UserDto;
import com.learning.yourmartpmp.service.IYourMartService;
import com.learning.yourmartpmp.service.impl.YourMartAdminService;
import com.learning.yourmartpmp.utils.VerifyCaptcha;

/**
 * Controller Class.
 * 
 * @author ayushsaxena
 *
 */
@Controller
public class YourMartPmpController {

	/**
	 * Dummy Method
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dummyMethod(Model model) {

		return "redirect:index.jsp";
	}

	/**
	 * Method to direct to login.jsp
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginAdmin(Model model) {
		model.addAttribute("loginAdmin", new LoginCredentials());
		return "login";
	}

	/**
	 * Method that handles post from login page
	 * 
	 * @param loginCredentials
	 * @param captchaResponse
	 * @param result
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("loginAdmin") @Valid LoginCredentials loginCredentials,
			@RequestParam("g-recaptcha-response") String captchaResponse, BindingResult result, Model model,
			HttpSession httpSession) {
		if (result.hasErrors() && !VerifyCaptcha.verify(captchaResponse)) {
			return "login";
		}

		System.out.println(captchaResponse);
		IYourMartService martService = new YourMartAdminService();
		ResponseDataCredentialsDto responseDataCredentialsDto = martService.authenticateAdmin(loginCredentials);

		ObjectMapper mapper = new ObjectMapper();

		UserDto userDto = new UserDto();

		StatusData statusData = new StatusData();

		try {
			userDto = mapper.readValue(mapper.writeValueAsBytes(responseDataCredentialsDto.getData()), UserDto.class);
			statusData = mapper.readValue(mapper.writeValueAsBytes(responseDataCredentialsDto.getStatus()),
					StatusData.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userDto != null && userDto.getToken() != null) {
			String token = userDto.toString();
			System.out.println("YMPADMIN : " + userDto.getToken());
			httpSession.setAttribute(Constants.TOKEN, userDto.getToken());
			return "redirect:displaySellers.html";
		}
		model.addAttribute("errormessage", statusData.getErrorMessage());

		return "login";
	}

	/**
	 * Method to display sellers
	 * 
	 * @param model
	 * @param keyword
	 * @param searchAlias
	 * @param sort
	 * @param httpSession
	 * @param ref
	 * @return
	 */
	@RequestMapping(value = "/displaySellers", method = RequestMethod.GET)
	public String displaySellers(Model model, @RequestParam(required = false, value = "keyword") String keyword,
			@RequestParam(required = false, value = "search-alias") String searchAlias,
			@RequestParam(required = false, value = "sort") String sort, HttpSession httpSession,
			@RequestParam(required = false, value = "ref") String ref) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		IYourMartService martService = new YourMartAdminService();
		String url = Constants.URL_PATH + "sellers";
		ResponseDataCredentialsDto responseDto = martService.getAll("seller", url, keyword, searchAlias, sort, ref);
		List<SellerDetails> list = ((List<SellerDetails>) responseDto.getData());
		System.out.println(responseDto.getPaginationDetails().getCurrentPage() + " : "
				+ responseDto.getPaginationDetails().isNextPage());
		model.addAttribute("result", list);
		model.addAttribute("page", responseDto.getPaginationDetails());
		return "displaySellers";
	}

	/**
	 * Method to display products
	 * 
	 * @param model
	 * @param keyword
	 * @param searchAlias
	 * @param sort
	 * @param httpSession
	 * @param ref
	 * @return
	 */
	@RequestMapping(value = "/displayProducts", method = RequestMethod.GET)
	public String displayProducts(Model model, @RequestParam(required = false, value = "keyword") String keyword,
			@RequestParam(required = false, value = "search-alias") String searchAlias,
			@RequestParam(required = false, value = "sort") String sort, HttpSession httpSession,
			@RequestParam(required = false, value = "ref") String ref) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		IYourMartService martService = new YourMartAdminService();
		String url = Constants.URL_PATH + "products";
		ResponseDataCredentialsDto responseDto = martService.getAll("product", url, keyword, searchAlias, sort, ref);
		List<ProductDetails> list = ((List<ProductDetails>) responseDto.getData());
		System.out.println(responseDto.getPaginationDetails().getCurrentPage() + " : "
				+ responseDto.getPaginationDetails().isNextPage());

		// Seller Company Name
		String sellerUrl = Constants.URL_PATH + "sellers";
		ResponseDataCredentialsDto sellerResponseDto = martService.getAll("seller", sellerUrl, null, null, null, null);
		List<SellerDetails> sellerList = ((List<SellerDetails>) sellerResponseDto.getData());
		model.addAttribute("sellers", sellerList);

		// Category Fetch
		String categoryUrl = Constants.URL_PATH + "categories";
		ResponseDataCredentialsDto categoryResponseDto = martService.getAll("category", categoryUrl, null, null, null, null);
		List<CategoryDetails> categories = ((List<CategoryDetails>) categoryResponseDto.getData());
		model.addAttribute("categories", categories);

		model.addAttribute("result", list);
		model.addAttribute("page", responseDto.getPaginationDetails());
		return "displayProducts";
	}

	/**
	 * Method to display seller details
	 * 
	 * @param sellerId
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/viewSeller", method = RequestMethod.GET)
	public String viewSeller(@RequestParam(value = "sellerId") Integer sellerId, Model model, HttpSession httpSession) {
		String url = Constants.URL_PATH + "sellers/" + sellerId;
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		IYourMartService martService = new YourMartAdminService();
		ResponseDataCredentialsDto responseDto = martService.getEntity(url, sellerId);

		ObjectMapper mapper = new ObjectMapper();
		SellerDetails sellerDetails = new SellerDetails();
		try {
			sellerDetails = mapper.readValue(mapper.writeValueAsBytes(responseDto.getData()), SellerDetails.class);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// SellerDetails sellerDetails = (SellerDetails) responseDto.getData();
		// model.addAttribute("seller", sellerDetails);
		model.addAttribute("seller", sellerDetails);
		return "viewSeller";
	}

	/**
	 * Method to display product
	 * 
	 * @param productId
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewProduct", method = RequestMethod.GET)
	public String viewProduct(@RequestParam(value = "productId") Integer productId, HttpSession httpSession,
			Model model) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		IYourMartService martService = new YourMartAdminService();
		String url = Constants.URL_PATH + "products/" + productId;
		System.out.println(url);
		ResponseDataCredentialsDto responseDto = martService.getEntity(url, productId);

		ObjectMapper mapper = new ObjectMapper();
		ProductDetails productDetails = new ProductDetails();
		System.out.println(responseDto.getData().toString());
		try {
			productDetails = mapper.readValue(mapper.writeValueAsBytes(responseDto.getData()), ProductDetails.class);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("product", productDetails);
		model.addAttribute("productImages", productDetails.getProductImages());
		return "viewProduct";
	}

	/**
	 * Method to change seller status.
	 * 
	 * @param httpSession
	 * @param updatedSellerDetails
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeSellerStatus", method = RequestMethod.POST)
	public String changeSellerStatus(HttpSession httpSession,
			@ModelAttribute("seller") SellerDetails updatedSellerDetails, Model model) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		String token = (String) httpSession.getAttribute(Constants.TOKEN);
		IYourMartService martService = new YourMartAdminService();
		ResponseDataCredentialsDto dto = martService.updateEntity("seller", updatedSellerDetails, token);
		System.out.println(dto.getStatus().getStatusMessage());

		return "redirect:viewSeller.html?sellerId=" + updatedSellerDetails.getSellerId();
	}

	/**
	 * Method to change product status.
	 * 
	 * @param httpSession
	 * @param updatedProductDetails
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeProductStatus", method = RequestMethod.POST)
	public String changeProductStatus(HttpSession httpSession,
			@ModelAttribute("product") ProductDetails updatedProductDetails, Model model) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		String token = (String) httpSession.getAttribute(Constants.TOKEN);
		IYourMartService martService = new YourMartAdminService();
		System.out.println("upda" + updatedProductDetails.getProductCategory());
		ResponseDataCredentialsDto dto = martService.updateEntity("product", updatedProductDetails, token);
		System.out.println(dto.getStatus().getStatusMessage());

		return "redirect:viewProduct.html?productId=" + updatedProductDetails.getProductId();
	}

	/**
	 * Method to approve products.
	 * 
	 * @param products
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/approveProducts", method = RequestMethod.GET)
	public String approveProducts(@RequestParam("productId") String products, Model model, HttpSession httpSession) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		IYourMartService martService = new YourMartAdminService();
		String token = (String) httpSession.getAttribute(Constants.TOKEN);
		martService.handleApproval("product", products, token);
		return "redirect:displayProducts.html";
	}

	/**
	 * Method to approve sellers.
	 * 
	 * @param sellers
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/approveSellers", method = RequestMethod.GET)
	public String approveSellers(@RequestParam("sellerId") String sellers, Model model, HttpSession httpSession) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		IYourMartService martService = new YourMartAdminService();
		String token = (String) httpSession.getAttribute(Constants.TOKEN);
		martService.handleApproval("seller", sellers, token);

		return "redirect:displaySellers.html";
	}

	/**
	 * Method to display categories.
	 * 
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/displayCategory", method = RequestMethod.GET)
	public String displayCategory(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		model.addAttribute("category", new CategoryDetails());

		IYourMartService martService = new YourMartAdminService();
		String url = Constants.URL_PATH + "categories";
		ResponseDataCredentialsDto responseDto = martService.getAll("category", url, null, null, null, null);
		List<CategoryDetails> list = ((List<CategoryDetails>) responseDto.getData());

		model.addAttribute("result", list);
		return "displayCategory";
	}

	/**
	 * Method to edit or add category
	 * 
	 * @param model
	 * @param actionType
	 * @param categoryDetails
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/manageCategory", method = RequestMethod.POST)
	public String manageCategory(Model model, @RequestParam(value = "action-type") String actionType,
			@ModelAttribute("category") CategoryDetails categoryDetails, HttpSession httpSession) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		String token = (String) httpSession.getAttribute(Constants.TOKEN);
		IYourMartService martService = new YourMartAdminService();
		ResponseDataCredentialsDto responseDto;
		if (actionType.equalsIgnoreCase("add")) {
			responseDto = martService.addEntity(categoryDetails, token);
		} else {
			responseDto = martService.updateEntity("category", categoryDetails, token);
		}
		System.out.println(responseDto != null ? responseDto.getStatus().getStatusMessage() : "Error");
		return "redirect:displayCategory.html";
	}

	/**
	 * Method to delete category.
	 * 
	 * @param httpSession
	 * @param model
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(HttpSession httpSession, Model model,
			@RequestParam(required = true, value = "categoryId") int categoryId) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			return "redirect:error.html";
		}
		if (categoryId > 0) {
			String token = (String) httpSession.getAttribute(Constants.TOKEN);
			IYourMartService martService = new YourMartAdminService();
			ResponseDataCredentialsDto responseDto = martService.deleteEntity(categoryId, token);
			System.out.println(responseDto.getStatus().getStatusMessage());
		}
		return "redirect:displayCategory.html";
	}

	/**
	 * Method to display error
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String displayError(Model model) {
		model.addAttribute("error", "Unauthorized Access");
		return "error";
	}

	/**
	 * Method to logout
	 * 
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutAdmin(Model model, HttpSession httpSession) {
		if (httpSession.getAttribute(Constants.TOKEN) == null) {
			model.addAttribute("error", "No admin logged");
			return "error";
		}
		httpSession.removeAttribute(Constants.TOKEN);
		return "redirect:login.html";
	}
}
