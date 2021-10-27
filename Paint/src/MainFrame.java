import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends javax.swing.JFrame implements MouseListener {
    private JButton buttonDortgen;
    private JButton geriButton;
    private JButton cizgiButton;
    private JButton cemberButton;
    private JPanel panel1;
    private JPanel menu;
    private JButton deleteAllButton;
    private JRadioButton siyahButton;
    private JRadioButton kirmiziButton;
    private JRadioButton yesilButton;
    private JRadioButton maviButton;
    private JButton silgiButton;
    int objectType;
    Color colorType = new Color(0,0,0);
    int x1,y1;
    int x2,y2;
    Storage storage = new Storage();

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(int i=0;i<storage.shapes.length;i++){
            if(storage.shapes[i]==null){
                continue;
            }
            if(storage.shapes[i] instanceof Dortgen){
                Dortgen d = (Dortgen) storage.shapes[i];
                g.setColor(d.renk);
                g.drawRect(d.x,d.y,d.w,d.h);
            }
            if(storage.shapes[i] instanceof Cember){
                Cember d = (Cember) storage.shapes[i];
                g.setColor(d.renk);
                g.drawOval(d.x,d.y,d.w,d.h);
            }
            if(storage.shapes[i] instanceof Cizgi){
                Cizgi d = (Cizgi) storage.shapes[i];
                g.setColor(d.renk);
                g.drawLine(d.x1,d.y1,d.x2,d.y2);
            }
        }
    }
    public MainFrame(){
        add(panel1);
        addMouseListener(this);
        buttonDortgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectType = 1;
            }
        });
        cemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectType = 2;
            }
        });
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastindex1 = storage.shapes.length-1;
                for (int lastindex=lastindex1;lastindex>=0;lastindex--){
                    if(storage.shapes[lastindex] instanceof NullShape || storage.shapes[lastindex] == null){
                        continue;
                    }else{
                        storage.remove(lastindex);
                        repaint();
                        break;
                    }
                }
            }
        });
        cizgiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectType = 3;
            }
        });
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.deleteAll();
                repaint();
            }
        });
        siyahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color myBlack = new Color(0,0,0);
                colorType = myBlack;
            }
        });
        kirmiziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color myRed = new Color(255,0,0);
                colorType = myRed;
            }
        });
        yesilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color myGreen = new Color(0,255,0);
                colorType = myGreen;
            }
        });
        maviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color myBlue = new Color(0,0,255);
                colorType = myBlue;
            }
        });
        silgiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objectType = 4;
            }
        });
    }

    public static void FrameStartUp() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); /* Windowsun componentlerini aldık */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();

                frame.setResizable(false);
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                frame.setVisible(true);
            }
        });
    }

    public static void main(String args[]) {
        FrameStartUp();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0;i<storage.shapes.length;i++){
            if(storage.shapes[i]!=null)
            System.out.println(storage.shapes[i]+" "+i);
        }
        if(x1==0 && y1==0){
            x1 = e.getX();
            y1 = e.getY();
            if(objectType==4){
                int index=storage.shapes.length-1;
                for(;index>=0;index--){
                    if(storage.shapes[index] instanceof Dortgen){
                        Dortgen d = (Dortgen) storage.shapes[index];
                        if(x1>d.x&&x1<d.x+d.w&&y1>d.y&&y1<d.y+d.h){
                            storage.remove(index);
                            System.out.println("sildi1");
                            break;
                        }
                    }
                    else if (storage.shapes[index] instanceof Cember){
                        Cember d = (Cember) storage.shapes[index];
                        if(x1>d.x&&x1<d.x+d.w&&y1>d.y&&y1<d.y+d.h){
                            storage.remove(index);
                            System.out.println("sildi2");
                            break;
                        }
                    }
                    else if(storage.shapes[index] instanceof Cizgi){
                        Cizgi d = (Cizgi) storage.shapes[index];
                        if(x1>d.x1&&x1<d.x2&&y1>d.y1&&y1<d.y2){
                            storage.remove(index);
                            System.out.println("sildi3");
                            break;
                        }
                    }
                    repaint();

                }
                x1 = 0;
                y1 = 0;
            }
            System.out.println("1.tıklama");
        }else{
            int x,y,w,h;
            x2 = e.getX();
            y2 = e.getY();
            System.out.println("2.tıklama");
            if(x1>x2){
                x = x2;
            }else{
                x = x1;
            }
            if(y1>y2){
                y = y2;
            }else{
                y = y1;
            }
            w = Math.abs(x1-x2);
            h = Math.abs(y1-y2);
            if(objectType==1){
                Dortgen dortgen = new Dortgen();
                dortgen.x = x;
                dortgen.y = y;
                dortgen.w = w;
                dortgen.h = h;
                dortgen.renk = colorType;
                storage.add(dortgen);
            }
            else if(objectType==2){
                Cember cember = new Cember();
                cember.x = x;
                cember.y = y;
                cember.w = w;
                cember.h = h;
                cember.renk = colorType;
                storage.add(cember);
            }
            else if(objectType==3){
                Cizgi cizgi = new Cizgi();
                cizgi.x1 = x1;
                cizgi.y1 = y1;
                cizgi.x2 = x2;
                cizgi.y2 = y2;
                cizgi.renk = colorType;
                storage.add(cizgi);
            }
            x1=0;
            y1=0;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
