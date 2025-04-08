import java.io.*;

public class Prelab {
    public static void main(String[] args){
        final String filename = "iris_full_normalized.csv";
        final int Rows = 150;
        final int Columns = 4;
        double[][] Features = new double[Rows][Columns];
        int[] Labels = new int[Rows];
        int[] Predictions = new int[Rows];

        //try to read feature and labels from csv file
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            int row = 0;

            while((line = reader.readLine()) != null && row < Rows){
                String[] tokens = line.trim().split(",");
                for(int col = 0; col < Columns; col++){
                    Features[row][col] = Double.parseDouble(tokens[col]);
                }
                Labels[row] = Integer.parseInt(tokens[4]);
                row++;
            }
        }catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
            return;
        }


        //Example Prediction: If col = 2, petal length > 0.5, is Virginia, else -1
        for(int i = 0; i < Rows; i++){
            double petalLength = Features[i][2];

            //Rule-based prediction
            if(petalLength > 0.5){
                Predictions[i] = 1;
            }else{
                Predictions[i] = -1;
            }
        }

        //Number of correct predictions
        int Correct = 0;
        for(int i = 0; i < Rows; i++){
            if(Predictions[i] == Labels[i]){
                Correct++;
            }
        }
        double percentCorrect = (double) Correct / Rows * 100.0;
        System.out.printf("Correct Predictions: %d/%d = %.2f%%%n", Correct, Rows, percentCorrect);


    }

}
