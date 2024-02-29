package br.com.taxi.recognizer;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

public class Reconhecimento {
    public static void main(String args[]) throws FrameGrabber.Exception {
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0); // 0 indica a câmera padrão
        String[] pessoas = {"", "Gabriel", "Neymar Jr"};
        camera.start();
        
        CascadeClassifier detectorFace = new CascadeClassifier("src/main/resources/haarcascade_frontalface_alt.xml");
        
        EigenFaceRecognizer reconhecedor = EigenFaceRecognizer.create();
        reconhecedor.read("src/main/resources/classificadorEigenFaces.yml");
        
        CanvasFrame cFrame = new CanvasFrame("Reconhecimento", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        Mat imagemColorida = new Mat();
        
        while ((frameCapturado = camera.grab()) != null) {
            imagemColorida = converteMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            opencv_imgproc.cvtColor(imagemColorida, imagemCinza, opencv_imgproc.COLOR_BGRA2GRAY);
            RectVector facesDetectadas = new RectVector();
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 2, 0, new Size(100,100), new Size(500,500));
            for (int i = 0; i < facesDetectadas.size(); i++) {
                Rect dadosFace = facesDetectadas.get(i);
                opencv_imgproc.rectangle(imagemColorida, dadosFace, new Scalar(0,255,0,0));
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160,160));
                
                double confiancaMinima = 0.5;

                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);
                reconhecedor.predict(faceCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);
                double confiancaPredicao = confianca.get(0);

                String nome;
                if (predicao == -1 || confiancaPredicao < confiancaMinima) {
                    nome = "Desconhecido";
                } else {
                    nome = pessoas[predicao];
                }
                
                int x = Math.max(dadosFace.tl().x() - 10, 0);
                int y = Math.max(dadosFace.tl().y() - 10, 0);
                opencv_imgproc.putText(imagemColorida, nome, new Point(x, y), opencv_imgproc.FONT_HERSHEY_PLAIN, 1.4, new Scalar(0,255,0,0));
            }
            if (cFrame.isVisible()) {
                cFrame.showImage(frameCapturado);
            }
        }
        cFrame.dispose();
        camera.stop();
    }
}
