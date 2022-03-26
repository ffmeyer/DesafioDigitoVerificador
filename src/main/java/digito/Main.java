// 
// Decompiled by Procyon v0.5.36
// 

package digito;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main
{
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel centralPanel;
    private JPanel bottomPanel;
    private JLabel lblFile;
    private JTextField txtFile;
    private JButton btnBrowse;
    private JButton btnGenerate;
    private JFileChooser fileChooser;
    
    public static void main(final String[] args) {
        final Main main = new Main();
        main.init();
    }
    
    private void init() {
        this.frame = new JFrame("D\u00edgito verificador");
        final BorderLayout borderLayout = new BorderLayout();
        final FlowLayout flowLayoutLeft = new FlowLayout(0);
        final FlowLayout flowLayoutRight = new FlowLayout(2);
        this.mainPanel = new JPanel(borderLayout);
        this.centralPanel = new JPanel(flowLayoutLeft);
        this.bottomPanel = new JPanel(flowLayoutRight);
        this.lblFile = new JLabel("Arquivo: ");
        this.txtFile = new JTextField(20);
        (this.btnBrowse = new JButton("...")).addActionListener(new BuscarActionListener((BuscarActionListener)null));
        this.centralPanel.add(this.lblFile);
        this.centralPanel.add(this.txtFile);
        this.centralPanel.add(this.btnBrowse);
        (this.btnGenerate = new JButton("Gerar")).addActionListener(new GerarActionListener((GerarActionListener)null));
        this.bottomPanel.add(this.btnGenerate);
        this.fileChooser = new JFileChooser();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos ttx", new String[] { "ttx" });
        this.fileChooser.setFileFilter(filter);
        this.mainPanel.add(this.centralPanel, "Center");
        this.mainPanel.add(this.bottomPanel, "South");
        this.frame.setContentPane(this.mainPanel);
        this.frame.setDefaultCloseOperation(3);
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
    public static List<String> readLines(final String absolutePath) throws IOException {
        final FileReader reader = new FileReader(absolutePath);
        final BufferedReader br = new BufferedReader(reader);
        final List<String> content = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
        br.close();
        reader.close();
        return content;
    }
    
    public static void writeFile(final List<String> output, final String path) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
        for (final String s : output) {
            writer.write(String.valueOf(s) + System.getProperty("line.separator"));
        }
        writer.close();
    }
    
   public  static String generateCheckDigit(final String enrollmentNumber) throws Exception {
        if (enrollmentNumber.length() != 4) {
            throw new Exception();
        }
        int digit4 = Integer.parseInt(enrollmentNumber.substring(0, 1));
        int digit5 = Integer.parseInt(enrollmentNumber.substring(1, 2));
        int digit6 = Integer.parseInt(enrollmentNumber.substring(2, 3));
        int digit7 = Integer.parseInt(enrollmentNumber.substring(3, 4));
        digit4 *= 5;
        digit5 *= 4;
        digit6 *= 3;
        digit7 *= 2;
        final int sum = digit4 + digit5 + digit6 + digit7;
        final int mod = sum % 16;
        return String.format("%x", mod).toUpperCase();
    }
    
    public class BuscarActionListener implements ActionListener
    {
        public BuscarActionListener(BuscarActionListener buscarActionListener) {
			// TODO Auto-generated constructor stub
		}

		public void actionPerformed(final ActionEvent e) {
            final int ret = Main.this.fileChooser.showOpenDialog(null);
            if (ret == 0) {
                Main.this.txtFile.setText(Main.this.fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }
    
    public class GerarActionListener implements ActionListener
    {
        public GerarActionListener(GerarActionListener gerarActionListener) {
			// TODO Auto-generated constructor stub
		}

		public void actionPerformed(final ActionEvent e) {
            try {
                final List<String> input = readLines(Main.this.txtFile.getText());
                final List<String> output = new ArrayList<String>();
                for (int i = 1; i < input.size(); ++i) {
                    final String element = input.get(i);
                    output.add(String.valueOf(element) + "-" + generateCheckDigit(element));
                }
                final File f = new File(Main.this.txtFile.getText());
                Main.this.writeFile(output, new File(f.getParent(), "matriculasComDV.txt").getAbsolutePath());
                JOptionPane.showMessageDialog(Main.this.frame, "Arquivo gerado com sucesso.", "Sucesso", 1);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(Main.this.frame, ex, "Erro", 0);
            }
        }
    }
}