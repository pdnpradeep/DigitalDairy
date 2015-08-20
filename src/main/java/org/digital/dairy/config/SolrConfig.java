package org.digital.dairy.config;

import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import java.io.FileNotFoundException;

/**
 * Created by administrator on 04/08/15.
 */

@Configuration
@EnableSolrRepositories("org.digital.dairy.repository.search")
public class SolrConfig {

    private String SOLR_HTTP_SERVER_URL = "SOLR_HTTP_SERVER_URL";

    @Bean(name="solrTemplate")
    public SolrTemplate solrTagTemplate() throws Exception {
        if(isEmbeddedServer()){
            EmbeddedSolrServer embeddedSolrServer = new EmbeddedSolrServer(getCoreContainer(), "collection1");
            return new SolrTemplate(embeddedSolrServer);
        } else {
            HttpSolrServer httpSolrServer = new HttpSolrServer(getSolrURL());
            return new SolrTemplate(httpSolrServer, "collection1");
        }
    }

    @Bean
    @Scope
    public CoreContainer getCoreContainer() throws FileNotFoundException {
        if(isEmbeddedServer()){
            String dir = Thread.currentThread().getContextClassLoader().getResource("solr").getPath();
            System.setProperty("solr.solr.home", dir);
            CoreContainer container = new CoreContainer(dir);
            container.load();
            return container;
        }
        return null;
    }

    private String getSolrURL(){
        System.setProperty("SOLR_HTTP_SERVER_URL","http://localhost:8983/solr");
        System.out.println(" System.getProperty(SOLR_HTTP_SERVER_URL);====>>>>>>>"+System.getProperty(SOLR_HTTP_SERVER_URL));
        return System.getProperty(SOLR_HTTP_SERVER_URL);
    }
    private boolean isEmbeddedServer(){
        return false;
    }

}
