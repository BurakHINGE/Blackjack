import java.util.Scanner;

public class blackjack {
    public static void main(String[] args) throws InterruptedException {

    Scanner input = new Scanner(System.in);

    int[] deck = new int[52];
    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    for (int i = 0; i < deck.length; i++){
        deck[i] = i;
    }

    System.out.println("BlackJack'e Hoş Geldiniz!");
    System.out.println("Başlamak için start, çıkış yapmak için exit yazınız: ");

    String go = input.nextLine().toLowerCase();

    boolean start = false;

    if (go.equals("start")) {
        start = true;
    }
    else if (go.equals("exit")) {
        start = false;
    }
    else {
        return;
    }

    while (start) {
        System.out.println("Kartlar karıştırılıyor...");

        shuffleDeck(deck);

        Thread.sleep(3000);

        int nextIndex = 0;

        int[] player = new int[20];
        int playerTotal = 0;
        int playerCount = 0;

        int[] dealer = new int[20];
        int dealerTotal = 0;
        int dealerCount = 0;

        System.out.println("Kartlar karıştırıldı, dağıtıma başlanıyor.");

        Thread.sleep(2000);

        dealer[dealerCount++] = deck[nextIndex++];
        dealerTotal += cardValue(dealer[0]);
        System.out.println("Kasa kendine kart verdi.");
        
        Thread.sleep(2000);

        player[playerCount++] = deck[nextIndex++];
        playerTotal = cardValue(player[0]);
        System.out.println("Kasa kartınızı verdi: " + cardValue(player[0]));

        Thread.sleep(2000);

        dealer[dealerCount++] = deck[nextIndex++];
        dealerTotal += cardValue(dealer[1]);
        System.out.println("Kasa 2. kartını aldı: " + cardValue(dealer[1]));

        Thread.sleep(2000);

        player[playerCount++] = deck[nextIndex++];
        playerTotal += cardValue(player[1]);
        System.out.println("Kasa 2. kartınızı verdi: " + cardValue(player[1]));

        Thread.sleep(2000);

        System.out.println("Kartlarınızın toplamı: " + playerTotal);

        System.out.println("Kart çekmek ister misiniz, yoksa kalmak mı istersiniz? (H/S): ");
        String decision = input.nextLine().toUpperCase();

        if (decision.equals("H")) {
            while (decision.equals("H")) {
                player[playerCount++] = deck[nextIndex++];
                playerTotal += cardValue(player[playerCount - 1]);
                System.out.println("Yeni kartınız: " + cardValue(player[playerCount - 1]));
                Thread.sleep(2000);
                System.out.println("Kartlarınızın toplamı: " + playerTotal);

                if (playerTotal > 21) {
                    System.out.println("Patladınız! Toplamınız 21'i aştı. Kasa kazandı.");
                    break;
                }

                System.out.println("Kart çekmek ister misiniz, yoksa kalmak mı istersiniz? (H/S): ");
                decision = input.nextLine().toUpperCase();
            }
        }
        else if(decision.equals("S")) {
            System.out.println("Kart çekmeyi bıraktınız. Dealer'ın sırası.");
        }

        if (playerTotal <= 21) {
            System.out.println("Kasa kartlarını açıyor...");
            Thread.sleep(2000);
            System.out.println("Kasanın ilk kartı: " + cardValue(dealer[0]));
            Thread.sleep(1000);
            System.out.println("Kasanın kartlarının toplamı: " + dealerTotal);
            while (dealerTotal < 17) {
                dealer[dealerCount++] = deck[nextIndex++];
                dealerTotal += cardValue(dealer[dealerCount - 1]);
                System.out.println("Kasa yeni kart aldı: " + cardValue(nextIndex - 1));
                Thread.sleep(1000);
                System.out.println("Kasanın kartlarının toplamı: " + dealerTotal);
                Thread.sleep(2000);
            }
        }
        
        if (dealerTotal > 21) {
            System.out.println("Kasa patladı! Kazandınız!");
        } 
        else {
            if (dealerTotal < playerTotal) {
                System.out.println("Tebrikler, kazandınız!");
            }
            else if (dealerTotal > playerTotal) {
                System.out.println("Üzgünüm, kaybettiniz.");
            }
            else if (dealerTotal == playerTotal) {
                System.out.println("Berabere!");
            }
        }

        Thread.sleep(2000);

        System.out.println("Tekrar oynamak için start, çıkış yapmak için exit yazınız: ");
        go = input.nextLine().toLowerCase();

        if (go.equals("exit")) {
            break;
        }

    }

    System.out.println("Çıkış yapılıyor...");

    Thread.sleep(2000);
    
    }

    public static int[] shuffleDeck(int[] shufflingdeck) {

        for (int i = 0; i < shufflingdeck.length; i++) {

        int index = (int)(Math.random() * shufflingdeck.length);
        int temp = shufflingdeck[i];
        shufflingdeck[i] = shufflingdeck[index];
        shufflingdeck[index] = temp;
        }

        return shufflingdeck;
    }

    public static int cardValue(int card) {
        int val = (card % 13) + 1;
        if (val > 10) val = 10;
        return val;
    }
    
}
