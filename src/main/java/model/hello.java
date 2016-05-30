package model;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.xmlbeans.XmlException;

@Path("/hello")
public class hello {

    MessageService messageService = new MessageService();
    BatchService batchService;
    Test_property r = new Test_property();

    public hello() throws XmlException, IOException {
        this.batchService = new BatchService();
    }

    @GET
    @Path("getMessage")
    public Response getMsg() {

        return Response.ok() //200
                .entity("hello")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With")
                .header("Access-Control-Max-Age", "151200")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("getBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBatch() throws IOException {
        return Response.ok() //200
                .entity(batchService.getAllBatches())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With")
                .header("Access-Control-Max-Age", "151200")
                .allow("OPTIONS").build();
    }
    @POST
    @Path("postMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMessage(Message message) throws IOException{
        messageService.addMessage(message);
        return Response.ok()
                .entity(messageService.getAllMessagges())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With") 
                .header( "Access-Control-Max-Age", "1000" )
                .header("Access-Control-Allow-Credentials", true)
                .build();
    }
    
    
}
