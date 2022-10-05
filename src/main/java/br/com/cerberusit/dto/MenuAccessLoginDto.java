package br.com.cerberusit.dto;

import br.com.cerberusit.domain.model.auth.ProfileMenuAccess;
import lombok.Data;

@Data
public class MenuAccessLoginDto {

    public MenuAccessLoginDto(ProfileMenuAccess profileMenuAccess){
        this.menutitle = profileMenuAccess.getMenuAccesses().getMenuTitle();
        this.menuPath = profileMenuAccess.getMenuAccesses().getMenuPath();
        this.canRead = profileMenuAccess.isRead();
        this.canWrite = profileMenuAccess.isWrite();
    }

    private String menuPath;
    private String menutitle;
    private boolean canRead;
    private boolean canWrite;
}
