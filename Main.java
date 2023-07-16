import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Doctor {
   private String name;
   private String specialization;
   private int maxAppointmentsPerDay;
   private List<String> appointments;

   public Doctor(String name, String specialization, int maxAppointmentsPerDay) {
      this.name = name;
      this.specialization = specialization;
      this.maxAppointmentsPerDay = maxAppointmentsPerDay;
      this.appointments = new ArrayList<>();
   }

   public String getName() {
      return name;
   }

   public String getSpecialization() {
      return specialization;
   }

   public int getMaxAppointmentsPerDay() {
      return maxAppointmentsPerDay;
   }

   public List<String> getAppointments() {
      return appointments;
   }
}

class DoctorAppointmentSystem {
   private Map<String, Doctor> doctors;

   public DoctorAppointmentSystem() {
      doctors = new HashMap<>();
   }

   public void addDoctor(Doctor doctor) {
      doctors.put(doctor.getName(), doctor);
      System.out.println("Doctor " + doctor.getName() + " added successfully.");
   }

   public void scheduleAppointment(String doctorName, String appointment) {
      Doctor doctor = doctors.get(doctorName);
      if (doctor == null) {
         System.out.println("Doctor not found.");
         return;
      }

      List<String> appointments = doctor.getAppointments();
      if (appointments.size() >= doctor.getMaxAppointmentsPerDay()) {
         System.out.println("Doctor is fully booked for the day.");
         return;
      }

      appointments.add(appointment);
      System.out.println("Appointment scheduled with " + doctor.getName() +
            " (" + doctor.getSpecialization() + ") on " + appointment);
   }

   public void printDoctorAppointments(String doctorName) {
      Doctor doctor = doctors.get(doctorName);
      if (doctor == null) {
         System.out.println("Doctor not found.");
         return;
      }

      System.out.println("Appointments for " + doctor.getName() +
            " (" + doctor.getSpecialization() + "):");

      List<String> appointments = doctor.getAppointments();
      if (appointments.isEmpty()) {
         System.out.println("No appointments scheduled.");
      } else {
         for (String appointment : appointments) {
            System.out.println(appointment);
         }
      }
   }
}

public class Main {
   public static void main(String[] args) {
      DoctorAppointmentSystem appointmentSystem = new DoctorAppointmentSystem();
      Scanner scanner = new Scanner(System.in);

      while (true) {
         System.out.println("\n------- Doctor Appointment System -------");
         System.out.println("1. Add Doctor");
         System.out.println("2. Schedule Appointment");
         System.out.println("3. Print Doctor Appointments");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline character

         switch (choice) {
            case 1:
               System.out.print("Enter doctor's name: ");
               String name = scanner.nextLine();
               System.out.print("Enter doctor's specialization: ");
               String specialization = scanner.nextLine();
               System.out.print("Enter maximum appointments per day: ");
               int maxAppointments = scanner.nextInt();
               scanner.nextLine(); // Consume newline character

               Doctor doctor = new Doctor(name, specialization, maxAppointments);
               appointmentSystem.addDoctor(doctor);
               break;

            case 2:
               System.out.print("Enter doctor's name: ");
               String docName = scanner.nextLine();
               System.out.print("Enter appointment date and time: ");
               String appointment = scanner.nextLine();

               appointmentSystem.scheduleAppointment(docName, appointment);
               break;

            case 3:
               System.out.print("Enter doctor's name: ");
               String doctorName = scanner.nextLine();
               appointmentSystem.printDoctorAppointments(doctorName);
               break;

            case 4:
               System.out.println("Exiting program...");
               System.exit(0);
               break;

            default:
               System.out.println("Invalid choice. Please try again.");
         }
      }
   }
}
