//made by Rafael Bauer Sampaio
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        try {

        //Defines the URL
        URL url = new URL(args[0]);

        //Creates a reader to read the html content
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        //Declaring variables
        String line;
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<Integer> depths = new ArrayList<>();
        int currentDepth = 0;
        int maxDepthIndex;

        while ((line = reader.readLine()) != null) {
            if(isOpeningTag(line)){                      //Checks if the line is an opening tag
                currentDepth++;
            }
            else {
                if(isClosingTag(line)){                  //Checks if the line is a closing tag
                    currentDepth--;
                }
                else {
                    if (!line.trim().isEmpty()){         //Checks if the line isn't empty
                        texts.add(line.trim());
                        depths.add(currentDepth);
                    }
                }
            }
        }
        maxDepthIndex = getFirstDeepestElementsIndex(depths);
        System.out.println(texts.get(maxDepthIndex));

        }
        catch (UnknownHostException e) {
            System.out.println("URL connection error");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Verifies if the given line is an opening tag
    public static boolean isOpeningTag(String line) {
        line = line.trim();
        return (line.startsWith("<") && !line.startsWith("</"));
    }

    //Verifies if the given line is a closing tag
    public static boolean isClosingTag(String line) {
        line = line.trim();
        return (line.startsWith("</"));
    }

    //Returns the first deepest element's index in the array
    public static int getFirstDeepestElementsIndex(ArrayList<Integer> array) {
        int maxIndex = 0;
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i) > array.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
