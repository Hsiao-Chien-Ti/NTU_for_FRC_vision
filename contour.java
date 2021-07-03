import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
public class contour
{
    public static VideoCapture init() {
        VideoCapture cap = new VideoCapture(0);
        return cap;
    }
    public static Mat proc(Mat mat)
    {
        Mat gray=new Mat();
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Mat dilate_img=new Mat();
        Mat erode_img=new Mat();
        Mat contour=new Mat();
        Imgproc.dilate(gray, dilate_img, kernel);
        Imgproc.erode(gray,erode_img,kernel);
        Core.absdiff(dilate_img,erode_img,contour);
        return contour;
    }
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture cap = init();
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
