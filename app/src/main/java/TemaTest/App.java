/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TemaTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    
    public App() {/* compiled code */}

    public static void main(java.lang.String[] strings) {
        File file = new File("users.csv"); //csv file the retains users username
        if (!file.exists()) { //exception case
            try (FileWriter fw = new FileWriter("users.csv", true);//if file does not exist, create it
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Username,Password");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        file = new File("posts.csv"); //csv file that retains posts
        if (!file.exists()) { //exception case file does not exist
            try (FileWriter fw = new FileWriter("posts.csv", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Id,Text,Author");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        file = new File("comments.csv"); //csv file that retains comments
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter("comments.csv", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Id,Text,Author,PostId");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (strings == null) //first test case
            System.out.print("Hello world!");

        else {
            try {
                String message = ""; //message to be printed
                if (strings[0].equals("-create-user")) {
                    if (strings.length < 2) //username not provided, only one parameter given
                        throw new Exception("Please provide username");
                    if (strings.length < 3) //password not provided, only two parameters given
                        throw new Exception("Please provide password");

                    int ok = 1; //verify if user already exists
                    String username = strings[1].split("'")[1]; //sepparated b y "'"
                    String password = strings[2].split("'")[1];
                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line;
                        boolean first = true; //we skip first line bc it is the user's command input(command, name, password)
                        while ((line = br.readLine()) != null) {
                            if (first) {
                                first = false; //getting to next line
                                continue;
                            }
                            String[] values = line.split(",");
                            if (values[0].equals(username))
                                ok = 0; //user already exists
                        }
                    }
                    if (ok == 0)
                        throw new Exception("User already exists");
                    Utilizator utilizator = new Utilizator(username, password);
                    try (FileWriter fw = new FileWriter("users.csv", true); //adding new user
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {
                        out.println(utilizator.getUsername() + "," + utilizator.getPassword());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    message = "User created successfully";
                } else if (strings[0].equals("-cleanup-all")) {
                    try {
                        Files.delete(Paths.get("users.csv")); //deleting all csv files
                        Files.delete(Paths.get("posts.csv"));
                        Files.delete(Paths.get("comments.csv"));
                        Postare.number_of_posts = 0; //setting the number of posts and comments to 0
                        Comentariu.number_of_comments = 0;
                        message = "Cleaned up successfully";
                    } catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    if (strings.length < 3) //authentification case exception
                        throw new Exception("You need to be authenticated");

                    int ok = 0; //we start with 0 bc we need to find the user in the list. (check if it already exists)
                    String username = strings[1].split("'")[1]; //format
                    String password = strings[2].split("'")[1];
                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line;
                        boolean first = true;
                        while ((line = br.readLine()) != null) {
                            if (first) {
                                first = false; //similar to same above code snippet
                                continue;
                            }
                            String[] values = line.split(","); //elements are separated by "," on each line
                            if (values[0].equals(username) && values[1].equals(password)) {
                                ok = 1;
                            }
                        }
                    }
                    if (ok == 0)
                        throw new Exception("Login failed");

                    Utilizator utilizator = new Utilizator(username, password); //user object created

                    if (strings[0].equals("-create-post")) {
                        if (strings.length < 4) //no post argument provided
                            throw new Exception("No text provided");
                        String text = strings[3].split("'")[1];
                        if (text.length() > 300) //text length too long
                            throw new Exception("Post text length exceeded");

                        Postare postare = new Postare(text, utilizator); //new postare object created

                        try (FileWriter fw = new FileWriter("posts.csv", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println(postare.getIdPostare() + "," + postare.getPost_text() + ","
                                    + postare.getPost_author().getUsername());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        message = "Post added successfully";
                    } else if (strings[0].equals("-delete-post-by-id")) {
                        if (strings.length < 4) //id post not provided
                            throw new Exception("No identifier was provided");
                        int id = Integer.parseInt(strings[3].split("'")[1]);
                        if (id > Postare.number_of_posts || id < 1)
                            throw new Exception("The identifier was not valid");

                        int allowed = 1; //check if post is user's who wants to delete post
                                        //analog if user exists
                        try (BufferedReader br = new BufferedReader(new FileReader("posts.csv"))) {
                            String line;
                            boolean first = true;
                            while ((line = br.readLine()) != null) {
                                if (first) {
                                    first = false;
                                    continue;
                                }
                                String[] values = line.split(",");
                                int id_current = Integer.parseInt(values[0]);
                                if (id_current == id && !values[2].equals(username)) {
                                    allowed = 0;
                                }
                            }
                        }
                        //the following if statements are exception cases for unprovided parameters
                        if (allowed == 0)
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
                        if (strings.length < 5)
                            throw new Exception("No text provided");
                        String aux = strings[3].split("'")[1];
                        int idPostare = Integer.parseInt(aux);
                        String text = strings[4].split("'")[1];
                        if (text.length() > 300)
                            throw new Exception("Comment text length exceeded");

                        Comentariu comentariu = new Comentariu(idPostare, text, new Utilizator(username, password));


                        //analog postare (creare si stergere comentariu)
                        try (FileWriter fw = new FileWriter("comments.csv", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println(comentariu.getIdComentariu() + "," + comentariu.getTextComentariu() + ","
                                    + comentariu.getComentator().getUsername() + "," + comentariu.getIdPostare());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        message = "Comment added successfully";
                    } else if (strings[0].equals("-delete-comment-by-id")) {
                        if (strings.length < 4)
                            throw new Exception("No identifier was provided");
                        int id = Integer.parseInt(strings[3].split("'")[1]);
                        if (id > Comentariu.number_of_comments || id < 1)
                            throw new Exception("The identifier was not valid");

                        int allowed = 1;
                        try (BufferedReader br = new BufferedReader(new FileReader("comments.csv"))) {
                            String line;
                            boolean first = true;
                            while ((line = br.readLine()) != null) {
                                if (first) {
                                    first = false;
                                    continue;
                                }
                                String[] values = line.split(",");
                                int id_current = Integer.parseInt(values[0]);
                                //checking if user's id matches the comment's user id. No user is allowed to delete other user's comment
                                if (id_current == id && !values[2].equals(username)) {
                                    allowed = 0;
                                }
                            }
                        }
                        if (allowed == 0)
                            throw new Exception("The identifier was not valid");

                        message = "Operation executed successfully";
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
                //finally statement
                System.out.println("{'status':'ok','message':'" + message + "'}");
            } catch (Exception e) {//daca blocul mare de try nu a fost executat cu succes
                System.out.println("{'status':'error','message':'" + e.getMessage() + "'}");
            }
        }
    }
}