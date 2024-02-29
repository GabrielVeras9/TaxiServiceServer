package br.com.taxi.recognizer;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_face.FisherFaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;

import java.io.File;
import java.nio.IntBuffer;

public class TreinamentoYale {
    public static void main(String[] args) {
        File diretorio = new File("src\\yalefaces\\treinamento");
        File[] arquivos = diretorio.listFiles();
        MatVector fotos = new MatVector(arquivos.length);
        Mat rotulos = new Mat(arquivos.length, 1, opencv_core.CV_32SC1);
        IntBuffer rotulosBuffer = rotulos.createBuffer();
        int contador = 0;
      
        for (File imagem : arquivos) {
            Mat foto = opencv_imgcodecs.imread(imagem.getAbsolutePath(), opencv_imgcodecs.IMREAD_GRAYSCALE);
            int classe = Integer.parseInt(imagem.getName().substring(7,9));
            opencv_imgproc.resize(foto, foto, new Size(160, 160));
            fotos.put(contador, foto);
            rotulosBuffer.put(contador, classe);
            contador++;
        }
        
        EigenFaceRecognizer eigenfaces = EigenFaceRecognizer.create();
        FisherFaceRecognizer fisherfaces = FisherFaceRecognizer.create();
        LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(2, 9, 9, 9, 1);

        eigenfaces.train(fotos, rotulos);
        eigenfaces.save("src\\recursos\\classificadorEigenfacesYale.yml");
        
        fisherfaces.train(fotos, rotulos);
        fisherfaces.save("src\\recursos\\classificadorFisherfacesYale.yml");
        
        lbph.train(fotos, rotulos);
        lbph.save("src\\recursos\\classificadorLBPHYale.yml");
    }
}
