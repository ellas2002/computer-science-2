import java.awt.Color;

    public class Parallelmandelbrot extends Thread {
        int id;
        private static double xc;
        private static double yc;
        private static final int n = 1024;
        private static double size;
        public static Picture picture = new Picture(n,n);
        public Parallelmandelbrot(int id){
            this.id = id;
        }
        @Override
        public void run() {
            this.Mandelbotthread(xc, yc, size);
        }

        public void Mandelbotthread(double xc,double yc,double size ){
            int max = 255;   // maximum number of iterations

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double x0 = xc - size/2 + size*i/n;
                    double y0 = yc - size/2 + size*j/n;
                    Complex z0 = new Complex(x0, y0);
                    int gray = max - mand(z0, max);
                    Color color = new Color(gray, gray, gray);
                    picture.set(i, n-1-j, color);
                }
            }
        }
        public static int mand(Complex z0, int max) {
            Complex z = z0;
            for (int t = 0; t < max; t++) {
                if (z.abs() > 2.0) return t;
                z = z.times(z).plus(z0);
            }
            return max;
        }
        public static void main(String[] args) throws InterruptedException {
             xc   = Double.parseDouble(args[0]);
             yc   = Double.parseDouble(args[1]);
             size = Double.parseDouble(args[2]);

             Stopwatch stopwatch = new Stopwatch();

            Thread[] threads = new Parallelmandelbrot[10];

            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Parallelmandelbrot(i);
            }
            for (Thread thread : threads){
                thread.start();
            }
            for(Thread thread: threads){
                thread.join(3000);
            }
            StdOut.println(stopwatch.elapsedTime());
            picture.show();

        }
    }

