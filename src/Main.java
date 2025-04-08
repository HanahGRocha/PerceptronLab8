import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "iris_full_normalized.csv";
        List<double[]> features = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();


        // Read CSV and separate features/labels
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                double[] Rows = new double[4];
                for (int i = 0; i < 4; i++) {
                    Rows[i] = Double.parseDouble(tokens[i]);
                }
                features.add(Rows);
                labels.add(Integer.parseInt(tokens[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Neuron perceptron = new Neuron(4);
        int maxEpochs = 100;
        int totalSamples = features.size();

        for (int epoch = 0; epoch < maxEpochs; epoch++) {
            int correct = 0;

            for (int i = 0; i < totalSamples; i++) {
                double[] x = features.get(i);
                int y = labels.get(i);

                boolean error = perceptron.train(x, y);

                int pred = perceptron.Predict(x);
                if (pred == y) {
                    correct++;
                }

                System.out.printf("Epoch %d Sample %d: Weights = %s, prediction = %d, expected = %d -> %s\n",
                        epoch + 1, i + 1, Arrays.toString(perceptron.getWeights()), pred, y,
                        error ? "ERROR!" : "CORRECT!");
            }

            double percentCorrect = (correct * 100.0) / totalSamples;
            System.out.printf("Epoch %d Complete: %d/%d correct = %.2f%%\n", epoch + 1, correct, totalSamples, percentCorrect);

            if (correct == totalSamples) {
                System.out.println("All samples classified correctly. Stopping training.");
                break;
                }
            }
        }
    }
