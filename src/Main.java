import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int i;
    static String name;

    public static void main(String[] args) {
        createOrOpenFile();
        i = readNum();

        // Увеличим значение и сохраним его
        increase();
        save();

        // Запускаем GUI в потоке обработки событий
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Current Value");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("Текущее значение i: " + i, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 20));

            frame.add(label);
            frame.setVisible(true);
        });
    }

    public static void increase() {
        i++;
    }

    public static void createOrOpenFile() {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(String.valueOf(i));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int readNum() {
        int num = 0;
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                num = Integer.parseInt(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return num;
    }
}
