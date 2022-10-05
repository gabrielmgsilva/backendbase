package br.com.cerberusit.service.interfaces;

import br.com.cerberusit.controller.request.ProfileRequestDto;
import br.com.cerberusit.controller.response.ProfileResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProfileService {

    ProfileResponseDto findById(Long id);
    Page<ProfileResponseDto> findAll(Pageable pageable);
    ProfileResponseDto createOrUpdateProfile(ProfileRequestDto profileRequestDto);
}
