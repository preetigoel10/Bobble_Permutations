

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static ArrayList<ArrayList<String>> readDataLineByLine(String file)
    {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        Path pathToFile = Paths.get(file);

        // create an instance of BufferedReader
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter and removing double quotes and single quotes from string
                line = line.replace("\"", "");
                line = line.replace("'", "");
                String[] attributes = line.split(",");
                ArrayList<String> subArray = new ArrayList<>();

                for(int i=0;i<attributes.length;i++){
                    subArray.add(attributes[i]);
                }
                arrayList.add(subArray);


                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return arrayList;
    }
    public static ArrayList<String> permutationsOfDynamicArray(ArrayList<ArrayList<String>> arrayList){
        //number of arrays
        int length = arrayList.size();

        //ArrayList to store the permutations
        ArrayList<String> stringArrayList = new ArrayList<>();

        //to keep track of the next element in each of the n arrays
        int index[] = new int[length];

        while(true) {
            String resultantString = "";
            //required combination of arrays
            for (int i = 0; i < length; i++) {
                resultantString += (arrayList.get(i).get(index[i]));
            }
            //add current combination to the array-list
            stringArrayList.add(resultantString);

            //find the array from the right side that has more elements left than the current array
            int next = length -1;

            while(next >=0 && index[next] + 1 >= arrayList.get(next).size())
                next--;

            //when no more combinations are possible
            if(next< 0)
                return stringArrayList;

            //move to next element in the array
            index[next]++;

            //make all the arrays to the right to point to the first element again
            for(int i=next +1;i<length;i++)
            {
                index[i] = 0;
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> arrayList;
         //To read the data from the CSV file and store it into a dynamic array
       if(args.length > 0) {
             arrayList= readDataLineByLine(args[0]);
        }
        else{
             arrayList= readDataLineByLine("input.csv");
        }
        //To find out all the possible permutations and store them into an ArrayList
        ArrayList<String> permutationsOfArrays = permutationsOfDynamicArray(arrayList);
        //Print the array list with all possible permutations
        System.out.println(permutationsOfArrays);
    }
}
