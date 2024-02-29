package br.com.taxi.recognizer;

import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;

import java.io.File;

import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;

public class TesteYale {
    public static void main(String[] args) {
        int totalAcertos = 0;
        double percentualAcerto = 0;
        double totalConfianca = 0;

        // Criação do reconhecedor LBPH
        FileStorage fs = new FileStorage("src//main//resources//classificadorLBPHYale.yml", FileStorage.READ);
        LBPHFaceRecognizer reconhecedor = LBPHFaceRecognizer.create();
        reconhecedor.read(fs.get("classificadorLBPHYale"));

        File diretorio = new File("src\\main\\resources\\yalefaces\\teste");
        File[] arquivos = diretorio.listFiles();
        
        for (File imagem : arquivos) {           
            Mat foto = imread(imagem.getAbsolutePath(), IMREAD_GRAYSCALE);
            int classe = Integer.parseInt(imagem.getName().substring(7, 9));          
            resize(foto, foto, new Size(160, 160)); 

            int[] rotulo = new int[1];
            double[] confianca = new double[1];
            reconhecedor.predict(foto, rotulo, confianca);
            int predicao = rotulo[0];
            System.out.println(classe + " foi reconhecido como " + predicao + " - " + confianca[0]);
            if (classe == predicao) {
                totalAcertos++;
                totalConfianca += confianca[0];
            }
        }
        
        percentualAcerto = (totalAcertos / 30.0) * 100;
        totalConfianca = totalConfianca / totalAcertos;
        System.out.println("Percentual de acerto: " + percentualAcerto);
        System.out.println("Total confiança: " + totalConfianca);
    }
}
