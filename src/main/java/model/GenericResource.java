package model;

import model.Batch;
import model.BatchService;
import model.Test_property;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.management.monitor.Monitor;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.apache.xmlbeans.XmlException;
import org.eclipse.jetty.server.Request;
import static org.glassfish.jersey.server.model.Parameter.Source.PATH;

@Path("home")
public class GenericResource {
    MessageService messageService = new MessageService();
    BatchService batchService;
    Test_property r = new Test_property();  

    public GenericResource() throws XmlException, IOException {
        this.batchService = new BatchService();
    }
    
    /*
    
    @GET
    @Produces(MediaType.APPLICATION_XML) //MediaType.APPLICATION_XML
    public List<Message> getMessages(){
        return messageService.getAllMessagges();
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long messageId){
        return messageService.getMessage(messageId);
    }
    
    
*/
   
  /*  
    @PUT
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Message updateMessage(@PathParam("messageId") long messageId,Message message){
        message.setId(messageId);
        return messageService.updateMessage(message);
    }
    
    @DELETE
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message delectMessage(@PathParam("messageId") long messageId){
        return messageService.removeMessage(messageId);
    }
    */
    @POST
    @Path("postMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Message> postMessage(Message message) throws IOException{
        messageService.addMessage(message);
        return messageService.getAllMessagges();
    }

    @GET
    @Path("getMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessage() throws IOException{
        return messageService.getAllMessagges();
    }

    /***************************TEXT PLAIN**************************************/
    @GET
    @Path("batch")
    @Produces(MediaType.TEXT_PLAIN)
    public String doGetBat(){            
        //r.createProperties();        
        String filepath = r.readProperties();
        //System.out.println(filepath);
        String result = r.runBatFile(filepath);                        
	return result + " ! it works!!! ";      
   }
    
    @POST
    @Path("a")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(String a){
        System.out.println("time = " + a);
        //System.out.println("message = " + input.getMessage());
        
        String var = "26 mai,5:53pm"+a;      
        return Response.ok().entity(var)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }  
    
    /*************************** JSON **************************************/
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Batch getBatchByCode(@PathParam("code") String code)throws IOException, XmlException{
        return batchService.getBatch(code);
    }
     
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getBatchByCode(){
        return "2:51pm";
    }
    
    @GET
    @Path("getBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Batch> getAllBatch() throws IOException{
        return batchService.getAllBatches();
    }
    
    @OPTIONS
    public Response getOptions() {
      return Response.ok()
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
        .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
  }

}
    
    
 

