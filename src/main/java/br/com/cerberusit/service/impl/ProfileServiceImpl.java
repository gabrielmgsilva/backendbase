package br.com.cerberusit.service.impl;

import br.com.cerberusit.controller.request.MenuAccessRequestDto;
import br.com.cerberusit.controller.request.ProfileRequestDto;
import br.com.cerberusit.controller.response.ProfileResponseDto;
import br.com.cerberusit.domain.model.auth.MenuAccess;
import br.com.cerberusit.domain.model.auth.Profile;
import br.com.cerberusit.domain.model.auth.ProfileMenuAccess;
import br.com.cerberusit.domain.repositories.auth.IMenuAccessRepository;
import br.com.cerberusit.domain.repositories.auth.IProfileMenuAccessRepository;
import br.com.cerberusit.domain.repositories.auth.IProfileRepository;
import br.com.cerberusit.service.interfaces.IProfileService;
import br.com.cerberusit.service.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProfileServiceImpl implements IProfileService {

    final IProfileRepository profileRepository;
    final IMenuAccessRepository menuAccessRepository;
    final IProfileMenuAccessRepository profileMenuAccessRepository;
    final ModelMapper modelMapper = new ModelMapper();

    public ProfileServiceImpl(IProfileRepository profileRepository, IMenuAccessRepository menuAccessRepository,
                              IProfileMenuAccessRepository profileMenuAccessRepository){
        this.profileRepository = profileRepository;
        this.menuAccessRepository = menuAccessRepository;
        this.profileMenuAccessRepository = profileMenuAccessRepository;
    }

    @Override
    public ProfileResponseDto findById(Long id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        return profileOptional.map(profile -> modelMapper.map(profile, ProfileResponseDto.class)).orElse(null);
    }

    @Override
    public Page<ProfileResponseDto> findAll(Pageable pageable) {
        return Mapper.mapEntityToPagedDto(profileRepository.findAll(pageable), ProfileResponseDto.class);
    }

    @Override
    public ProfileResponseDto createOrUpdateProfile(ProfileRequestDto profileRequestDto) {
        Profile profile = new Profile(profileRequestDto);
        profileRepository.save(profile);

        List<MenuAccessRequestDto> menuAccessRequestDtos = profileRequestDto.getMenuAccessDtoList();

        menuAccessRequestDtos.stream().forEach(object -> {
            MenuAccess menuAccess = menuAccessRepository.findById(object.getId())
                    .orElseThrow();
            ProfileMenuAccess profileMenuAccess = new ProfileMenuAccess();
            profileMenuAccess.setProfiles(profile);
            profileMenuAccess.setMenuAccesses(menuAccess);
            profileMenuAccess.setRead(object.isCanRead());
            profileMenuAccess.setWrite(object.isCanWrite());
            profileMenuAccessRepository.save(profileMenuAccess);
            profile.getProfileMenuAccesses().add(profileMenuAccess);
            menuAccess.getProfileMenuAccesses().add(profileMenuAccess);
            menuAccessRepository.save(menuAccess);
        });

        profileRepository.save(profile);
        return modelMapper.map(profile, ProfileResponseDto.class);
    }


}
