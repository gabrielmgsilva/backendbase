package br.com.cerberusit.service.impl;

import br.com.cerberusit.controller.request.UserRequestDto;
import br.com.cerberusit.controller.response.UserResponseDto;
import br.com.cerberusit.domain.model.auth.User;
import br.com.cerberusit.domain.repositories.auth.IUserRepository;
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
    private final ModelMapper modelMapper = new ModelMapper();

    public UserServiceImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) {
        return Mapper.mapEntityToPagedDto(userRepository.findAll(pageable), UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> modelMapper.map(user, UserResponseDto.class)).orElse(null);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return null; //TODO: FINISH IMPLEMENTATION
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        return null;//TODO: FINISH IMPLEMENTATION
    }

    @Override
    public UserResponseDto switchUserState(Long id, String operation) {
        return null;//TODO: FINISH IMPLEMENTATION
    }

    @Override
    public void deleteUser(Long id) {
        //TODO: FINISH IMPLEMENTATION
    }
}
