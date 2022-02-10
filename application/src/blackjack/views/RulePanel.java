package blackjack.views;

import blackjack.controllers.RuleController;
import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
/**
 * RulePanel, the panel that showcase the rules
 * @author Lukas Wigren
 */
public class RulePanel extends JPanel implements Observer<MainModel> {

    public RulePanel(RuleController ruleController) {
        
    }

    @Override
    public void update(MainModel observable) {

    }
}
