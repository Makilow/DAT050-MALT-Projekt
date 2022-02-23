package blackjack.views;

import blackjack.Observer;
import blackjack.controllers.SettingController;
import blackjack.models.MainModel;

import javax.swing.*;

/**
 * SettingPanel, the panel that showcase the settings
 *
 * @author Lukas Wigren
 */
public class SettingPanel extends JPanel implements Observer<MainModel> {

    public SettingPanel(SettingController settingController) {

    }

    @Override
    public void update(MainModel observable) {

    }
}
