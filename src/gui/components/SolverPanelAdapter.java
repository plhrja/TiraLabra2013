package gui.components;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SolverPanelAdapter extends JPanel {

    private static final String[] HEURISTIC = {"Manhattan", "Euclidean", "SemiEuc", "Dijikstra"};
    private static final String ALGORITHM_LABEL_TEXT = "Use heuristic";
    private static final String SOLVE_TEXT = "Solve";
    private static final String CLEAR_PATH_TEXT = "Clear path";
    private static final String DELAY_LABEL_TEXT = "Delay";
    private static final int DELAY_WIDTH = 3;
    private static final int DEFAULT_DELAY_VALUE = 0;
    private static final int DELAY_MAX_VALUE = 200;
    private static final int PADDING = 30;
    private JLabel algorithmLabel;
    private JLabel delayLabel;
    private JComboBox heuristics;
    private JButton solve;
    private JButton clearPath;
    private JTextField delay;
    private JPanel algorithmWrapper;
    private JPanel delayWrapper;

    public SolverPanelAdapter() {
        super(new FlowLayout(FlowLayout.CENTER, PADDING, 0));

        algorithmLabel = new JLabel(ALGORITHM_LABEL_TEXT);
        heuristics = new JComboBox(HEURISTIC);
        algorithmWrapper = new JPanel(new FlowLayout());
        algorithmWrapper.add(algorithmLabel);
        algorithmWrapper.add(heuristics);

        delayLabel = new JLabel(DELAY_LABEL_TEXT);
        delay = new JTextField(DELAY_WIDTH);
        delayWrapper = new JPanel(new FlowLayout());
        delayWrapper.add(delayLabel);
        delayWrapper.add(delay);

        solve = new JButton(SOLVE_TEXT);
        solve.addActionListener(solveFunction());

        clearPath = new JButton(CLEAR_PATH_TEXT);
        clearPath.addActionListener(clearFunction());

        this.add(algorithmWrapper);
        this.add(delayWrapper);
        this.add(solve);
        this.add(clearPath);

    }
    
    public String getHeuristicsValue(){
        return (String) heuristics.getSelectedItem();
    }

    public int getDelayValue() {
        int delayValue;

        try {
            delayValue = Integer.parseInt(this.delay.getText());
        } catch (NumberFormatException e) {
            delayValue = DEFAULT_DELAY_VALUE;
        }
        return (delayValue >= 0 && delayValue <= DELAY_MAX_VALUE)
                ? delayValue : DEFAULT_DELAY_VALUE;
    }
    
    @Override
    public void setEnabled(boolean enabled){
        heuristics.setEnabled(enabled);
        delay.setEnabled(enabled);
        solve.setEnabled(enabled);
        clearPath.setEnabled(enabled);
    }

    //to be overwritten
    public ActionListener solveFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
    }

    //to be overwritten
    public ActionListener clearFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
    }
}
