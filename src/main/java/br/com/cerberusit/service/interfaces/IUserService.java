package br.com.cerberusit.service.interfaces;

import br.com.cerberusit.controller.request.UserRequestDto;
import br.com.cerberusit.controller.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    Page<UserResponseDto> findAll(Pageable pageable);
    UserResponseDto findById(Long id);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(UserRequestDto userRequestDto);
    UserResponseDto switchUserState(Long id, String operation);
    void deleteUser(Long id);
}
