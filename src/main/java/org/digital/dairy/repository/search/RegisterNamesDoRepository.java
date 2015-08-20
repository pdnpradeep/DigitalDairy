package org.digital.dairy.repository.search;

import org.digital.dairy.model.search.RegisterNamesDo;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Created by administrator on 05/08/15.
 */
public interface RegisterNamesDoRepository extends SolrCrudRepository<RegisterNamesDo,String> {
}
