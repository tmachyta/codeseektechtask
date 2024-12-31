package codeseek.codeseektechtask.service.role;

import codeseek.codeseektechtask.model.Role;
import codeseek.codeseektechtask.model.Role.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
