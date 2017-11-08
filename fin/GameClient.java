import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.util.concurrent.TimeUnit;

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
    public static void main(String[] args) throws IOException, InterruptedException {

       Socket s = new Socket("127.0.0.1", 5000);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter w = new PrintWriter(s.getOutputStream(),true);
		String dataToServer = "new";
		System.out.println("Requesting a new game"); 
		w.println(dataToServer.toString());
        String data;
		
            while(true)
            {
                 data = input.readLine();
				if(data.trim().equals("tie")){
					System.out.println("The Game is a tie!");
					dataToServer = "end";
					w.println(dataToServer.toString());
					break;  

				} else if(data.trim().equals("xwin")){
					System.out.println("Client(We) won the Game! "); 
					dataToServer = "end";
					w.println(dataToServer.toString());
					break; 
				}
				else if(data.trim().equals("owin")){
					System.out.println("We Lost ! Server won the Game! ");
					dataToServer = "end";
					w.println(dataToServer.toString());
					break; 

				}else{	
						System.out.println("Game from server!");
						printGame(data);
						System.out.println("Clients Move!");
						TimeUnit.SECONDS.sleep(2);
						dataToServer = playGame(data);
						System.out.println(dataToServer);		
 						w.println(dataToServer.toString());
				}
            }
            

        System.exit(0);
    }
    public static String playGame(String iData){
        if (iData != null)
         {   
		 	if(iData.length() >=9)
			{
          		char game[][] = new char[3][3];
          		game[0] = iData.substring(0,3).toCharArray();
          		game[1] = iData.substring(3,6).toCharArray();
          		game[2] = iData.substring(6,9).toCharArray();
				game = makeMove(game);
				return gameToString(game);
        	}
		 
		 }
		return "end";
             
    }
   public static char[][] makeMove(char[][] game){
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
	   return game;
    }
	public static String gameToString(char[][] game){
		String buffer = "";
	     for (int i=0; i < 3; i++) {
		        for (int j=0; j < 3; j++) {
		            buffer += game[i][j];
		        }
		    }
			
		return buffer;
	}
	public static void printGame(String game){
	System.out.println(game); 

	}
	
}
