import java.lang.reflect.Array;
import java.util.Arrays;

public class Neuron {
    private double[] weights;
    private double alpha = 0.05;

    public Neuron(int inputSize){
        weights = new double[inputSize + 1];
        Arrays.fill(weights,0.0);
    }

    public int Predict(double[] inputs){
        double sum = 0.0;
        for(int i = 0; i < inputs.length; i++){
            sum += inputs[i] * weights[i];
        }
        sum += 1 * weights[weights.length - 1];
        return sum >= 0 ? 1 : -1;
    }

    public boolean train(double[] inputs, int Target){
        int Prediction = Predict(inputs);
        boolean Error = Prediction != Target;

        if(Error){
            for(int i = 0; i < inputs.length; i++){
                weights[i] += alpha * (Target - Prediction) * inputs[i];
            }
            weights[weights.length - 1] += alpha * (Target - Prediction);
        }
        return Error;
    }
    public  double[] getWeights(){
        return weights;
    }
}
