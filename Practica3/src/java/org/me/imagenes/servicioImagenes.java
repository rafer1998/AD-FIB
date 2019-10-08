/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.imagenes;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ruben
 */
@WebService(serviceName = "servicioImagenes")
public class servicioImagenes {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "RegisterImage")
    public int RegisterImage(@WebParam(name = "Image") Image Image) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") String image) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ListImages")
    public List ListImages() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyCreatDate")
    public List SearchbyCreatDate(@WebParam(name = "creaDate") String creaDate) {
        //TODO write your implementation code here:
        return null;
    }
}
