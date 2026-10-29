import java.util.*; // Collections aur Scanner jaise classes use karne ke liye

// Leave ke valid types define karne ke liye enum use kiya hai
enum LeaveType {
    CASUAL, SICK, EARNED
}

// Leave request ki current condition track karne ke liye enum
enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\\//\\                                                                                                  
// Employee na milne par custom exception throw hogi
class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message); // Parent Exception class ko error message dena
    }
}

// Leave balance kam hone par custom exception throw hogi
class InsufficientLeaveException extends Exception {
    public InsufficientLeaveException(String message) {
        super(message); // Exception ke andar error message store karna
    }
}

// Employee class common employee information store karegi
class Employee {
    private String employeeId; // private se data ko directly access hone se rokte hain
    private String employeeName;
    private String department;
    private String email;
    protected int availableLeaveBalance; // Child classes mein bhi access ho sakega

    // Constructor object banate waqt employee details initialize karta hai
    public Employee(String employeeId, String employeeName,
                    String department, String email, int balance) {
        this.employeeId = employeeId; // this current object ke variable ko refer karta hai
        this.employeeName = employeeName;
        this.department = department;
        this.email = email;
        this.availableLeaveBalance = balance;
    }

    // Getters private variables ki values read karne ke liye hain
    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public int getAvailableLeaveBalance() {
        return availableLeaveBalance;
    }

    // Setter balance update karne deta hai, negative balance allow nahi hai
    public void setAvailableLeaveBalance(int balance) {
        if (balance >= 0) {
            this.availableLeaveBalance = balance;
        } else {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
    }

    // Parent class ka method child classes override kar sakti hain
    public void displayEmployee() {
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Department : " + department);
        System.out.println("Email : " + email);
        System.out.println("Leave Balance : " + availableLeaveBalance);
        System.out.println("Employee Type : General");
    }

    // Method overloading: same method, lekin alag parameter
    public void displayEmployee(boolean showEmail) {
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Employee Name : " + employeeName);

        if (showEmail) {
            System.out.println("Email : " + email);
        }
    }

    // Leave approve hone par balance se days deduct karne ke liye
    public void deductLeave(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0.");
        }

        if (days > availableLeaveBalance) {
            throw new IllegalArgumentException("Insufficient leave balance.");
        }

        availableLeaveBalance -= days; // Balance mein se approved days minus karna
    }
}

// Inheritance: PermanentEmployee, Employee ki properties inherit karega
class PermanentEmployee extends Employee {
    public PermanentEmployee(String id, String name, String dept,
                             String email, int balance) {
        super(id, name, dept, email, balance); // Parent constructor call karna
    }

    // Method overriding: permanent employee ka type display karna
    @Override
    public void displayEmployee() {
        super.displayEmployee(); // Parent ki common details print karna
        System.out.println("Employment Type : Permanent");
    }
}

// ContractEmployee bhi Employee class ko inherit karta hai
class ContractEmployee extends Employee {
    public ContractEmployee(String id, String name, String dept,
                            String email, int balance) {
        super(id, name, dept, email, balance);
    }

    // Parent method ko override karke contract type show karna
    @Override
    public void displayEmployee() {
        super.displayEmployee();
        System.out.println("Employment Type : Contract");
    }
}

// LeaveRequest class ek leave application ki details store karti hai
class LeaveRequest {
    private String requestId;
    private String employeeId;
    private LeaveType leaveType;
    private int numberOfDays;
    private String reason;
    private LeaveStatus status;

    // Constructor leave request ki saari details initialize karta hai
    public LeaveRequest(String requestId, String employeeId,
                        LeaveType leaveType, int days, String reason) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.numberOfDays = days;
        this.reason = reason;
        this.status = LeaveStatus.PENDING; // Nayi request pehle pending hoti hai
    }

    // Getters request ki details access karne ke liye
    public String getRequestId() {
        return requestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status; // Request ka status update karna
    }

    // Leave request ki complete details print karna
    public void displayRequest() {
        System.out.println("Request ID : " + requestId);
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Leave Type : " + leaveType);
        System.out.println("Days : " + numberOfDays);
        System.out.println("Reason : " + reason);
        System.out.println("Status : " + status);
        System.out.println("-----------------------------");
    }
}

// Main management class saare employees aur leave requests manage karegi
class LeaveManagementSystem {
    private List<Employee> employees = new ArrayList<>();
    // ArrayList employee records ko order mein store karti hai

    private Set<String> emails = new HashSet<>();
    // HashSet duplicate email addresses ko rokne mein help karta hai

    private Map<String, Employee> employeeMap = new HashMap<>();
    // HashMap employee ID se employee ko quickly search karta hai

    private Map<String, LeaveRequest> leaveRequests = new LinkedHashMap<>();
    // LinkedHashMap requests ko insertion order mein store karta hai

    private int requestCounter = 1001; // Pehli request ka ID LR1001 hoga

    private Scanner sc = new Scanner(System.in);
    // Scanner user se keyboard input lene ke liye hai

    // Integer input safely read karne ka method
    private int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value; // Valid integer milne par return karna
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    // Employee ID se employee find karna
    private Employee findEmployee(String id)
            throws EmployeeNotFoundException {
        Employee employee = employeeMap.get(id);

        if (employee == null) {
            throw new EmployeeNotFoundException(
                "Employee " + id + " does not exist."
            );
        }

        return employee;
    }

    // Option 1: Naya employee add karna
    private void addEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        int balance = readInt("Enter Leave Balance: ");

        System.out.print("Employee Type (Permanent/Contract): ");
        String type = sc.nextLine().trim();

        // Required fields aur negative balance validate karna
        if (id.isEmpty() || name.isEmpty() || dept.isEmpty()
                || email.isEmpty() || type.isEmpty()) {
            System.out.println("Error: Fields cannot be empty.");
            return;
        }

        if (balance < 0) {
            System.out.println("Error: Leave balance cannot be negative.");
            return;
        }

        // Duplicate ID aur email ko reject karna
        if (employeeMap.containsKey(id)) {
            System.out.println("Error: Employee ID already exists.");
            return;
        }

        if (emails.contains(email)) {
            System.out.println("Error: Email already exists.");
            return;
        }

        // Parent reference child object ko point kar sakta hai
        Employee employee;

        if (type.equalsIgnoreCase("Permanent")) {
            employee = new PermanentEmployee(id, name, dept, email, balance);
        } else if (type.equalsIgnoreCase("Contract")) {
            employee = new ContractEmployee(id, name, dept, email, balance);
        } else {
            System.out.println("Error: Invalid employee type.");
            return;
        }

        // Employee ko teeno collections mein store karna
        employees.add(employee);
        employeeMap.put(id, employee);
        emails.add(email);

        System.out.println("Employee added successfully.");
    }

    // Option 2: Iterator se saare employees display karna
    private void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        Iterator<Employee> iterator = employees.iterator();
        // Iterator collection ke elements ko ek-ek karke read karta hai

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            // Polymorphism ke kaaran child ka overridden method call hoga
            employee.displayEmployee();
            System.out.println("-----------------------------");
        }
    }

    // Option 3: Employee ID se search karna
    private void searchEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine().trim();

        try {
            Employee employee = findEmployee(id);
            employee.displayEmployee();
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Option 4: Leave apply karna
    private void applyLeave() {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine().trim();

        try {
            Employee employee = findEmployee(id);

            System.out.print("Enter Leave Type (CASUAL/SICK/EARNED): ");
            String type = sc.nextLine().trim().toUpperCase();

            // valueOf valid enum value ko LeaveType mein convert karta hai
            LeaveType leaveType;

            try {
                leaveType = LeaveType.valueOf(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid leave type.");
                return;
            }

            int days = readInt("Enter Number of Days: ");

            // Zero aur negative days valid nahi hain
            if (days <= 0) {
                System.out.println("Error: Leave days must be greater than 0.");
                return;
            }

            // Available balance se zyada leave apply nahi kar sakte
            if (days > employee.getAvailableLeaveBalance()) {
                throw new InsufficientLeaveException(
                    "Insufficient leave balance. Available Leave: "
                    + employee.getAvailableLeaveBalance()
                    + ", Requested Leave: " + days
                );
            }

            System.out.print("Enter Reason: ");
            String reason = sc.nextLine().trim();

            if (reason.isEmpty()) {
                System.out.println("Error: Reason cannot be empty.");
                return;
            }

            // Unique request ID generate karna
            String requestId = "LR" + requestCounter++;

            LeaveRequest request = new LeaveRequest(
                requestId, id, leaveType, days, reason
            );

            leaveRequests.put(requestId, request);

            System.out.println("Leave request created successfully.");
            System.out.println("Request ID: " + requestId);
            System.out.println("Status: PENDING");

        } catch (EmployeeNotFoundException
                 | InsufficientLeaveException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Option 5: Pending leave request approve karna
    private void approveLeave() {
        System.out.print("Enter Request ID: ");
        String id = sc.nextLine().trim();

        LeaveRequest request = leaveRequests.get(id);

        if (request == null) {
            System.out.println("Error: Leave request does not exist.");
            return;
        }

        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println("Error: Request is already processed.");
            return;
        }

        try {
            Employee employee = findEmployee(request.getEmployeeId());

            // Approval ke waqt balance dobara check karna zaroori hai
            if (request.getNumberOfDays()
                    > employee.getAvailableLeaveBalance()) {
                throw new InsufficientLeaveException(
                    "Insufficient leave balance at approval time."
                );
            }

            int previousBalance = employee.getAvailableLeaveBalance();

            employee.deductLeave(request.getNumberOfDays());
            request.setStatus(LeaveStatus.APPROVED);

            System.out.println("Leave Request Approved.");
            System.out.println("Employee ID : " + employee.getEmployeeId());
            System.out.println("Previous Balance: " + previousBalance);
            System.out.println("Leave Used : " + request.getNumberOfDays());
            System.out.println("Remaining Leave : "
                    + employee.getAvailableLeaveBalance());

        } catch (EmployeeNotFoundException
                 | InsufficientLeaveException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Option 6: Leave request reject karna
    private void rejectLeave() {
        System.out.print("Enter Request ID: ");
        String id = sc.nextLine().trim();
3
        LeaveRequest request = leaveRequests.get(id);

        if (request == null) {
            System.out.println("Error: Leave request does not exist.");
            return;
        }

        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println("Error: Request is already processed.");
            return;
        }

        request.setStatus(LeaveStatus.REJECTED);
        // Reject karne par employee balance deduct nahi hota
        System.out.println("Leave Request Rejected.");
    }

    // Option 7: Saari leave requests display karna
    private void displayAllLeaveRequests() {
        if (leaveRequests.isEmpty()) {
            System.out.println("No leave requests found.");
            return;
        }

        for (LeaveRequest request : leaveRequests.values()) {
            request.displayRequest();
        }
    }

    // Option 8: Employee ka current leave balance dikhana
    private void displayLeaveBalance() {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine().trim();

        try {
            Employee employee = findEmployee(id);
            System.out.println("Employee Name : "
                    + employee.getEmployeeName());
            System.out.println("Available Leave Balance : "
                    + employee.getAvailableLeaveBalance());
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Application ka main menu baar-baar show karna
    public void start() {
        int choice;

        do {
            System.out.println("\n===== Employee Leave Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Apply Leave");
            System.out.println("5. Approve Leave");
            System.out.println("6. Reject Leave");
            System.out.println("7. Display All Leave Requests");
            System.out.println("8. Display Employee Leave Balance");
            System.out.println("9. Exit");

            choice = readInt("Enter your choice: ");

            // switch selected menu option ke according method call karta hai
            switch (choice) {
                case 1:
                    addEmployee();
                    break;

                case 2:
                    displayAllEmployees();
                    break;

                case 3:
                    searchEmployee();
                    break;

                case 4:
                    applyLeave();
                    break;

                case 5:
                    approveLeave();
                    break;

                case 6:
                    rejectLeave();
                    break;

                case 7:
                    displayAllLeaveRequests();
                    break;

                case 8:
                    displayLeaveBalance();
                    break;

                case 9:
                    System.out.println("Thank you. Application closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 9); // Option 9 tak menu repeat hota rahega

        sc.close(); // Kaam khatam hone par Scanner resource close karna
    }
}

// Java program execution yahin se start hota hai
public class JavaConcept {
    public static void main(String[] args) {
        // Management system ka object create karna
        LeaveManagementSystem system = new LeaveManagementSystem();

        // Menu-driven application start karna
        system.start();
    }
}
