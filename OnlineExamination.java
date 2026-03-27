import java.util.*;

class Question {
    String q;
    String[] options;
    int correct; // 1..4

    Question(String q, String[] options, int correct) {
        this.q = q;
        this.options = options;
        this.correct = correct;
    }
}

public class OnlineExamination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Simple login
        String user = "student";
        String pass = "1234";

        System.out.println("📝 Online Examination System");
        System.out.print("Username: ");
        String u = sc.nextLine().trim();
        System.out.print("Password: ");
        String p = sc.nextLine().trim();

        if (!u.equals(user) || !p.equals(pass)) {
            System.out.println("Invalid login ❌");
            sc.close();
            return;
        }

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "1) Which keyword is used to inherit a class in Java?",
                new String[]{"this", "super", "extends", "implements"},
                3
        ));
        questions.add(new Question(
                "2) Which is NOT a Java primitive type?",
                new String[]{"int", "boolean", "String", "double"},
                3
        ));
        questions.add(new Question(
                "3) Which method is the entry point of Java program?",
                new String[]{"start()", "main()", "run()", "init()"},
                2
        ));
        questions.add(new Question(
                "4) Which collection does NOT allow duplicates?",
                new String[]{"List", "Set", "ArrayList", "LinkedList"},
                2
        ));

        int score = 0;

        System.out.println("\n✅ Login successful! Exam started...\n");

        for (Question qu : questions) {
            System.out.println(qu.q);
            for (int i = 0; i < 4; i++) {
                System.out.println((i + 1) + ". " + qu.options[i]);
            }

            int ans = readOption(sc);
            if (ans == qu.correct) score++;

            System.out.println();
        }

        System.out.println("🎉 Exam finished!");
        System.out.println("Your Score: " + score + "/" + questions.size());

        sc.close();
    }

    static int readOption(Scanner sc) {
        while (true) {
            System.out.print("Choose option (1-4): ");
            String s = sc.nextLine().trim();
            try {
                int x = Integer.parseInt(s);
                if (x >= 1 && x <= 4) return x;
            } catch (Exception ignored) {}
            System.out.println("Invalid choice. Try again.");
        }
    }
}