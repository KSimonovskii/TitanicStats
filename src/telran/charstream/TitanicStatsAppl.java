package telran.charstream;

import telran.charstream.model.Passenger;
import telran.charstream.model.enums.Category;
import telran.charstream.model.enums.Sex;
import telran.charstream.model.utils.Utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TitanicStatsAppl {

    public static void main(String[] args) {

        String FILE_PATH = "train.csv";

        List<Passenger> passengers = new ArrayList<>();
        int countStr = 0;

        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));) {

            String str = bf.readLine();
            boolean isFirstString = true;

            while (str != null) {
                countStr++;
                if (isFirstString){
                    str = bf.readLine();
                    isFirstString = false;
                    continue;
                }

                String[] dataPassenger = str.split(",");

                try {
                    Passenger passenger = new Passenger(dataPassenger);
                    passengers.add(passenger);
                } catch (Exception e){
                    System.out.println("Incorrect data format in string # " + countStr + ": " + e.getMessage());
                }
                str = bf.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File '" + FILE_PATH + "' not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        double totalFares = passengers.stream()
                .map(Passenger::getFare)
                .reduce(Double::sum)
                .orElse(0.);

        System.out.println("Total fares = " + Utils.roundDoubleValue(totalFares, 2));

        Map<Integer, Double> tmpMap = passengers.stream()
                .collect(Collectors.groupingBy(Passenger::getpClass, Collectors.averagingDouble(Passenger::getFare)));

        tmpMap.forEach((pClass, avgFare) -> System.out.println("Average fare for class " + pClass + " = " + Utils.roundDoubleValue(avgFare, 2)));

        Map<Category, List<Passenger>> mapPassengers = passengers.stream()
                .collect(Collectors.groupingBy(Passenger::getCategory));

        int totalSurv = 0;

        for (Category category: mapPassengers.keySet()) {

            List<Passenger> tmpList = mapPassengers.get(category);

            int survived = (int) tmpList.stream()
                    .filter(Passenger::isSurvived)
                    .count();
            totalSurv += survived;

            System.out.println("For category passengers " + category + " quantity of survived = " + survived +
                    ", not survived = " + (tmpList.size() - survived));
        }

        System.out.println("Total quantity of survived = " + totalSurv + ", not survived = " + (passengers.size() - totalSurv));









    }

}
