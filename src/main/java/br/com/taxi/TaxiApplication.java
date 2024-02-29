package br.com.taxi;

import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiApplication.class, args);

        // Iniciar o reconhecimento de faces quando o aplicativo for iniciado
        startFaceRecognition();
    }

    public static void startFaceRecognition() {
        // Inicializar o reconhecedor de Eigenfaces
        EigenFaceRecognizer recognizer = EigenFaceRecognizer.create();
        
        // Restante do código de reconhecimento de faces...
    }
}
