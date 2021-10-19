public class Main {
    public static void main(String[] args) {
        School school = new School();

        //Add 10 students to student list
        school.addStudent("Cristiano", "Ronaldo", 1);
        school.addStudent("Lebron", "James", 2);
        school.addStudent("Michael", "Jordan", 3);
        school.addStudent("Tom", "Brady", 4);
        school.addStudent("Conor", "McGregor", 5);
        school.addStudent("Mike", "Tyson", 6);
        school.addStudent("Lionel ", "Messi", 7);
        school.addStudent("Stephen", "Curry", 8);
        school.addStudent("Tiger", "Woods", 9);
        school.addStudent("Floyd", "Mayweather", 10);

        //add 3 teachers to teacher list
        school.addTeacher("Robert", "Downey", "Math");
        school.addTeacher("Scarlett", "Johansson", "PE");
        school.addTeacher("Morgan", "Freeman", "Social");

        //display both lists
        System.out.println("-------- Teachers ---------");
        school.displayAllTeachers();
        System.out.println();
        System.out.println("-------- Students ---------");
        school.displayAllStudents();
        System.out.println();

        //remove 2 students
        school.delStudent("Tom", "Brady", 4);
        school.delStudent("Stephen", "Curry", 8);

        //remove 1 teacher
        school.delTeacher("Robert", "Downey", "Math");

        System.out.println();

        //display both lists
        System.out.println("-------- Teachers ---------");
        school.displayAllTeachers();
        System.out.println();
        System.out.println("-------- Students ---------");
        school.displayAllStudents();
        System.out.println();
    }
}
