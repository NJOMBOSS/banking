package tech.fonke.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tech.fonke.banking.models.Role;
import tech.fonke.banking.models.User;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoleDto {

    private Integer id;

    private String name;

    private Integer userId;

    public static RoleDto fromEntity(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .userId(role.getUser().getId())
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .user(
                        User.builder()
                                .id(roleDto.getUserId())
                                .build()
                )
                .build();
    }
}
