package ru.chentsov.javacore;

import ru.chentsov.javacore.phones.PhoneBook;

import java.util.*;

/**
 * @author Chentsov Evgenii
 */
public class App 
{

    public static void main( String[] args )
    {
        String[] poem = {"ночь", "улица", "фонарь", "аптека", "бессмысленный", "и", "тусклый", "свет",
                "живи", "еще", "хоть", "четверть", "века", "все", "будет", "так", "исхода", "нет", "умрешь",
                "начнешь", "опять", "сначала", "и", "повторится", "все", "как", "встарь", "ночь", "ледяная",
                "рябь", "канала", "аптека", "улица", "фонарь"};
        showWordsFrequency(poem);
        testPhoneBook();
    }

    public static void showWordsFrequency(String[] textArray)
    {
        List<String> words = Arrays.asList(textArray);
        Set<String> wordsSet = new LinkedHashSet<>(words);
        //printing unique words in a way they were added
        System.out.println(wordsSet);

        System.out.println("Printing frequency for each word:");
        StringBuilder freqBuilder = new StringBuilder();
        for (String word : wordsSet) {
            freqBuilder
                    .append(word)
                    .append(" = ")
                    .append(Collections.frequency(words, word))
                    .append(", ");
            System.out.print(freqBuilder);
            //fastest way to empty builder is to reset it without creating a new StringBuilder instance
            freqBuilder.setLength(0);
        }
        System.out.println();
    }

    public static void testPhoneBook()
    {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "+00484563215");
        phoneBook.add("Иванов", "+00484563216");
        phoneBook.add("Петров", "+00484563217");
        phoneBook.add("Сидоров", "+00484563218");
        phoneBook.add("Иванов", "+00484563219");
        phoneBook.add("Измайлов", "+00484563220");
        phoneBook.add("Симонов", "+00484563221");
        phoneBook.add("Савченко", "+00484563222");
        phoneBook.add("Симонов", "+00484563223");

        for (String lastName : phoneBook.getAllLastNames()) {
            phoneBook.printPhone(lastName);
        }
        phoneBook.printPhone("Харитонов");
    }

}
