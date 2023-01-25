import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class editor extends JFrame implements ActionListener{

    //frame object
    JFrame f;
    //text object
    JTextArea t;

    //constructor
    editor(){
        //initialise frame to editor
        f = new JFrame("Text Editor");
        try{
            //set theme
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){}

        //initialize text area
        t = new JTextArea();

        // Initialize menu bar;
        JMenuBar mb = new JMenuBar();
        //create menu
        JMenu m1 = new JMenu("File");

        //create menu items for the file menu;
        JMenuItem i1 = new JMenuItem("New");
        JMenuItem i2 = new JMenuItem("Open");
        JMenuItem i3 = new JMenuItem("Save");
        JMenuItem i4 = new JMenuItem("Print");

        //adding actionListener to menu items;
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);

        //adding menu items to the desired menu;
        m1.add(i1);
        m1.add(i2);
        m1.add(i3);
        m1.add(i4);

        //create menu
        JMenu m2 = new JMenu("Edit");

        //create menu items for the edit menu

        JMenuItem i5 = new JMenuItem("Cut");
        JMenuItem i6 = new JMenuItem("Copy");
        JMenuItem i7 = new JMenuItem("Paste");

        //add action listener
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);

        //add menu items to desired menu
        m2.add(i5);
        m2.add(i6);
        m2.add(i7);

        //create close button in menu bar
        JMenuItem c = new JMenuItem("Close");

        c.addActionListener(this);

        //adding all the menu to menubar
        mb.add(m1);
        mb.add(m2);
        mb.add(c);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500,500);
        f.show();

    }
    public void actionPerformed(ActionEvent e){

        //exctract the button pressed by user into string s
        String s = e.getActionCommand();

        if(s.equals("New")){

        }
        else if(s.equals("Open")){
            JFileChooser j = new JFileChooser("D:");

            int r = j.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    String s1 = "" , s2 = "";

                    FileReader read = new FileReader(fi);

                    BufferedReader buffer = new BufferedReader(read);

                    s1 = buffer.readLine();

                    while ((s2 = buffer.readLine()) != null){
                        s1 = s1 + "\n" + s2;
                    }
                    t.setText(s1);
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }

            }
            else{
                JOptionPane.showMessageDialog(f,"User has Cancelled the operation");
            }

        }
        else if(s.equals("Print")){
            try{
                t.print();
            }
            catch (Exception evt){
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }

        }
        else if(s.equals("Save")){
            JFileChooser j = new JFileChooser("C:");

            //invoke the save dialog box
            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                //set file label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //create filewriter - writes to the existing files
                    FileWriter write = new FileWriter(fi, false);

                    //create buffered Writer - creates the place wr u write the data
                    BufferedWriter buffer = new BufferedWriter(write);

                    buffer.write(t.getText());

                    buffer.flush();
                    buffer.close();


                }catch(Exception ev){
                    JOptionPane.showMessageDialog(f, ev.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f, "User has cancelled the operation");
            }
        }
        else if(s.equals("Cut")){
            t.cut();
        }
        else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }
        else if(s.equals("Close")){
            f.setVisible(false);

        }

    }


    public static void main(String[] args) {
        editor e = new editor();
    }

}
