import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Set;

public class AdminMenuUI {
    JPanel AdminPanel;
    private JTabbedPane tabbedPane1;
    private JTextArea ConsoleOutput1;
    private JButton ReportUsers;


    public AdminMenuUI(InsuranceCompany insuranceCompany) {
        AdminPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });
        ReportUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsoleOutput1.append(insuranceCompany.printUsersToString());
            }
        });
    }
}
