package org.digital.dairy.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;
import org.digital.dairy.model.search.RegisterNamesDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administrator on 09/08/15.
 */
@Controller
public class SolrController {

    @Autowired
    SolrTemplate solrTemplate;


    @RequestMapping("/SolrQuery")
    @ResponseBody
    public String solrQuerying(@RequestParam("term") String searchText){
        System.out.println("checking given input === > "+searchText);
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setParam(CommonParams.Q,"*:*");
        solrQuery.setParam("facet",true);
        solrQuery.setParam("facet.field","author");
        solrQuery.setParam("facet.prefix",searchText.toLowerCase());
        // Specifying the limit to 15, so max 15 auto suggestions would show up
        solrQuery.setParam("facet.limit","15");
        // Return facet which have a minimum count of 1
        solrQuery.setParam("facet.mincount","1");
        solrQuery.setParam(CommonParams.FL,"id,author");


        QueryResponse rsp = null;
        try {
            rsp = ((SolrServer) solrTemplate.getSolrServer()).query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        System.out.println("tesdf =====>>>"+rsp);
        List<String> suggestedStringsList = new ArrayList<String>();

        // Retrieve the Faceted results
        List<FacetField> facetFields = rsp.getFacetFields();
        if (facetFields!=null && !facetFields.isEmpty()){
            for (FacetField facetField: facetFields){
                List<FacetField.Count> facetFieldValues = facetField.getValues();
                if (facetFieldValues!=null && !facetFieldValues.isEmpty()) {
                    for (FacetField.Count facetFieldValue : facetFieldValues){
                        suggestedStringsList.add(facetFieldValue.getName());
                    }
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(suggestedStringsList);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        //return "[\"value\",\"idonknow\"]";
    }
    @RequestMapping("/searchbuttonclick")
    @ResponseBody
    public String searchbuttonclick(@RequestParam("term") String searchText){

        List<RegisterNamesDo> registerNamesDo = new ArrayList<RegisterNamesDo>();
        StringBuffer querystring = new StringBuffer();
        querystring.append("*:*");
        querystring.append("(author:"+searchText+")");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setParam(CommonParams.Q,querystring.toString());
        // Enable highlighting option
        solrQuery.setParam("hl", true);
        // Specify the fields to be highlighted
        solrQuery.setParam("hl.fl", "author,id,title");
        // Specify that entire document should be scanned
        solrQuery.setParam("hl.maxAnalyzedChars", "-1");
        solrQuery.setParam("hl.fragsize","200");
        // Specify the number of snippet to be returned. Set it to 20.
        solrQuery.setParam("hl.snippets","20");
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        QueryResponse rsp = null;
        try {
            rsp = ((SolrServer) solrTemplate.getSolrServer()).query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        System.out.println("result of search button =====>>>" + rsp);
        registerNamesDo =  rsp.getBeans(RegisterNamesDo.class);
        System.out.println("got result===="+registerNamesDo);
        ObjectMapper mapper = new ObjectMapper();
        try {
        return mapper.writeValueAsString(registerNamesDo);
        }
        catch (JsonGenerationException e){

        }catch (JsonMappingException e){

        }catch (Exception e){

        }
        return "[\"value\",\"idonknow\"]";
    }
}
