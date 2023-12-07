/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TemaTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.SimpleTimeZone;

public class App {
    
public App() {/* compiled code */}

    public static void main(java.lang.String[] strings) {
        File file = new File("users.csv");
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter("users.csv", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Username,Password");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        file = new File("posts.csv");
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter("posts.csv", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Id,Text,Author");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (strings == null)
            System.out.print("Hello world!");
        else {
            try {
                String message = "";
                if (strings[0].equals("-create-user")) {
                    if (strings.length < 2)
                        throw new Exception("Please provide username");
                    if (strings.length < 3)
                        throw new Exception("Please provide password");

                    int ok = 1;
                    String username = strings[1].split("'")[1];
                    String password = strings[2].split("'")[1];
                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line;
                        boolean first = true;
                        while ((line = br.readLine()) != null) {
                            if (first) {
                                first = false;
                                continue;
                            }
                            String[] values = line.split(",");
                            if (values[0].equals(username))
                                ok = 0;
                        }
                    }
                    if (ok == 0)
                        throw new Exception("User already exists");

                    try (FileWriter fw = new FileWriter("users.csv", true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {
                        Utilizator utilizator = new Utilizator(username, password);
                        out.println(utilizator.getUsername() + "," + utilizator.getPassword());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    message = "User created successfully";
                } else if (strings[0].equals("-cleanup-all")) {
                    try {
                        Files.delete(Paths.get("users.csv"));
                        Files.delete(Paths.get("posts.csv"));
                        Postare.number_of_posts = 0;
                        message = "Cleaned up successfully";
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    if (strings.length < 3)
                        throw new Exception("You need to be authenticated");

                    int ok = 0;
                    String username = strings[1].split("'")[1];
                    String password = strings[2].split("'")[1];
                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line;
                        boolean first = true;
                        while ((line = br.readLine()) != null) {
                            if (first) {
                                first = false;
                                continue;
                            }
                            String[] values = line.split(",");
                            if (values[0].equals(username) && values[1].equals(password)) {
                                ok = 1;
                            }
                        }
                    }
                    if (ok == 0)
                        throw new Exception("Login failed");

                    Utilizator utilizator = new Utilizator(username, password);

                    if (strings[0].equals("-create-post")) {
                        if (strings.length < 4)
                            throw new Exception("No text provided");
                        String text = strings[3].split("'")[1];
                        if (text.length() > 300)
                            throw new Exception("Post text length exceeded");

                        Postare postare = new Postare(text, utilizator);

                        try (FileWriter fw = new FileWriter("posts.csv", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println(postare.getIdPostare() + "," + postare.getPost_text() + "," + postare.getPost_author().getUsername());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        message = "Post added successfully";
                    } else if (strings[0].equals("-delete-post-by-id")) {
                        if (strings.length < 4)
                            throw new Exception("No identifier was provided");
                        int id = Integer.parseInt(strings[3].split("'")[1]);
                        if (id > Postare.number_of_posts || id < 1)
                            throw new Exception("The identifier was not valid");

                        message = "Post deleted successfully";
                    } else if (strings[0].equals("-follow-user-by-username")) {
                        if (strings.length < 4)
                            throw new Exception("No username to follow was provided");

                        message = "Operation executed successfully";
                    } else if (strings[0].equals("-unfollow-user-by-username")) {
                        if (strings.length < 4)
                            throw new Exception("No username to unfollow was provided");

                        message = "Operation executed successfully";
                    } else if (strings[0].equals("-like-post")) {
                        if (strings.length < 4)
                            throw new Exception("No post identifier to like was provided");

                        message = "Operation executed successfully";
                    } else if (strings[0].equals("-unlike-post")) {
                        if (strings.length < 4)
                            throw new Exception("No post identifier to unlike was provided");

                        message = "Operation executed successfully";
                    } else if (strings[0].equals("-like-comment")) {
                        if (strings.length < 4)
                            throw new Exception("No comment identifier to like was provided");

                        message = "Operation executed successfully";
                    } else if (strings[0].equals("-unlike-comment")) {
                        if (strings.length < 4)
                            throw new Exception("No comment identifier to unlike was provided");

                        message = "Operation executed successfully";
                    } else if (strings[0].equals("-get-followings-posts")) {

                    } else if (strings[0].equals("-get-user-posts")) {
                        if (strings.length < 4)
                            throw new Exception("No username to list posts was provided");
                    } else if (strings[0].equals("-get-post-details")) {
                        if (strings.length < 4)
                            throw new Exception("No post identifier was provided");
                    } else if (strings[0].equals("-comment-post")) {
                        if (strings.length < 4)
                            throw new Exception("No text provided");

                        message = "Comment added successfully";
                        String text = strings[3].split("'")[1];
                        if (text.length() > 300)
                            throw new Exception("Comment text length exceeded");
                    } else if (strings[0].equals("-delete-comment-by-id")) {
                        if (strings.length < 4)
                            throw new Exception("No identifier was provided");
                    } else if (strings[0].equals("-get-following")) {

                    } else if (strings[0].equals("-get-followers")) {
                        if (strings.length < 4)
                            throw new Exception("No username to list followers was provided");
                    } else if (strings[0].equals("-get-most-liked-posts")) {

                    } else if (strings[0].equals("-get-most-commented-posts")) {

                    } else if (strings[0].equals("-get-most-followed-users")) {

                    } else if (strings[0].equals("-get-most-liked-users")) {

                    }
                }
                System.out.println("{'status':'ok','message':'" + message + "'}");
            } catch (Exception e) {
                System.out.println("{'status':'error','message':'" + e.getMessage() + "'}");
            }
        }
    }
}