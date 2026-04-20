package core;

import decorators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class ClientGUI extends JFrame {
    private final Nordian_stew baseStew;
    private Nordian_stew currentStew;
    private final List<JCheckBox> checkBoxes;
    private final List<OrderRecord> orderHistory;
    private JLabel statusLabel;

    public ClientGUI(Nordian_stew baseStew) {
        this.baseStew = baseStew;
        this.currentStew = baseStew;
        this.checkBoxes = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        initGUI();
    }

    private void initGUI() {
        setTitle("Система формирования заказа (макс. " + 3 + " модификаторов)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Выбрано: 0 из " + 3, SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel combo = new JPanel();
        combo.setLayout(new GridLayout(0, 1, 5, 5));
        JLabel combotitle = new JLabel("Выберите свои модификаторы (до " + 3 + "):");
        combo.add(combotitle);

        //кнопки
        JButton but = new JButton("Оформить заказ");
        JButton scrollButton = new JButton("Свиток заказов");
        scrollButton.addActionListener(e -> showOrderScroll());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(but);
        buttonPanel.add(scrollButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JCheckBox doubleolen = new JCheckBox("Двойной олень");
        JCheckBox flame = new JCheckBox("Соус огонь");
        JCheckBox froze = new JCheckBox("Охлаждённые ягоды");
        JCheckBox lepeha = new JCheckBox("Лепёшка");

        checkBoxes.add(doubleolen);
        checkBoxes.add(flame);
        checkBoxes.add(froze);
        checkBoxes.add(lepeha);

        ItemListener checkboxListener = e -> {
            updateSelectionState();
            rebuildOrder();
        };

        for (JCheckBox cb : checkBoxes) {
            cb.addItemListener(checkboxListener);
            combo.add(cb);
        }

        but.addActionListener(e -> {
            String orderName = getCurrentOrderName();
            int price = currentStew.return_value();

            orderHistory.add(new OrderRecord(orderName, price));
            showMessageDialog(this, "Заказ оформлен!");

            //сброс чекбоксов
            for (JCheckBox cb : checkBoxes) {
                cb.setSelected(false);
            }
            updateSelectionState();
            rebuildOrder();
        });


        add(topPanel, BorderLayout.NORTH);
        add(combo, BorderLayout.CENTER);

        updateSelectionState();
        setVisible(true);
    }

    private String getCurrentOrderName() {
        List<String> mods = currentStew.return_modsList();
        if (mods.isEmpty()) return "Рагу";
        return "Рагу + " + String.join(" + ", mods);
    }

    private int getSelectedCount() {
        int count = 0;
        for (JCheckBox cb : checkBoxes) {
            if (cb.isSelected()) {
                count++;
            }
        }
        return count;
    }

    private void updateSelectionState() {
        int selectedCount = getSelectedCount();

        statusLabel.setText("Выбрано: " + selectedCount + " из " + 3);

        //чекбоксы проверяем (3 из 4)
        if (selectedCount >= 3) {
            statusLabel.setForeground(Color.RED);
            for (JCheckBox cb : checkBoxes) {
                if (!cb.isSelected()) {
                    cb.setEnabled(false);
                }
            }
        } else {
            statusLabel.setForeground(Color.BLUE);
            for (JCheckBox cb : checkBoxes) {
                cb.setEnabled(true);
            }
        }
    }

    private void rebuildOrder() {
        Nordian_stew newState = baseStew;

        for (JCheckBox cb : checkBoxes) {
            if (cb.isSelected()) {
                if (cb.getText().equals("Двойной олень")) {
                    newState = new Doubleolen(newState);
                } else if (cb.getText().equals("Соус огонь")) {
                    newState = new Flamesouse(newState);
                } else if (cb.getText().equals("Охлаждённые ягоды")) {
                    newState = new Frozeberries(newState);
                } else if (cb.getText().equals("Лепёшка")) {
                    newState = new Nordianlepeha(newState);
                }
            }
        }

        this.currentStew = newState;
        printRes();
    }

    private void printRes() {
        System.out.println("Цена: " + currentStew.return_value());
        System.out.println("Модификаторы: " + currentStew.return_modsList());
    }

    private void showOrderScroll() {
        if (orderHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет заказов");
            return;
        }

        String message = "История заказов\n";
        for (int i = 0; i < orderHistory.size(); i++) {
            message += (i + 1) + ". " + orderHistory.get(i).toString() + "\n";
        }
        JOptionPane.showMessageDialog(this, message, "Свиток заказов",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static class OrderRecord {
        private final LocalDateTime dateTime;
        private final String orderName;
        private final int price;

        public OrderRecord(String orderName, int price) {
            this.dateTime = LocalDateTime.now();
            this.orderName = orderName;
            this.price = price;
        }

        public String getFormattedDateTime() {
            return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        }

        @Override
        public String toString() {
            return String.format("[%s] %s - %d руб.", getFormattedDateTime(), orderName, price);
        }
    }
}