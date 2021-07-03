import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;
import java.util.ArrayList;
import java.util.List;
import org.opencv.imgproc.Imgproc;

public class App {
    public static VideoCapture init() {
        VideoCapture cap = new VideoCapture(0);
        return cap;
    }

    public static Mat proc(Mat mat) {
        Mat hsv = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Mat res = new Mat();        
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_BGR2HSV);
        /// red
        Scalar red_lower = new Scalar(138, 131, 104);
        Scalar red_upper = new Scalar(180, 255, 251);
        Mat red_mask = new Mat();
        Core.inRange(hsv, red_lower, red_upper, red_mask);
        Imgproc.cvtColor(red_mask, red_mask, Imgproc.COLOR_GRAY2BGR);
        Imgproc.dilate(red_mask, red_mask, kernel);
        Core.bitwise_and(mat, red_mask, res);
        Imgproc.cvtColor(res, res, Imgproc.COLOR_BGR2GRAY);
        contours.clear();
        Imgproc.findContours(res, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 3000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 255));
                Imgproc.putText(mat, "Red", new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(0, 0, 255));
            }
        }
        /// blue
        Scalar blue_lower = new Scalar(98, 149, 130);
        Scalar blue_upper = new Scalar(149, 255, 191);
        Mat blue_mask = new Mat();
        Core.inRange(hsv, blue_lower, blue_upper, blue_mask);
        Imgproc.cvtColor(blue_mask, blue_mask, Imgproc.COLOR_GRAY2BGR);
        Imgproc.dilate(blue_mask, blue_mask, kernel);
        Core.bitwise_and(mat, blue_mask, res);
        contours = new ArrayList<>();
        hierarchy = new Mat();
        contours.clear();
        Imgproc.cvtColor(res, res, Imgproc.COLOR_BGR2GRAY);
        Imgproc.findContours(res, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 3000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(255, 0, 0));
                Imgproc.putText(mat, "Blue", new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(255, 0, 0));
            }
        }
        /// green
        Scalar green_lower = new Scalar(50, 114, 126);
        Scalar green_upper = new Scalar(85, 200, 175);
        Mat green_mask = new Mat();
        res = new Mat();
        Core.inRange(hsv, green_lower, green_upper, green_mask);
        Imgproc.cvtColor(green_mask, green_mask, Imgproc.COLOR_GRAY2BGR);
        Imgproc.dilate(green_mask, green_mask, kernel);
        Core.bitwise_and(mat, green_mask, res);
        contours = new ArrayList<>();
        hierarchy = new Mat();
        contours.clear();
        Imgproc.cvtColor(res, res, Imgproc.COLOR_BGR2GRAY);
        Imgproc.findContours(res, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 3000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));
                Imgproc.putText(mat, "Green", new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(0, 255, 0));
            }
        }
        /// yellow
        Scalar yellow_lower = new Scalar(0, 112, 159);
        Scalar yellow_upper = new Scalar(76, 245, 255);
        Mat yellow_mask = new Mat();
        res = new Mat();
        Core.inRange(hsv, yellow_lower, yellow_upper, yellow_mask);
        Imgproc.cvtColor(yellow_mask, yellow_mask, Imgproc.COLOR_GRAY2BGR);
        Imgproc.dilate(yellow_mask, yellow_mask, kernel);
        Core.bitwise_and(mat, yellow_mask, res);
        contours = new ArrayList<>();
        hierarchy = new Mat();
        contours.clear();
        Imgproc.cvtColor(res, res, Imgproc.COLOR_BGR2GRAY);
        Imgproc.findContours(res, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 3000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 255));
                Imgproc.putText(mat, "Yellow", new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(0, 255, 255));
            }
        }
        /// orange
        Scalar orange_lower = new Scalar(0, 129, 124);
        Scalar orange_upper = new Scalar(17, 229, 203);
        Mat orange_mask = new Mat();
        res = new Mat();
        Core.inRange(hsv, orange_lower, orange_upper, orange_mask);
        Imgproc.cvtColor(orange_mask, orange_mask, Imgproc.COLOR_GRAY2BGR);
        Imgproc.dilate(orange_mask, orange_mask, kernel);
        Core.bitwise_and(mat, orange_mask, res);
        contours = new ArrayList<>();
        hierarchy = new Mat();
        contours.clear();
        Imgproc.cvtColor(res, res, Imgproc.COLOR_BGR2GRAY);
        Imgproc.findContours(res, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 3000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 200, 255));
                Imgproc.putText(mat, "Orange", new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(0, 200, 255));
            }
        }
        /*
        /// white
        Scalar white_lower = new Scalar(77, 0, 128);
        Scalar white_upper = new Scalar(99, 33, 170);
        Mat white_mask = new Mat();
        res = new Mat();
        Core.inRange(hsv, white_lower, white_upper, white_mask);
        Imgproc.cvtColor(white_mask, white_mask, Imgproc.COLOR_GRAY2BGR);
        Imgproc.dilate(white_mask, white_mask, kernel);
        Core.bitwise_and(mat, white_mask, res);
        contours = new ArrayList<>();
        hierarchy = new Mat();
        contours.clear();
        Imgproc.cvtColor(white_mask, white_mask, Imgproc.COLOR_BGR2GRAY);
        Imgproc.findContours(white_mask, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 3000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 0));
                Imgproc.putText(mat, "White", new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(0, 0, 0));
            }
        }*/

        return mat;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture cap = new VideoCapture(0);
        if (!cap.isOpened()) {
            System.out.print("not open");
        } else {
            Mat img = new Mat();
            while (true) {
                if (!cap.read(img)) {
                    System.out.println("fail to read");
                } else {
                    img = proc(img);
                    HighGui.imshow("test", img);
                    HighGui.waitKey(1);
                }
            }
        }
    }
}
