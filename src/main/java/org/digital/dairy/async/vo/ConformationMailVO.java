package org.digital.dairy.async.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.digital.dairy.entity.User;

import java.util.Locale;

/**
 * Created by Pradeep.P on 07-05-2015.
 */
@Data
@ToString
@AllArgsConstructor
public class ConformationMailVO implements AsyncVO {

    private  String userEmail;
    private  Locale locale;
    private  String appUrl;


    public ConformationMailVO() {
    }
}
