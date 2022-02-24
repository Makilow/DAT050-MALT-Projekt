package blackjack.views;

import blackjack.Observer;
import blackjack.controllers.RuleController;
import blackjack.models.MainModel;

import javax.swing.*;
import java.awt.*;

/**
 * RulePanel, the panel that showcase the rules
 *
 * @author Lukas Wigren , Mark Villarosa
 */
public class RulePanel extends JPanel implements Observer<MainModel> {

    private JTextArea panel;
    private JPanel panel1;
    private JButton returnButton;

    public RulePanel(RuleController ruleController) {
        returnButton.setActionCommand(State.MENU.toString());
        returnButton.addActionListener(ruleController);
        setLayout(new GridLayout(1, 0));
        add(panel1);
    }

    @Override
    public void update(MainModel observable) {

    }
}
