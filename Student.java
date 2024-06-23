public class Student {
    private String name;
    private String gender;
    private int age;
    private String studentID;

    public Student(String name, String gender, int age, String studentID) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return "Họ tên: " + name + ", Giới tính: " + gender + ", Tuổi: " + age + ", Mã Sinh Viên: " + studentID;
    }
}
