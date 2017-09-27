package net.ddns.frosqh.botpaikea;

import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.SortedMap;

public class ClientThread implements Runnable{

    final static int port = 2302;
    private MainThread ma = null;
    private Socket socket;
    public BufferedReader in = null;
    public PrintStream out = null;
    public InetAddress serveur;

    public ClientThread(String ip) throws UnknownHostException {

    }

    public ClientThread(String ip, MainThread mainThread) throws UnknownHostException {
        serveur = InetAddress.getByName(ip);
        ma = mainThread;

    }

    @Override
    public void run() {
        Looper.prepare();
        try {
            socket = new Socket(serveur, port);
            socket.setSoTimeout(5);
            Log.d("BITE", String.valueOf(socket.isConnected()));


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            out.println("coucou");
            String receivedData = "";
            String s ="";

            while(!receivedData.equals("\\cfini")){
                while((receivedData = in.readLine())==null){
                }
                s+=receivedData + "\n";
            }

            //System.out.println(s);

            String stringFile = s;

            SortedMap<String,ArrayList<String>> mapSong = ListHashMap.ListToHash(ListHashMap.recupList(stringFile));
            MainActivity.setMapSong(mapSong);
            //System.out.println(mapSong);
            //System.out.println(MainActivity.getMapSong());
            synchronized (ma) {
                ma.notify();
            }
            synchronized(this){
                wait();
            }

        } catch (SocketException e){
            System.out.println("coucou2");
            MainActivity.showError(e.getMessage());
        } catch (IOException e) {
            MainActivity.showError(e.getMessage());
        } catch (InterruptedException e) {
            MainActivity.showError(e.getMessage());
        }

    }

    /*public void playPause() throws IOException {
        String receivedData;
        out.println("playPause");
    }*/

    /*public void next() throws IOException{
        String receivedData;
        out.println("next");
    }*/

    /*public void vlaLaChanson(String song) throws IOException{
        String receivedData;
        out.println("vlalachanson"+song);
        while((receivedData = in.readLine())==null);
    }*/

    public String[] getPlaying() throws IOException {
        String[] playing = new String[2];
        String receivedData;
        out.println("givemeinfo");
        while((receivedData = in.readLine())==null){

        }
        playing[0] = receivedData;
        while((receivedData = in.readLine())==null){

        }
        playing[1] = receivedData;
        return playing;
    }

}
