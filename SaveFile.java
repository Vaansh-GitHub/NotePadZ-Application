import java.io.*;
import java.util.*;
public class SaveFile {
	 boolean go=false;
	 Notepad_Code npc;
	 String text,fname,path;
	 File f;
	 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     SaveFile(String text,String fname,String path,Notepad_Code npc)
     {
    	 this.npc=npc;
    	 this.text=text;
    	 this.path=path;
    	 if(fname.endsWith(".txt"))
    	 {
    		 this.fname=fname;
    	 }
    	 else
    	 {
    		 this.fname=fname+".txt";
    	 }
    	 f=new File(path,this.fname);
    	 makeAndSave();
     }
     void makeAndSave()
     {
    	 try {
    	 if(f.exists())
    	 {
    		 System.out.println("This file exists");
        	 System.out.println("Type :\n\"R\" to Replace File\n\"N\" to type new name\n\"E\" to don't save file:");
    		 String s=" ";
    		 while(s!="N")
    		 {
    			 s=br.readLine();
    			 if(s.equalsIgnoreCase("R"))
    			 {
    				 f.delete();
    	    		 f=new File(path,fname);
    	    		 makeAndSave();
    	    		 return;
    			 }
    			 if(s.equalsIgnoreCase("N"))
    			 {
    				 break;
    			 }
    			 if(s.equalsIgnoreCase("E"))
    			 {
    				 npc.fname=" ";
    				 go=true;
    				 return;
    			 }
    			 else
    			 {
    				 System.out.println("Wrong choice!!choose again.");
    			 }
    		 }
    		 
    		 System.out.println("Enter new name :");
    		 fname=br.readLine();
    		 SaveFile sf=new SaveFile(text,fname,path,npc);
    	 }
    	 else
    	 {
    		 f.createNewFile();
			 FileWriter fw=new FileWriter(f);
		     StringTokenizer st=new StringTokenizer(text," ");
			 while(st.hasMoreTokens())
			 {
				 fw.write(st.nextToken()+" "); 
			 }
			 fw.close();
			 npc.fname=f.getName();
			 npc.saveas.setEnabled(false);
			 npc.savedasornot=1;
			 go=true;
    	 }
    	 }catch(Exception e) {}
     }
}
