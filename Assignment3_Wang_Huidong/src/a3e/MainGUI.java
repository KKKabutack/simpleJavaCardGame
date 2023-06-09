package a3e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the GUI class. It contains all JComponents.
 * @auther Wang Huidong
 * @version 1.0.0
 * @since 04.04.2023
 */

public class MainGUI{
    ImageIcon Image1, Image2, Image3, Image4, Image5, Image6;
    private static final ArrayList<String> imageNumber = new ArrayList<>();
    JLabel label_Image1, label_Image2, label_Image3, label_Image4, label_Image5, label_Image6, bet, amount, info;
    JPanel MainPanel, DealerPanel, ButtonPanel, InfoPanel, RpCardBtnPanel, PlayerPanel;
    JTextField t1;
    JButton replaceButton1, replaceButton2, replaceButton3, start, result;
    JMenu menu;
    JMenuBar menuBar;
    JMenuItem menuItem;
    JFrame frame;
    static boolean rp1Used = false, rp2Used = false, rp3Used = false;
    private int betMoney;
    private static int remainingMoney = 100;

    /**
     * This function initiates the default UI of the simple card game.
     */
    public void go(){
        // Image 1
        Image1 = new ImageIcon("Images/card_back.gif");
        label_Image1 = new JLabel();
        label_Image1.setIcon(Image1);
        // Image 2
        Image2 = new ImageIcon("Images/card_back.gif");
        label_Image2 = new JLabel();
        label_Image2.setIcon(Image2);
        // Image 3
        Image3 = new ImageIcon("Images/card_back.gif");
        label_Image3 = new JLabel();
        label_Image3.setIcon(Image3);
        // Image 4
        Image4 = new ImageIcon("Images/card_back.gif");
        label_Image4 = new JLabel();
        label_Image4.setIcon(Image4);
        // Image 5
        Image5 = new ImageIcon("Images/card_back.gif");
        label_Image5 = new JLabel();
        label_Image5.setIcon(Image5);
        // Image 6
        Image6 = new ImageIcon("Images/card_back.gif");
        label_Image6 = new JLabel();
        label_Image6.setIcon(Image6);

        MainPanel = new JPanel();
        DealerPanel = new JPanel();
        ButtonPanel = new JPanel();
        InfoPanel = new JPanel();
        RpCardBtnPanel = new JPanel();
        PlayerPanel = new JPanel();

        DealerPanel.add(label_Image1);
        DealerPanel.add(label_Image2);
        DealerPanel.add(label_Image3);
        PlayerPanel.add(label_Image4);
        PlayerPanel.add(label_Image5);
        PlayerPanel.add(label_Image6);

        replaceButton1 = new JButton("Replace 1");
        replaceButton2 = new JButton("Replace 2");
        replaceButton3 = new JButton("Replace 3");
        replaceButton1.addActionListener(new replaceListener1());
        replaceButton2.addActionListener(new replaceListener2());
        replaceButton3.addActionListener(new replaceListener3());
        replaceButton1.setEnabled(false);
        replaceButton2.setEnabled(false);
        replaceButton3.setEnabled(false);
        RpCardBtnPanel.add(replaceButton1);
        RpCardBtnPanel.add(replaceButton2);
        RpCardBtnPanel.add(replaceButton3);

        // Button Panel
        bet = new JLabel("Bet $");
        t1 = new JTextField(10);
        start = new JButton("Start");
        start.addActionListener(new startListener());
        result = new JButton("Result");
        result.addActionListener(new resultListener());
        result.setEnabled(false);
        ButtonPanel.add(bet);
        ButtonPanel.add(t1);
        ButtonPanel.add(start);
        ButtonPanel.add(result);

        // Info Panel
        info = new JLabel("Please place your bet! The amount of money you have: $");
        amount = new JLabel("100");
        InfoPanel.add(info);
        InfoPanel.add(amount);

        MainPanel.setLayout(new GridLayout(5,1));
        MainPanel.add(DealerPanel); // add DealerPanel to MainPanel
        MainPanel.add(PlayerPanel);
        MainPanel.add(RpCardBtnPanel);
        MainPanel.add(ButtonPanel);
        MainPanel.add(InfoPanel);
        // set Menu
        menuBar = new JMenuBar();
        menu = new JMenu("Control");
        menuItem = new JMenuItem("Exit");
        menu.add(menuItem);
        menuBar.add(menu);
        menuItem.addActionListener(new exitListener());

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(MainPanel);
        frame.setJMenuBar(menuBar);
        frame.setTitle("A Simple Card Game");
        frame.setSize(400, 700);
        frame.setVisible(true);
    }

    /**
     * This inner class responds to button "start" when it is clicked.
     * @auther Wang Huidong
     * @version 1.0.0
     * @since 04.04.2023
     */
    class startListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String address = "Images/card_";

            ArrayList<String> playerHandCard = pickACard();
            String finalAddress = address + playerHandCard.get(0);

            Image1 = new ImageIcon("Images/card_back.gif");
            label_Image1.setIcon(Image1);
            // Image 2
            Image2 = new ImageIcon("Images/card_back.gif");
            label_Image2.setIcon(Image2);
            // Image 3
            Image3 = new ImageIcon("Images/card_back.gif");
            label_Image3.setIcon(Image3);

            Image4 = new ImageIcon(finalAddress);
            label_Image4.setIcon(Image4);
            finalAddress = address + playerHandCard.get(1);
            Image5 = new ImageIcon(finalAddress);
            label_Image5.setIcon(Image5);
            finalAddress = address + playerHandCard.get(2);
            Image6 = new ImageIcon(finalAddress);
            label_Image6.setIcon(Image6);
            betMoney = Integer.parseInt(t1.getText());
            // Debug Use
            System.out.println("User's input: " + betMoney);
            // set buttons statuses
            start.setEnabled(false);
            result.setEnabled(true);
            replaceButton1.setEnabled(true);
            replaceButton2.setEnabled(true);
            replaceButton3.setEnabled(true);
            rp1Used = false;
            rp2Used = false;
            rp3Used = false;
            // set text
            info.setText("Current bet is: $ " + betMoney + " Amount of money you have: $");
            
        }

        /**
         * This function generates an ArrayList storing the filenames with reference address.
         * @return arr, is an ArrayList containing the effective addresses String of cards to be displayed
         */
        private static ArrayList<String> pickACard(){
            // This function helps to generate 3 random and non-redundant image addresses.
            ArrayList<String> arr = new ArrayList<>();
            while (arr.size() < 3) {
                String res = "";
                Random r = new Random();
                int i = r.nextInt(4) + 1;
                res += Integer.toString(i);
                i = r.nextInt(13) + 1;
                res += Integer.toString(i);
                res += ".gif";
                imageNumber.add(res);
                if (!arr.contains(res)){
                    arr.add(res);
                }
            }
            return arr;
        }
    }

    /**
     * This inner class responds to button "Replace 1" when clicked.
     * @auther Wang Huidong
     * @version 1.0.0
     * @since 04/04/2023
     */
    class replaceListener1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // get a new Icon by constructing the address.
            // the address should not be the same as the other two.
            String address = "Images/card_";
            Random r = new Random();
            int suit = r.nextInt(4) + 1;
            int value = r.nextInt(13) + 1;
            String finalAddress = address + Integer.toString(suit) + Integer.toString(value) + ".gif";
            // if the randomly generated result is the same as the other two
            while (finalAddress.equals(Image5.getDescription()) || finalAddress.equals(Image6.getDescription())){
                suit = r.nextInt(4) + 1;
                value = r.nextInt(13) + 1;
                finalAddress = address + Integer.toString(suit) + Integer.toString(value) + ".gif";
            }
            // update Icon.
            Image4 = new ImageIcon(finalAddress);
            label_Image4.setIcon(Image4);
            replaceButton1.setEnabled(false);
            rp1Used = true;
            // checking the times of replace button being used
            if (rp2Used || rp3Used){
                if(rp2Used){
                    replaceButton3.setEnabled(false);
                }else{
                    replaceButton2.setEnabled(false);
                }
            }

        }
    }

    /**
     * This inner class responds to button "Replace 2" when clicked
     * @auther Wang Huidong
     * @version 1.0.0
     * @since 04.04.2023
     */
    class replaceListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // get a new Icon by constructing the address.
            // the address should not be the same as the other two.
            String address = "Images/card_";
            Random r = new Random();
            int suit = r.nextInt(4) + 1;
            int value = r.nextInt(13) + 1;
            String finalAddress = address + Integer.toString(suit) + Integer.toString(value) + ".gif";
            // if the randomly generated result is the same as the other two
            while (finalAddress.equals(Image4.getDescription()) || finalAddress.equals(Image6.getDescription())){
                suit = r.nextInt(4) + 1;
                value = r.nextInt(13) + 1;
                finalAddress = address + Integer.toString(suit) + Integer.toString(value) + ".gif";
            }
            // update Icon.
            Image5 = new ImageIcon(finalAddress);
            label_Image5.setIcon(Image5);
            replaceButton2.setEnabled(false);
            rp2Used = true;
            // checking the times of replace button being used
            if (rp1Used || rp3Used){
                if(rp1Used){
                    replaceButton3.setEnabled(false);
                }else{
                    replaceButton1.setEnabled(false);
                }
            }
        }
    }
    /**
     * This inner class responds to button "Replace 3" when clicked
     * @auther Wang Huidong
     * @version 1.0.0
     * @since 04.04.2023
     */
    class replaceListener3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // get a new Icon by constructing the address.
            // the address should not be the same as the other two.
            String address = "Images/card_";
            Random r = new Random();
            int suit = r.nextInt(4) + 1;
            int value = r.nextInt(13) + 1;
            String finalAddress = address + Integer.toString(suit) + Integer.toString(value) + ".gif";
            // if the randomly generated result is the same as the other two
            while (finalAddress.equals(Image4.getDescription()) || finalAddress.equals(Image5.getDescription())){
                suit = r.nextInt(4) + 1;
                value = r.nextInt(13) + 1;
                finalAddress = address + Integer.toString(suit) + Integer.toString(value) + ".gif";
            }
            // update Icon.
            Image6 = new ImageIcon(finalAddress);
            label_Image6.setIcon(Image6);
            replaceButton3.setEnabled(false);
            rp3Used = true;
            // checking the times of replace button being used
            if (rp1Used || rp2Used){
                if(rp2Used){
                    replaceButton1.setEnabled(false);
                }else{
                    replaceButton2.setEnabled(false);
                }
            }
        }
    }
    /**
     * This inner class responds to button "result" when clicked
     * @auther Wang Huidong
     * @version 1.0.0
     * @since 04.04.2023
     */
    class resultListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Base address
            String address = "Images/card_";

            ArrayList<String> dealerHandList = pickACard();
            String finalAddress = address + dealerHandList.get(0);
            Image1 = new ImageIcon(finalAddress);
            label_Image1.setIcon(Image1);
            finalAddress = address + dealerHandList.get(1);
            Image2 = new ImageIcon(finalAddress);
            label_Image2.setIcon(Image2);
            finalAddress = address + dealerHandList.get(2);
            Image3 = new ImageIcon(finalAddress);
            label_Image3.setIcon(Image3);
            boolean win = whoWins();
            if (win){
                remainingMoney += betMoney;
                amount.setText(Integer.toString(remainingMoney));
                JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!"); // pop-up window
            }else{
                remainingMoney -= betMoney;
                amount.setText(Integer.toString(remainingMoney));
                JOptionPane.showMessageDialog(frame, "Sorry! The Dealer wins this round!"); // pop-up window
            }
            // reset
            imageNumber.clear();
            result.setEnabled(false);
            start.setEnabled(true);
            replaceButton1.setEnabled(false);
            replaceButton2.setEnabled(false);
            replaceButton3.setEnabled(false);
            // reset the infoPanel's text
            info.setText("Please place your bet! Amount of money you have: $");
            // if a player loses all the money
            if (remainingMoney <= 0){
                JOptionPane.showMessageDialog(frame, "Game Over!\n You have no more money!\n Please start a new game!");
                start.setEnabled(false);
                result.setEnabled(false);
                replaceButton1.setEnabled(false);
                replaceButton2.setEnabled(false);
                replaceButton3.setEnabled(false);
                info.setText("You have no more money! Please start a new game!");
                amount.setText("");
            }
        }

        /**
         * This function pick random cards for Dealer
         * @return resList, an ArrayList<String> containing the addresses of cards.
         */
        private ArrayList<String> pickACard(){
            ArrayList<String> resList = new ArrayList<>();
            while (resList.size() < 3){
                String res = "";
                Random r = new Random();
                int i = r.nextInt(4) + 1;
                res += Integer.toString(i);
                i = r.nextInt(13) + 1;
                res += Integer.toString(i);
                res += ".gif";
                if (!resList.contains(res) && !imageNumber.contains(res)){
                    resList.add(res);
                }
            }
            return resList;
        }
    }
    /**
     * This inner class responds to menu bar when clicked. It simply exits the program.
     * @auther Wang Huidong
     * @version 1.0.0
     * @since 04.04.2023
     */
    static class exitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * This function check who wins
     * @return boolean value, if player wins, it returns true.
     */
    public boolean whoWins(){
        // this function calculates who wins
        // get all card values first
        int dealerSpeCount = 0;
        int playerSpeCount = 0;
        String address1 = Image1.getDescription();
        address1 = address1.substring(13);
        address1 = address1.replaceAll("[^0-9]", "");
        int card1 = Integer.parseInt(address1);

        System.out.print("card1: ");
        System.out.println(card1);

        if (card1 > 10) {
            dealerSpeCount++;
            card1 = 0;
        }

        String address2 = Image2.getDescription();
        address2 = address2.substring(13);
        address2 = address2.replaceAll("[^0-9]", "");
        int card2 = Integer.parseInt(address2);

        System.out.print("card2: ");
        System.out.println(card2);

        if (card2 > 10) {
            dealerSpeCount++;
            card2 = 0;
        }

        String address3 = Image3.getDescription();
        address3 = address3.substring(13);
        address3 = address3.replaceAll("[^0-9]", "");
        int card3 = Integer.parseInt(address3);

        System.out.print("card3: ");
        System.out.println(card3);

        if (card3 > 10) {
            dealerSpeCount++;
            card3 = 0;
        }

        String address4 = Image4.getDescription();
        address4 = address4.substring(13);
        address4 = address4.replaceAll("[^0-9]", "");
        int card4 = Integer.parseInt(address4);

        System.out.print("card4: ");
        System.out.println(card4);

        if (card4 > 10) {
            playerSpeCount++;
            card4 = 0;
        }

        String address5 = Image5.getDescription();
        address5 = address5.substring(13);
        address5 = address5.replaceAll("[^0-9]", "");
        int card5 = Integer.parseInt(address5);

        System.out.print("card5: ");
        System.out.println(card5);

        if (card5 > 10) {
            playerSpeCount++;
            card5 = 0;
        }

        String address6 = Image6.getDescription();
        address6 = address6.substring(13);
        address6 = address6.replaceAll("[^0-9]", "");
        int card6 = Integer.parseInt(address6);

        System.out.print("card6: ");
        System.out.println(card6);

        if (card6 > 10) {
            playerSpeCount++;
            card6 = 0;
        }

        int dealerTotal = (card1 + card2 + card3) % 10;
        System.out.println("DealerTotal: " + dealerTotal);
        int playerTotal = (card4 + card5 + card6) % 10;
        System.out.println("PlayerTotal: " + playerTotal);
        if (dealerSpeCount == playerSpeCount){
            System.out.print("Dealer special count: ");
            System.out.println(dealerSpeCount);
            System.out.print("Player special count: ");
            System.out.println(playerSpeCount);
            return dealerTotal < playerTotal;
        }
        return dealerSpeCount < playerSpeCount;

    }
}