package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ATM extends JFrame implements ActionListener {

   private JTextField amountField;
   private JPasswordField pinField;
   private JButton withdrawButton,depositButton,changePinButton,savingsButton;
   private JLabel balanceLabel,savingsLabel;
   private int balance = 5000,savingsBalance = 0;
   private String currentPin = "1234";

   public ATM() {
      setTitle("ATM Machine");
      setSize(400, 350);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(7, 2));

      JLabel amountLabel = new JLabel("Enter amount:");
      amountField = new JTextField(10);

      JLabel pinLabel = new JLabel("Enter PIN:");
      pinField = new JPasswordField(10);

      JLabel newPinLabel = new JLabel("New PIN:");
      JPasswordField newPinField = new JPasswordField(10);

      withdrawButton = new JButton("Withdraw from Checking");
      withdrawButton.addActionListener(this);

      depositButton = new JButton("Deposit to Checking");
      depositButton.addActionListener(this);

      changePinButton = new JButton("Change PIN");
      changePinButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String currentPinInput = new String(pinField.getPassword());
            if (!currentPinInput.equals(currentPin)) {
               JOptionPane.showMessageDialog(null, "Incorrect current PIN!");
            } else {
               currentPin = new String(newPinField.getPassword());
               JOptionPane.showMessageDialog(null, "PIN changed successfully.");
            }
         }
      });

      savingsButton = new JButton("Access Savings Account");
      savingsButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String savingsPin = JOptionPane.showInputDialog("Enter your savings account PIN:");
            if (savingsPin.equals(currentPin)) {
               JOptionPane.showMessageDialog(null, "Savings balance: $" + savingsBalance);
            } else {
               JOptionPane.showMessageDialog(null, "Incorrect PIN!");
            }
         }
      });

      balanceLabel = new JLabel("Checking Balance: $" + balance);
      savingsLabel = new JLabel("Savings Balance: $" + savingsBalance);

      panel.add(amountLabel);
      panel.add(amountField);
      panel.add(pinLabel);
      panel.add(pinField);
      panel.add(newPinLabel);
      panel.add(newPinField);
      panel.add(withdrawButton);
      panel.add(depositButton);
      panel.add(changePinButton);
      panel.add(savingsButton);
      panel.add(balanceLabel);
      panel.add(savingsLabel);

      add(panel);
      setVisible(true);
   }

   public void actionPerformed(ActionEvent e) {
      int amount = Integer.parseInt(amountField.getText());
      @SuppressWarnings("unused")
	String pin = new String(pinField.getPassword());
      if (e.getSource() == withdrawButton) {
         if (balance < amount) {
            JOptionPane.showMessageDialog(null, "Insufficient funds!");
         } else {
            balance -= amount;
            balanceLabel.setText("Checking Balance: $" + balance);
            JOptionPane.showMessageDialog(null, "Please take your cash.");
         }
      } else if (e.getSource() == depositButton) {
         balance += amount;
         balanceLabel.setText("Checking Balance: $" + balance);
         JOptionPane.showMessageDialog(null, "Deposit complete.");
      }
   }

   public static void main(String[] args) {
      new ATM();
   }
}
