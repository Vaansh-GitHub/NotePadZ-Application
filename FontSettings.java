import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
@SuppressWarnings("serial")
public class FontSettings extends JFrame implements ItemListener,ActionListener{
	Container c;
	Notepad_Code npc;
	String family="Arial";
	int size=20,style=Font.PLAIN;
	JRadioButton bold,italic,plain;
	Font font;
	JButton but;
	@SuppressWarnings("rawtypes")
	JComboBox availablefamilies,availablesizes;
	ButtonGroup bg=new ButtonGroup();
     FontSettings(String title,Font f,Notepad_Code npc)
     {
    	 super(title);
    	 this.npc=npc;
    	 setLayout(null);
    	 c=getContentPane();
    	 this.style=f.getStyle();
    	 this.size=f.getSize();
    	 this.family=f.getFamily();
    	 setSize(700,500);
    	 setResizable(false);
    	 setVisible(true);
    	 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	 
    	 GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    	 Integer arr[]=new Integer[6];
    	 int j=10;
    	 for(int i=0;i<arr.length;i++)
    	 {
    		 arr[i]=j;
    		 j+=5;
    	 }
    	 bold=new JRadioButton("BOLD");
     	 italic=new JRadioButton("ITALIC");
     	 plain=new JRadioButton("PLAIN");
     	 but=new JButton("OK");
     	 availablefamilies=new JComboBox<String>(ge.getAvailableFontFamilyNames());
     	 availablesizes=new JComboBox<Integer>(arr);
     	
     	 
     	 availablefamilies.setSelectedItem(family);
     	 availablesizes.setSelectedItem(size);
 
     	 bg.add(bold);
     	 bg.add(italic);
     	 bg.add(plain);
     	 if(style==Font.PLAIN)
     	 {
     		 plain.setSelected(true);
     	 }
     	 else if(style==Font.ITALIC)
    	 {
    		 italic.setSelected(true);
    	 }
     	 else
    	 {
    		 bold.setSelected(true);
    	 }
     	 but.setBounds(300,400,100,30);
     	 availablefamilies.setBounds(100,200,150,30);
     	 availablesizes.setBounds(500,200,100,30);
     	 bold.setBounds(100,100,100,50);
     	 plain.setBounds(300,100,100,50);
     	 italic.setBounds(500,100,100,50);
     	 
     	 but.addActionListener(this);
     	 bold.addItemListener(this);
     	 italic.addItemListener(this);
     	 plain.addItemListener(this);
     	 availablefamilies.addItemListener(this);
     	 availablesizes.addItemListener(this);
     	 
     	 c.add(but);
     	 c.add(availablefamilies);
     	 c.add(availablesizes);
     	 c.add(bold);
     	 c.add(italic);
     	 c.add(plain);
     	 
     	availablefamilies.setToolTipText("Font Families");
     	availablesizes.setToolTipText("Font Sizes");
     }

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(bold.getModel().isSelected())
        {
	        style=Font.BOLD;
        }
		if(italic.getModel().isSelected())
        {
	        style=Font.ITALIC;
        }
		if(plain.getModel().isSelected())
        {
	        style=Font.PLAIN;
        }
		family=(String)availablefamilies.getSelectedItem();
		size=(Integer)availablesizes.getSelectedItem();
		font=new Font(family,style,size);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		family=(String)availablefamilies.getSelectedItem();
		size=(Integer)availablesizes.getSelectedItem();
		font=new Font(family,style,size);
		returnFontType();
	}
	
	
    void returnFontType()
	{
		npc.jta.setFont(font);
		npc.size=size;
		npc.family=family;
		npc.font=font;
		this.setVisible(false);
	}
}
