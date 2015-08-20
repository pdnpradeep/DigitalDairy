package org.digital.dairy.model.search;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

/**
 * Created by administrator on 05/08/15.
 */
@Data
public class RegisterNamesDo {
    @Id
    @Field
    private String id;

    @Field
    private String title;

    @Field
    private String author;

 /*   @Data
    public static class Builder {
        private String id;
        private String Name;
        private String tittle;

        public Builder(String id) {
            this.id = id;
        }

        public Builder courseId(String Name){
            this.Name = Name;
            return this;
        }
        public Builder encryptedCourseId(String tittle) {
            this.tittle = tittle;
            return this;
        }
    }*/
}
