package cn.itcast.txz.ui;

import javax.swing.*;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//用作显示的主窗体
//需要继承Frame类----继承
public class MainFrame extends Frame implements KeyListener {
    //1.最起码应该设置窗口最基本的信息
    //1.1窗口是否显示
    //构造方法
    public MainFrame(){

        //由于添加图片组件的时候存在顺序，因此要根据需要调整添加图片组件的顺序
        //做笼子（目标位置）
        targetInit();
        //做灰太狼（人物）
        wolfInit();
        //做懒洋洋（箱子）
        sheepInit();
        //做树木(障碍)
        treeInit();
        //制作一个背景，并将背景添加到窗体中（背景图片由于是最大的图片，因此在最后添加）
        backgroundInit();
        //设置整个窗口
        setMainFrameUI();
        //使窗口能够监督用户是不是点了键盘
        this.addKeyListener(this);
    }
    private void victory(){
        if (num == total) {
            //Pop.showPop(jf,jf);
            /*
            JDialog d = new JDialog();
            d.setTitle("成功");
            d.setBounds(400, 300, 200, 200);
            d.setLayout(null);
            JButton b = new JButton();
            b.setText("确定");
            b.setBounds(50, 50, 80, 30);
            d.add(b);
            d.setVisible(true);*/
        }
    }
    //当前操作的组件是JLabel，现在无法判断到底在什么位置
    //设定一个与我们数据存储对应的JLabel存储数组,制作的箱子放到数组中
    JLabel[][] sheeps = new JLabel[12][16];
    //场景数据的模拟，使用二维数组模拟
    //1代表障碍，0代表空地，4代表箱子
    //int[][] datas = new int[12][16];
    int[][] datas = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,8,8,8,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    //代表人物,箱子横向的位置
    int wx,sx1,sx2,sx3;
    //代表人物、箱子纵向的位置
    int wy,sy1,sy2,sy3;
    //代表
    int num = 0;
    int total = 3;
    //推箱子障碍的初始化
    private void treeInit(){
        /*for(int a=0;a<=11;a++){
            for(int b=0;b<=15;b++)
                if ((a==0) || (a==11) || (b==0) || (b==15))
                    datas[a][b] = 1;
                else
                    datas[a][b] = 0;
        }*/
        //1.创建图片
        Icon ic = new ImageIcon("tree.png");
        //遍历二维数组
        for (int i = 0;i <= 11;i++){
            for (int j = 0;j <= 15;j++){
                if (datas[i][j] == 1) {
                    //障碍的初始化
                    //2.创建JLabel
                    JLabel lab_tree = new JLabel(ic);
                    //3.设置大小位置
                    lab_tree.setBounds(12 + 50 * j, 36 + 50 * i , 50, 50);
                    //4.添加到窗体中
                    this.add(lab_tree);
                }
            }
        }
    }

    //设置主窗口界面显示效果
    private void setMainFrameUI(){
        //设置整个窗体的布局模式为自由布局
        this.setLayout(null);
        //设置窗口的位置和尺寸，多长多宽
        this.setBounds(110,30,824,648);
        //设置窗口的标题
        this.setTitle("推箱子 v1.0");
        //设置窗口显示出来
        this.setVisible(true);
    }
    //背景初始化
    private void backgroundInit(){
        //背景是如何实现的？如何做背景
        //想使用图片作为JLabel内部显示的内容
        Icon i = new ImageIcon("bg.png");
        //使用JLabel制作背景
        JLabel lab_bg = new JLabel(i);
        //设置要添加的组件的位置和大小
        lab_bg.setBounds(12,36,800,600);
        //将这个东西添加到窗体里面：窗体.添加（东西）
        this.add(lab_bg);
    }
    //推箱子人物的初始化
    JLabel lab_wolf;  //让人物变量的作用范围变大，使得下面其他方法也可以调用人物变量
    private void wolfInit(){
        //人物最初位置在哪里？
        wx = 4;
        wy = 6;
        //使用一张图片来模拟人物
        //1.创建一张图片，人物图片
        Icon i = new ImageIcon("wolf-zm.png");
        //2.使用JLabel组件模拟人物
        lab_wolf = new JLabel(i);
        //3.设置人物在屏幕上的显示位置
        lab_wolf.setBounds(50 * wx + 12,50 * wy + 36,50,50);
        //4.把这个人物添加到窗体里面
        this.add(lab_wolf);
        //把人物的数据添加到数组中

    }
    JLabel lab_sheep1,lab_sheep2,lab_sheep3;
    //推箱子箱子的初始化
    private void sheepInit(){
        sx1 = 4;sy1 = 6;
        sx2 = 6;sy2 = 6;
        sx3 = 8;sy3 = 6;
        //制作一只羊按照下面的方式进行
        //制作箱子的模型
        //1.创建一张图片，箱子图片
        Icon i = new ImageIcon("sheep-no.png");
        //2.使用JLabel组件模拟箱子
        lab_sheep1 = new JLabel(i);
        //3.设置箱子的位置
        lab_sheep1.setBounds(sy1 * 50 + 12,sx1 * 50 + 36,50,50);
        //4.把箱子添加到窗体中
        this.add(lab_sheep1);
        //制作第二只羊，图片不需要在制作了，只需要修改对应的显示位置
        lab_sheep2 = new JLabel(i);
        lab_sheep2.setBounds(sy2 * 50 + 12,sx2 * 50 + 36,50,50);
        this.add(lab_sheep2);
        //
        //制作第三只羊，图片不需要在制作了，只需要修改对应的显示位置
        lab_sheep3 = new JLabel(i);
        lab_sheep3.setBounds(sy3 * 50 + 12,sx3 * 50 +36,50,50);
        this.add(lab_sheep3);
        //标记箱子（羊）
        datas[sx1][sy1] = 4;
        datas[sx2][sy2] = 4;
        datas[sx3][sy3] = 4;
        //把这个JLabel组件放入到sheeps数组中
        sheeps[sx1][sy1] = lab_sheep1;
        sheeps[sx2][sy2] = lab_sheep2;
        sheeps[sx3][sy3] = lab_sheep3;
    }
    //推箱子目标笼子的初始化
    private void targetInit(){
        //制作笼子方式同人物或者箱子的制作
        //1.创建图片
        Icon i = new ImageIcon("target.png");
        //1.Jlabel
        JLabel lab_target1 = new JLabel(i);
        //2.设位置
        lab_target1.setBounds(700+12,300+36,50,50);
        //3.添加进窗体
        this.add(lab_target1);
        //同理制作其他两个笼子
        JLabel lab_target2 = new JLabel(i);
        lab_target2.setBounds(650+12,300+36,50,50);
        this.add(lab_target2);
        JLabel lab_target3 = new JLabel(i);
        lab_target3.setBounds(600+12,300+36,50,50);
        this.add(lab_target3);
    }
    //按下键盘
    public void keyPressed(KeyEvent e){
        //点击键盘后现在程序有动作执行了
        //如何分辨用户点击的是键盘上的某个按键？通过键码值分辨是哪一个按键，获取键码值的方式：
        int key =e.getKeyCode();  //key代表了你输入的是哪个按键 左上右下分别是37,38,39,40
        if(key == 39){
            //让人物向右移动,一定要知道人物移动的位置有没有障碍
            if(datas[wy][wx+1]==1 || (datas[wy][wx+1]==4 && (datas[wy][wx+2]==1 || datas[wy][wx+2]==4 || datas[wy][wx+2]==12)) || (datas[wy][wx+1]==12 && (datas[wy][wx+2]==1 || datas[wy][wx+2]==4 || datas[wy][wx+2]==12))){
                return;
            }
            //让人物向右移动，首先要知道人物现在在什么位置，要知道移动一次步子多大，有上面两个数据计算出移动以后人物在哪
            int x = (int) lab_wolf.getLocation().getX();
            int y = (int) lab_wolf.getLocation().getY();
            lab_wolf.setLocation(x + 50, y);
            //人物移动后要进行图片更新，首先要有图片，然后设置显示图片为指定图片
            Icon i = new ImageIcon("wolf-yb.png");
            lab_wolf.setIcon(i);
            wx = wx + 1;
            if(datas[wy][wx] == 0 || datas[wy][wx] == 8){
                return;
            }
            //如果遇到了箱子
            //箱子在方向上移动一格，箱子的实际数据4位置与被推位置互换
            //箱子的显示位置也要发生变化，需要获取箱子对象调用改变坐标的方法
            if(datas[wy][wx] == 4 && datas[wy][wx+1] == 0){
                datas[wy][wx] = 0;
                datas[wy][wx+1] = 4;
            }
            if(datas[wy][wx] == 4 && datas[wy][wx+1] == 8){
                datas[wy][wx] = 0;
                datas[wy][wx+1] = 12;
                num = num + 1;
            }
            if(datas[wy][wx] == 12 && datas[wy][wx+1] == 0){
                datas[wy][wx] = 8;
                datas[wy][wx+1] = 4;
                num = num - 1;
            }
            if(datas[wy][wx] == 12 && datas[wy][wx+1] == 8){
                datas[wy][wx] = 8;
                datas[wy][wx+1] = 12;
            }
            sheeps[wy][wx].setLocation((wx+1)*50+12,wy*50+36);
            sheeps[wy][wx+1] = sheeps[wy][wx];
            sheeps[wy][wx] = null;
            victory();
            return;
        }
        if(key == 37){
            //让人物向左移动
            //如果没有障碍移动，如果有障碍，什么事情都不做
            if(datas[wy][wx-1]==1 || (datas[wy][wx-1]==4 && (datas[wy][wx-2]==1 || datas[wy][wx-2]==4 || datas[wy][wx-2]==12)) || (datas[wy][wx-1]==12 && (datas[wy][wx-2]==1 || datas[wy][wx-2]==4 || datas[wy][wx-2]==12))){
                return;
            }
            //让人物向右移动，首先要知道人物现在在什么位置，要知道移动一次步子多大，有上面两个数据计算出移动以后人物在哪
            int x = (int) lab_wolf.getLocation().getX();
            int y = (int) lab_wolf.getLocation().getY();
            lab_wolf.setLocation(x - 50, y);
            Icon i = new ImageIcon("wolf-zb.png");
            lab_wolf.setIcon(i);
            wx = wx - 1;
            if(datas[wy][wx] == 0 || datas[wy][wx] == 8){
                return;
            }
            if(datas[wy][wx] == 4 && datas[wy][wx-1] == 0){
                datas[wy][wx] = 0;
                datas[wy][wx-1] = 4;
            }
            if(datas[wy][wx] == 4 && datas[wy][wx-1] == 8){
                datas[wy][wx] = 0;
                datas[wy][wx-1] = 12;
                num = num + 1;
            }
            if(datas[wy][wx] == 12 && datas[wy][wx-1] == 0){
                datas[wy][wx] = 8;
                datas[wy][wx-1] = 4;
                num = num - 1;
            }
            if(datas[wy][wx] == 12 && datas[wy][wx-1] == 8){
                datas[wy][wx] = 8;
                datas[wy][wx-1] = 12;
            }
            //箱子的显示位置也要发生变化，需要获取箱子对象调用改变坐标的方法
            sheeps[wy][wx].setLocation((wx-1)*50+12,wy*50+36);
            sheeps[wy][wx-1] = sheeps[wy][wx];
            sheeps[wy][wx] = null;
            victory();
            return;
        }
        if(key == 38){
            //让人物向上移动
            if(datas[wy-1][wx]==1 || (datas[wy-1][wx]==4 && (datas[wy-2][wx]==1 || datas[wy-2][wx]==4 || datas[wy-2][wx]==12)) || (datas[wy-1][wx]==12 && (datas[wy-2][wx]==1 || datas[wy-2][wx]==4 || datas[wy-2][wx]==12))){
                return;
            }
            int x = (int) lab_wolf.getLocation().getX();
            int y = (int) lab_wolf.getLocation().getY();
            lab_wolf.setLocation(x, y - 50);
            Icon i = new ImageIcon("wolf-sm.png");
            lab_wolf.setIcon(i);
            wy = wy - 1;
            if(datas[wy][wx] == 0 || datas[wy][wx] == 8){
                return;
            }
            if(datas[wy][wx] == 4 && datas[wy-1][wx] == 0){
                datas[wy][wx] = 0;
                datas[wy-1][wx] = 4;
            }
            if(datas[wy][wx] == 4 && datas[wy-1][wx] == 8){
                datas[wy][wx] = 0;
                datas[wy-1][wx] = 12;
                num = num + 1;
            }
            if(datas[wy][wx] == 12 && datas[wy-1][wx] == 0){
                datas[wy][wx] = 8;
                datas[wy-1][wx] = 4;
                num = num - 1;
            }
            if(datas[wy][wx] == 12 && datas[wy-1][wx] == 8){
                datas[wy][wx] = 8;
                datas[wy-1][wx] = 12;
            }
            sheeps[wy][wx].setLocation(wx*50+12,(wy-1)*50+36);
            sheeps[wy-1][wx] = sheeps[wy][wx];
            sheeps[wy][wx] = null;
            victory();
            return;
        }
        if(key == 40){
            //让人物向下移动
            if(datas[wy+1][wx]==1 || (datas[wy+1][wx]==4 && (datas[wy+2][wx]==1 || datas[wy+2][wx]==4 || datas[wy+2][wx]==12)) || (datas[wy+1][wx]==12 && (datas[wy+2][wx]==1 || datas[wy+2][wx]==4 || datas[wy+2][wx]==12))){
                return;
            }
            int x = (int) lab_wolf.getLocation().getX();
            int y = (int) lab_wolf.getLocation().getY();
            lab_wolf.setLocation(x, y + 50);
            Icon i = new ImageIcon("wolf-zm.png");
            lab_wolf.setIcon(i);
            wy = wy + 1;
            if(datas[wy][wx] == 0 || datas[wy][wx] == 8){
                return;
            }
            if(datas[wy][wx] == 4 && datas[wy+1][wx] == 0){
                datas[wy][wx] = 0;
                datas[wy+1][wx] = 4;
            }
            if(datas[wy][wx] == 4 && datas[wy+1][wx] == 8){
                datas[wy][wx] = 0;
                datas[wy+1][wx] = 12;
                num = num + 1;
            }
            if(datas[wy][wx] == 12 && datas[wy+1][wx] == 0){
                datas[wy][wx] = 8;
                datas[wy+1][wx] = 4;
                num = num - 1;
            }
            if(datas[wy][wx] == 12 && datas[wy+1][wx] == 8){
                datas[wy][wx] = 8;
                datas[wy+1][wx] = 12;
            }
            sheeps[wy][wx].setLocation(wx*50+12,(wy+1)*50+36);
            sheeps[wy+1][wx] = sheeps[wy][wx];
            sheeps[wy][wx] = null;
            victory();
            return;
        }
    }
    //松开键盘
    public void keyReleased(KeyEvent e){
    }
    //键盘类别区别
    public void keyTyped(KeyEvent e){
    }
}