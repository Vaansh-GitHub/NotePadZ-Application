import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class Notepad_Code extends JFrame implements ActionListener {
	ImageIcon ii=new ImageIcon("NotePadZLogo.png");
	String text,family="Arial",path=" ",fname=" ";
	int size=15,savedasornot=0;
	JTextArea jta;
	Font font=new Font(family,Font.PLAIN,size);
	JMenuBar bar;
	JMenu file,edit;
	JMenuItem save,saveas,open,close,fontsettings,delete,cleararea;
	JRadioButtonMenuItem bold,italic,plain;
	ButtonGroup bg=new ButtonGroup();
	File f;
	Container c;
	JPanel panelForScroll = new JPanel(null);
	JScrollPane scroll; 
	
    Notepad_Code(String str)
    {
    	super(str);
    	this.setLayout(new BorderLayout());
        c=this.getContentPane();
    	int w=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    	int h=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    	
    	
    	jta=new JTextArea("Welcome To NotePadZ 1.0.1\nEnjoy Writing!! It has a much user friendly-environment.\nClear this sample text by choosing \"Clear Text Area\" option from \"Edit\" option.");//textarea
    	bar=new JMenuBar();//menubar
    	file=new JMenu("File");//file option
    	edit=new JMenu("Edit");//edit option
    	save=new JMenuItem("Save");//save(file)
    	saveas=new JMenuItem("Save as");//save as(file)
    	open=new JMenuItem("Open");//open(file)
    	close=new JMenuItem("Close");//close(file)
    	delete=new JMenuItem("Delete");
    	cleararea=new JMenuItem("Clear Text Area");
    	fontsettings=new JMenuItem("Font Settings");//font settings
    	scroll= new JScrollPane(jta);
    	
    	bar.setBounds(0,0,w,30);
    	jta.setLocation(w, h);
    	jta.setFont(font);
    	
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(close);
        file.add(delete);
        
        edit.add(fontsettings);
        edit.add(cleararea);
        
        close.addActionListener(this);
        save.addActionListener(this);
        open.addActionListener(this);
        saveas.addActionListener(this);
        delete.addActionListener(this);
        fontsettings.addActionListener(this);
        cleararea.addActionListener(this);
        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
  
        
    	bar.add(file);
    	bar.add(edit);
    	c.add(bar,BorderLayout.NORTH);
    	c.add(scroll); 
    	this.pack();
    	try {
    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	SwingUtilities.updateComponentTreeUI(c);}
    	catch(Exception e) {}
    	jta.setSelectedTextColor(Color.YELLOW);
    	
    	ii.setDescription("NotePadZ");
    	this.setIconImage(ii.getImage());
    }

    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(close.isArmed())
		{
			System.exit(0);
		}
		if(save.isArmed())
		{
			if(savedasornot>0 && f!=null)
			{
				try {
				 if(f.exists())
				 {
					 f.delete();
					 f=new File(path,fname);
					 text=jta.getText();
					 JustSave js=new JustSave(f,text,this);
					 if(js.justSaveIt())
					 {
						 System.out.println("Saved file");
						 print();
					 }
					 else
					 {
						 System.out.println("Saving unsuccessful!!");
					 }
				 }
				 else
				 {
					 System.out.println("This file doen't exists now.");
					 print();
					 this.setTitle("NotePadZ 1.0.1");
					 fname=" ";
					 path=" ";
					 f=null;
					 savedasornot=1;
					 saveas.setEnabled(true);
				 }
				}catch(Exception exception) {}
			}
			else
			{
				savingProcedure();
			}
		}
		if(open.isArmed())
		{
			openAndLoadFile();
		}
		if(saveas.isArmed())
		{
			savingProcedure();
		}
		if(delete.isArmed())
		{
			deleteAFile();
		}
		if(fontsettings.isArmed())
		{
			FontSettings fs=new FontSettings("Font Settings",font,this);
			System.out.println("Updating the Text's Looks and Fonts....");
			pause();
			print();
		}
		if(cleararea.isArmed())
		{
			jta.setText("");
		}
	}
	
	
	//Deleting a .txt file 
	void deleteAFile()
	{
		JFileChooser jfc=new JFileChooser();
		int i=jfc.showDialog(this, "Delete");
		File del=null;
		
		if(i==JFileChooser.APPROVE_OPTION)
		{
			System.out.println("Deleting in progress....");
			pause();
			del=jfc.getSelectedFile();
			if(del.getName().endsWith(".txt")) {
			  del.delete();
			  System.out.println("Deleted Successfully.");
			}
			else
			{
				System.out.println("Delete Unsuccessful!!\nThis option is for deleting .txt files only.");
			}
		}
	}
	
	
	//Saving the Files
	void savingProcedure()
	{
		text=jta.getText();
		JFileChooser jfc=new JFileChooser();
		int i=jfc.showSaveDialog(this);
		String fname=" ",path=" ";
		if(i==JFileChooser.APPROVE_OPTION)
		{
			fname=jfc.getSelectedFile().getName();
			path=jfc.getSelectedFile().getAbsolutePath();
			path=path.substring(0,path.indexOf(fname));
			SaveFile sv=new SaveFile(text,fname,path,this);
			while(!sv.go)
			{}
		}
		this.path=path;
		this.f=new File(path,this.fname);
		System.out.println("Saving in progress..");
		pause();
		if(this.fname!=" "){
			if(this.fname.endsWith(".txt"))
			{
			   this.setTitle(this.fname);
			}
			else
			{
				this.setTitle(this.fname+".txt");
			}
			
			System.out.println("File Saved Successfully!!");
			pause();
			print();
		}
		else
		{
			System.out.println("Saving Unsuccessfull\nFile not saved.");
			
		}
	}
	
	
	//Opening the Files
	void openAndLoadFile()
	{
		JFileChooser jfc=new JFileChooser();
		int i=jfc.showOpenDialog(this);
		File file=null;
		
		BufferedReader br=null;
		String path=" ";
		if(i==JFileChooser.APPROVE_OPTION)
		{
			if(jta.getText()!="")
			{
				jta.setText("");
			}
			file=jfc.getSelectedFile();
			fname=file.getName();
			path=file.getAbsolutePath();
			this.path=path.substring(0,path.indexOf(fname));
			this.setTitle(fname);
			saveas.setEnabled(false);
			savedasornot=1;
			f=new File(this.path,fname);
		}
		try {
		    br=new BufferedReader(new FileReader(f));
		    while((text=br.readLine())!=null)
			{
				jta.append(text+"\n");
			}
		}
		catch(Exception e){}
	}
	
	
	//some cache code.
    void pause()
    {
    	try {Thread.sleep(2000);}
		catch(Exception e) {}
    }
    void print()
    {
    	System.out.println("Changes Updated Enjoy Writing with NotePadZ.");
    }
    
    
    //Driver method
    public static void main(String args[]) {
    	Notepad_Code npc=new Notepad_Code("NotePadZ  1.0.1");
    	npc.setSize(800,600);
    	npc.setVisible(true);
    	npc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
