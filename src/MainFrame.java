import javax.swing.*;
// import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainFrame extends JFrame implements ActionListener{
    JPanel paneltitle, panelstartword, panelendword, panelbutton, contentPane, panelradio;
    JLabel title, label1, label2, label3;
    JTextField textField, textField2;
    JButton button;
    JRadioButton radio1, radio2, radio3;
    ImageIcon image;
    ButtonGroup group;
    ArrayList<String> wordList;
    Function function = new Function();
    Solution solution = new Solution();
    
    MainFrame(ArrayList<String> words) {    
        // Set ArrayList
        wordList = new ArrayList<String>();
        wordList = words;

        // Set icon
        image = new ImageIcon("image.png");

        // Set label
        title = new JLabel();
        title.setText("Kalin");
        title.setFont(new Font("Georgia", Font.BOLD, 30));
        title.setForeground(Color.decode("0x141414"));
        // title.setPreferredSize(new Dimension(100, 25));
        

        label1 = new JLabel();
        label1.setText("Start Word");
        // label1.setPreferredSize(new Dimension(100, 25));

        label2 = new JLabel();
        label2.setText("End Word");

        // Status pencarian
        label3 = new JLabel();

        // Set text field
        textField = new JTextField(20);
        // textField.setPreferredSize(new Dimension(100, 25));
        textField2 = new JTextField(20);

        // Set button
        button = new JButton();
        button.setText("Search");
        button.setBackground(Color.decode("0x253354"));
        button.setForeground(Color.decode("0xF2F3F4"));
        button.addActionListener(this);

        // Set RadioButton
        radio1 = new JRadioButton("UCS");
        radio1.setBackground(Color.decode("0x7587B4"));
        radio2 = new JRadioButton("Greedy Best First Search");
        radio2.setBackground(Color.decode("0x7587B4"));
        radio3 = new JRadioButton("A*");
        radio3.setBackground(Color.decode("0x7587B4"));

        // Set ButtonGroup
        group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);

        //Set panel
        paneltitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        paneltitle.setPreferredSize(new Dimension(600, 50));
        paneltitle.add(title);
        paneltitle.setBackground(Color.decode("0x7587B4"));
        // paneltitle.setBackground(Color.BLUE);

        panelstartword = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelstartword.setPreferredSize(new Dimension(250, 70));
        panelstartword.add(label1);
        panelstartword.add(textField);
        panelstartword.setBackground(Color.decode("0x7587B4"));

        panelendword = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelendword.setPreferredSize(new Dimension(250, 70));
        panelendword.add(label2);
        panelendword.add(textField2);
        panelendword.setBackground(Color.decode("0x7587B4"));
        // panelstartword.setBackground(Color.);

        panelradio = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelradio.setPreferredSize(new Dimension(800, 30));
        panelradio.add(radio1);
        panelradio.add(radio2);
        panelradio.add(radio3);
        panelradio.setBackground(Color.decode("0x7587B4"));

        panelbutton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelbutton.setPreferredSize(new Dimension(800, 50));
        panelbutton.add(button);
        panelbutton.setBackground(Color.decode("0x7587B4"));

        contentPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        contentPane.setPreferredSize(new Dimension(600, 5000));
        contentPane.setBackground(Color.decode("0xB3BDD4"));

        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 700));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setTitle("Word Ladder");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 5));
        this.getContentPane().setBackground(Color.decode("0x7587B4"));
        // this.setResizable(false);
        this.add(paneltitle);
        this.add(panelstartword);
        this.add(panelendword);
        this.add(panelradio);
        this.add(panelbutton);
        this.add(label3);
        this.add(scrollPane);
        this.setIconImage(image.getImage());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            contentPane.removeAll();
            label3.setText("   ");
            String startWord = textField.getText();
            startWord = startWord.toUpperCase();
            String endWord = textField2.getText();
            endWord = endWord.toUpperCase();
            ArrayList<String> wordList_same_length = new ArrayList<String>();
            if (startWord.length() != endWord.length() || !wordList.contains(startWord) || !wordList.contains(endWord)) {
                label3.setText("Kata yang anda memasukkan memiliki panjang yang berbeda atau tidak valid dalam kamus, silahkan masukkan kata yang benar");
            }
            else {
                wordList_same_length = function.get_same_Length(startWord, wordList);
                // System.out.println(wordList);
                if(radio1.isSelected()) {
                    label3.setText("    ");
                    UCS ucs = new UCS();
                    solution = ucs.get_path_UCS(startWord, endWord, wordList_same_length);
                } else if(radio2.isSelected()) {
                    label3.setText("    ");
                    Greedy_Best_First greedy = new Greedy_Best_First();
                    solution = greedy.get_path_UCS(startWord, endWord, wordList_same_length);
                } else if(radio3.isSelected()) {
                    label3.setText("    ");
                    Astar astar = new Astar();
                    solution = astar.get_path_Astar(startWord, endWord, wordList_same_length);
                } else {
                    label3.setText("Silahkan pilih algoritma yang ingin digunakan");
                }

                if (solution.wordList.size() == 0) {
                    JLabel path = new JLabel("Tidak ada jalur yang ditemukan");
                    path.setPreferredSize(new Dimension(600, 15));
                    contentPane.add(path);
                    JLabel time = new JLabel("Waktu yang diperlukan: " + String.format("%.4f", solution.time) + " detik");
                    time.setPreferredSize(new Dimension(600, 15));
                    contentPane.add(time);
                    JLabel node = new JLabel("Node yang dilalui: " + solution.visited_nodes);
                    node.setPreferredSize(new Dimension(600, 15));
                    contentPane.add(node);  
                    for(int i = solution.wordList.size() + 2; i < 30; i++) {
                        JLabel empty = new JLabel("                       ");
                        empty.setPreferredSize(new Dimension(600, 15));
                        contentPane.add(empty);
                    }
                }
                else {
                    for (int i = 0; i < solution.wordList.size(); i++) {
                        JLabel path = new JLabel(String.valueOf(i+1)+". "+solution.wordList.get(i));
                        path.setPreferredSize(new Dimension(600, 15));
                        contentPane.add(path);
                    }
                    JLabel time = new JLabel("Waktu yang diperlukan: " + String.format("%.4f", solution.time) + " detik");
                    time.setPreferredSize(new Dimension(600, 15));
                    contentPane.add(time);
                    JLabel node = new JLabel("Node yang dilalui: " + solution.visited_nodes);
                    node.setPreferredSize(new Dimension(600, 15));
                    contentPane.add(node);
                    for(int i = solution.wordList.size() + 2; i < 30; i++) {
                        JLabel empty = new JLabel("                       ");
                        empty.setPreferredSize(new Dimension(600, 15));
                        contentPane.add(empty);
                    }
                }
            }
            // label3.setText(wordList.get(0));
        }
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            File myObj = new File("dictionary.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.add(data.toUpperCase());
            }
            myReader.close();
            } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
        new MainFrame(words);
    }
}
