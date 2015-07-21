package org.digital.dairy.async.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Locale;

/**
 * Created by Pradeep.P on 29-05-2015.
 */
@Data
@ToString
@AllArgsConstructor
public class ResentConformationMailVO implements AsyncVO{

    private  String userEmail;
    private String existtoken;

    ResentConformationMailVO(){
    }

}
