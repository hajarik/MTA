
public class Subway {


    public static void planTrip(String startLine, String startStation, String endLine, String endStation) {

        if (!(startLine.equals("N") || startLine.equals("L") || startLine.equals("6"))) {
            System.out.println("Invalid start");
            return;
        }

        // array of string of the stops between start and Union
        String[] stopsFromStartToUnion = getStopsBetween(startLine, startStation, "Union Square");

        // array of string of the stops between Union and end station
        String[] stopsFromUnionToEnd = getStopsBetween(endLine, "Union Square", endStation);

        // Print the stops on the start line
        System.out.println("You must travel through the following stops on the " + startLine + " line: ");
        printStops(stopsFromStartToUnion);

        System.out.println("Change at Union Square.");

        // Print the stops on the end line
        System.out.println("Your journey continues through the following stops on the " + endLine + " line: ");
        printStops(stopsFromUnionToEnd);

        // Calculate and print the total number of stops
        int totalStops = stopsFromStartToUnion.length + stopsFromUnionToEnd.length;
        System.out.println(totalStops + " stops in total.");
    }


    // get the stops between start station and end station on a line
    private static String[] getStopsBetween(String line, String startStation, String endStation) {

        String[] stops;

        // get the line stops
        if (line.equals("N")) {
            stops = new String[]{"Times Square", "34th", "28th", "23rd", "Union Square", "8th"};
        } else if (line.equals("L")) {
            stops = new String[]{"8th", "6th", "Union Square", "3rd", "1st"};
        } else {
            // else was better with "6" issue naming array
            stops = new String[]{"Grand Central", "33rd", "28th", "23rd", "Union Square", "Astor Place"};
        }

        // Find the indices of start and end station on a line
        int startIndex = getStopIndex(stops, startStation); // 0
        int endIndex = getStopIndex(stops, endStation); //   4


        // Get the stops between start and end stations based on their indices
        if (startIndex < endIndex) {
            // 0 and 4
            return getSubArray(stops, startIndex + 1, endIndex + 1);
        } else {
            String[] reversedStops = getSubArray(stops, endIndex, startIndex);
            reverseArray(reversedStops);
            return reversedStops;
        }
    }

    // get the index of a stop in the stops array
    private static int getStopIndex(String[] stops, String stopName) {
        for (int i = 0; i < stops.length; i++) {
            if (stops[i].equals(stopName)) {
                return i;
            }
        }
        return -1; // Return -1 if the stop is not found
    }

    // subarray from the original array
    private static String[] getSubArray(String[] array, int startIndex, int endIndex) {
        int subArrayLength = endIndex - startIndex; //
        String[] subArray = new String[subArrayLength];
        for (int i = 0; i < subArrayLength; i++) {
            subArray[i] = array[startIndex + i];
        }

        return subArray;
    }


    // reverse the order of elements in an array
    private static void reverseArray(String[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            String temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    //  print stops
    private static void printStops(String[] stops) {
        for (String stop : stops) {
            System.out.println(stop);
        }
    }
}