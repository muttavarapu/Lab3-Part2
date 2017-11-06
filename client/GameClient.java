import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class GameClient {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {

       Socket s = new Socket("127.0.0.1", 5000);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter w = new PrintWriter(s.getOutputStream(),true);
        String data;

            do
            {
                 data = input.readLine();
                 playGame(data);
                 w.println("Move");
            }
            while(!data.trim().equals("tie"));

        System.exit(0);
    }
    public static void playGame(String iData){
        if (iData != null)
         {   
			 System.out.println("Dat ain func is "+ iData); 
		 	if(iData.length() >=9)
			{
          		char game[][] = new char[3][3];
          		game[0] = iData.substring(0,2).toCharArray();
          		game[1] = iData.substring(3,5).toCharArray();
          		game[2] = iData.substring(6,8).toCharArray();
				makeMove(game);
        	}
		 
		 }
         if( iData == null){
             System.out.println("data is null");
        }     
    }
   public static void makeMove(char[][] game){
		outerloop:
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(game[i][j] == '_')
				{
					game[i][j] = 'X';
					break outerloop;
				}
            }
        }
    }
	
}
