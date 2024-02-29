package br.com.taxi.recognizer;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

public class Captura {
    public static void main(String arg[]) throws FrameGrabber.Exception, InterruptedException {
        KeyEvent tecla = null;
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();

        CascadeClassifier detectorFace = new CascadeClassifier("src/main/resources/haarcascade_frontalface_alt.xml");

        CanvasFrame cFrame = new CanvasFrame("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        Mat imagemColorida = new Mat();
        int numeroAmostras = 25;
        int amostra = 1;
        System.out.println("Digite seu id: ");
        Scanner cadastro = new Scanner(System.in);
        int idPessoa = cadastro.nextInt();
        while ((frameCapturado = camera.grab()) != null) {
            imagemColorida = converteMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            opencv_imgproc.cvtColor(imagemColorida, imagemCinza, opencv_imgproc.COLOR_BGRA2GRAY);
            RectVector facesDetectadas = new RectVector();
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(150,150), new Size(500,500));
            if (tecla == null) {
                //tecla = cFrame.waitKey(2);
            }
            for (int i = 0; i < facesDetectadas.size(); i++) {
                Rect dadosFace = facesDetectadas.get(i);
                opencv_imgproc.rectangle(imagemColorida, dadosFace, new Scalar(0,0,255, 0));
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160,160));
                if (tecla != null && tecla.getKeyChar() == 'q' && amostra <= numeroAmostras) {
                    opencv_imgcodecs.imwrite("src/main/resources/fotos/pessoa." + idPessoa + "." + amostra + ".jpg", faceCapturada);
                    System.out.println("Foto " + amostra + " capturada\n");
                    amostra++;
                }
                tecla = null;
            }
            if (tecla == null) {
                tecla = cFrame.waitKey(20);
            }
            if (cFrame.isVisible()) {
                cFrame.showImage(frameCapturado);
            }

            if (amostra > numeroAmostras) {
                break;
            }
        }
        cFrame.dispose();
        camera.stop();
    }
}
