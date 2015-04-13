package org.digital.dairy.repository.rdbmysql;

import org.digital.dairy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Pradeep.P on 07-04-2015.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
