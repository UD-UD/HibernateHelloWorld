import model.Student;
import config.hibernateConfig;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ud on 18/3/17.
 */
public class HiberanateMain {

    @SuppressWarnings("unused")
    public static void main(String args[]){
        HiberanateMain hiberanateMain=new HiberanateMain();

        int st1=hiberanateMain.saveStudent("Ujjal","Kumar","awsum");
        int st2=hiberanateMain.saveStudent("UD","UD","SuperAwsum");

        List<Student> studentList=hiberanateMain.getAllStudents();
        for(Student student:studentList){
            System.out.println(student);
        }

        hiberanateMain.updateStudent(st1,"ZINGHA LALA  LALA HUH");

        studentList=hiberanateMain.getAllStudents();
        for(Student student:studentList){
            System.out.println(student);
        }

        hiberanateMain.deleteStudent(st1);

        studentList=hiberanateMain.getAllStudents();
        for(Student student:studentList){
            System.out.println(student);
        }

        hibernateConfig.getSessionFactory().close();
    }

    public int saveStudent(String firstName,String lastName,String section){
        Student student=new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setSection(section);

        Session session=hibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        int id= (Integer) session.save(student);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public List<Student> getAllStudents(){
        Session session=hibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        List<Student> studentList=(List<Student>) session.createQuery("From Student s order by s.firstName asc").list();
        session.getTransaction().commit();
        session.close();
        return studentList;
    }

    public void updateStudent(int id,String section){
        Session session=hibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Student student=(Student)session.get(Student.class,id);
        student.setSection(section);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteStudent(int id){
        Session session=hibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Student student=(Student)session.get(Student.class,id);
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }
}

