
package org.me.imagenes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.imagenes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteImage_QNAME = new QName("http://imagenes.me.org/", "DeleteImage");
    private final static QName _DeleteImageResponse_QNAME = new QName("http://imagenes.me.org/", "DeleteImageResponse");
    private final static QName _DownloadImage_QNAME = new QName("http://imagenes.me.org/", "DownloadImage");
    private final static QName _DownloadImageResponse_QNAME = new QName("http://imagenes.me.org/", "DownloadImageResponse");
    private final static QName _ListImages_QNAME = new QName("http://imagenes.me.org/", "ListImages");
    private final static QName _ListImagesResponse_QNAME = new QName("http://imagenes.me.org/", "ListImagesResponse");
    private final static QName _ModifyImage_QNAME = new QName("http://imagenes.me.org/", "ModifyImage");
    private final static QName _ModifyImageResponse_QNAME = new QName("http://imagenes.me.org/", "ModifyImageResponse");
    private final static QName _RegisterImage_QNAME = new QName("http://imagenes.me.org/", "RegisterImage");
    private final static QName _RegisterImageResponse_QNAME = new QName("http://imagenes.me.org/", "RegisterImageResponse");
    private final static QName _SearchComb_QNAME = new QName("http://imagenes.me.org/", "SearchComb");
    private final static QName _SearchCombResponse_QNAME = new QName("http://imagenes.me.org/", "SearchCombResponse");
    private final static QName _SearchbyAuthor_QNAME = new QName("http://imagenes.me.org/", "SearchbyAuthor");
    private final static QName _SearchbyAuthorResponse_QNAME = new QName("http://imagenes.me.org/", "SearchbyAuthorResponse");
    private final static QName _SearchbyCreatDate_QNAME = new QName("http://imagenes.me.org/", "SearchbyCreatDate");
    private final static QName _SearchbyCreatDateResponse_QNAME = new QName("http://imagenes.me.org/", "SearchbyCreatDateResponse");
    private final static QName _SearchbyId_QNAME = new QName("http://imagenes.me.org/", "SearchbyId");
    private final static QName _SearchbyIdResponse_QNAME = new QName("http://imagenes.me.org/", "SearchbyIdResponse");
    private final static QName _SearchbyKeywords_QNAME = new QName("http://imagenes.me.org/", "SearchbyKeywords");
    private final static QName _SearchbyKeywordsResponse_QNAME = new QName("http://imagenes.me.org/", "SearchbyKeywordsResponse");
    private final static QName _SearchbyTitle_QNAME = new QName("http://imagenes.me.org/", "SearchbyTitle");
    private final static QName _SearchbyTitleResponse_QNAME = new QName("http://imagenes.me.org/", "SearchbyTitleResponse");
    private final static QName _RegisterImageImageBytes_QNAME = new QName("", "imageBytes");
    private final static QName _DownloadImageResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.imagenes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteImage }
     * 
     */
    public DeleteImage createDeleteImage() {
        return new DeleteImage();
    }

    /**
     * Create an instance of {@link DeleteImageResponse }
     * 
     */
    public DeleteImageResponse createDeleteImageResponse() {
        return new DeleteImageResponse();
    }

    /**
     * Create an instance of {@link DownloadImage }
     * 
     */
    public DownloadImage createDownloadImage() {
        return new DownloadImage();
    }

    /**
     * Create an instance of {@link DownloadImageResponse }
     * 
     */
    public DownloadImageResponse createDownloadImageResponse() {
        return new DownloadImageResponse();
    }

    /**
     * Create an instance of {@link ListImages }
     * 
     */
    public ListImages createListImages() {
        return new ListImages();
    }

    /**
     * Create an instance of {@link ListImagesResponse }
     * 
     */
    public ListImagesResponse createListImagesResponse() {
        return new ListImagesResponse();
    }

    /**
     * Create an instance of {@link ModifyImage }
     * 
     */
    public ModifyImage createModifyImage() {
        return new ModifyImage();
    }

    /**
     * Create an instance of {@link ModifyImageResponse }
     * 
     */
    public ModifyImageResponse createModifyImageResponse() {
        return new ModifyImageResponse();
    }

    /**
     * Create an instance of {@link RegisterImage }
     * 
     */
    public RegisterImage createRegisterImage() {
        return new RegisterImage();
    }

    /**
     * Create an instance of {@link RegisterImageResponse }
     * 
     */
    public RegisterImageResponse createRegisterImageResponse() {
        return new RegisterImageResponse();
    }

    /**
     * Create an instance of {@link SearchComb }
     * 
     */
    public SearchComb createSearchComb() {
        return new SearchComb();
    }

    /**
     * Create an instance of {@link SearchCombResponse }
     * 
     */
    public SearchCombResponse createSearchCombResponse() {
        return new SearchCombResponse();
    }

    /**
     * Create an instance of {@link SearchbyAuthor }
     * 
     */
    public SearchbyAuthor createSearchbyAuthor() {
        return new SearchbyAuthor();
    }

    /**
     * Create an instance of {@link SearchbyAuthorResponse }
     * 
     */
    public SearchbyAuthorResponse createSearchbyAuthorResponse() {
        return new SearchbyAuthorResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreatDate }
     * 
     */
    public SearchbyCreatDate createSearchbyCreatDate() {
        return new SearchbyCreatDate();
    }

    /**
     * Create an instance of {@link SearchbyCreatDateResponse }
     * 
     */
    public SearchbyCreatDateResponse createSearchbyCreatDateResponse() {
        return new SearchbyCreatDateResponse();
    }

    /**
     * Create an instance of {@link SearchbyId }
     * 
     */
    public SearchbyId createSearchbyId() {
        return new SearchbyId();
    }

    /**
     * Create an instance of {@link SearchbyIdResponse }
     * 
     */
    public SearchbyIdResponse createSearchbyIdResponse() {
        return new SearchbyIdResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywords }
     * 
     */
    public SearchbyKeywords createSearchbyKeywords() {
        return new SearchbyKeywords();
    }

    /**
     * Create an instance of {@link SearchbyKeywordsResponse }
     * 
     */
    public SearchbyKeywordsResponse createSearchbyKeywordsResponse() {
        return new SearchbyKeywordsResponse();
    }

    /**
     * Create an instance of {@link SearchbyTitle }
     * 
     */
    public SearchbyTitle createSearchbyTitle() {
        return new SearchbyTitle();
    }

    /**
     * Create an instance of {@link SearchbyTitleResponse }
     * 
     */
    public SearchbyTitleResponse createSearchbyTitleResponse() {
        return new SearchbyTitleResponse();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "DeleteImage")
    public JAXBElement<DeleteImage> createDeleteImage(DeleteImage value) {
        return new JAXBElement<DeleteImage>(_DeleteImage_QNAME, DeleteImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "DeleteImageResponse")
    public JAXBElement<DeleteImageResponse> createDeleteImageResponse(DeleteImageResponse value) {
        return new JAXBElement<DeleteImageResponse>(_DeleteImageResponse_QNAME, DeleteImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "DownloadImage")
    public JAXBElement<DownloadImage> createDownloadImage(DownloadImage value) {
        return new JAXBElement<DownloadImage>(_DownloadImage_QNAME, DownloadImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "DownloadImageResponse")
    public JAXBElement<DownloadImageResponse> createDownloadImageResponse(DownloadImageResponse value) {
        return new JAXBElement<DownloadImageResponse>(_DownloadImageResponse_QNAME, DownloadImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "ListImages")
    public JAXBElement<ListImages> createListImages(ListImages value) {
        return new JAXBElement<ListImages>(_ListImages_QNAME, ListImages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "ListImagesResponse")
    public JAXBElement<ListImagesResponse> createListImagesResponse(ListImagesResponse value) {
        return new JAXBElement<ListImagesResponse>(_ListImagesResponse_QNAME, ListImagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "ModifyImage")
    public JAXBElement<ModifyImage> createModifyImage(ModifyImage value) {
        return new JAXBElement<ModifyImage>(_ModifyImage_QNAME, ModifyImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "ModifyImageResponse")
    public JAXBElement<ModifyImageResponse> createModifyImageResponse(ModifyImageResponse value) {
        return new JAXBElement<ModifyImageResponse>(_ModifyImageResponse_QNAME, ModifyImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "RegisterImage")
    public JAXBElement<RegisterImage> createRegisterImage(RegisterImage value) {
        return new JAXBElement<RegisterImage>(_RegisterImage_QNAME, RegisterImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "RegisterImageResponse")
    public JAXBElement<RegisterImageResponse> createRegisterImageResponse(RegisterImageResponse value) {
        return new JAXBElement<RegisterImageResponse>(_RegisterImageResponse_QNAME, RegisterImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchComb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchComb")
    public JAXBElement<SearchComb> createSearchComb(SearchComb value) {
        return new JAXBElement<SearchComb>(_SearchComb_QNAME, SearchComb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCombResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchCombResponse")
    public JAXBElement<SearchCombResponse> createSearchCombResponse(SearchCombResponse value) {
        return new JAXBElement<SearchCombResponse>(_SearchCombResponse_QNAME, SearchCombResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyAuthor")
    public JAXBElement<SearchbyAuthor> createSearchbyAuthor(SearchbyAuthor value) {
        return new JAXBElement<SearchbyAuthor>(_SearchbyAuthor_QNAME, SearchbyAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyAuthorResponse")
    public JAXBElement<SearchbyAuthorResponse> createSearchbyAuthorResponse(SearchbyAuthorResponse value) {
        return new JAXBElement<SearchbyAuthorResponse>(_SearchbyAuthorResponse_QNAME, SearchbyAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreatDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyCreatDate")
    public JAXBElement<SearchbyCreatDate> createSearchbyCreatDate(SearchbyCreatDate value) {
        return new JAXBElement<SearchbyCreatDate>(_SearchbyCreatDate_QNAME, SearchbyCreatDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreatDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyCreatDateResponse")
    public JAXBElement<SearchbyCreatDateResponse> createSearchbyCreatDateResponse(SearchbyCreatDateResponse value) {
        return new JAXBElement<SearchbyCreatDateResponse>(_SearchbyCreatDateResponse_QNAME, SearchbyCreatDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyId")
    public JAXBElement<SearchbyId> createSearchbyId(SearchbyId value) {
        return new JAXBElement<SearchbyId>(_SearchbyId_QNAME, SearchbyId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyIdResponse")
    public JAXBElement<SearchbyIdResponse> createSearchbyIdResponse(SearchbyIdResponse value) {
        return new JAXBElement<SearchbyIdResponse>(_SearchbyIdResponse_QNAME, SearchbyIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyKeywords")
    public JAXBElement<SearchbyKeywords> createSearchbyKeywords(SearchbyKeywords value) {
        return new JAXBElement<SearchbyKeywords>(_SearchbyKeywords_QNAME, SearchbyKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyKeywordsResponse")
    public JAXBElement<SearchbyKeywordsResponse> createSearchbyKeywordsResponse(SearchbyKeywordsResponse value) {
        return new JAXBElement<SearchbyKeywordsResponse>(_SearchbyKeywordsResponse_QNAME, SearchbyKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyTitle")
    public JAXBElement<SearchbyTitle> createSearchbyTitle(SearchbyTitle value) {
        return new JAXBElement<SearchbyTitle>(_SearchbyTitle_QNAME, SearchbyTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://imagenes.me.org/", name = "SearchbyTitleResponse")
    public JAXBElement<SearchbyTitleResponse> createSearchbyTitleResponse(SearchbyTitleResponse value) {
        return new JAXBElement<SearchbyTitleResponse>(_SearchbyTitleResponse_QNAME, SearchbyTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "imageBytes", scope = RegisterImage.class)
    public JAXBElement<byte[]> createRegisterImageImageBytes(byte[] value) {
        return new JAXBElement<byte[]>(_RegisterImageImageBytes_QNAME, byte[].class, RegisterImage.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = DownloadImageResponse.class)
    public JAXBElement<byte[]> createDownloadImageResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_DownloadImageResponseReturn_QNAME, byte[].class, DownloadImageResponse.class, ((byte[]) value));
    }

}
