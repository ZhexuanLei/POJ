package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

// http://poj.org/problem?id=1009
// 原理参考了 https://leons.im/posts/poj-1009-edge-detection-report/
// 但是有一个特例上述报告中没有提到，就是图片左下角的像素可以作为输出连续段的起点而不违反上述定理

public class POJ1009 {
    public static class Image {
        private final Integer width;
        private Integer maxIndex;
        private final ArrayList<Integer[]> info = new ArrayList<Integer[]>(); // 用于储存连续段信息的ArrayList
        private final Integer[] defaultNeighInd;

        public Image(Integer width) {
            this.width = width;
            defaultNeighInd = new Integer[]{-width - 1, -width, -width + 1, -1, 1, width - 1, width, width + 1};
        }

        public void setMaxIndex(Integer maxInd) {
            this.maxIndex = maxInd;
        }

        public Integer getMaxIndex() {
            return this.maxIndex;
        }

        public void addContiPer(int val, int start) {
            // 此处把连续段长度换成了连续段起始的索引值位置，方便后续算法
            Integer[] conPer = new Integer[2];
            conPer[0] = val;
            conPer[1] = start;
            info.add(conPer);
        }

        public Integer getWidth() {
            return this.width;
        }

        public ArrayList<Integer[]> getInfo() {
            return this.info;
        }

        public int getValue(int ind) {
            // 根据一个索引值返回其对应像素的值
            int marker = 0;
            int val = 0;
            for (int i = 0; i < this.info.size(); i++) {
                marker = this.info.get(i)[1];
                if (marker > ind) {
                    val = this.info.get(i - 1)[0];
                    break;
                }
                if (val == 0) {
                    val = this.info.get(this.info.size() - 1)[0];
                }
            }
            return val;
        }

        public Integer[] getNeighInd(int ind) {
            // 这个函数硬编码了不同特殊位置的像素用于计算输出值的相邻像素
            Integer[] neighInds;
            if (ind == 0) {
                neighInds = new Integer[]{1, width, width + 1};
            } else if (ind == width - 1) {
                neighInds = new Integer[]{-1, width - 1, width};
            } else if (ind == this.maxIndex - 1) {
                neighInds = new Integer[]{-width - 1, -width, -1};
            } else if (ind == this.maxIndex - width) {
                neighInds = new Integer[]{-width, -width + 1, 1};
            } else if (ind < width) {
                neighInds = new Integer[]{-1, 1, width - 1, width, width + 1};
            } else if (ind % width == 0) {
                neighInds = new Integer[]{-width, -width + 1, 1, width, width + 1};
            } else if (ind % width == width - 1) {
                neighInds = new Integer[]{-width, -width - 1, -1, width, width - 1};
            } else if (ind > this.maxIndex - width) {
                neighInds = new Integer[]{-width - 1, -width, -width + 1, -1, 1};
            } else {
                neighInds = this.defaultNeighInd;
            }
            return neighInds;
        }


        public int calcOutPix(int ind) {
            // 根据一个像素的索引值（位置）计算其输出值
            ArrayList<Integer> diffs = new ArrayList<Integer>();
            for (int i : this.getNeighInd(ind)) {
                int neighInd = ind + i;
                if (neighInd >= 0 && neighInd < this.maxIndex) {
                    diffs.add(Math.abs(this.getValue(ind) - this.getValue(neighInd)));
                }
            }
            return Collections.max(diffs);
        }


        public Image calcOutIm() {
            // 返回输出的图像（同样是Image类）
            Image outIm = new Image(this.width);
            HashSet<Integer> indSet = new HashSet<Integer>();
            indSet.add(0);
            indSet.add(this.maxIndex - this.width); // 这就是前面讨论的特殊点：左下角的像素点

            // 得到可能是输出图像中连续段起始位置的像素点索引值
            // 即输入图像中连续段起始点相邻的点
            for (Integer[] integers : this.info) {
                int ind = integers[1];
                indSet.add(ind);
                for (int j : this.defaultNeighInd) {
                    int neighInd = ind + j;
                    if (neighInd >= 0 && neighInd < this.maxIndex) {
                        indSet.add(neighInd);
                    }
                }
            }
            ArrayList<Integer> indNeedCalc = new ArrayList<Integer>(indSet);
            Collections.sort(indNeedCalc);
            outIm.addContiPer(this.calcOutPix(0), 0);
            for (int possStart : indNeedCalc) {
                int outVal = this.calcOutPix(possStart);
                if (possStart != 0 && outVal != this.calcOutPix(possStart - 1)) {
                    outIm.addContiPer(outVal, possStart);
                }
            }
            return outIm;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String next = input.nextLine();
        int ind = 0;
//        ArrayList<Image> imageList = new ArrayList<Image>();
        Image im = new Image(0);

        while (!next.equals("0")) {
            if (next.equals("0 0")) {
            // 当读到0 0时，说明一个图像读取结束，此时直接进行输出
                im.setMaxIndex(ind);
                Image outputImage = im.calcOutIm();
                System.out.println(im.getWidth());
                // 下面的部分将连续段的起始位置转化为其长度（题目要求）进行输出
                int marker = 0;
                ArrayList<Integer[]> infoList = outputImage.getInfo();
                for (int i = 0; i < infoList.size() - 1; i++) {
                    Integer[] contiPer = infoList.get(i);
                    System.out.println(contiPer[0] + " " + (infoList.get(i + 1)[1] - marker));
                    marker = infoList.get(i + 1)[1];
                }
                System.out.println((infoList.get(infoList.size() - 1)[0]) + " " + (im.getMaxIndex() - marker));
                System.out.println("0 0");
            } else if (next.split(" ").length == 1) {
            // 如果读到单个整数，说明读入了一个新的图像
                im = new Image(Integer.valueOf(next));
                ind = 0;
            } else {
            // 读到两个不均为0的整数。说明是图像的连续段信息，进行储存操作
                String[] vars = next.split(" ");
                int value = Integer.parseInt(vars[0]);
                int length = Integer.parseInt(vars[1]);
                im.addContiPer(value, ind);
                ind += length;
            }
            next = input.nextLine();
        }
        System.out.println("0");
    }
}


