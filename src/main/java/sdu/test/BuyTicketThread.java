package sdu.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class BuyTicketThread {

    public static void main(String[] args) {

    }

    class SellTicketor extends Thread{

    }

    class BuyTicketor extends Thread{

    }

    class Tikcetcontainer{
        final Integer MaxTicketNum=20;
        ArrayList<Ticket> ticketList=new ArrayList<>(20);
        Integer ticketNum=20;

        public synchronized void addticket(Ticket ticket) throws InterruptedException{
            if(ticketNum<MaxTicketNum){
                ticketList.add(new Ticket(ticketNum+1));
                log.addticketLog.add("车票"+ticketNum+"成功添加!  "+new Date());
            } else {
                System.out.println("车票数量已满,不可添加车票!");
            }
        }

        public synchronized void sellticket(User user) throws InterruptedException{
            ReentrantLock lock=new ReentrantLock();
            try {
                lock.lock();
                if(ticketNum>0){
                    Ticket ticket=ticketList.get(ticketList.size()-1);
                    ticketList.remove(ticket);
                    log.sellticketLog.add("车票"+ticket.id+"已被"+user.id+"购买");
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class Ticket{

        int id;
        public Ticket(int id){
            this.id=id;
        }
    }

    class User{
        int id;
        public User(int id){
            this.id=id;
        }
    }

    static class log{
        public static ArrayList<String> addticketLog=new ArrayList<>();
        public static ArrayList<String> sellticketLog=new ArrayList<>();
    }

//    public static void main(String[] args) {
//        SellTicket sellTicket=new SellTicket();
//        Thread thread=new Thread(sellTicket);
//        thread.start();
//    }
//
//    static class SellTicket implements Runnable {
//        private final ReentrantLock lock = new ReentrantLock();
//        int ticketNum = 20;
//
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    lock.lock();
//                    Thread.sleep(1000);
//                    if (ticketNum > 0) {
//                        System.out.println("票卖出!" + "剩余" + --ticketNum + "张");
//                    } else {
//                        System.out.println("票已卖完!");
//                        break;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//    }


}
