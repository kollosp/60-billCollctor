package loginSetup;


import java.io.Console;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;



 
public class TestEndpoint extends Endpoint {

    public void onOpen(Session session, EndpointConfig config) {
    	String data = "Odpowiadam endpointem";
    	ByteBuffer payload = ByteBuffer.wrap(data.getBytes());
        final RemoteEndpoint remote = session.getBasicRemote();
        session.addMessageHandler(String.class, new MessageHandler.Whole<String>() {
            public void onMessage(String text) {
                try {
                    remote.sendPong(payload);
                    //System.out.println(remote.se)
                } catch (IOException ioe) {
                    // handle send failure here
                }
            }
        });
    }

}