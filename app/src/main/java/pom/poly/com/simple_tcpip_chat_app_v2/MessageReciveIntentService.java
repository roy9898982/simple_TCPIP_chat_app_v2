package pom.poly.com.simple_tcpip_chat_app_v2;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MessageReciveIntentService extends IntentService {
    private Socket socket;
    private BufferedReader is;
    private PrintWriter os;


    public MessageReciveIntentService() {
        super("MessageReciveIntentService");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            is.close();//关闭Socket输入流
            os.close();//关闭Socket输出流
            socket.close();//关闭Socket
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("onHandleIntent", "hihihihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        try {
            //192.168.56.1
            //192.168.31.170

            socket = new Socket("192.168.31.170", 110);
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(socket.getOutputStream());
            /// BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));


            os.println("get time");
            os.flush(); //刷新输出流，使Server马上收到该字符串
            //Log.d("onHandleIntent", "after flush");
            for (int i = 0; i <= 10; i++) {
                String str = is.readLine();//从系统标准输入读入一字符串
                Log.d("onHandleIntent", "From server: " + str);
                Thread.sleep(3000);

            }
            Log.d("onHandleIntent", "finish");


            is.close();//关闭Socket输入流
            os.close();//关闭Socket输出流
            socket.close();//关闭Socket


        } catch (Exception e) {
            Log.d("onHandleIntent", e.toString());
        }

    }


}
