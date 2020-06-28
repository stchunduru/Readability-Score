
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(args[0]));
        String input = reader.nextLine();
        reader.close();
        String[] words = input.split("\\s+");
        String[] lines = input.split("[//.//?//!]");
        String[] chars = input.replace(" ", "").split("");

        System.out.println("Words: " + words.length);
        System.out.println("Sentences: " + lines.length);
        System.out.println("Characters: " + chars.length);
        System.out.println("Syllables: " + vCounter(input));
        System.out.println("Polysyllables: " + pCounter(input));

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner scanner = new Scanner(System.in);
        String ainput = scanner.nextLine();
        switch (ainput) {
            case "ARI":
                ARI(chars, words, lines, input);
                break;

            case "FK":
                FK(chars, words, lines, input);
                break;

            case "SMOG":
                SMOG(chars, words, lines, input);
                break;

            case "CL":
                CL(chars, words, lines, input);
                break;

            case "all":
                int a = ARI(chars, words, lines, input);
                int b = FK(chars, words, lines, input);
                int c = SMOG(chars, words, lines, input);
                int d = CL(chars, words, lines, input);
                System.out.println();
                System.out.println("This text should be understood in average by " + (((double) a + b + c + d) / (double) 4) + " year olds.");
                break;
        }

    }

    public static int ARI(String[] chars, String[] words, String[] lines, String input) {
        double scored = 4.71 * (double) chars.length / (double) words.length + 0.5 * (double) words.length / (double) lines.length
                - 21.43;
        double round = Math.round(scored);
        System.out.println("Automated Readability Index: " + scored + " (about " + age((int) round) + " year olds).");
        return age((int) round);
    }

    public static int FK(String[] chars, String[] words, String[] lines, String input) {
        double scored1 = 0.39 * (double) words.length / (double) lines.length + 11.8 * (double) vCounter(input) / (double) words.length - 15.59;
        double round1 = Math.round(scored1);
        System.out.println("Flesch–Kincaid readability tests: " + scored1 + " (about " + age((int) round1) + " year olds).");
        return age((int) round1);
    }

    public static int SMOG(String[] chars, String[] words, String[] lines, String input) {
        double scored = 1.043 * (double) Math.sqrt((double) pCounter(input) * (double) 30 / (double) lines.length) + (double) 3.1291;
        double round = Math.round(scored);
        System.out.println("Simple Measure of Gobbledygook: " + scored + " (about " + age((int) round) + " year olds).");
        return age((int) round);
    }

    public static int CL(String[] chars, String[] words, String[] lines, String input) {
        double averagew = (double) chars.length / words.length * 100;
        double averages = (double) lines.length / words.length * 100;
        double scored = 0.0588 * averagew - 0.296 * averages - 15.8;
        double round = Math.round(scored);
        System.out.println("Coleman–Liau index: " + scored + " (about " + age((int) round) + " year olds).");
        return age((int) round);
    }

    public static int age(int round) {
        switch (round) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
                return 24;
            default:
                return 24;

        }
    }

    public static int vCounter(String input) {
        String test = input.replace(".", "").replace("!", "").replace("?", "");
        String[] war = test.split("\\s+");
        int count = 0;
        for (String a : war) {
            int colly = 0;
            a = a.toLowerCase();
            for (int x = 0; x < a.length(); x++) {
                if (x == 0 && (a.charAt(x) == 'a' || a.charAt(x) == 'e' || a.charAt(x) == 'i' || a.charAt(x) == 'o' || a.charAt(x) == 'u' || a.charAt(x) == 'y')) {
                    count += 1;
                    colly += 1;
                } else if ((a.charAt(x) == 'a' || a.charAt(x) == 'e' || a.charAt(x) == 'i' || a.charAt(x) == 'o' || a.charAt(x) == 'u' || a.charAt(x) == 'y') &&
                        !(a.charAt(x - 1) == 'a' || a.charAt(x - 1) == 'e' || a.charAt(x - 1) == 'i' || a.charAt(x - 1) == 'o' || a.charAt(x - 1) == 'u' || a.charAt(x - 1) == 'y')) {
                    count += 1;
                    colly += 1;
                    if ((x == a.length() - 1) && a.charAt(x) == 'e') {
                        count -= 1;
                        colly -= 1;
                    }
                }
            }
            if (colly == 0) {
                count += 1;
            }
        }
        return count;
    }

    public static int pCounter(String input) {
        String test = input.replace(".", "").replace("!", "").replace("?", "");
        String[] war = test.split("\\s+");
        int count = 0;
        for (String a : war) {
            int colly = 0;
            a = a.toLowerCase();
            for (int x = 0; x < a.length(); x++) {
                if (x == 0 && (a.charAt(x) == 'a' || a.charAt(x) == 'e' || a.charAt(x) == 'i' || a.charAt(x) == 'o' || a.charAt(x) == 'u' || a.charAt(x) == 'y')) {
                    colly += 1;
                } else if ((a.charAt(x) == 'a' || a.charAt(x) == 'e' || a.charAt(x) == 'i' || a.charAt(x) == 'o' || a.charAt(x) == 'u' || a.charAt(x) == 'y') &&
                        !(a.charAt(x - 1) == 'a' || a.charAt(x - 1) == 'e' || a.charAt(x - 1) == 'i' || a.charAt(x - 1) == 'o' || a.charAt(x - 1) == 'u' || a.charAt(x - 1) == 'y')) {
                    colly += 1;
                    if ((x == a.length() - 1) && a.charAt(x) == 'e') {

                        colly -= 1;
                    }
                }
            }
            if (colly > 2) {
                count += 1;
            }
        }
        return count;
    }
}
