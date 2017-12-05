package co.uk.app.commerce.users.address.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.users.address.service.AddressService;
import co.uk.app.commerce.users.beans.AddressBean;
import co.uk.app.commerce.users.entity.Address;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public @ResponseBody Iterable<Address> getAddressByUserId(HttpServletRequest request) {
		String userId = String.valueOf(request.getAttribute("USER_ID"));
		return addressService.getActiveAddressesByUserId(Long.valueOf(userId));
	}

	@PutMapping
	public ResponseEntity<?> addAddress(@RequestBody AddressBean addressBean, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = String.valueOf(request.getAttribute("USER_ID"));
		addressBean.setUsersId(Long.valueOf(userId));
		Address savedAddress = addressService.save(addressBean);
		return ResponseEntity.ok(savedAddress);
	}

	@PatchMapping
	public ResponseEntity<?> updateAddress(@RequestBody AddressBean addressBean, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = String.valueOf(request.getAttribute("USER_ID"));
		addressBean.setUsersId(Long.valueOf(userId));
		Address savedAddress = addressService.save(addressBean);
		return ResponseEntity.ok(savedAddress);
	}

	@GetMapping("/selfaddress")
	public @ResponseBody Address getSelfAddress(HttpServletRequest request) {
		String userId = String.valueOf(request.getAttribute("USER_ID"));
		return addressService.getActiveSelfAddressByUserId(Long.valueOf(userId));
	}
}
