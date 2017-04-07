import okhttp3.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by mohsen raeisi on 06/04/2017.
 */
public class Main {

    public static void main(String argv[]) throws Exception
    {

        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);



        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()
                            )
                    );

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);

            //ex : 29 12
           String pings[] = clientSentence.split(" ");
           sendPost(Float.parseFloat(pings[0]) , Float.parseFloat(pings[1]));

            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }





    }

    private static void sendPost(float localPing , float extPing) throws Exception {
        String url = "https://api.mlab.com/api/1/databases/smartobject/collections/internet?apiKey=z98ehgf31t5H6LIasfsSWdvL4sASQ16b";

        String json = bowlingJson(localPing, extPing);

        System.out.println(post(url,json));
    }


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    static  OkHttpClient client = new OkHttpClient();

    static String  post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    static String  bowlingJson(float localPing , float extPing) {

        Long date  = new Date().getTime();
        date += 7200000;
        return "{'idObject':'1',"
                + "'localNetwork':"+localPing+","
                + "'externalNetwork':"+extPing+","
                + "'timestamp': \""+ date+"\""
                + "}";
    }


}
