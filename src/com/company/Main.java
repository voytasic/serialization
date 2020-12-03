package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void saveMoviesToFile(List<Movies> moviesList, File file) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            // writeObject spodziewa się obiektu więc konwertujemy rozmiar do typu Integer,
            // metoda moviesList.size() zwraca bowiem typ prosty int

            Integer sizeOfList = Integer.valueOf(moviesList.size());
            objectOutputStream.writeObject(sizeOfList);

            for (Movies movies : moviesList) {
                objectOutputStream.writeObject(movies);
            }
            System.out.println("All list has been written correctly to the file: " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Movies> readMoviesListFromFile(File file) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            List<Movies> moviesList = new ArrayList<>();
            // konwertujemy obiekt zapisanego wcześniej rozmiaru listy do int
            int sizeOfList = (Integer) objectInputStream.readObject();
            if (sizeOfList > 0) {
                for (int i = 0; i < sizeOfList; i++) {
                    moviesList.add((Movies) objectInputStream.readObject());
                }
            } else {
                return null;
            }
//            do {
//                moviesList.add((Movies) objectInputStream.readObject());
//            } while (objectInputStream.readObject() == null);
            return moviesList;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) {

        Movies movie1 = new Movies("Terminator");
        Movies movie2 = new Movies("Godfather");
        Movies movie3 = new Movies("Titanic");

        List<Movies> moviesList = new ArrayList<>();

        moviesList.add(movie1);
        moviesList.add(movie2);
        moviesList.add(movie3);

        saveMoviesToFile(moviesList, new File("movies.lst"));
        moviesList.clear();

        moviesList = readMoviesListFromFile(new File("movies.lst"));
        if (moviesList != null) {
            for (Movies movie : moviesList) {
                System.out.println(movie.getTitle());
            }
        }

    }


}
