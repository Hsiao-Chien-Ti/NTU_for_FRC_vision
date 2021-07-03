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
import java.lang.*;

public class distance {
    public static VideoCapture init() {
        VideoCapture cap = new VideoCapture(0);
        return cap;
    }

    public static Mat proc(Mat mat) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(mat, hsv, Imgproc.COLOR_BGR2HSV);
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Mat res = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
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
        Imgproc.cvtColor(yellow_mask, yellow_mask, Imgproc.COLOR_BGR2GRAY);
        Imgproc.findContours(yellow_mask, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = new Mat();
            contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 8000) {
                Rect rect = Imgproc.boundingRect(contours.get(i));
                Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 255));
                int d=(9940-15*rect.width)/284;
                String s=String.valueOf(d);
                Imgproc.putText(mat, s, new Point(rect.x, rect.y), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0,
                        new Scalar(0, 255, 255));
            }
        }
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
