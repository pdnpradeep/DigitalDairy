package org.digital.dairy.serviceimp;

import org.digital.dairy.repository.search.RegisterNamesDoRepository;
import org.digital.dairy.service.CommonSolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.support.SolrRepositoryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by administrator on 05/08/15.
 */
@Service
@Transactional
public class CommonSolrServiceImp implements CommonSolrService{

    @Autowired
    public SolrTemplate solrTemplate;

    public RegisterNamesDoRepository getContentResourceRepository() {
        return new SolrRepositoryFactory(this.solrTemplate).getRepository(RegisterNamesDoRepository.class);
    }

}
