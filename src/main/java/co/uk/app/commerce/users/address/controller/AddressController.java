package co.uk.app.commerce.users.address.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{id}")
	public @ResponseBody Iterable<Address> getAddressByUserId(@PathVariable("id") Long userId) {
		return addressService.getActiveAddressesByUserId(userId);
	}

	@PutMapping
	public ResponseEntity<?> addAddress(@RequestBody AddressBean addressBean, HttpServletRequest request,
			HttpServletResponse response) {
		Address savedAddress = addressService.save(addressBean);
		return ResponseEntity.ok(savedAddress);
	}

	@PatchMapping
	public ResponseEntity<?> updateAddress(@RequestBody AddressBean addressBean, HttpServletRequest request,
			HttpServletResponse response) {
		Address savedAddress = addressService.save(addressBean);
		return ResponseEntity.ok(savedAddress);
	}

	@GetMapping("/selfaddress/{id}")
	public @ResponseBody Address getSelfAddress(@PathVariable("id") Long userId) {
		return addressService.getActiveSelfAddressByUserId(userId);
	}
}
