#include <iostream>
#include <opencv2/highgui.hpp>
using namespace std;
using namespace cv;
extern "C" {
    void play(char* url);
}
void play(const char* url){
    //打开视频文件
    VideoCapture capture(url);

    //isOpen判断视频是否打开成功
    if(!capture.isOpened())
    {
        cout<<"Movie open Error"<<endl;
        exit(-1);
    }
    //获取视频帧频
    double rate=capture.get(CAP_PROP_FPS);
    cout<<"帧率为:"<<" "<<rate<<endl;
    cout<<"总帧数为:"<<" "<<capture.get(CAP_PROP_FRAME_COUNT)<<endl;//输出帧总数
    Mat frame;
    namedWindow("Movie Player");

    double position=0.0;
    //设置播放到哪一帧，这里设置为第0帧
    capture.set(CAP_PROP_POS_FRAMES,position);
    while(1)
    {
        //读取视频帧
        if(!capture.read(frame))
            break;

        imshow("Movie Player",frame);
        //获取按键值
        char c=waitKey(33);
        if(c==27)
            break;
    }
    capture.release();
    destroyWindow("Movie Player");
    exit(0);
}
int main(){
    string i="./不负韶光.mp4";
    play(i.c_str());
    return 0;
}