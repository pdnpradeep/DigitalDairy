package org.digital.dairy.repository.rdbmysql;

import org.digital.dairy.async.vo.ResentConformationMailVO;
import org.digital.dairy.entity.User;
import org.digital.dairy.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pradeep.P on 11-04-2015.
 */

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);

/*    @Query("SELECT v FROM VerificationToken v")
    public List<VerificationToken> findTokensForUnExpireUsers();*/

    @Query("SELECT u.email,v.token FROM User u , VerificationToken v where u.id = v.user and u.enabled = 'b0' and v.expiryDate <= '2015-05-24 20:03:10'")
    public List<ResentConformationMailVO> findTokensForUnExpireUsers();


    List<VerificationToken> findAll();

}