package org.digital.dairy.repository.rdbmysql;

import org.digital.dairy.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Pradeep.P on 11-04-2015.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);
}
