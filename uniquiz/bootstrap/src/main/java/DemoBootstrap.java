import model.*;
import org.eclipse.persistence.jpa.jpql.parser.QualifiedIdentificationVariableBNF;
import repositories.*;

import java.util.*;

/**
 * Created by fabiolourenco on 09/09/17.
 */
public class DemoBootstrap {

    public static void main(String[] args) {
        new DemoBootstrap().execute();
    }

    public void execute() {

        //CREATE USERS
        UserRepository userRepository = new UserRepository();

        userRepository.save(new User("test", "test", "test", "phoenixmakeorbreak@test.com"));
        User author = new User("pedro", "test", "pedro", "pedro@test.com");
        Set<User.Roles> roles = new HashSet<>();
        roles.add(User.Roles.TEACHER);
        author.setRoles(roles);

        //CREATE FIELDS
        FieldRepository fieldRepository = new FieldRepository();

        Field field1 = new Field("Engenharia");
        Field field2 = new Field("Ciências");
        Field field3 = new Field("Medicina");
        Field field4 = new Field("Economia");
        Field field5 = new Field("Agricultura");
        Field field6 = new Field("Artes");
        Field field7 = new Field("Educação");
        Field field8 = new Field("Direito");
        Field field9 = new Field("Humanidades");
        Field field10 = new Field("Desporto");

        field1 = fieldRepository.save(field1);
        field2 = fieldRepository.save(field2);
        field3 = fieldRepository.save(field3);
        field4 = fieldRepository.save(field4);
        field5 = fieldRepository.save(field5);
        field6 = fieldRepository.save(field6);
        field7 = fieldRepository.save(field7);
        field8 = fieldRepository.save(field8);
        field9 = fieldRepository.save(field9);
        field10 = fieldRepository.save(field10);


        //CREATE COURSE
        CourseRepository courseRepository = new CourseRepository();

        Course course1 = new Course("Informática", field1.getFieldName(), field1.getPk());
        Course course2 = new Course("Civil", field1.getFieldName(), field1.getPk());
        Course course3 = new Course("Mecânica", field1.getFieldName(), field1.getPk());

        Course course4 = new Course("Biologia", field2.getFieldName(), field2.getPk());
        Course course5 = new Course("Física", field2.getFieldName(), field2.getPk());
        Course course6 = new Course("Química", field2.getFieldName(), field2.getPk());

        Course course7 = new Course("Enfermagem", field3.getFieldName(), field3.getPk());
        Course course8 = new Course("Fisioterapia", field3.getFieldName(), field3.getPk());
        Course course9 = new Course("Medicina", field3.getFieldName(), field3.getPk());

        Course course10 = new Course("Contabilidade", field4.getFieldName(), field4.getPk());
        Course course11 = new Course("Economia", field4.getFieldName(), field4.getPk());
        Course course12 = new Course("Finanças", field4.getFieldName(), field4.getPk());

        course1 = courseRepository.save(course1);
        course2 = courseRepository.save(course2);
        course3 = courseRepository.save(course3);
        course4 = courseRepository.save(course4);
        course5 = courseRepository.save(course5);
        course6 = courseRepository.save(course6);
        course7 = courseRepository.save(course7);
        course8 = courseRepository.save(course8);
        course9 = courseRepository.save(course9);
        course10 = courseRepository.save(course10);
        course11 = courseRepository.save(course11);
        course12 = courseRepository.save(course12);


        //CREATE SUBJECTS
        SubjectRepository subjectRepository = new SubjectRepository();

        Subject subject1 = new Subject("Gestão");
        Subject subject2 = new Subject("Álgebra");
        Subject subject3 = new Subject("Grafos");
        Subject subject4 = new Subject("Design de Software");
        Subject subject5 = new Subject("Princípios da Computação");
        Subject subject6 = new Subject("Redes de Computadores");

        subject1 = subjectRepository.save(subject1);
        subject2 = subjectRepository.save(subject2);
        subject3 = subjectRepository.save(subject3);
        subject4 = subjectRepository.save(subject4);
        subject5 = subjectRepository.save(subject5);
        subject6 = subjectRepository.save(subject6);


        //CREATE QUIZZES ----------------------------------------------------------------------------------------------------------
        QuizRepository quizRepository = new QuizRepository();

        List<Question> questionList = new ArrayList<>();

        Question question1 = new Question("Durante a execução de um processo o que pode acontecer?");

        List<Answer> answerList1 = new LinkedList<>();
        answerList1.add(new Answer("O processo emitir um pedido IO, e consequentemente ser colocado numa fila de um IO device, passando para o estado de ready.", false));
        answerList1.add(new Answer("O tempo que o escalonador tinha atribuÌdo ao processo (time slice) terminar e consequentemente ser colocado na fila dos ready.", true));
        answerList1.add(new Answer("O processo pode criar um novo processo, ficando à espera que ele termine e consequentemente ser colocado na fila dos ready.", false));
        answerList1.add(new Answer("O processo pode ser removido da UCP em consequÍncia duma interrupção passando sempre para o estado de waiting.", false));

        question1.setAnswers(answerList1);
        questionList.add(question1);

        Question question2 = new Question("Relativamente aos métodos de escalonamento FCFS e Round-Robin pode-se dizer que:");

        List<Answer> answerList2 = new LinkedList<>();
        answerList2.add(new Answer("O Round-Robin favorece a multiprogramação.", true));
        answerList2.add(new Answer("Favorecem os processos IOBound.", false));
        answerList2.add(new Answer("O FCFS tem uma implementação mais complexa.", false));
        answerList2.add(new Answer("O Round-Robin favorece os processos mais prioritários.", false));

        question2.setAnswers(answerList2);
        questionList.add(question2);

        Question question3 = new Question("Num computador o barramento é partilhado entre o CPU e os dispositivos de entrada e saída (I/O):");

        List<Answer> answerList3 = new LinkedList<>();
        answerList3.add(new Answer("A ligação entre o CPU e os dispositivos de entrada e saída é feita exclusivamente pelo barramento de dados.", false));
        answerList3.add(new Answer("A ligação entre o CPU e os dispositivos de entrada e saída é feita exclusivamente pelas linhas de interrupção.", false));
        answerList3.add(new Answer("A ligação entre o CPU e dos dispositivos de entrada e saída é feita pelo barramento de dados e por uma ou mais linhas de interrupção.", true));
        answerList3.add(new Answer("Nenhuma das anteriores.", false));

        question3.setAnswers(answerList3);
        questionList.add(question3);

        Question question4 = new Question("Um dos objetivos da paginação de memória é:");

        List<Answer> answerList4 = new LinkedList<>();
        answerList4.add(new Answer("Permitir a utilização de endereços físicos contíguos.", false));
        answerList4.add(new Answer("Permitir os acessos aos periféricos.", false));
        answerList4.add(new Answer("Permitir a utilização de espaços de memória não contíguos.", true));
        answerList4.add(new Answer("Aumentar a performance do sistema.", false));

        question4.setAnswers(answerList4);
        questionList.add(question4);

        Question question5 = new Question("Para que ocorra um deadlock entre processos é necessário:");

        List<Answer> answerList5 = new LinkedList<>();
        answerList5.add(new Answer("Exclusão mútua, posse e espera, preempção dos recursos, ausência de espera circular", false));
        answerList5.add(new Answer("Ausência de exclusão mútua, posse e espera, preempção dos recursos, espera circular", false));
        answerList5.add(new Answer("Exclusão mútua, ausência de posse e espera, preempção dos recursos, espera circular", false));
        answerList5.add(new Answer("Exclusão mútua, posse e espera, ausência de preempção dos recursos, espera circular", true));

        question5.setAnswers(answerList5);
        questionList.add(question5);

        Quiz quiz = new Quiz(Difficulty.NORMAL, subject5.getPk(), subject5.getSubjectName(),
                course1.getPk(), course1.getCourseName(), "Princípios da computação I", questionList.size(), questionList,author);

        quizRepository.save(quiz);
        //SAVED QUIZ 1 -----------------------------------------------------------------------------------------------------

        questionList.clear();

        question1 = new Question("Numa \"routing table\" o \"next hop\" pode ser um enderenço pertencente a uma rede remota");
        answerList1.clear();
        answerList1.add(new Answer("Verdadeiro", false));
        answerList1.add(new Answer("Falso", true));

        question1.setAnswers(answerList1);
        questionList.add(question1);

        question2 = new Question("A mesma versão do protocolo ICMP é usada com IPv4 e IPv6");
        answerList2.clear();
        answerList2.add(new Answer("Verdadeiro", false));
        answerList2.add(new Answer("Falso", true));

        question2.setAnswers(answerList2);
        questionList.add(question2);

        question3 = new Question("O mecanismo Path Maximum Transmission Unit Discovery evita a fragmentação de datagramas IPv4");
        answerList3.clear();
        answerList3.add(new Answer("Verdadeiro", true));
        answerList3.add(new Answer("Falso", false));

        question3.setAnswers(answerList3);
        questionList.add(question3);

        question4 = new Question("Quando uma aplicação recebe um datagrama UDP fica a saber o número de porto de origem e endereço IP de origem");
        answerList4.clear();
        answerList4.add(new Answer("Verdadeiro", true));
        answerList4.add(new Answer("Falso", false));

        question4.setAnswers(answerList4);
        questionList.add(question4);

        question5 = new Question("Numa ligação DSL é utilizado um par de fibras óticas para transimitir sinais digitais em \"full-duplex\"");
        answerList5.clear();
        answerList5.add(new Answer("Verdadeiro", false));
        answerList5.add(new Answer("Falso", true));

        question5.setAnswers(answerList5);
        questionList.add(question5);

        Quiz quiz2 = new Quiz(Difficulty.HARD, subject6.getPk(), subject6.getSubjectName(),
                course1.getPk(), course1.getCourseName(), "Redes de computadores", questionList.size(), questionList,author);

        quizRepository.save(quiz2);
        //SAVED QUIZ 2 ------------------------------------------------------------------------------------------------------
    }
}
