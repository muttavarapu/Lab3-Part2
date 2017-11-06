import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class GameServer {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(5000);
        try {
            
            while (true) {
                Socket socket = listener.accept();
                try {
					int i=0;
					do{
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                        if(i<5)
                            out.println(playGame().toString());
                        else
                            out.println("tie");
                    i++;
					}
					while(i<6);

                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }

    public static String playGame(){
        char game[][] = new char[3][3];
    //initialize game
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = '8';
            }
        }
		checkGame(game);
        String buffer = "";
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                buffer += game[i][j];
            }
        }
        return buffer;
    }
	public static int checkGame( char[][] iData){
	//incomplete 0, 1 tie, 2 Xwin, 3 O win
		//check columns
		char [] arr = new char[]{iData[0][0],iData[0][1],iData[0][2]};
		check(arr);
		arr = null;
		return 0;
	}
	public static int check(char[] elems){
		//Typetester typeTester = new Typetester();
		//typeTester.printType("X");
		if(elems.length>=3)
		{
			//typeTester.printType(elems[0]);			
			if(elems[0] =='_' || elems[1] =='_' || elems[2] =='_'){
				return 0;
			}
			else if(elems[0] =='_' || elems[1] =='_' || elems[2] =='_'){
				return 2;
			}
			if(elems[0] =='_' || elems[1] =='_' || elems[2] =='_'){
				return 3; 
			}
			else {
				return 1;	
			}
		}
		return 0;
	}
	public static void makeMove(char[][] game){
		outerloop:
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(game[i][j] == '_')
				{
					game[i][j] = 'O';
					break outerloop;
				}
            }
        }
    }
}
