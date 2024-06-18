import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginSignUpApp {

    private static String quot;
private JFrame loginFrame;
private JFrame signUpFrame;
private JFrame welcomeFrame;
private JPanel loginPanel;
private JPanel signUpPanel;
private JPanel employeeDataPanel;
private JTextField usernameField;
private JPasswordField passwordField;
private JButton loginButton;
private JButton openSignUpButton;
private JTextField signUpUsernameField;
private JPasswordField signUpPasswordField;
private JButton signUpButton;
private JButton backToLoginButton;
private JLabel welcomeLabel;
private String currentUser;

private static final String CSV_FILE = &quot;users.csv&quot;;
private static final String EMPLOYEE_DATA_CSV = &quot;EmployeeData.csv&quot;;

public LoginSignUpApp() {
// Login Frame
loginFrame = new JFrame(&quot;Payroll Login&quot;);
loginFrame.setSize(600, 400);
loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
loginFrame.setLocationRelativeTo(null);
loginFrame.getContentPane().setBackground(new Color(240, 240, 240));

// Login Panel
loginPanel = new JPanel();
loginPanel.setLayout(new GridLayout(5, 1, 10, 10));

// Title panel
JPanel titlePanel = new JPanel();
JLabel titleLabel = new JLabel(&quot;Payroll System&quot;, SwingConstants.CENTER);
titleLabel.setFont(new Font(&quot;Arial&quot;, Font.BOLD, 28));
titlePanel.add(titleLabel);
loginPanel.add(titlePanel);

// Username panel
JPanel usernamePanel = new JPanel();
JLabel usernameLabel = new JLabel(&quot;Username:&quot;);
usernameField = new JTextField(15);
usernamePanel.add(usernameLabel);
usernamePanel.add(usernameField);
loginPanel.add(usernamePanel);

// Password panel
JPanel passwordPanel = new JPanel();

JLabel passwordLabel = new JLabel(&quot;Password:&quot;);
passwordField = new JPasswordField(15);
passwordPanel.add(passwordLabel);
passwordPanel.add(passwordField);
loginPanel.add(passwordPanel);

// Button panel
JPanel loginButtonPanel = new JPanel();
loginButton = new JButton(&quot;Login&quot;);
openSignUpButton = new JButton(&quot;Sign Up&quot;);
loginButtonPanel.add(loginButton);
loginButtonPanel.add(openSignUpButton);
loginPanel.add(loginButtonPanel);

// Add login panel to frame
loginFrame.add(loginPanel, BorderLayout.CENTER);

// Show login frame
loginFrame.setVisible(true);

// Action listeners for login page
loginButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
login();
}
});

openSignUpButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {

openSignUpPage();
}
});

// Signup Frame
signUpFrame = new JFrame(&quot;Sign Up&quot;);
signUpFrame.setSize(600, 400);
signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
signUpFrame.setLocationRelativeTo(null);
signUpFrame.setVisible(false);

// Signup Panel
signUpPanel = new JPanel();
signUpPanel.setLayout(new GridLayout(5, 1, 10, 10));

// Title panel for sign up
JPanel signUpTitlePanel = new JPanel();
JLabel signUpTitleLabel = new JLabel(&quot;Sign Up for Payroll&quot;, SwingConstants.CENTER);
signUpTitleLabel.setFont(new Font(&quot;Arial&quot;, Font.BOLD, 28));
signUpTitlePanel.add(signUpTitleLabel);
signUpPanel.add(signUpTitlePanel);

// Username panel
JPanel signUpUsernamePanel = new JPanel();
JLabel signUpUsernameLabel = new JLabel(&quot;Username:&quot;);
signUpUsernameField = new JTextField(15);
signUpUsernamePanel.add(signUpUsernameLabel);
signUpUsernamePanel.add(signUpUsernameField);
signUpPanel.add(signUpUsernamePanel);

// Password panel
JPanel signUpPasswordPanel = new JPanel();
JLabel signUpPasswordLabel = new JLabel(&quot;Password:&quot;);
signUpPasswordField = new JPasswordField(15);
signUpPasswordPanel.add(signUpPasswordLabel);
signUpPasswordPanel.add(signUpPasswordField);
signUpPanel.add(signUpPasswordPanel);

// Button panel
JPanel signUpButtonPanel = new JPanel();
signUpButton = new JButton(&quot;Sign Up&quot;);
backToLoginButton = new JButton(&quot;Back to Login&quot;);
signUpButtonPanel.add(signUpButton);
signUpButtonPanel.add(backToLoginButton);
signUpPanel.add(signUpButtonPanel);

// Add sign up panel to frame
signUpFrame.add(signUpPanel, BorderLayout.CENTER);

// Action listeners for sign-up page
signUpButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
signUp();
}
});

backToLoginButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {

backToLoginPage();
}
});

// Welcome Frame
welcomeFrame = new JFrame(&quot;Welcome to Payroll System&quot;);
welcomeFrame.setSize(600, 400);
welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
welcomeFrame.setLocationRelativeTo(null);
welcomeFrame.setVisible(false);

// Welcome Panel
JPanel welcomePanel = new JPanel();
welcomePanel.setLayout(new GridLayout(3, 1, 10, 10));

// Welcome Label
welcomeLabel = new JLabel(&quot;&quot;, SwingConstants.CENTER);
welcomeLabel.setFont(new Font(&quot;Arial&quot;, Font.BOLD, 24));
welcomePanel.add(welcomeLabel);

// Button panel for options
JPanel welcomeButtonPanel = new JPanel();
JButton employeeDataButton = new JButton(&quot;Employee Data&quot;);
JButton payslipButton = new JButton(&quot;Payslip&quot;);
JButton payrollSummaryButton = new JButton(&quot;Payroll Summary&quot;);
welcomeButtonPanel.add(employeeDataButton);
welcomeButtonPanel.add(payslipButton);
welcomeButtonPanel.add(payrollSummaryButton);
welcomePanel.add(welcomeButtonPanel);

// Add welcome panel to frame
welcomeFrame.add(welcomePanel, BorderLayout.CENTER);

// Action listeners for welcome page buttons
employeeDataButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
showEmployeeData();
}
});

payslipButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
showPayslip();
}
});

payrollSummaryButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
showPayrollSummary();
}
});

// Call showLoginFrame to display the login page initially
showLoginFrame();
}

private void showLoginFrame() {
loginFrame.setVisible(true);

signUpFrame.setVisible(false);
welcomeFrame.setVisible(false);
}

private void login() {
String username = usernameField.getText();
String password = new String(passwordField.getPassword());

if (username.isEmpty() || password.isEmpty()) {
showMessage(&quot;Please enter both username and password.&quot;);
return;
}

if (authenticateUser(username, password)) {
currentUser = username;
showWelcomePage();
} else {
showMessage(&quot;Incorrect username or password. Please try again.&quot;);
}
}

private boolean authenticateUser(String username, String password) {
try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
String line;
while ((line = br.readLine()) != null) {
String[] data = line.split(&quot;,&quot;);
if (data.length == 2 &amp;&amp; data[0].equals(username) &amp;&amp; data[1].equals(password)) {
return true;
}

}
} catch (IOException e) {
e.printStackTrace();
}
return false;
}

private void openSignUpPage() {
loginFrame.setVisible(false);
signUpFrame.setVisible(true);
}

private void signUp() {
String username = signUpUsernameField.getText();
String password = new String(signUpPasswordField.getPassword());

if (username.isEmpty() || password.isEmpty()) {
showMessage(&quot;Please enter both username and password.&quot;);
return;
}

try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
bw.write(username + &quot;,&quot; + password);
bw.newLine();
showMessage(&quot;Sign up successful! You can now login.&quot;);
signUpFrame.setVisible(false);
loginFrame.setVisible(true);
} catch (IOException e) {
e.printStackTrace();

showMessage(&quot;Failed to sign up. Please try again later.&quot;);
}
}

private void backToLoginPage() {
signUpFrame.setVisible(false);
loginFrame.setVisible(true);
}

private void showWelcomePage() {
welcomeLabel.setText(&quot;Welcome, &quot; + currentUser + &quot;!&quot;);
loginFrame.setVisible(false);
signUpFrame.setVisible(false);
welcomeFrame.setVisible(true);
}

private void showEmployeeData() {
List&lt;String[]&gt; employeeData = readCSVFile(EMPLOYEE_DATA_CSV);
if (employeeData != null &amp;&amp; !employeeData.isEmpty()) {
showEmployeeDataTable(employeeData);
} else {
showMessage(&quot;No employee data found.&quot;);
}
}

private void showEmployeeDataTable(List&lt;String[]&gt; data) {
employeeDataPanel = new JPanel(new BorderLayout());

// Create the table with data

JTable dataTable = new JTable(new DataTableModel(data));
JScrollPane scrollPane = new JScrollPane(dataTable);
employeeDataPanel.add(scrollPane, BorderLayout.CENTER);

// Button panel for CRUD operations
JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
JButton addButton = new JButton(&quot;Add Employee&quot;);
JButton updateButton = new JButton(&quot;Update Employee&quot;);
JButton deleteButton = new JButton(&quot;Delete Employee&quot;);

buttonPanel.add(addButton);
buttonPanel.add(updateButton);
buttonPanel.add(deleteButton);
employeeDataPanel.add(buttonPanel, BorderLayout.SOUTH);

// Create the employee data frame
JFrame employeeDataFrame = new JFrame(&quot;Employee Data&quot;);
employeeDataFrame.setSize(800, 600);
employeeDataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
employeeDataFrame.setLocationRelativeTo(null);
employeeDataFrame.add(employeeDataPanel);
employeeDataFrame.setVisible(true);

// Action listeners for CRUD operations
addButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
addEmployee();
}
});

updateButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
updateEmployee(dataTable);
}
});

deleteButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
deleteEmployee(dataTable);
}
});
}

private void addEmployee() {
String employeeName = JOptionPane.showInputDialog(welcomeFrame, &quot;Enter Employee Name:&quot;);
if (employeeName != null &amp;&amp; !employeeName.isEmpty()) {
String employeeId = JOptionPane.showInputDialog(welcomeFrame, &quot;Enter Employee ID:&quot;);
if (employeeId != null &amp;&amp; !employeeId.isEmpty()) {
String[] newEmployee = {employeeId, employeeName};
if (writeToCSV(EMPLOYEE_DATA_CSV, newEmployee)) {
showMessage(&quot;Employee added successfully.&quot;);
} else {
showMessage(&quot;Failed to add employee. Please try again.&quot;);
}
}
}
}

private void updateEmployee(JTable dataTable) {
int selectedRow = dataTable.getSelectedRow();
if (selectedRow == -1) {
showMessage(&quot;Please select an employee to update.&quot;);
return;
}

String employeeId = (String) dataTable.getValueAt(selectedRow, 0);
String employeeName = (String) dataTable.getValueAt(selectedRow, 1);

String newEmployeeName = JOptionPane.showInputDialog(welcomeFrame, &quot;Enter new name for
Employee ID &quot; + employeeId + &quot;:&quot;, employeeName);
if (newEmployeeName != null &amp;&amp; !newEmployeeName.isEmpty()) {
String[] updatedEmployee = {employeeId, newEmployeeName};
if (updateInCSV(EMPLOYEE_DATA_CSV, selectedRow + 1, updatedEmployee)) {
showMessage(&quot;Employee updated successfully.&quot;);
} else {
showMessage(&quot;Failed to update employee. Please try again.&quot;);
}
}
}

private void deleteEmployee(JTable dataTable) {
int selectedRow = dataTable.getSelectedRow();
if (selectedRow == -1) {
showMessage(&quot;Please select an employee to delete.&quot;);
return;
}

int confirm = JOptionPane.showConfirmDialog(welcomeFrame, &quot;Are you sure you want to delete
this employee?&quot;, &quot;Confirm Deletion&quot;, JOptionPane.YES_NO_OPTION);
if (confirm == JOptionPane.YES_OPTION) {
if (deleteFromCSV(EMPLOYEE_DATA_CSV, selectedRow + 1)) {
showMessage(&quot;Employee deleted successfully.&quot;);
} else {
showMessage(&quot;Failed to delete employee. Please try again.&quot;);
}
}
}

private boolean writeToCSV(String fileName, String[] data) {
try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
bw.write(String.join(&quot;,&quot;, data));
bw.newLine();
return true;
} catch (IOException e) {
e.printStackTrace();
return false;
}
}

private boolean updateInCSV(String fileName, int lineNum, String[] newData) {
List&lt;String&gt; lines = new ArrayList&lt;&gt;();
try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = br.readLine()) != null) {
lines.add(line);
}

if (lineNum &gt; 0 &amp;&amp; lineNum &lt;= lines.size()) {
lines.set(lineNum - 1, String.join(&quot;,&quot;, newData));
} else {
return false;
}
} catch (IOException e) {
e.printStackTrace();
return false;
}

try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
for (String line : lines) {
bw.write(line);
bw.newLine();
}
return true;
} catch (IOException e) {
e.printStackTrace();
return false;
}
}

private boolean deleteFromCSV(String fileName, int lineNum) {
List&lt;String&gt; lines = new ArrayList&lt;&gt;();
try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = br.readLine()) != null) {
lines.add(line);
}

if (lineNum &gt; 0 &amp;&amp; lineNum &lt;= lines.size()) {
lines.remove(lineNum - 1);
} else {
return false;
}
} catch (IOException e) {
e.printStackTrace();
return false;
}

try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
for (String line : lines) {
bw.write(line);
bw.newLine();
}
return true;
} catch (IOException e) {
e.printStackTrace();
return false;
}
}

private void showPayslip() {
showMessage(&quot;Payslip functionality coming soon!&quot;);
}

private void showPayrollSummary() {
showMessage(&quot;Payroll Summary functionality coming soon!&quot;);
}

private List&lt;String[]&gt; readCSVFile(String fileName) {
List&lt;String[]&gt; data = new ArrayList&lt;&gt;();
try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = br.readLine()) != null) {
String[] rowData = line.split(&quot;,&quot;);
data.add(rowData);
}
} catch (IOException e) {
e.printStackTrace();
}
return data;
}

private void showMessage(String message) {
JOptionPane.showMessageDialog(welcomeFrame, message);
}

public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
new LoginSignUpApp();
}
});
}

// DataTableModel class for JTable
class DataTableModel extends AbstractTableModel {

private List&lt;String[]&gt; data;
private List&lt;String&gt; columnNames;

public DataTableModel(List&lt;String[]&gt; data) {
this.data = data;
// Assuming the first row contains column headers
if (!data.isEmpty()) {
columnNames = new ArrayList&lt;&gt;();
for (String columnName : data.get(0)) {
columnNames.add(columnName);
}
data.remove(0); // Remove the header row from data
}
}

@Override
public int getRowCount() {
return data.size();
}

@Override
public int getColumnCount() {
return columnNames.size();
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
return data.get(rowIndex)[columnIndex];
}

@Override
public String getColumnName(int column) {
return columnNames.get(column);
}
}
}