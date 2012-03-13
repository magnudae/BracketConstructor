import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class BracketContructor {

    public static void main(String[] args) throws IOException{
        new BracketMaker();
    }
}


class BracketMaker {
	Scanner in;
	FileWriter out;
	int bracketSize;
	int teams;
	BracketMaker() throws IOException{
		JOptionPane p = new JOptionPane();
		bracketSize = Integer.parseInt(p.showInputDialog("Bracket-size? 32/64/128"));
		teams  = bracketSize/2;
		if(bracketSize != 32 && bracketSize != 64 && bracketSize != 128)
			System.exit(1);
		try{
			in = new Scanner(new File("test.txt"));
			out = new FileWriter(new File("OutputFile.txt"));
		}catch(Exception e){
			System.out.println(in + " " + out);
			System.exit(1);
		}
		out.write("==Bracket==\n{{" + bracketSize + "TeamBracket\n");
		readAndMake();
		out.write("}}");
		out.close();
	}
   
	public void readAndMake() throws IOException{
		int i = 1;
		int r = 1;
		int round = 1;
		String number;
		while(in.hasNextLine()){
			String line1 = in.nextLine();
			String line2 = in.nextLine();
			char w1 = line1.charAt(0);
			char w2 = line2.charAt(0);
			if(r < 10) number = "0" + r;
			else number = "" + r;
			if(w1 == '1')
				out.write("| RD" + round + "-team" + number + "= '''" + line1.substring(1) + "'''\n");
			else
				out.write("| RD" + round + "-team" + number + "= " + line1.substring(1) + "\n");
			out.write("| RD" + round + "-score" + number + "=" + w1 + "\n");
            //out.write("| RD1-seed" + number + "=" + i);
			r++;
			if(r < 10) number = "0" + r;
			else number = "" + r;
			if(w2 == '1')
				out.write("| RD" + round + "-team" + number + "= '''" + line2.substring(1) + "'''\n");
			else
				out.write("| RD" + round + "-team" + number + "= " + line2.substring(1) + "\n");
			out.write("| RD" + round + "-score" + number + "=" + w2 + "\n\n");
            //out.write("| RD1-seed" + number + "=" + i);
			if(i == teams){
				round++;
				teams /= 2;
				i = 0;
				r = 0;
			}
			i++;
			r++;
		}
	}
}