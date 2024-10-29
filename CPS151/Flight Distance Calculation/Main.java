import java.io.*;
import java.util.*;

public class Main {

	private static int distanceBetween(String city1, String city2, Map<String, Integer> distances) {
	    // Check if direct connection exists in distances map
	    String key1 = city1 + "-" + city2;
	    String key2 = city2 + "-" + city1;
	    if (distances.containsKey(key1)) {
	        return distances.get(key1);
	    } else if (distances.containsKey(key2)) {
	        return distances.get(key2);
	    }
	    return Integer.MAX_VALUE; // If there is no direct connection, return a very large value
	}


    public static void main(String[] args) {

        // System greeting for the user
        System.out.println("Welcome to the Flight Network System!");
        System.out.println("\nOnce you load the network from a file,"
                + "\nI will show you the distances between the"
                + "\ncities in the network!");

        // Prompt for text file data to choose from
        System.out.print("Enter the network file name: ");

        // Create a scanner for user input
        Scanner in = new Scanner(System.in);

        // Get user input for the name of the file as a String
        String fileName = in.next();

        // Create a new file based on file name
        File travelNet = new File(fileName);
        
     // Prompt user for the starting city in which they reside
        System.out.print("Where is your starting point (city name)? ");
        String from = in.next();

        // Declare a PriorityQueue
        PriorityQueue<DistanceTo> pq = new PriorityQueue<DistanceTo>();

        // Add a DistanceTo object to the PriorityQueue using the user input
        // city and 0
        pq.add(new DistanceTo(from, 0));

        // Declare a map using city names and distances
        Map<String, Integer> shortestKnownDistance = new HashMap<>();

        // Read the file and construct the graph
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        try (Scanner fileScanner = new Scanner(travelNet)) {
            while (fileScanner.hasNext()) {
                String city1 = fileScanner.next();
                String city2 = fileScanner.next();
                int distance = fileScanner.nextInt();
                distances.put(city1 + "-" + city2, distance);
                distances.put(city2 + "-" + city1, distance);
                graph.computeIfAbsent(city1, k -> new ArrayList<>()).add(city2);
                graph.computeIfAbsent(city2, k -> new ArrayList<>()).add(city1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return;
        }

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            // Get smallest element from priority queue
            DistanceTo current = pq.poll();
            String currentCity = current.getTarget();
            int currentDistance = current.getDistance();

            // Update shortest known distance if not already present
            if (!shortestKnownDistance.containsKey(currentCity)) {
                shortestKnownDistance.put(currentCity, currentDistance);

                // Explore neighbors of current city
                List<String> neighbors = graph.getOrDefault(currentCity, new ArrayList<>());
                for (String neighbor : neighbors) {
                    int newDistance = currentDistance + distanceBetween(currentCity, neighbor, distances);
                    pq.offer(new DistanceTo(neighbor, newDistance));
                }
            }
        }

        for (String city : shortestKnownDistance.keySet()) {
            if (!city.equals(from)) {
                System.out.println("Distance to " + city + " is " + shortestKnownDistance.get(city));
            }
            else {
            	System.out.println("Distance to " + city + " is " + 0);
            }
        }
    }
}
