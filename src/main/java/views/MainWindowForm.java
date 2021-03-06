package views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import views.listeners.GuiElemListener;
import views.listeners.MenuOpenListener;
import views.listeners.MenuSaveListener;
import views.map.MapApplet;
import views.utils.AlgorithmSelectionHelper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class MainWindowForm extends JFrame {

    // FIXME Remove these initializers, I put them here so it won't crash, but
    // it's definitely wrong
    private static final long serialVersionUID = -372666681152456536L;
    private JPanel mainPanel = new JPanel();
    private JButton generateDistributionButton = new JButton();
    private final JSpinner btsNumberSpinner = new JSpinner();
    private JTabbedPane mainTabPanel;
    private JSpinner numberOfSubscriberCenters = new JSpinner();
    private JRadioButton alg1;
    private JRadioButton alg2;
    private JRadioButton greedyRadioButton;
    private JRadioButton mixedSubscriberCenterRadioButton;
    private JRadioButton evolutionaryRadioButton;
    private MapApplet mapApplet;

    public MainWindowForm() {
        $$$setupUI$$$();
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(ResourceBundle.getBundle("language").getString("Application_title"));
        pack();
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        mapApplet = new MapApplet(mainTabPanel.getSize());
        setJMenuBar(createJMenuBar());
        GuiElemListener guiElemListener = new GuiElemListener(btsNumberSpinner, numberOfSubscriberCenters, mapApplet);
        btsNumberSpinner.addChangeListener(guiElemListener);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setMinimum(0);
        spinnerModel.setValue(30);
        btsNumberSpinner.setModel(spinnerModel);
        SpinnerNumberModel subscriberSpinnerModel = new SpinnerNumberModel();
        subscriberSpinnerModel.setMinimum(0);
        subscriberSpinnerModel.setValue(5);
        numberOfSubscriberCenters.addChangeListener(guiElemListener);
        numberOfSubscriberCenters.setModel(subscriberSpinnerModel);
        generateDistributionButton.addActionListener(guiElemListener);
        ButtonGroup buttonGroup = new ButtonGroup();
        alg1.setSelected(true);
        buttonGroup.add(alg1);
        buttonGroup.add(alg2);
        buttonGroup.add(greedyRadioButton);
        buttonGroup.add(mixedSubscriberCenterRadioButton);
        buttonGroup.add(evolutionaryRadioButton);
        AlgorithmSelectionHelper.getInstance().initialize(alg1, alg2, greedyRadioButton, mixedSubscriberCenterRadioButton, evolutionaryRadioButton);
        mainTabPanel.addTab("Map", mapApplet);
    }

    private JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.add(createOpenMenuItem());
        menu.add(createSaveMenuItem());
        return menu;
    }

    private JMenuItem createSaveMenuItem() {
        JMenuItem saveFile = new JMenuItem(ResourceBundle.getBundle("language").getString(
                "MenuBar_file_saveFile"));
        saveFile.addActionListener(new MenuSaveListener(mainPanel, mapApplet));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        return saveFile;
    }

    private JMenuItem createOpenMenuItem() {
        JMenuItem openFile = new JMenuItem(ResourceBundle.getBundle("language").getString(
                "MenuBar_file_openFile"));

        openFile.addActionListener(new MenuOpenListener(numberOfSubscriberCenters, btsNumberSpinner, mapApplet));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        return openFile;
    }

    @SuppressWarnings("unused")
    private void createUIComponents() {
        // IntelliJ requires this method, do not delete it
    }

    public static void main(String[] args) {
        setLookAndFeel("Nimbus");
        new MainWindowForm();

    }

    private static void setLookAndFeel(String lookAndFeel) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMinimumSize(new Dimension(445, 600));
        mainPanel.setOpaque(true);
        mainPanel.setPreferredSize(new Dimension(1000, 800));
        mainPanel.setRequestFocusEnabled(true);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ResourceBundle.getBundle("language").getString("BTS_configuration_panel"), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        panel1.add(btsNumberSpinner, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 10), null, null, 0, false));
        final JLabel label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("language").getString("BTS_number"));
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberOfSubscriberCenters = new JSpinner();
        panel1.add(numberOfSubscriberCenters, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 10), null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Number of subscriber centers");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alg1 = new JRadioButton();
        alg1.setLabel("Random");
        alg1.setText("Random");
        panel1.add(alg1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alg2 = new JRadioButton();
        alg2.setLabel("Place bts in subscriber center");
        alg2.setText("Place bts in subscriber center");
        panel1.add(alg2, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        greedyRadioButton = new JRadioButton();
        greedyRadioButton.setSelected(false);
        greedyRadioButton.setText("Greedy");
        panel1.add(greedyRadioButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mixedSubscriberCenterRadioButton = new JRadioButton();
        mixedSubscriberCenterRadioButton.setText("Mixed subscriber center");
        panel1.add(mixedSubscriberCenterRadioButton, new GridConstraints(6, 0, 2, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateDistributionButton = new JButton();
        generateDistributionButton.setActionCommand(ResourceBundle.getBundle("language").getString("Generate_random_BTS_distribution"));
        generateDistributionButton.setLabel(ResourceBundle.getBundle("language").getString("Generate_random_BTS_distribution"));
        generateDistributionButton.setText("Generate BTS distribution");
        panel1.add(generateDistributionButton, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        evolutionaryRadioButton = new JRadioButton();
        evolutionaryRadioButton.setText("Evolutionary");
        panel1.add(evolutionaryRadioButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainTabPanel = new JTabbedPane();
        mainPanel.add(mainTabPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        label1.setLabelFor(btsNumberSpinner);
        label2.setLabelFor(numberOfSubscriberCenters);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
