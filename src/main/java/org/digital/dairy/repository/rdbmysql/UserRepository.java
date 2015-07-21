package org.digital.dairy.repository.rdbmysql;

import org.digital.dairy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Pradeep.P on 04-04-2015.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String userEmail);
}