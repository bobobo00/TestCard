package test;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @ClassName TestCard
 * @Description TODO
 * @Auther danni
 * @Date 2019/9/10 19:00]
 * @Version 1.0
 **/

public class TestCard {
    private static List<Card> buyCard(List<Card> deck){
        String[] flower={"♥","♠","♦","♣"};
        for (int i = 0; i <flower.length ; i++) {
            String str=flower[i];
            for (int j = 1; j <=13 ; j++) {
                int value=j;
                Card card=new Card(value,str);
                deck.add(card);
            }

        }
        return deck;
    }
    private static void swamp(List<Card> deck,int i,int j){
        Card a=(Card)deck.get(i);
        Card b=(Card)deck.get(j);
        deck.set(i,b);
        deck.set(j,a);

    }
    private static List<Card> shuffle(List<Card> deck){
        Random random=new Random(13);
        for (int i = deck.size()-1; i >0 ; i--) {
            int x=random.nextInt(i);
           swamp(deck,i,x);
        }
        return  deck;
    }

    private static List<List<Card>> send(List<Card> deck,int numperson,int numcard){
        List<List<Card>> hands=new ArrayList<>();
        for (int i = 0; i <numperson ; i++) {
            hands.add(new ArrayList<>());
        }
        for (int i = 0; i <numperson ; i++) {
            for (int j = 0; j <numcard ; j++) {
                Card card=deck.remove(0);
                hands.get(i).add(card);
            }
        }
       return hands;
    }

    private static void print( List<List<Card>> hands,int numperson){
        for (int i = 0; i <numperson ; i++) {
            char name=(char)('A'+i);
            System.out.println(name+"号玩家的牌:"+hands.get(i));
        }
    }
    private static void play(List<List<Card>> hands){
        Card card=new Card(1,"♠");
        for (int i = 0; i <hands.size() ; i++) {
            if(hands.get(i).contains(card)){
                char c=(char)('A'+i);
                System.out.println(c+"号玩家赢了");
                return;
            }
        }
    }
    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>(52);
        Scanner scanner=new Scanner(System.in);
        deck=buyCard(deck);
        System.out.println("新牌");
        System.out.println(deck);
        deck=shuffle(deck);
        System.out.println("洗好的牌");
        System.out.println(deck);
        System.out.println("请输入玩家数");
        int numperson=scanner.nextInt();
        while(numperson>52){
            System.out.println("请重新输入玩家数，共有52张牌，玩家数不得大于52");
            numperson=scanner.nextInt();
        }
        System.out.println("请输入每个玩家需要的牌数");
        int numcard=scanner.nextInt();
        while(numcard*numperson>52){
            System.out.println("请重新属于每个玩家需要的牌数，共有52张牌");
            numcard=scanner.nextInt();
        }
        List<List<Card>> hands=send(deck,numperson,numcard);
        print(hands,numperson);
        System.out.println("剩余牌:"+deck);
        play(hands);
    }
}
