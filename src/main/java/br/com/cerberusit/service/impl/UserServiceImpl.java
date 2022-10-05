package br.com.cerberusit.service.impl;

import br.com.cerberusit.controller.request.UserRequestDto;
import br.com.cerberusit.controller.response.UserResponseDto;
import br.com.cerberusit.domain.model.auth.Profile;
import br.com.cerberusit.domain.model.auth.User;
import br.com.cerberusit.domain.repositories.auth.IUserRepository;
import br.com.cerberusit.service.interfaces.IProfileService;
import br.com.cerberusit.service.interfaces.IUserService;
import br.com.cerberusit.service.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    final IUserRepository userRepository;
    final IProfileService profileService;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserServiceImpl(IUserRepository userRepository, IProfileService profileService){
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) {
        return Mapper.mapEntityToPagedDto(userRepository.findAllByDeletedFalse(pageable), UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> modelMapper.map(user, UserResponseDto.class)).orElse(null);
    }

    @Override
    public UserResponseDto createOrUpdateUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        Profile profile = modelMapper.map(profileService.findById(userRequestDto.getProfileId()), Profile.class);
        user.setProfile(profile);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto switchUserState(Long id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new Exception("User not found with given ID: " + id));
        boolean status = user.isActive();
        user.setActive(!status);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }
}
