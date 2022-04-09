import java.io.*;
import java.util.StringTokenizer;
public class JustSave {
	File f;
	Notepad_Code npc;
	String text;
    JustSave(File f,String text,Notepad_Code npc)
    {
    	this.f=f;
    	this.npc=npc;
    	this.text=text;
    }
    boolean justSaveIt()
    {
    	try {
    	 f.createNewFile();
		 FileWriter fw=new FileWriter(f);
	     StringTokenizer st=new StringTokenizer(text," ");
		 while(st.hasMoreTokens())
		 {
			 fw.write(st.nextToken()+" "); 
		 }
		 fw.close();
    	}catch(Exception e) {}
    	return true;
    }
}
