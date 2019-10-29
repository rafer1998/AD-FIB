/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author raul.fernandez.almendros
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @return
    */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage  (@FormParam("title") String title, 
            @FormParam("description") String description,
            @FormParam("keywords") String keywords,
            @FormParam("author") String author,
            @FormParam("creation") String crea_date){
        return null;
    }
    /**
 * POST method to register a new image
 * @param title
 * @param description
 * @param keywords
 * @param author
 * @param crea_date
 * @return
 */
 @Path("modify")
 @POST
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 @Produces(MediaType.TEXT_HTML)
 public String modifyImage (@FormParam("title") String title,
    @FormParam("description") String description,
    @FormParam("keywords") String keywords,
    @FormParam("author") String author,
    @FormParam("creation") String crea_date) {
     return null;
 }
 /**
 * GET method to list images
 * @return
 */
 @Path("list")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String listImages () {
     return null;
 }
 /**
 * GET method to search images by id
 * @param id
 * @return
 */
 
        
}
