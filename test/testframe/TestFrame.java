package testframe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestFrame extends JFrame implements ActionListener {

    public static void main(String[] args) {
        TestFrame tf = new TestFrame();
        tf.initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton butTestSisteme = new JButton("Test SistemeDeEcuatiiGauss");
        butTestSisteme.addActionListener(this);
        add(butTestSisteme);

        JButton butTestRegresie = new JButton("Test Regresie");
        butTestRegresie.addActionListener(this);
        add(butTestRegresie);

        JButton butTestEcuatii = new JButton("Test Ecuatii");
        butTestEcuatii.addActionListener(this);
        add(butTestEcuatii);

        JButton butTestOrdonare = new JButton("Test Ordonare");
        butTestOrdonare.addActionListener(this);
        add(butTestOrdonare);

        JButton butTestSimplex = new JButton("Test Simplex");
        butTestSimplex.addActionListener(this);
        add(butTestSimplex);

        JButton butTestKnap = new JButton("KnapSack");
        butTestKnap.addActionListener(this);
        add(butTestKnap);

        JButton butTestCutting = new JButton("Cutting Stock");
        butTestCutting.addActionListener(this);
        add(butTestCutting);

        JButton butIesire = new JButton("Iesire");
        butIesire.addActionListener(this);
        add(butIesire);

        pack();

        // <editor-fold defaultstate="collapsed" desc="Center in Screen">      
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = 0;
        if (dim.width > w) {
            x = (dim.width - w) / 2;
        }
        int y = 0;
        if (dim.height > h) {
            y = (dim.height - h) / 2;
        }
        // Move the window
        this.setLocation(x, y);
        //</editor-fold>

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Test test = new Test();
        switch (ae.getActionCommand()) {
            case "Test SistemeDeEcuatiiGauss":
                if (test.testeSistemEcLiniare()) {
                    JOptionPane.showMessageDialog(this, "Pare corect");
                } else {
                    JOptionPane.showMessageDialog(this, "Eroare");
                }
                break;
            case "Test Regresie":
                if (test.testeRegresie()) {
                    JOptionPane.showMessageDialog(this, "Pare corect");
                } else {
                    JOptionPane.showMessageDialog(this, "Eroare");
                }
                break;
            case "Test Ecuatii":
                if (test.testeEcuatii()) {
                    JOptionPane.showMessageDialog(this, "Pare corect");
                } else {
                    JOptionPane.showMessageDialog(this, "Eroare");
                }
                break;
            case "Test Ordonare":
                test.testOrdonare();
                    JOptionPane.showMessageDialog(this, "Gata");
                break;
            case "Test Simplex":
                test.testSimplex();
                //JOptionPane.showMessageDialog(this, "Gata");
                break;
            case "KnapSack":
                test.testKnapSack();
                //JOptionPane.showMessageDialog(this, "Gata");
                break;
            case "Cutting Stock":
                test.testCutting();
                //JOptionPane.showMessageDialog(this, "Gata");
                break;
            case "Iesire":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Comanda necunoscuta:" + ae.getActionCommand());
        }
    }
}
