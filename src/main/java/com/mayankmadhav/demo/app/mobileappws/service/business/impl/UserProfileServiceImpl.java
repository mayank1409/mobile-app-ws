package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.AddressType;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.AddressDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.models.Address;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserProfileRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IAddressService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserProfileService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.AddressTransformer;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserProfileTransformer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileServiceImpl extends AbstractService<UserProfile, Long> implements IUserProfileService {

	@Autowired
	private IUserProfileRepository userProfileRepository;

	@Autowired
	private UserProfileTransformer userProfileTransformer;

	@Autowired
	private IUserService userService;

	@Autowired
	AddressTransformer addressTransformer;

	@Autowired
	IAddressService addressService;

	@Override
	protected JpaRepository<UserProfile, Long> repository() {
		return userProfileRepository;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserProfile completeProfile(UserProfileDTO userProfileDto, String username) {
		log.info("complete profile invoked by username {}", username);
		Users user = userService.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException();
		}
		UserProfile userProfile = user.getUserProfile();

		if (userProfile == null) {
			log.info("saving user profile");
			userProfile = userProfileTransformer.fromDTO(userProfileDto);
			userProfile.setUser(user);
			userProfile = save(userProfile);
			log.info("adding prfile to user");
			user.setUserProfile(userProfile);
		} else {
			log.info("updating user profile");
			userProfile.setUpdateDate(System.currentTimeMillis());
			userProfile.setDateOfBirth(userProfileDto.getDateOfBirth());
			userProfile.setNationality(userProfileDto.getNationality());
			save(userProfile);
		}

		if (user.getAddress() == null || user.getAddress().isEmpty()) {
			log.info("saving user address");
			List<Address> addresses = addressTransformer.fromListDTO(userProfileDto.getAddress());
			user.setAddress(addresses);
		} else {
			List<AddressDTO> addressToUpdate = userProfileDto.getAddress();
			for (int i = 0; i < addressToUpdate.size(); i++) {
				log.info("adress dto {}", addressToUpdate);
				if (user.getAddress().size() == addressToUpdate.size()) {
					user.getAddress().get(i).setAddressLLine(addressToUpdate.get(i).getAddressLLine());
					user.getAddress().get(i).setCity(addressToUpdate.get(i).getCity());
					user.getAddress().get(i).setState(addressToUpdate.get(i).getState());
					user.getAddress().get(i).setCountry(addressToUpdate.get(i).getCountry());
					user.getAddress().get(i).setPincode(addressToUpdate.get(i).getPincode());
					user.getAddress().get(i).setPhone(addressToUpdate.get(i).getPhone());
					user.getAddress().get(i)
							.setAddressType(AddressType.valueOf(addressToUpdate.get(i).getAddressType()));
					user.getAddress().get(i).setUpdateDate(System.currentTimeMillis());
				} else {
					user.getAddress().add(addressTransformer.fromDTO(addressToUpdate.get(i)));
				}
			}
		}
		userService.save(user);
		log.info("user profile saved successfully");
		return userProfile;
	}

	@Override
	public UserProfile getProfile(String userId) {
		log.info("fetching user with id {}", userId);
		Users user = userService.findByUsername(userId);
		if (user == null) {
			log.warn("user not found");
			throw new EntityNotFoundException();
		}
		log.info("return user with details {} ", user);
		return user.getUserProfile() == null ? null : user.getUserProfile();
	}

}
