public class myTimer implements Runnable{

    private int seconds;
    private boolean isRunning;

    public myTimer(){
        this.seconds = 0;
        this.isRunning = true;
    }

    @Override
    public void run() {
        isRunning = true;
        while(isRunning){
            try{
                Thread.sleep(1000);
                seconds++;
            } catch (InterruptedException e){
                e.printStackTrace();
                isRunning = false;
            }
        }
    }

    public int getSeconds(){
        return seconds;
    }

}
